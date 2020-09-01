package com.laioffer.travelplanner.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laioffer.travelplanner.entities.Category;
import com.laioffer.travelplanner.model.common.CategoryModel;
import com.laioffer.travelplanner.repositories.CategoryRepository;
import com.laioffer.travelplanner.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<CategoryModel> getAllCategory() throws Exception {
		Iterable<Category> categories = categoryRepository.findAll();
		
		List<CategoryModel> models = new ArrayList<>();
		for(Category category : categories) {
			CategoryModel model = new CategoryModel();
			model.setCategoryId(category.getCategoryId());
			model.setCategoryName(category.getCategoryName());
		}
		return models;
	}

}
