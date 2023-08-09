package br.com.gubee.interview.application.business.outbound;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.gubee.interview.application.business.ResponseMessageConstants;

public class DeleteHeroResponse {

	private String message;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date timestamp;
	
	public DeleteHeroResponse() {
		super();
		message = ResponseMessageConstants.HERO_HAS_BEEN_DELETED;
		timestamp = new Date();
	}

	public String getMessage() {
		return message;
	}

	public Date getTimestamp() {
		return timestamp;
	}
}
