package com.apirestspringboot.exception;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ApiExceptionHandler {

	/**
	 * Captura los errores de validación de los Dtos realizados por la
	 * anotación @Valid
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST) // 400 Bad Request
	@ResponseBody
	public ErrorMessage handleValidationExceptions(MethodArgumentNotValidException exception,
			HttpServletRequest request) {
		List<String> messages = new ArrayList<>();

		exception.getBindingResult().getAllErrors().forEach((error) -> {
			messages.add(error.getDefaultMessage());
		});

		return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), messages,
				request.getRequestURI());
	}

	@ExceptionHandler({ ApiBadRequestException.class, DuplicateKeyException.class,
			HttpMediaTypeNotSupportedException.class, MissingRequestHeaderException.class,
			MissingServletRequestParameterException.class, MethodArgumentTypeMismatchException.class,
			HttpMessageNotReadableException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST) // 400 Bad Request
	@ResponseBody
	public ErrorMessage badRequest(Exception exception, HttpServletRequest request) {
		List<String> messages = new ArrayList<>();
		messages.add(exception.getMessage());
		return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), messages,
				request.getRequestURI());
	}

	@ExceptionHandler({ ApiUnauthorizedException.class, AccessDeniedException.class })
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED) // 401 Unauthorized
	public void unauthorized() {
		// El protocolo Http no permite que se devuelva nada en caso de 401
	}

	@ExceptionHandler({ ApiForbiddenException.class })
	@ResponseStatus(value = HttpStatus.FORBIDDEN) // 403 Forbidden
	@ResponseBody
	public ErrorMessage forbiddenRequest(Exception exception, HttpServletRequest request) {
		List<String> messages = new ArrayList<>();
		messages.add(exception.getMessage());
		return new ErrorMessage(HttpStatus.FORBIDDEN.value(), HttpStatus.FORBIDDEN.getReasonPhrase(), messages,
				request.getRequestURI());
	}

	@ExceptionHandler({ ApiNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND) // 404 Not Found
	@ResponseBody
	public ErrorMessage notFoundRequest(Exception exception, HttpServletRequest request) {
		List<String> messages = new ArrayList<>();
		messages.add(exception.getMessage());
		return new ErrorMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase(), messages,
				request.getRequestURI());
	}

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR) // 500 Internal Server Error
	@ResponseBody
	public ErrorMessage fatalErrorUnexpected(Exception exception, HttpServletRequest request) {
		List<String> messages = new ArrayList<>();
		messages.add(exception.getMessage());
		return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), messages, request.getRequestURI());
	}

}
