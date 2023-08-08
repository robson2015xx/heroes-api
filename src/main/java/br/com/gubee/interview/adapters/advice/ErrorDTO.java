package br.com.gubee.interview.adapters.advice;

import java.io.Serializable;

public class ErrorDTO implements Serializable{

	private static final long serialVersionUID = 4947514610991195682L;
	private String details;
	private String message;
	
	public ErrorDTO() {}

	public ErrorDTO(String details, String message) {
		super();
		this.details = details;
		this.message = message;
	}
	
	public String getDetails() {
		return details;
	}
	public String getMessage() {
		return message;
	}	
}
