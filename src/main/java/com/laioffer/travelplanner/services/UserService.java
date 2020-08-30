package com.laioffer.travelplanner.services;


import com.laioffer.travelplanner.model.common.AuthModel;
import com.laioffer.travelplanner.model.common.LoginRequestModel;
import com.laioffer.travelplanner.model.common.LoginResponse;
import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.user.RegisterRequestModel;

public interface UserService {
    
    OperationResponse registerUser(RegisterRequestModel model) throws Exception;
    
    LoginResponse login(LoginRequestModel model) throws Exception;
    
    OperationResponse auth(AuthModel model) throws Exception;

}
