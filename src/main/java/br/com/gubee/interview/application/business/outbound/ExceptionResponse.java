package br.com.gubee.interview.application.business.outbound;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.gubee.interview.adapters.advice.ErrorDTO;

public class ExceptionResponse implements Serializable{

	private static final long serialVersionUID = 2576463581334026303L;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private Date timestamp;
	private String message;
	private String path;
	private String httpStatus;
	private String traceld;
	private List<ErrorDTO> errors;
	
	public ExceptionResponse() {}
	
	public ExceptionResponse(Date timestamp, String message, String path, String httpStatus, String traceld,
			List<ErrorDTO> errors) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.path = path;
		this.httpStatus = httpStatus;
		this.traceld = traceld;
		this.errors = errors;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getPath() {
		return path;
	}
	public String getHttpStatus() {
		return httpStatus;
	}
	public String getTraceld() {
		return traceld;
	}
	public List<ErrorDTO> getErrors() {
		return errors;
	}
}