package com.laioffer.travelplanner.services;


import com.laioffer.travelplanner.entities.User;
import com.laioffer.travelplanner.model.user.UserInfoModel;

public interface UserService {
	UserInfoModel saveUser(User user);

    UserInfoModel findByEmail(String email);

}
