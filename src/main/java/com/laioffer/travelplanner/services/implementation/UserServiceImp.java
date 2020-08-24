package com.laioffer.travelplanner.services.implementation;


import com.laioffer.travelplanner.entities.User;
import com.laioffer.travelplanner.repositories.UserRepository;
import com.laioffer.travelplanner.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPassword(user.getPassword());
        user.setTimeCreate(new Date());
        user.setTimeUpdated(new Date());
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findById(email).orElse(null);
    }
}
