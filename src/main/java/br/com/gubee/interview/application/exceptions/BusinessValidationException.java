package br.com.gubee.interview.application.exceptions;

import java.util.Collections;
import java.util.List;

import br.com.gubee.interview.adapters.advice.ErrorDTO;

public class BusinessValidationException extends RuntimeException {

	private static final long serialVersionUID = -2959584245854260603L;

	private int status;

	private List<ErrorDTO> errors;
	
	private String details;

	private static final int DEFAULT_HTTP_STATUS_CODE = 400;

	public BusinessValidationException(String message) {
		super(message);
		this.status = DEFAULT_HTTP_STATUS_CODE;
		this.details = "";
		this.errors = List.of(new ErrorDTO("", message));
	}
	
	public BusinessValidationException(String message, String details) {
		super(message);
		this.status = DEFAULT_HTTP_STATUS_CODE;
		this.details = details;
		this.errors = List.of(new ErrorDTO(details, message));
	}
	
	public BusinessValidationException(String message, String details, int status) {
		super(message);
		this.status = status;
		this.details = details;
		this.errors = List.of(new ErrorDTO(details, message));
	}

	public BusinessValidationException(String message, int status) {
		super(message);
		this.status = status;
		this.details = "";
		this.errors = List.of(new ErrorDTO("", message));
	}

	public BusinessValidationException(String message, List<ErrorDTO> errors) {
		super(message);
		this.details = "";
		this.status = DEFAULT_HTTP_STATUS_CODE;
		this.errors = Collections.unmodifiableList(errors);
	}

	public BusinessValidationException(String message, List<ErrorDTO> errors, int status) {
		super(message);
		this.status = status;
		this.errors = Collections.unmodifiableList(errors);
	}

	public BusinessValidationException(List<ErrorDTO> errors, Exception e) {
		super(e.getMessage(), e);
		this.details = "";
		this.status = DEFAULT_HTTP_STATUS_CODE;
		this.errors = Collections.unmodifiableList(errors);
	}

	public BusinessValidationException(List<ErrorDTO> errors, int status, Exception e) {
		super(e.getMessage(), e);
		this.details = "";
		this.status = status;
		this.errors = Collections.unmodifiableList(errors);
	}

	public BusinessValidationException(String message, Exception e) {
		super(message, e);
		this.details = "";
		this.status = DEFAULT_HTTP_STATUS_CODE;
		this.errors = List.of(new ErrorDTO(e.getMessage(), message));
	}

	public BusinessValidationException(String message, Exception e, int status) {
		super(message, e);
		this.details = "";
		this.status = status;
		this.errors = List.of(new ErrorDTO(e.getStackTrace()[0].toString(), e.getMessage()));
	}

	public int getStatus() {
		return status;
	}
	
	public String getDetails() {
		return details;
	}

	public List<ErrorDTO> getErrors() {
		return errors;
	}
}
