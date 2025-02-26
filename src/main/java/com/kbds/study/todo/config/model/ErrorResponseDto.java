package com.kbds.study.todo.config.model;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDto {
  private String error;
  private String message;

  public ErrorResponseDto(String error, String message) {
    this.error = error;
    this.message = message;
  }
}
