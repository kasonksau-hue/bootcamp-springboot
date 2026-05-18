package com.bootcamp.demo.bc_forum.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorDTO {
  private Long code;
  private String message;
}