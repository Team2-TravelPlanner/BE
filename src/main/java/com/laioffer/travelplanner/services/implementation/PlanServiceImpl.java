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

import com.google.maps.errors.ApiException;
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
	public OperationResponse savePlan(PlanSaveRequestModel model) throws Exception {
		User user = userRepository.findByEmail(model.getAuthModel().getUserEmail()).orElse(null);
		if(user == null) {
			return OperationResponse.getFailedResponse("No such user.");
			
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
		return OperationResponse.getSuccessResponse();
	}






    @Override
    public CustomizedPlanModel generateCustomizedPlan(List<String> names, List<String> categories, SettingsRequestModel settings) throws InterruptedException, ApiException, IOException {
        List<Place> placeList = new ArrayList<>();
        for (String name : names) {
//            Place place;
//            placeList.add(placeRepository.findByPlaceName(name).orElse
//                    (place = placeSearchService.searchPlaceIfNotExist(googleSearch.getInfo(name))));
//            placeRepository.save(place);
            Place place = placeRepository.findByPlaceName(name).orElse(null);
            if (place == null) {
                place = placeSearchService.searchPlaceIfNotExist(googleSearch.getInfo(name));
                placeRepository.save(place);
            }
            placeList.add(place);
        }
        Place origin = new Place();
        origin.setPlaceName("startPoint");
        origin.setLon(settings.getLon());
        origin.setLat(settings.getLat());
        placeList.add(origin);
        ACO aco = new ACO(placeList);
        aco.iterator();

        CustomizedPlanModel customizedPlanModel = new CustomizedPlanModel();
        OriginPlanModel originPlanModel = new OriginPlanModel();
        originPlanModel.setLat(settings.getLat());
        originPlanModel.setLon(settings.getLon());
        customizedPlanModel.setStartDate(settings.getStartDate());
        customizedPlanModel.setEndDate(settings.getEndDate());
        customizedPlanModel.setPlaceDetailModels(aco.getPlaceDetailModels());
        customizedPlanModel.setOriginPlanModel(originPlanModel);
        return customizedPlanModel;
    }


    @Override
    public PlanDisplayModel generateRecommendedPlan(RequestRecommendedPlan model) throws Exception {
        Double startLatitude = model.getSettings().getLat();
        Double startLongitude = model.getSettings().getLon();
        int NumberOfPlace = 0;
        if (TypeOfPlan.valueOf(model.getSettings().getTravelStyle()).equals(TypeOfPlan.Loose)) {
            NumberOfPlace = 2;
        } else if (TypeOfPlan.valueOf(model.getSettings().getTravelStyle()).equals(TypeOfPlan.Moderate)) {
            NumberOfPlace = 4;
        } else {
            NumberOfPlace = 6;
        }

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
        long diffDays = diff / (24 * 60 * 60 * 1000);
        NumberOfPlace *= diffDays + 1;

        int index = 0;
        if (placeListFit.size() < NumberOfPlace) {
            List<Place> pool = (List<Place>) placeRepository.findAll();
            while (placeListFit.size() < NumberOfPlace) {
                Place place = pool.get(index);
                Double distance = DistanceUtil.getDistance(startLatitude, startLongitude, place.getLon(), place.getLat());
                //距离小于10mile & 去重
                if (distance < 10.0 && placeIds.add(place.getPlaceId())) {
                    placeListFit.add(pool.get(index));
                    index++;
                }
            }
        }
//
        //按popularity降序排列
        Collections.sort(placeListFit, (p1, p2) -> p1.getPopularity() - p2.getPopularity() < 0 ? 1 : -1);

        //...
        //cut days
        
        List<Place> placeList = new ArrayList<>();
        int count = 0;
        while (count < NumberOfPlace) {
            placeList.add(placeListFit.get(count));
            count++;
        }
        ACO aco = new ACO(placeList);
        aco.iterator();

        PlanDisplayModel recommendedPlan = new RecommendedPlan();
        Origin origin = new Origin();
        origin.setLat(model.getSettings().getLat());
        origin.setLon(model.getSettings().getLon());
        recommendedPlan.setStartDate(model.getSettings().getStartDate());
        recommendedPlan.setEndDate(model.getSettings().getEndDate());
        recommendedPlan.setPlaceDetails(aco.getPlaceDetailModels());
        recommendedPlan.setOrigin(origin);
        return PlanDisplayModel;
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
				
				Place place = placeRepository.findByPlaceId(placeOfPlan.getPlaceId()).orElse(null);
				placeOfPlanDetailModel.setAddress(place.getAddress());
				placeOfPlanDetailModel.setImageLink(place.getImageLink());
				placeOfPlanDetailModel.setPlaceId(place.getPlaceId());
				placeOfPlanDetailModel.setPlaceName(place.getPlaceName());
				placeOfPlanDetailModel.setWeblink(place.getWebsite());
				placeOfPlanDetailModels.add(placeOfPlanDetailModel);
			}
			
			dayOfPlanDisplayModels.add(dayOfPlanDisplayModel);
		}
		
		model.setDayOfPlanDisplayModels(dayOfPlanDisplayModels);
		
		return model;
	}

	
}
