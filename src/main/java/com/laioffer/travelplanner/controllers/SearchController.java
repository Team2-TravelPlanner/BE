package com.laioffer.travelplanner.controllers;

import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.search.SearchRequestModel;
import com.laioffer.travelplanner.model.search.SearchResponseModel;
import com.laioffer.travelplanner.services.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	private SearchService searchService;


	/**
	 * Get the health status of elastic search cluster
	 */
	@RequestMapping(value = "/health",method = RequestMethod.GET)
	public boolean getHealthStatus() {
		try {
			return searchService.ping();
		} catch (Exception e) {
			return false;
		}
	}

	@RequestMapping(value = "/query",method = RequestMethod.POST)
	public ResponseEntity<SearchResponseModel> searchPlace(@RequestBody SearchRequestModel searchRequestModel) {
		SearchResponseModel res = new SearchResponseModel();

//		if(!jwtTokenProvider.authenToken(searchRequestModel.getAuthModel().getToken())){
//			res.setOperationResponse(OperationResponse.getFailedResponse("No such user Or token is wrong"));
//			return new ResponseEntity<>(res, HttpStatus.OK);
//		}

		try {
			String keyword = searchRequestModel.getKeyword();
			String category = searchRequestModel.getCategory();

			if (StringUtils.isEmpty(keyword) && StringUtils.isEmpty(category)) {
				res = searchService.getAllPlaces(searchRequestModel);
			} else {
				res = searchService.searchPlaces(searchRequestModel);
			}
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			res.setOperationResponse(OperationResponse.getFailedResponse(e.getMessage()));
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@RequestMapping(value = "/getAllPlace",method = RequestMethod.POST)
	public ResponseEntity<SearchResponseModel> getAllPlace(@RequestBody SearchRequestModel searchRequestModel) {
		SearchResponseModel res = new SearchResponseModel();

		try {
			res = searchService.getAllPlaces(searchRequestModel);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			res.setOperationResponse(OperationResponse.getFailedResponse(e.getMessage()));
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
