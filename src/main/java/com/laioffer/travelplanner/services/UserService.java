package com.laioffer.travelplanner.services;


import com.laioffer.travelplanner.entities.User;

public interface UserService {
    User saveUser(User user);

    User findByEmail(String email);

}
