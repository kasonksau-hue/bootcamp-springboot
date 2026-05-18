package com.bootcamp.demo.bc_forum.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.bc_forum.dto.UserCommentDTO;
import com.bootcamp.demo.bc_forum.dto.UserDTO;

public interface ForumOperation {
  // ! Exercise 2 Question 1
  @GetMapping(value = "/super/users")
  List<UserDTO> getUsers();

  // ! Exercise 2 Question 2
  @GetMapping(value = "/super/comments")
  UserCommentDTO getCommentsByUserId(@RequestParam String userId);
}