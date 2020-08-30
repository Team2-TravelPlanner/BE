package com.laioffer.travelplanner.services.implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.laioffer.travelplanner.model.requestModel.RequestSettingsModel;
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
public class PlanServiceImpl implements PlanService{

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
	public PlanDisplayResponseModel getPlan(PlanGetModel model) throws Exception {
		PlanDisplayResponseModel res = new PlanDisplayResponseModel();
		User user = userRepository.findByEmail(model.getAuthModel().getUserEmail()).orElse(null);
		if(user == null) {
			res.setOperationResponse(OperationResponse.getFailedResponse("No such user."));
			return res;
			
		}
		
		//planId
		
		// user
		
		return null;
	}

	@Override
	public PlanDisplayResponseModel getAllPlan(AuthModel model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanDisplayResponseModel getAllPlan(AuthModel model) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}




	// package function
	//private

	@Override
	public PlanDisplayModel generateRecommendedPlan(List<String> categories, RequestSettingsModel settings) {


		int NumberOfPlace = 0;
		if (findbyTypeofPlan(typeOfPlan) == "Loose ArrangeMent") {
			NumberOfPlace = 2;
		} else if (findbyTypeofPlan(typeOfPlan) == "Moderate ArrangeMent") {
			NumberOfPlace = 4;
		} else {
			NumberOfPlace = 6;
		}

		//拿符合category的place
		List<Place> placeListFit = new ArrayList<>();
		for (String category : categories) {
			Place place = CategoryRepository.findByPlaceCategory(category).orElse(null);
			placeListFit.add(place);
		}
		//如果符合category的景点少于今天要浏览的景点总数
		if (placeListFit.size() < NumberOfPlace) {
			Place place = (PlaceRepository.findAll());
			placeListFit.add(place);
		}
		//按popularity降序排列
		Collections.sort(placeListFit, new Comparator<Place>() {
			@Override
			public float compare(Place p1, Place p2) {
				return p2.getPopularity() - p1.getPopularity();
			}
		});


		List<Place> placeList = new ArrayList<>();
		int count = 0;
		while (count < NumberOfPlace) {
			placeList.add(placeListFit.get(count));
			count++;
		}
		ACO aco = new ACO(placeList);
		aco.iterator();

		RecommendedPlan recommendedPlan = new RecommendedPlan();
		Origin origin = new Origin();
		origin.setLat(settings.getLat());
		origin.setLon(settings.getLon());
		recommendedPlan.setStartDate(settings.getStartDate());
		recommendedPlan.setEndDate(settings.getEndDate());
		recommendedPlan.setPlaceDetails(aco.getPlaceDetails());
		recommendedPlan.setOrigin(origin);
		return recommendedPlan;
	}



}
