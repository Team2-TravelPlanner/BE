package com.laioffer.travelplanner.controllers;


import com.laioffer.travelplanner.model.common.LoginRequestModel;
import com.laioffer.travelplanner.model.common.LoginResponse;
import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.user.RegisterRequestModel;
import com.laioffer.travelplanner.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<OperationResponse> register(@RequestBody RegisterRequestModel model) {
        
		OperationResponse res = new OperationResponse();
		
		try {
			res = userService.registerUser(model);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			return new ResponseEntity<>(OperationResponse.getFailedResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequestModel loginRequestModel) {

    	LoginResponse res = new LoginResponse();
		
		try {
			res = userService.login(loginRequestModel);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			res.setOperationResponse(OperationResponse.getFailedResponse(e.getMessage()));
			return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
