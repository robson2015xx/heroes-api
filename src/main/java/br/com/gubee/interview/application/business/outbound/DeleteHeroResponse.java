package br.com.gubee.interview.application.business.outbound;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DeleteHeroResponse {

	private String message;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date timestamp;
	
	public DeleteHeroResponse() {
		super();
		message = "";
		timestamp = new Date();
	}

	public String getMessage() {
		return message;
	}

	public Date getTimestamp() {
		return timestamp;
	}
}
