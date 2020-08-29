package com.laioffer.travelplanner.services.implementation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.maps.errors.ApiException;
import com.laioffer.travelplanner.antcolonyalgorithm.ACO;
import com.laioffer.travelplanner.entities.*;
import com.laioffer.travelplanner.mapsearch.GoogleSearch;
import com.laioffer.travelplanner.model.common.SettingsRequestModel;
import com.laioffer.travelplanner.model.plan.*;
import com.laioffer.travelplanner.repositories.*;

import com.laioffer.travelplanner.services.PlaceSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.laioffer.travelplanner.model.common.OperationResponse;
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
		User user = userRepository.findById(model.getAuthModel().getUserId()).orElse(null);
		if(user == null) {
			return OperationResponse.getFailedResponse("No such user.");
			
		}
		Plan plan = new Plan();
		plan.setStartLatitude(model.getStartLatitude());
		plan.setStartLongitude(model.getStartLongitude());
		plan.setStartDate(model.getStartDate());
		plan.setEndDate(model.getEndDate());
		plan.setUserId(user.getUserId());
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

}
