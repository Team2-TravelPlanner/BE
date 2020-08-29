package com.laioffer.travelplanner.controllers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.maps.errors.ApiException;
import com.laioffer.travelplanner.model.common.CustomizedPlanRequestModel;
import com.laioffer.travelplanner.model.common.SettingsRequestModel;
import com.laioffer.travelplanner.model.plan.CustomizedPlanModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.laioffer.travelplanner.jwtUtils.JwtTokenProvider;
import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.plan.PlanSaveRequestModel;
import com.laioffer.travelplanner.services.PlanService;

import java.io.IOException;

@RestController
@RequestMapping("plan")
public class PlanController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlanController.class);
	
	@Autowired
	private PlanService planService;
	
	
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
	/**
	 * Descriptive: save user basic plan to database
	 * 
	 * @author Rocky
	 * @since 2020-08-25
	 * 
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ResponseEntity<OperationResponse> savePlan(@RequestBody PlanSaveRequestModel planSaveRequestModel) {

		
		if(!jwtTokenProvider.authenToken(planSaveRequestModel.getAuthModel().getToken())){
			return new ResponseEntity<>(OperationResponse.getFailedResponse("No such user Or token is wrong"), HttpStatus.OK);
		}
		
		OperationResponse res = new OperationResponse();
		
		try {
			res = planService.savePlan(planSaveRequestModel);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			return new ResponseEntity<>(OperationResponse.getFailedResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/customized")
	public ResponseEntity<?> generateCustomizedPlan(@RequestBody CustomizedPlanRequestModel customizedPlan) throws InterruptedException, ApiException, IOException {
//        JSONArray places = jsonObject.getJSONArray("place");
//        List<Place> placeList = JSONObject.parseArray(places.toJSONString(), Place.class);
//        ACO aco = new ACO(placeList);
//        aco.iterator();
//        System.out.println(aco.getOrder());
//
//        Integer startDate = jsonObject.getJSONObject("settings").getInteger("startDate");
//        Integer endDate = jsonObject.getJSONObject("settings").getInteger("endDate");
//
//        Integer duration = (endDate - startDate) / (60 * 60 * 24);
//        System.out.println(duration);
//
//        JSONArray res = JSONArray.parseArray(JSON.toJSONString(aco.getOrder(), SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.IgnoreErrorGetter));
//
//
//        CustomizedPlan customizedPlan = new CustomizedPlan();
//        Origin origin = new Origin();
//        customizedPlan.setStartDate(startDate);
//        customizedPlan.setEndDate(endDate);
//        customizedPlan.setPlaceDetails(aco.getPlaceDetails());
//        customizedPlan.setOrigin(origin);
//
//        String response = JSON.toJSONString(customizedPlan, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.IgnoreErrorGetter);
		SettingsRequestModel settings = customizedPlan.getSettings();

		CustomizedPlanModel res = planService.generateCustomizedPlan(customizedPlan.getPlaces(), customizedPlan.getCategories(), customizedPlan.getSettings());
		String response = JSON.toJSONString(res, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.IgnoreErrorGetter);

		return new ResponseEntity<>(response,HttpStatus.OK);
	}


}
