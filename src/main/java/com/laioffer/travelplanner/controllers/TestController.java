package com.laioffer.travelplanner.controllers;

import com.laioffer.travelplanner.model.common.FetchRequestModel;
import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.services.FetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private FetchService fetchService;

    @GetMapping("/testAuth")
    public String testAuth() {
        return "Passed Authorization";
    }


    @PostMapping("/insertData")
    public ResponseEntity<OperationResponse> insertCategories(@RequestBody FetchRequestModel model) {
        OperationResponse res = fetchService.FetchCategories(model.getPlaceName());
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
