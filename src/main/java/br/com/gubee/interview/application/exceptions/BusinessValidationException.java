package br.com.gubee.interview.application.exceptions;

import java.util.Collections;
import java.util.List;

import br.com.gubee.interview.adapters.advice.ErrorDTO;

public class BusinessValidationException extends RuntimeException {

	private static final long serialVersionUID = -2959584245854260603L;

	private int status;

	private List<ErrorDTO> errors;

	private static final int DEFAULT_HTTP_STATUS_CODE = 400;

	public BusinessValidationException(String message) {
		super(message);
		this.status = DEFAULT_HTTP_STATUS_CODE;
		this.errors = List.of(new ErrorDTO("01", "", message));
	}

	public BusinessValidationException(String message, int status) {
		super(message);
		this.status = status;
		this.errors = List.of(new ErrorDTO("01", "", message));
	}

	public BusinessValidationException(String message, List<ErrorDTO> errors) {
		super(message);
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
		this.status = DEFAULT_HTTP_STATUS_CODE;
		this.errors = Collections.unmodifiableList(errors);
	}

	public BusinessValidationException(List<ErrorDTO> errors, int status, Exception e) {
		super(e.getMessage(), e);
		this.status = status;
		this.errors = Collections.unmodifiableList(errors);
	}

	public BusinessValidationException(String message, Exception e) {
		super(message, e);
		this.status = DEFAULT_HTTP_STATUS_CODE;
		this.errors = List.of(new ErrorDTO("01", "", e.getMessage()));
	}

	public BusinessValidationException(String message, Exception e, int status) {
		super(message, e);
		this.status = status;
		this.errors = List.of(new ErrorDTO("91", "", e.getMessage()));
	}

	public int getStatus() {
		return status;
	}

	public List<ErrorDTO> getErrors() {
		return errors;
	}
}
