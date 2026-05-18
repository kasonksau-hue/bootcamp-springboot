package com.bootcamp.demo.bc_forum.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.bootcamp.demo.bc_forum.dto.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(value = NumberFormatException.class)
  public ErrorDTO handleNFE(NumberFormatException e) {
    return ErrorDTO.builder() //
        .code(2L) //
        .message("Invalid Input.") //
        .build();
  }

  @ExceptionHandler(value = IllegalArgumentException.class)
  public ErrorDTO handleIAE(IllegalArgumentException e) {
    return ErrorDTO.builder() //
        .code(1L) //
        .message("User Not Found.") //
        .build();
  }

  // UserNotFoundException
  // PostNotFoundException
}