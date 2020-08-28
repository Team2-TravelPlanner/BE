package com.laioffer.travelplanner.controllers;

import com.laioffer.travelplanner.entities.Place;
import com.laioffer.travelplanner.mapsearch.GoogleSearch;
import com.laioffer.travelplanner.requestModel.RequestSettingsModel;
import com.laioffer.travelplanner.services.PlanService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.laioffer.travelplanner.jwtUtils.JwtTokenProvider;
import com.laioffer.travelplanner.model.common.AuthModel;
import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.plan.PlanDisplayResponseModel;
import com.laioffer.travelplanner.model.plan.PlanGetModel;
import com.laioffer.travelplanner.model.plan.PlanSaveRequestModel;

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

		if (!jwtTokenProvider.authenToken(planSaveRequestModel.getAuthModel().getToken())) {
			return new ResponseEntity<>(OperationResponse.getFailedResponse("No such user Or token is wrong"),
					HttpStatus.OK);
		}

		OperationResponse res = new OperationResponse();

		try {
			res = planService.savePlan(planSaveRequestModel);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			return new ResponseEntity<>(OperationResponse.getFailedResponse(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "getPlan", method = RequestMethod.POST)
	public ResponseEntity<PlanDisplayResponseModel> getPlan(@RequestBody PlanGetModel planGetModel) {
		PlanDisplayResponseModel res = new PlanDisplayResponseModel();
		if (!jwtTokenProvider.authenToken(planGetModel.getAuthModel().getToken())) {
			res.setOperationResponse(OperationResponse.getFailedResponse("No such user Or token is wrong"));
			return new ResponseEntity<>(res, HttpStatus.OK);
		}

		try {
			res = planService.getPlan(planGetModel);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			res.setOperationResponse(OperationResponse.getFailedResponse(e.getMessage()));
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "getAllPlan", method = RequestMethod.GET)
	public ResponseEntity<PlanDisplayResponseModel> getAllPlan(@RequestBody AuthModel authModel) {
		PlanDisplayResponseModel res = new PlanDisplayResponseModel();

		if (!jwtTokenProvider.authenToken(authModel.getToken())) {
			res.setOperationResponse(OperationResponse.getFailedResponse("No such user Or token is wrong"));
			return new ResponseEntity<>(res, HttpStatus.OK);
		}

		try {
			res = planService.getAllPlan(authModel);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			res.setOperationResponse(OperationResponse.getFailedResponse(e.getMessage()));
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/recommended")
	public ResponseEntity<?> generateRecommendedPlan(@RequestBody RequestRecommendedPlan RecommendedPlan) {

//		SearchRequest searchRequest = new SearchRequest();
//		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//		searchRequest.source(searchSourceBuilder);
//		RequestSettingsModel settings = RecommendedPlan.getSettings();

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

		sourceBuilder.sort(new ScoreSortBuilder("popularity").order(SortOrder.DESC));

		RecommendedPlan res = planService.generateRecommendedPlan(RecommendedPlan.getPlaces(),
				RecommendedPlan.getCategories(), RecommendedPlan.getSettings());
		String response = JSON.toJSONString(res, SerializerFeature.WriteNullStringAsEmpty,
				SerializerFeature.WriteNullNumberAsZero, SerializerFeature.IgnoreErrorGetter);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/searchPlace")
	public ResponseEntity<?> getPlaceInfo(@RequestParam(name = "placeName") String placeName) {
		List<Place> places = googleSearch.getInfo(placeName);

		com.laioffer.travelplanner.entities.Place place = new com.laioffer.travelplanner.entities.Place();
//        place.setPlaceName(places.get(0).getName());
//        place.setAddress(places.get(0).getAddress());
//        place.setLat(places.get(0).getLatitude());
//        place.setLon(places.get(0).getLongitude());
//        place.setPopularity((float) places.get(0).getRating());
//        place.setImageLink(places.get(0).getIconUrl());
//        place.setWebsite(places.get(0).getWebsite());

		return new ResponseEntity<>(place.toString(), HttpStatus.OK);
	}
}
