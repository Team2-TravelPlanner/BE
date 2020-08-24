package com.laioffer.travelplanner.services.implementation;


import com.laioffer.travelplanner.entities.User;
import com.laioffer.travelplanner.repositories.UserRepository;
import com.laioffer.travelplanner.services.UserService;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setPassword(user.getPassword()); //?

        Date currTime = new Date();
        user.setCreateTime(currTime);
        user.setUpdateTime(currTime);
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email).orElse(null);
    }
}
