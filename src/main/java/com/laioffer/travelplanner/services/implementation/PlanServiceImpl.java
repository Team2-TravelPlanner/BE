package com.laioffer.travelplanner.services.implementation;

import java.io.IOException;
import java.util.*;

import com.laioffer.travelplanner.model.requestModel.RequestSettingsModel;
import com.laioffer.travelplanner.planModel.Origin;
import com.laioffer.travelplanner.utils.DistanceUtil;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import com.google.maps.errors.ApiException;
import com.google.maps.model.PlaceDetails;
import com.laioffer.travelplanner.antcolonyalgorithm.ACO;
import com.laioffer.travelplanner.entities.*;
import com.laioffer.travelplanner.mapsearch.GoogleSearch;
import com.laioffer.travelplanner.model.common.SettingsRequestModel;
import com.laioffer.travelplanner.model.plan.*;
import com.laioffer.travelplanner.repositories.*;
import com.laioffer.travelplanner.services.PlaceSearchService;
import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.entities.DayOfPlan;
import com.laioffer.travelplanner.entities.PlaceOfPlan;
import com.laioffer.travelplanner.entities.Plan;
import com.laioffer.travelplanner.entities.User;
import com.laioffer.travelplanner.enumerate.TypeOfPlan;
import com.laioffer.travelplanner.model.common.AuthModel;
import com.laioffer.travelplanner.model.plan.DayOfPlanSaveModel;
import com.laioffer.travelplanner.model.plan.PlaceOfPlanSaveModel;
import com.laioffer.travelplanner.model.plan.PlanDisplayModel;
import com.laioffer.travelplanner.model.plan.PlanDisplayResponseModel;
import com.laioffer.travelplanner.model.plan.PlanGetModel;
import com.laioffer.travelplanner.model.plan.PlanSaveRequestModel;
import com.laioffer.travelplanner.model.requestModel.RequestRecommendedPlan;
import com.laioffer.travelplanner.planModel.RecommendedPlan;
import com.laioffer.travelplanner.repositories.DayOfPlanRepository;
import com.laioffer.travelplanner.repositories.PlaceOfPlanRepository;
import com.laioffer.travelplanner.repositories.PlanRepository;
import com.laioffer.travelplanner.repositories.UserRepository;
import com.laioffer.travelplanner.services.PlanService;

@Service
public class PlanServiceImpl implements PlanService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private DayOfPlanRepository dayOfPlanRepository;

    @Autowired
    private PlaceOfPlanRepository placeOfPlanRepository;

    @Autowired
    private GoogleSearch googleSearch;

    @Autowired
    private PlaceSearchService placeSearchService;

	@Autowired
	private PlaceRepository placeRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public PlanSaveResponseModel savePlan(PlanSaveRequestModel model) throws Exception {
		PlanSaveResponseModel planSaveResponseModel = new PlanSaveResponseModel();
		User user = userRepository.findByEmail(model.getAuthModel().getUserEmail()).orElse(null);
		if(user == null) {
			planSaveResponseModel.setOperationResponse(OperationResponse.getFailedResponse("No such user."));
			return planSaveResponseModel;
			
		}
		Plan plan = new Plan();
		plan.setStartLatitude(model.getStartLatitude());
		plan.setStartLongitude(model.getStartLongitude());
		plan.setStartDate(model.getStartDate());
		plan.setEndDate(model.getEndDate());
		plan.setUserId(user.getEmail());
		plan.setTypeOfPlan(model.getTypeOfPlan());
		
		List<String> dayOfPlanIds = new ArrayList<>();
		
		for(DayOfPlanSaveModel dModel : model.getDayOfPlanSaveModels()) {
			DayOfPlan dayOfPlan = new DayOfPlan();
			dayOfPlan.setIndex(dModel.getIndex());
			dayOfPlan.setPlanId(plan.getPlanId());
			
			List<String> placeOfPlanIds = new ArrayList<>();
			for(PlaceOfPlanSaveModel pModel : dModel.getPlaceOfPlanSaveModels()) {
				PlaceOfPlan placeOfPlan = new PlaceOfPlan();
				placeOfPlan.setPlaceId(pModel.getPlaceId());
				placeOfPlan.setDayOfPlanId(dayOfPlan.getDayId());
				placeOfPlan.setStartTime(pModel.getStartDate());
				placeOfPlan.setEndTime(pModel.getEndDate());
				
				placeOfPlanIds.add(placeOfPlan.getDayOfPlanId());
				
				placeOfPlanRepository.save(placeOfPlan);
			}
			dayOfPlan.setPlaceOfPlanIds(placeOfPlanIds);
			
			dayOfPlanIds.add(dayOfPlan.getDayId());
			dayOfPlanRepository.save(dayOfPlan);
		}
		plan.setDayOfPlanIds(dayOfPlanIds);
		
		planRepository.save(plan);
		List<String> planIds = user.getPlanIds();
		if(planIds == null || planIds.isEmpty()) {
			planIds = new ArrayList<>();
		}
		planIds.add(plan.getPlanId());
		user.setPlanIds(planIds);
		
		userRepository.save(user);
		planSaveResponseModel.setOperationResponse(OperationResponse.getSuccessResponse());;
		planSaveResponseModel.setPlanId(plan.getPlanId());
		return planSaveResponseModel;
	}


	@Override
	public PlanDisplayModel generateCustomizedPlan(List<String> ids, SettingsRequestModel settings) throws InterruptedException, ApiException, IOException {
		List<Place> placeList = new ArrayList<>();
		for (String id : ids) {
			Place place = placeRepository.findById(id).orElse(null);
			//System.out.println(place.toString());
			placeList.add(place);
		}
		Place origin = new Place();
		origin.setPlaceName("startPoint");
		origin.setLon(settings.getLon());
		origin.setLat(settings.getLat());
		placeList.add(origin);
		ACO aco = new ACO(placeList);
		aco.iterator();

		PlanDisplayModel planDisplayModel = new PlanDisplayModel();
		planDisplayModel.setStartDate(settings.getStartDate());
		planDisplayModel.setEndDate(settings.getEndDate());
		planDisplayModel.setStartLatitude(settings.getLat());
		planDisplayModel.setStartLongitude(settings.getLon());
		planDisplayModel.setTypeOfPlan(TypeOfPlan.valueOf(settings.getTravelStyle()));
		
		Integer placeOfDays = getDaysByTypeOfPlan(TypeOfPlan.valueOf(settings.getTravelStyle()));
		
		List<PlaceDetailModel> placeDetailModels = aco.getPlaceDetailModels();
		
		List<DayOfPlanDisplayModel> dayOfPlanDisplayModels = new ArrayList<>();
		int days = (placeDetailModels.size() + placeOfDays -1) / placeOfDays;
		for(int index = 1; index <= days; index++) {
			DayOfPlanDisplayModel dayOfPlanDisplayModel = new DayOfPlanDisplayModel();
			dayOfPlanDisplayModel.setIndex(index);
			dayOfPlanDisplayModel.setPlaceOfPlanDetailModels(new ArrayList<PlaceOfPlanDetailModel>());
			dayOfPlanDisplayModels.add(dayOfPlanDisplayModel);
		}
		
		for( int i = 1; i < placeDetailModels.size(); i++ ) {
			Place place = placeDetailModels.get(i).getPlace();
			
			PlaceOfPlanDetailModel placeOfPlanDetailModel = new PlaceOfPlanDetailModel();
			placeOfPlanDetailModel.setAddress(place.getAddress());
			placeOfPlanDetailModel.setImageLink(place.getImageLink());
			placeOfPlanDetailModel.setPlaceId(place.getPlaceId());
			placeOfPlanDetailModel.setPlaceName(place.getPlaceName());
			placeOfPlanDetailModel.setWeblink(place.getWebsite());
			placeOfPlanDetailModel.setLat(place.getLat());
			placeOfPlanDetailModel.setLon(place.getLon());
			int index = ( i + 1) / placeOfDays;
			dayOfPlanDisplayModels.get(index).getPlaceOfPlanDetailModels().add(placeOfPlanDetailModel);
		}
		planDisplayModel.setDayOfPlanDisplayModels(dayOfPlanDisplayModels);
		return planDisplayModel;
	}

	@Override
	public PlanDisplayResponseModel getPlan(PlanGetModel model) throws Exception {
		PlanDisplayResponseModel res = new PlanDisplayResponseModel();
		User user = userRepository.findByEmail(model.getAuthModel().getUserEmail()).orElse(null);
		if(user == null) {
			res.setOperationResponse(OperationResponse.getFailedResponse("No such user."));
			return res;
		}
		
		Plan plan= planRepository.findById(model.getPlanId()).orElse(null);
		if(plan == null) {
			res.setOperationResponse(OperationResponse.getFailedResponse("No such plan"));
			return res;
		}
	
		
		List<PlanDisplayModel> planDisplayModels = new ArrayList<>();
		planDisplayModels.add(display(plan));
		res.setPlanDisplayModel(planDisplayModels);
		res.setOperationResponse(OperationResponse.getSuccessResponse());
		return res;
	}

	@Override
	public PlanDisplayResponseModel getAllPlan(AuthModel model) throws Exception {
		PlanDisplayResponseModel res = new PlanDisplayResponseModel();
		User user = userRepository.findByEmail(model.getUserEmail()).orElse(null);
		if(user == null) {
			res.setOperationResponse(OperationResponse.getFailedResponse("No such user."));
		}
		
		List<PlanDisplayModel> planDisplayModels = new ArrayList<>();
		for(String planId : user.getPlanIds()) {
			Plan plan = planRepository.findById(planId).orElse(null);
			if(plan == null) {
				continue;
			}
			planDisplayModels.add(display(plan));
		}
		res.setPlanDisplayModel(planDisplayModels);
		
		return res;
	}

	@Override
    public PlanDisplayModel generateRecommendedPlan(RequestRecommendedPlan model) throws Exception {

        Double startLatitude = model.getSettings().getLat();
        Double startLongitude = model.getSettings().getLon();

        //拿符合category的place
        List<Place> placeListFit = new ArrayList<>();
        Set<String> placeIds = new HashSet<>();
        for (String categoryStr : model.getCategories()) {
            Category category = categoryRepository.findByCategoryName(categoryStr).orElse(null);

            //....
            for (String placeId : category.getPlaceIds()) {
                //起始位置坐标
                //get place by placeId ???
                //Optional<Place> place = placeRepository.findByPlaceId(placeId);
                Place place = placeRepository.findByPlaceId(placeId).orElse(null);

                Double distance = DistanceUtil.getDistance(startLatitude, startLongitude, place.getLon(), place.getLat());
                if (distance < 10.0  &&  placeIds.add(placeId)) {
                    placeListFit.add(place);
                }
            }
        }
        //如果符合category的景点少于要浏览的景点总数

        //get start and end date

        Date startDate = model.getSettings().getStartDate();
        Date endDate = model.getSettings().getEndDate();
        long diff = Math.abs(endDate.getTime() - startDate.getTime());
        long milsecOfday = 86400000L;
        int diffDays = (int) ((diff + milsecOfday - 1L) / (milsecOfday));
        
        
        Integer placeOfDays = getDaysByTypeOfPlan(TypeOfPlan.valueOf(model.getSettings().getTravelStyle()));
        
        Integer numberOfPlace =  placeOfDays * diffDays;

        int index = 0;
        if (placeListFit.size() < numberOfPlace) {
        	List<Place> pool = new ArrayList<>();
        	for(Place place :placeRepository.findAll()) {
        		pool.add(place);
        	}
            while (index < pool.size() && placeListFit.size() < numberOfPlace) {
                Place place = pool.get(index);
                Double distance = DistanceUtil.getDistance(startLatitude, startLongitude, place.getLat(),place.getLon() );
                //距离小于10mile & 去重
                if (distance < 10.0 && placeIds.add(place.getPlaceId())) {
                    placeListFit.add(pool.get(index));          
                }
                index++;
            }
        }
        //按popularity降序排列
        Collections.sort(placeListFit, (p1, p2) -> p1.getPopularity() - p2.getPopularity() < 0 ? 1 : -1);

        //...
        //cut days

		Place origin = new Place();
		origin.setPlaceName("startPoint");
		origin.setLon(startLongitude);
		origin.setLat(startLatitude);
		placeListFit.add(origin);
        ACO aco = new ACO(placeListFit);
        aco.iterator();

		PlanDisplayModel planDisplayModel = new PlanDisplayModel();
		planDisplayModel.setStartDate(model.getSettings().getStartDate());
		planDisplayModel.setEndDate(model.getSettings().getEndDate());
		planDisplayModel.setStartLatitude(model.getSettings().getLat());
		planDisplayModel.setStartLongitude(model.getSettings().getLon());
		planDisplayModel.setTypeOfPlan(TypeOfPlan.valueOf(model.getSettings().getTravelStyle()));
		
		
		List<PlaceDetailModel> placeDetailModels = aco.getPlaceDetailModels();
		
		List<DayOfPlanDisplayModel> dayOfPlanDisplayModels = new ArrayList<>();
		int days = (placeDetailModels.size() + placeOfDays -1) / placeOfDays;
		for(int i = 1; i <= days; i++) {
			DayOfPlanDisplayModel dayOfPlanDisplayModel = new DayOfPlanDisplayModel();
			dayOfPlanDisplayModel.setIndex(i);
			dayOfPlanDisplayModel.setPlaceOfPlanDetailModels(new ArrayList<PlaceOfPlanDetailModel>());
			dayOfPlanDisplayModels.add(dayOfPlanDisplayModel);
		}
		
		for( int i = 1; i < placeDetailModels.size(); i++ ) {
			Place place = placeDetailModels.get(i).getPlace();
			System.out.println(place);
			
			PlaceOfPlanDetailModel placeOfPlanDetailModel = new PlaceOfPlanDetailModel();
			placeOfPlanDetailModel.setAddress(place.getAddress());
			placeOfPlanDetailModel.setImageLink(place.getImageLink());
			placeOfPlanDetailModel.setPlaceId(place.getPlaceId());
			placeOfPlanDetailModel.setPlaceName(place.getPlaceName());
			placeOfPlanDetailModel.setWeblink(place.getWebsite());
			placeOfPlanDetailModel.setLat(place.getLat());
			placeOfPlanDetailModel.setLon(place.getLon());
			int j = ( i + 1) / placeOfDays;
			dayOfPlanDisplayModels.get(j).getPlaceOfPlanDetailModels().add(placeOfPlanDetailModel);
		}
		planDisplayModel.setDayOfPlanDisplayModels(dayOfPlanDisplayModels);
		return planDisplayModel;
    }
	

	// package function
	//private 
	private PlanDisplayModel display(Plan plan) {
		PlanDisplayModel model = new PlanDisplayModel();
		
		
		model.setStartDate(plan.getStartDate());
		model.setEndDate(plan.getEndDate());
		model.setStartLatitude(plan.getStartLatitude());
		model.setStartLongitude(plan.getStartLongitude());
		
		List<DayOfPlanDisplayModel> dayOfPlanDisplayModels = new ArrayList<>();
		for(String dayodPlanId : plan.getDayOfPlanIds()) {
			DayOfPlan dayOfPlan  = dayOfPlanRepository.findByDayId(dayodPlanId).orElse(null);
			DayOfPlanDisplayModel dayOfPlanDisplayModel = new DayOfPlanDisplayModel();
			dayOfPlanDisplayModel.setIndex(dayOfPlan.getIndex());
			
			//....
			if(dayOfPlan == null) {
				continue;
			}
			List<PlaceOfPlanDetailModel> placeOfPlanDetailModels = new ArrayList<>();
			for(String placeOfPlanId : dayOfPlan.getPlaceOfPlanIds()) {
				PlaceOfPlan placeOfPlan = placeOfPlanRepository.findByPlaceOfPlanId(placeOfPlanId).orElse(null);
				PlaceOfPlanDetailModel placeOfPlanDetailModel = new PlaceOfPlanDetailModel(); 
				
				Place place = placeRepository.findById(placeOfPlan.getPlaceId()).orElse(null);
				placeOfPlanDetailModel.setAddress(place.getAddress());
				placeOfPlanDetailModel.setImageLink(place.getImageLink());
				placeOfPlanDetailModel.setPlaceId(place.getPlaceId());
				placeOfPlanDetailModel.setPlaceName(place.getPlaceName());
				placeOfPlanDetailModel.setWeblink(place.getWebsite());
				placeOfPlanDetailModel.setLat(place.getLat());
				placeOfPlanDetailModel.setLon(place.getLon());
				placeOfPlanDetailModel.setCategories(place.getCategories());
				placeOfPlanDetailModels.add(placeOfPlanDetailModel);
			}
			
			dayOfPlanDisplayModels.add(dayOfPlanDisplayModel);
		}
		
		model.setDayOfPlanDisplayModels(dayOfPlanDisplayModels);
		
		return model;
	}

	
	
	private Integer getDaysByTypeOfPlan(TypeOfPlan typeOfPlan) {
		Integer ans = 0;
		
		switch(typeOfPlan) {
			case Loose:
				ans =  4;
				break;
				
			case Moderate:
				ans = 6;
				break;
			case Compact:
				ans = 8;
				break;
			default:
				ans = 8;
				break;
		}
		return ans;
	}
}
