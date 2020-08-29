package com.laioffer.travelplanner.model.common;

public class LoginResponse {

	private OperationResponse operationResponse;

    private String id;

    private String token;


    public OperationResponse getOperationResponse() {
		return operationResponse;
	}

	public void setOperationResponse(OperationResponse operationResponse) {
		this.operationResponse = operationResponse;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
