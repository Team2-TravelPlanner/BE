package com.laioffer.travelplanner.model.common;
public class OperationResponse {
	private Result result;
	private String reason;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}

	public static OperationResponse getFailedResponse(String reason) {
		OperationResponse operationResponse = new OperationResponse();
		operationResponse.setResult(Result.UNSUCCESSFUL);
		operationResponse.setReason(reason);
		return operationResponse;
	}

	public static OperationResponse getSuccessResponse() {
		OperationResponse operationResponse = new OperationResponse();
		operationResponse.setResult(Result.SUCCESSFUL);
		return operationResponse;
	}

	public Boolean isFailed() {
		return this.result == Result.UNSUCCESSFUL;
	}

	@Override
	public String toString() {
		return "OperationResponse [result=" + result + ", reason=" + reason + "]";
	}
	
	
}
