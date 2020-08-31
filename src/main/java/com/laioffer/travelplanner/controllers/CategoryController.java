package com.laioffer.travelplanner.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.laioffer.travelplanner.model.common.AuthModel;
import com.laioffer.travelplanner.model.common.CategoryModel;
import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.common.Result;
import com.laioffer.travelplanner.model.plan.PlanDisplayResponseModel;
import com.laioffer.travelplanner.services.CategoryService;
import com.laioffer.travelplanner.services.UserService;

@RestController
@RequestMapping("category")
public class CategoryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "getall", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryModel>> getAll() throws Exception {

		List<CategoryModel> res = null;

		try {
			res = categoryService.getAllCategory();
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
