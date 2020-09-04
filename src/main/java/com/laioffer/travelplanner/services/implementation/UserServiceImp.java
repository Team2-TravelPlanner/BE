package com.laioffer.travelplanner.services.implementation;

import com.laioffer.travelplanner.entities.User;
import com.laioffer.travelplanner.model.common.AuthModel;
import com.laioffer.travelplanner.model.common.LoginRequestModel;
import com.laioffer.travelplanner.model.common.LoginResponse;
import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.user.RegisterRequestModel;
import com.laioffer.travelplanner.repositories.UserRepository;
import com.laioffer.travelplanner.services.UserService;
import com.laioffer.travelplanner.utils.Dateutil;
import com.laioffer.travelplanner.utils.Encryption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public OperationResponse registerUser(RegisterRequestModel model) throws Exception {
		User user = userRepository.findByEmail(model.getEmail()).orElse(null);
		OperationResponse res = new OperationResponse();
		if (user != null) {
			return OperationResponse.getFailedResponse("Email Already been taken.");
		}
		user = new User();
		user.setEmail(model.getEmail());
		user.setUserName(model.getUserName());
		user.setPassword(Encryption.saltPassword(model.getPassword()));
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		userRepository.save(user);
		return OperationResponse.getSuccessResponse();
	}

	@Override
	public LoginResponse login(LoginRequestModel model) throws Exception {
		User user = userRepository.findByEmail(model.getEmail()).orElse(null);
		LoginResponse res = new LoginResponse();
		if (user == null) {
			res.setOperationResponse(OperationResponse.getFailedResponse("No such User"));
			return res;
		}
		if (!Encryption.saltPassword(model.getPassword()).equals(user.getPassword())) {
			res.setOperationResponse(OperationResponse.getFailedResponse("Password not match"));
			return res;
		}
		String newToken = UUID.randomUUID().toString();
		user.setToken(newToken);
		user.setExpireDate(Dateutil.getTwoWeeksLater());
		userRepository.save(user);
		res.setId(user.getEmail());
		res.setToken(newToken);
		res.setOperationResponse(OperationResponse.getSuccessResponse());
		return res;
	}

	@Override
	public OperationResponse auth(AuthModel model) throws Exception {
		User user = userRepository.findByEmail(model.getUserEmail()).orElse(null);
		OperationResponse res = new OperationResponse();
		if (user == null) {
			return OperationResponse.getFailedResponse("No such user.");
		}
		if (!user.getToken().equals(model.getToken()) || user.getExpireDate() == null || user.getExpireDate().getTime() < (new Date()).getTime()){
			return OperationResponse.getFailedResponse("Token no longer userful. Please log in again.");
		}
		return OperationResponse.getSuccessResponse();
	}
}
