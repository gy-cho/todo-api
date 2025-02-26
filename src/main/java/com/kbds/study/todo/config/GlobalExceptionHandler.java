package com.kbds.study.todo.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.kbds.study.todo.config.exception.ResourceNotFoundException;
import com.kbds.study.todo.config.model.ErrorResponseDto;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException ex) {
      ErrorResponseDto error = new ErrorResponseDto("Not Found", ex.getMessage());
      return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }
  
  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ErrorResponseDto> handleNoResourceFoundException(NoResourceFoundException ex) {
    // 정적 리소스 누락과 관련된 에러는 로깅만 하고 404 반환
    return new ResponseEntity<>(new ErrorResponseDto("Resource not found", ex.getMessage()), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(NoHandlerFoundException.class)
  public ResponseEntity<ErrorResponseDto> handle404(NoHandlerFoundException ex) {
    ErrorResponseDto error = new ErrorResponseDto("잘못된 요청 입니다.", "Not Found");
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDto> handleException(Exception ex) {
    ex.printStackTrace();
    
    ErrorResponseDto error = new ErrorResponseDto("Internal server error", ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
