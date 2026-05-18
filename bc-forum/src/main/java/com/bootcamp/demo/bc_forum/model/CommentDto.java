package com.bootcamp.demo.bc_forum.model;

import lombok.Getter;

@Getter
public class CommentDto {
  private Long id;
  private String name;
  private String email;
  private String body;
  private Long postId;
}