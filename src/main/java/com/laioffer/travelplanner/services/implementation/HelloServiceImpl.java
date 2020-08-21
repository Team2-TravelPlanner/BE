package com.laioffer.travelplanner.services.implementation;

import com.laioffer.travelplanner.model.common.OperationResponse;
import com.laioffer.travelplanner.services.HelloService;

public class HelloServiceImpl implements HelloService{

	@Override
	public OperationResponse hello() throws Exception {
		
		//return OperationResponse.getSuccessResponse();
		
		return OperationResponse.getFailedResponse("Ooops , sth went wrong.");
	}

}
