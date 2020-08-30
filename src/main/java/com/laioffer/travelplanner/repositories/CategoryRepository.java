package com.laioffer.travelplanner.repositories;

import java.util.Optional;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.laioffer.travelplanner.entities.Category;
import com.laioffer.travelplanner.entities.User;

public interface CategoryRepository extends ElasticsearchRepository<Category, String>{
	 Optional<Category> findByCategoryName(String categoryName);
}
