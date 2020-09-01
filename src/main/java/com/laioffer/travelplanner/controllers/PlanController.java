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

import com.laioffer.travelplanner.model.common.AuthModel;
import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.common.Result;
import com.laioffer.travelplanner.model.plan.PlanDisplayResponseModel;
import com.laioffer.travelplanner.model.plan.PlanGetModel;
import com.laioffer.travelplanner.model.plan.PlanSaveRequestModel;
import com.laioffer.travelplanner.services.PlanService;
import com.laioffer.travelplanner.services.UserService;

import java.io.IOException;

@RestController
@RequestMapping("plan")
public class PlanController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PlanController.class);

	@Autowired
	private PlanService planService;

	@Autowired
	private UserService userService;

	/**
	 * Descriptive: save user basic plan to database
	 * 
	 * @author Rocky
	 * @throws Exception
	 * @since 2020-08-25
	 * 
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public ResponseEntity<OperationResponse> savePlan(@RequestBody PlanSaveRequestModel planSaveRequestModel)
			throws Exception {
		OperationResponse res = new OperationResponse();
		res = userService.auth(planSaveRequestModel.getAuthModel());
		if (res.getResult().equals(Result.UNSUCCESSFUL)) {
			return new ResponseEntity<>(res, HttpStatus.OK);
		}

		try {
			res = planService.savePlan(planSaveRequestModel);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			return new ResponseEntity<>(OperationResponse.getFailedResponse(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/customized")
	public ResponseEntity<?> generateCustomizedPlan(@RequestBody CustomizedPlanRequestModel customizedPlan) {
		SettingsRequestModel settings = customizedPlan.getSettings();
		CustomizedPlanModel res = null;
		try {
			res = planService.generateCustomizedPlan(customizedPlan.getPlaceIds(), customizedPlan.getCategories(), customizedPlan.getSettings());
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			return new ResponseEntity<>(OperationResponse.getFailedResponse(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	
	@RequestMapping(value = "getPlan", method = RequestMethod.POST)
	public ResponseEntity<PlanDisplayResponseModel> getPlan(@RequestBody PlanGetModel planGetModel) throws Exception {
		PlanDisplayResponseModel ans = new PlanDisplayResponseModel();

		OperationResponse res = new OperationResponse();
		res = userService.auth(planGetModel.getAuthModel());
		if (res.getResult().equals(Result.UNSUCCESSFUL)) {
			ans.setOperationResponse(res);
			return new ResponseEntity<>(ans, HttpStatus.OK);
		}

		try {
			ans = planService.getPlan(planGetModel);
			return new ResponseEntity<>(ans, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			ans.setOperationResponse(OperationResponse.getFailedResponse(e.getMessage()));
			return new ResponseEntity<>(ans, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "getAllPlan", method = RequestMethod.POST)
	public ResponseEntity<PlanDisplayResponseModel> getAllPlan(@RequestBody AuthModel authModel) throws Exception {
		PlanDisplayResponseModel ans = new PlanDisplayResponseModel();

		OperationResponse res = new OperationResponse();
		res = userService.auth(authModel);
		if (res.getResult().equals(Result.UNSUCCESSFUL)) {
			ans.setOperationResponse(res);
			return new ResponseEntity<>(ans, HttpStatus.OK);
		}

		try {
			ans = planService.getAllPlan(authModel);
			return new ResponseEntity<>(ans, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			ans.setOperationResponse(OperationResponse.getFailedResponse(e.getMessage()));
			return new ResponseEntity<>(ans, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public ResponseEntity<String> hello() {
		return new ResponseEntity<>("hello", HttpStatus.OK);
	}
}
