package com.laioffer.travelplanner.repositories;

import com.laioffer.travelplanner.entities.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface UserRepository extends ElasticsearchRepository<User, String> {
    Optional<User> findByEmail(String email);
}
