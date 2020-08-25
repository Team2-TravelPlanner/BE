package com.laioffer.travelplanner.model.user;

import com.laioffer.travelplanner.model.common.OperationResponse;

public class UserInfoModel {
	
	private OperationResponse operationResponse;

	private String id;

	private String email;

	private String name;
	
	public OperationResponse getOperationResponse() {
		return operationResponse;
	}

	public void setOperationResponse(OperationResponse operationResponse) {
		this.operationResponse = operationResponse;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
