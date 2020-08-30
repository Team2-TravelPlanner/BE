package com.laioffer.travelplanner.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laioffer.travelplanner.entities.DayOfPlan;
import com.laioffer.travelplanner.entities.PlaceOfPlan;
import com.laioffer.travelplanner.entities.Plan;
import com.laioffer.travelplanner.entities.User;
import com.laioffer.travelplanner.enumerate.TypeOfPlan;
import com.laioffer.travelplanner.model.common.AuthModel;
import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.plan.DayOfPlanSaveModel;
import com.laioffer.travelplanner.model.plan.PlaceOfPlanSaveModel;
import com.laioffer.travelplanner.model.plan.PlanDisplayModel;
import com.laioffer.travelplanner.model.plan.PlanDisplayResponseModel;
import com.laioffer.travelplanner.model.plan.PlanGetModel;
import com.laioffer.travelplanner.model.plan.PlanSaveRequestModel;
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
		
		
		
		return res;
	}

	@Override
	public PlanDisplayResponseModel getAllPlan(AuthModel model) throws Exception {
		PlanDisplayResponseModel res = new PlanDisplayResponseModel();
		User user = userRepository.findByEmail(model.getUserEmail()).orElse(null);
		if(user == null) {
			res.setOperationResponse(OperationResponse.getFailedResponse("No such user."));
		}
		

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
		
		List<DayOfPlanSaveModel> dayOfPlanSaveModels = new ArrayList<>();
		for(String dayodPlanId : plan.getDayOfPlanIds()) {
			DayOfPlan dayOfPlan  = dayOfPlanRepository.findByDayId(dayodPlanId).orElse(null);
			DayOfPlanSaveModel dayOfPlanSaveModel = new DayOfPlanSaveModel();
			dayOfPlanSaveModel.setIndex(dayOfPlan.getIndex());
			
			//....
			if(dayOfPlan == null) {
				continue;
			}
			List<PlaceOfPlanSaveModel> placeOfPlanSaveModels = new ArrayList<>();
			for(String placeOfPlanId : dayOfPlan.getPlaceOfPlanIds()) {
				PlaceOfPlan placeOfPlan = placeOfPlanRepository.findByPlaceOfPlanId(placeOfPlanId).orElse(null);
				PlaceOfPlanSaveModel placeOfPlanSaveModel = new PlaceOfPlanSaveModel(); 
				placeOfPlanSaveModel.setPlaceId(placeOfPlan.getPlaceId());
				placeOfPlanSaveModel.setStartDate(placeOfPlan.getStartTime());
				placeOfPlanSaveModel.setEndDate(placeOfPlan.getEndTime());
				placeOfPlanSaveModels.add(placeOfPlanSaveModel);
				//placeOfPlanSaveModel.add(e);
			}
			
			dayOfPlanSaveModels.add(dayOfPlanSaveModel);
		}
		
		model.setDayOfPlanSaveModels(dayOfPlanSaveModels);
		
		return model;
	}
	
}
