package br.com.gubee.interview.application.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorResponse {

	private String message;
	
	private String path;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date timestamp;
	
	private List<String> errors;
	
	public ErrorResponse() {}
	
	public ErrorResponse(String message, String path, Date timestamp, List<String> errors) {
		super();
		this.message = message;
		this.path = path;
		this.timestamp = timestamp;
		this.errors = errors;
	}
	
	public String getMessage() {
		return message;
	}
	public String getPath() {
		return path;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public List<String> getErrors() {
		return errors;
	}
}
