package br.com.gubee.interview.adapters.advice;

import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;

import br.com.gubee.interview.application.domain.ErrorMessagesConstants;
import br.com.gubee.interview.application.exceptions.BusinessValidationException;
import lombok.extern.slf4j.Slf4j;

/*
 * Use This class for handle all exceptions
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {

		String message = ex.getMessage();
		String traceld = Optional.of(request.getHeader("X-traceId")).orElse(UUID.randomUUID().toString());
		headers.add("X-traceld", traceld);
		List<ErrorDTO> errors = new ArrayList<>();
		errors.add(new ErrorDTO(ex.getObjectName(), ex.getMessage()));
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), message, request.getDescription(false),
				status.toString(), traceld, errors);
		log.error(message, ex);
		return handleExceptionInternal(ex, exceptionResponse, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		String message = ex.getMessage();
		String traceId = Optional.ofNullable(request.getHeader("X-traceId")).orElse(UUID.randomUUID().toString());
		headers.add("X-traceld", traceId);
		List<ErrorDTO> errors = new ArrayList<>();
		errors.add(new ErrorDTO(ex.getParameterName() + "is missing", ex.getMessage()));
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), message, request.getDescription(false),
				status.toString(), traceId, errors);
		log.error(message, ex);
		return handleExceptionInternal(ex, exceptionResponse, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		return buildInvalidDataExceptions(ex, request);
	}

	@ExceptionHandler(BusinessValidationException.class)
	public final ResponseEntity<ExceptionResponse> handleCustomizedException(BusinessValidationException ex,
			WebRequest request) {
		HttpStatus status = HttpStatus.valueOf(ex.getStatus());
		String traceld = Optional.ofNullable(request.getHeader("X-traceld")).orElse(UUID.randomUUID().toString());
		String message = ex.getMessage();
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), message, request.getDescription(false),
				status.toString(), traceld, ex.getErrors());
		log.error(message, ex);
		return ResponseEntity.status(status).header("X-traceId", traceld).body(exceptionResponse);
	}

	@ExceptionHandler(TimeoutException.class)
	public final ResponseEntity<ExceptionResponse> handleTimeoutException(TimeoutException ex, WebRequest request) {
		HttpStatus status = HttpStatus.REQUEST_TIMEOUT;
		String traceld = Optional.ofNullable(request.getHeader("X-traceId")).orElse(UUID.randomUUID().toString());
		List<ErrorDTO> errors = new ArrayList<>();
		errors.add(new ErrorDTO(ex.getStackTrace()[0].toString(), ex.getMessage()));
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ErrorMessagesConstants.TIMEOUT_EXCEPTION_MESSAGE,
				request.getDescription(false), status.toString(), traceld, errors);
		log.error("Timeout application: ", ex);
		return ResponseEntity.status(status).header("X-traceId", traceld).body(exceptionResponse);
	}

	@ExceptionHandler(SQLSyntaxErrorException.class)
	public final ResponseEntity<Object> handleSOLSyntaxErrorException(SQLSyntaxErrorException ex, WebRequest request) {
		return buildInvalidDataExceptions(ex, request);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public final ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException ex, WebRequest request) {
		return buildInvalidDataExceptions(ex, request);
	}

	@ExceptionHandler(NumberFormatException.class)
	public final ResponseEntity<Object> handleNumberFormatException(NumberFormatException ex, WebRequest request) {
		return buildInvalidDataExceptions(ex, request);
	}

	@ExceptionHandler(JsonParseException.class)
	public final ResponseEntity<Object> handleJsonParseException(JsonParseException ex, WebRequest request) {
		return buildInvalidDataExceptions(ex, request);
	}

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
		return buildNotFoundExceptions(ex, request);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public final ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {
		return buildNotFoundExceptions(ex, request);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleGenericException(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		String traceId = Optional.ofNullable(request.getHeader("X-traceId")).orElse(UUID.randomUUID().toString());
		List<ErrorDTO> errors = new ArrayList<>();
		errors.add(new ErrorDTO(ex.getStackTrace()[0].toString(), ex.getMessage()));
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ErrorMessagesConstants.GENERIC_EXCEPTION_MESSAGE,
				request.getDescription(false), status.toString(), traceId, errors);
		log.error("Error not mapped: ", ex);
		return ResponseEntity.status(status).header("X-traceId", traceId).body(exceptionResponse);
	}

	private ResponseEntity<Object> buildNotFoundExceptions(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String traceld = Optional.ofNullable(request.getHeader("X-traceld")).orElse(UUID.randomUUID().toString());
		List<ErrorDTO> errors = new ArrayList<>();
		errors.add(new ErrorDTO(ex.getStackTrace()[0].toString(), ex.getMessage()));
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ErrorMessagesConstants.NOT_FOUND_MESSAGE,
				request.getDescription(false), status.toString(), traceld, errors);
		log.error("Invalid payload, unprocessable: ", ex);
		return ResponseEntity.status(status).header("X-traceld", traceld).body(exceptionResponse);
	}

	private ResponseEntity<Object> buildInvalidDataExceptions(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String traceld = Optional.ofNullable(request.getHeader("X-traceId")).orElse(UUID.randomUUID().toString());
		List<ErrorDTO> errors = new ArrayList<>();
		errors.add(new ErrorDTO(ex.getStackTrace()[0].toString(), ex.getMessage()));
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ErrorMessagesConstants.INVALID_DATA_MESSAGE,
				request.getDescription(false), status.toString(), traceld, errors);
		log.error("Invalid payload, unprocessable: ", ex);
		return ResponseEntity.status(status).header("X-traceId", traceld).body(exceptionResponse);
	}
}
