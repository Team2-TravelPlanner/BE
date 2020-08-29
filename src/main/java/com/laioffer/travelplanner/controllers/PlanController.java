package com.laioffer.travelplanner.controllers;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.laioffer.travelplanner.antAlg.ACO;
import com.laioffer.travelplanner.entities.Place;
import com.laioffer.travelplanner.mapsearch.GoogleSearch;
import com.laioffer.travelplanner.planModel.RecommendedPlan;
import com.laioffer.travelplanner.requestModel.RequestCustomizedPlan;
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
import se.walkercrou.places.Place;

import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {

	@Autowired
	private PlanService planService;

	@Autowired
	private GoogleSearch googleSearch;

	@PostMapping("/recommended")
	public ResponseEntity<?> generateRecommendedPlan(@RequestBody RequestRecommendedPlan RecommendedPlan) {

//		SearchRequest searchRequest = new SearchRequest();
//		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
//		searchRequest.source(searchSourceBuilder);
//		RequestSettingsModel settings = RecommendedPlan.getSettings();

		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

		sourceBuilder.sort(new ScoreSortBuilder("popularity").order(SortOrder.DESC));

		com.laioffer.travelplanner.planModel.RecommendedPlan res = planService.generateRecommendedPlan(RecommendedPlan.getPlaces(), RecommendedPlan.getCategories(), RecommendedPlan.getSettings());
		String response = JSON.toJSONString(res, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.IgnoreErrorGetter);

		return new ResponseEntity<>(response,HttpStatus.OK);
	}


	@GetMapping("/searchPlace")
	public ResponseEntity<?> getPlaceInfo(@RequestParam(name = "placeName") String placeName) {
		List<Place> places =  googleSearch.getInfo(placeName);

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