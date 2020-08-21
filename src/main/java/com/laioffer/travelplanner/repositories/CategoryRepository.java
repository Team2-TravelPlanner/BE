package com.laioffer.travelplanner.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.laioffer.travelplanner.entities.Category;

public interface CategoryRepository extends ElasticsearchRepository<Category, String>{

}
