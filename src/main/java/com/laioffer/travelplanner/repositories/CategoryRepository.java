package com.laioffer.travelplanner.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.laioffer.travelplanner.entities.Category;

import java.util.Optional;

public interface CategoryRepository extends ElasticsearchRepository<Category, String>{
    Optional<Category> findByCategoryName(String name);
}
