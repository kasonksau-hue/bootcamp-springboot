package com.bootcamp.demo.bc_forum.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.bootcamp.demo.bc_forum.dto.CommentReq;
import com.bootcamp.demo.bc_forum.entity.CommentEntity;
import com.bootcamp.demo.bc_forum.entity.PostEntity;
import com.bootcamp.demo.bc_forum.entity.UserEntity;

public interface Forum2Operation {
  @GetMapping(value = "/users")
  List<UserEntity> getUsers();

  @GetMapping(value = "/user")
  UserEntity getUser(@RequestParam Long userId);

  @PutMapping(value = "/user/{userId}")
  UserEntity updateUser(@PathVariable Long userId,
      @RequestBody UserEntity userEntity);

  @GetMapping(value = "/posts")
  List<PostEntity> getPosts();

  @GetMapping(value = "/posts/{userId}")
  List<PostEntity> getPosts(@PathVariable Long userId);

  // suppose postReqDto
  @PostMapping(value = "/post/{userId}")
  PostEntity createPost(@PathVariable Long userId,
      @RequestBody PostEntity postEntity);

  @DeleteMapping(value = "/post/{postId}")
  void deletePost(@PathVariable Long postId);

  @GetMapping(value = "/comments")
  List<CommentEntity> getComments();

  @GetMapping(value = "/comments/{postId}")
  List<CommentEntity> getComments(@PathVariable Long postId);

  // suppose commentReqDto
  @PostMapping(value = "/comment/postId")
  CommentEntity createComment(@RequestParam Long postId,
      @RequestBody CommentEntity commentEntity);

  @PatchMapping(value = "/comment/commentId")
  CommentEntity updateCommentBody(@RequestParam Long commentId,
      @RequestBody CommentReq commentReq);

  @DeleteMapping(value = "/comment/{commentId}")
  void deleteComment(@PathVariable Long commentId);
}