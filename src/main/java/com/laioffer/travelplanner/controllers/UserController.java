package com.laioffer.travelplanner.controllers;


import com.laioffer.travelplanner.entities.User;
import com.laioffer.travelplanner.jwtUtils.JwtTokenProvider;
import com.laioffer.travelplanner.model.common.AuthenticationRequest;
import com.laioffer.travelplanner.model.common.AuthenticationResponse;
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
import org.springframework.security.core.userdetails.UserDetails;
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
    private MyUserDetailService userDetailService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userService.findByEmail(user.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, userExistError, new Exception());
        }
        userService.saveUser(user);
        return ResponseEntity.ok(new MessageResponse(success));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        
        AuthenticationResponse ans = new AuthenticationResponse();
        
        
        try{
            authenticationManager.authenticate(authenticationToken);
        } catch( BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, wrongEmailPasswordError, new Exception());
        }
        String token = jwtTokenProvider.generateToken(authenticationToken);
        UserInfoModel model = userService.findByEmail(authenticationRequest.getEmail());
        ans.setToken(token);
        ans.setId(model.getId());
        ans.setOperationResponse(OperationResponse.getSuccessResponse());
        return ResponseEntity.ok(ans);
    }
}
