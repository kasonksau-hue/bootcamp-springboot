package com.bootcamp.demo.bc_forum.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class UserCommentDTO {
  private Long id;
  private String username;
  @Setter
  private List<CommentDTO> comments;

  @Getter
  @Builder
  public static class CommentDTO {
    private String name;
    private String email;
    private String body;
  }
}