package com.bootcamp.demo.bc_forum.service;

import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import com.bootcamp.demo.bc_forum.dto.CommentReq;
import com.bootcamp.demo.bc_forum.entity.CommentEntity;
import com.bootcamp.demo.bc_forum.entity.PostEntity;
import com.bootcamp.demo.bc_forum.entity.UserEntity;

public interface Forum2Service {
  List<UserEntity> getUsers();

  Optional<UserEntity> getUser(Long userId);

  @Transactional
  UserEntity updateUser(Long userId, UserEntity userEntity);

  List<PostEntity> getPosts();

  List<PostEntity> getPosts(Long userId);

  @Transactional
  PostEntity createPost(Long userId, PostEntity postEntity);

  // ! Need @Transactional - More than one write actions
  @Transactional
  void deletePost(Long postId);

  List<CommentEntity> getComments();

  List<CommentEntity> getComments(Long postId);

  @Transactional
  CommentEntity createComment(Long postId, CommentEntity commentEntity);

  // ! Exercise 3 Question 3 - All CRUD
  @Transactional
  CommentEntity updateCommentBody(Long id, CommentReq commentReq);
  // Rest of services for CRUD API

  // ! Don't need @Transactional - Single Write Action
  @Transactional
  void deleteComment(Long commentId);
}