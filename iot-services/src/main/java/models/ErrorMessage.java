package models;

import jakarta.ws.rs.core.Response.Status;

public class ErrorMessage {
	
	private String message;
    private Status status;
    
	public ErrorMessage(String message, Status status) {
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public Status getStatus() {
		return status;
	}
	
	
    
    

}
