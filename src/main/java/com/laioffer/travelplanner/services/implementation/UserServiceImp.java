package com.laioffer.travelplanner.services.implementation;


import com.laioffer.travelplanner.entities.User;
import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.user.UserInfoModel;
import com.laioffer.travelplanner.repositories.UserRepository;
import com.laioffer.travelplanner.services.UserService;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfoModel saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setPassword(user.getPassword()); //?

        Date currTime = new Date();
        user.setCreateTime(currTime);
        user.setUpdateTime(currTime);
        UserInfoModel  model = new UserInfoModel();
    	model.setEmail(user.getEmail());
    	model.setId(user.getUserId());
    	model.setName(user.getUserName());
    	model.setOperationResponse(OperationResponse.getSuccessResponse());
    	userRepository.save(user);
        return model;
    }

    @Override
    public UserInfoModel findByEmail(String email) {
    	User user = userRepository.findByEmail(email).orElse(null);
    	OperationResponse res = new OperationResponse();
    	UserInfoModel  model = new UserInfoModel();
    	if(user == null) {
    		res.getFailedResponse("No such User");
    		model.setOperationResponse(res);
    		return model;
    	}
    	model.setOperationResponse(res.getSuccessResponse());
    	model.setEmail(user.getEmail());
    	model.setId(user.getUserId());
    	model.setName(user.getUserName());
        return model;
    }
    
    
    private UserInfoModel convert(User user) {
    	UserInfoModel model = new UserInfoModel();
    	
    	model.setEmail(user.getEmail());
    	model.setId(user.getUserId());
    	model.setName(user.getPassword());
    	return model;
    }
}
