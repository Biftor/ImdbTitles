package com.example.imdb.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@SuppressWarnings("DataFlowIssue")
@ControllerAdvice
public class ControllersExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Leverage Exception Handler framework for id not found Exception.
   *
   * @param ex      NoSuchElementException
   * @param request WebRequest
   * @return http response
   */
  @ExceptionHandler(NoSuchElementException.class)
  public final ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex, WebRequest request) {

    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    return  createResponseEntity(pd, null, HttpStatus.NOT_FOUND, request);
  }

  /**
   * Leverage Exception Handler framework for id not found Exception.
   *
   * @param ex      NoSuchElementException
   * @param request WebRequest
   * @return http response
   */
  @ExceptionHandler(EntityNotFoundException.class)
  public final ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {

    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    return  createResponseEntity(pd, null, HttpStatus.NOT_FOUND, request);
  }

  /**
   * Leverage Exception Handler framework for id not found Exception.
   *
   * @param ex      ConstraintViolationException
   * @param request WebRequest
   * @return http response
   */
  @ExceptionHandler(ConstraintViolationException.class)
  public final ResponseEntity<Object> handleConstraintViolationException(
          ConstraintViolationException ex, WebRequest request) {

    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    return  createResponseEntity(pd, null, HttpStatus.BAD_REQUEST, request);
  }

  /**
   * Leverage Exception Handler framework for id not found Exception.
   *
   * @param ex      ValidationException
   * @param request WebRequest
   * @return http response
   */
  @ExceptionHandler(ValidationException.class)
  public final ResponseEntity<Object> handleValidationException(
          ValidationException ex, WebRequest request) {

    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    return  createResponseEntity(pd, null, HttpStatus.BAD_REQUEST, request);
  }

  /**
   * Leverage Exception Handler framework for id not found Exception.
   *
   * @param ex      IllegalArgumentException
   * @param request WebRequest
   * @return http response
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public final ResponseEntity<Object> handleIllegalArgumentException(
          IllegalArgumentException ex, WebRequest request) {

    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    return  createResponseEntity(pd, null, HttpStatus.BAD_REQUEST, request);
  }

  /**
   * Leverage Exception Handler framework for unexpected Exceptions.
   *
   * @param ex any exception
   * @param request WebRequest
   * @return http response
   */
  @ExceptionHandler(Exception.class)
  public final ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request) {
    ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    return createResponseEntity(pd, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
  }
}
