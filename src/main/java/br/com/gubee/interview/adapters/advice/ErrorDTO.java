package br.com.gubee.interview.adapters.advice;

import java.io.Serializable;

public class ErrorDTO implements Serializable{

	private static final long serialVersionUID = 4947514610991195682L;
	private String code;
	private String field;
	private String message;
	
	public ErrorDTO() {}

	public ErrorDTO(String code, String field, String message) {
		super();
		this.code = code;
		this.field = field;
		this.message = message;
	}
	
	public String getCode() {
		return code;
	}
	public String getField() {
		return field;
	}
	public String getMessage() {
		return message;
	}	
}
