package com.laioffer.travelplanner.controllers;


import com.laioffer.travelplanner.entities.User;
import com.laioffer.travelplanner.jwtUtils.JwtTokenProvider;
import com.laioffer.travelplanner.model.common.LoginRequestModel;
import com.laioffer.travelplanner.model.common.LoginResponse;
import com.laioffer.travelplanner.model.common.MessageResponse;
import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.model.user.UserInfoModel;
import com.laioffer.travelplanner.services.UserService;
import com.laioffer.travelplanner.services.implementation.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final String wrongEmailPasswordError = "Incorrect email or password";

    private final String userExistError = "User already exists";

    private final String success = "ok";

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()).getEmail() != null) {
            return new ResponseEntity<>(OperationResponse.getFailedResponse(userExistError), HttpStatus.CONFLICT);
        }
        userService.saveUser(user);
        return ResponseEntity.ok(OperationResponse.getSuccessResponse());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequestModel loginRequestModel) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequestModel.getEmail(), loginRequestModel.getPassword());

        LoginResponse ans = new LoginResponse();

        try{
            authenticationManager.authenticate(authenticationToken);
        } catch( BadCredentialsException e) {
            ans.setOperationResponse(OperationResponse.getFailedResponse(wrongEmailPasswordError));
            return new ResponseEntity<>(ans, HttpStatus.UNAUTHORIZED);
        }
        String token = jwtTokenProvider.generateToken(authenticationToken);
        UserInfoModel model = userService.findByEmail(loginRequestModel.getEmail());
        ans.setToken(token);
        ans.setId(model.getEmail());
        ans.setOperationResponse(OperationResponse.getSuccessResponse());
        return ResponseEntity.ok(ans);
    }
}
