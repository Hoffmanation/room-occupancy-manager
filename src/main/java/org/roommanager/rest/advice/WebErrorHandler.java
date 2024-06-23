package org.roommanager.rest.advice;

import java.util.Date;

import org.springdoc.api.ErrorMessage;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * The {@code WebErrorHandler} class implements {@code @RestControllerAdvice} to
 * intercept and modify the response body in case of any web error
 * 
 * This class specifically handles instances of {@code RoomOccupancyResponse}
 * and return an error message to the client in case any {@link Exception} has been thrown
 * 
 * 
 * @author Hoffman
 */
@RestControllerAdvice
public class WebErrorHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(ex.getMessage());

		return message;
	}
}