package com.bootcamp.demo.bc_forum.controller.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.bc_forum.controller.Forum2Operation;
import com.bootcamp.demo.bc_forum.dto.CommentReq;
import com.bootcamp.demo.bc_forum.entity.CommentEntity;
import com.bootcamp.demo.bc_forum.entity.PostEntity;
import com.bootcamp.demo.bc_forum.entity.UserEntity;
import com.bootcamp.demo.bc_forum.service.Forum2Service;

@RestController
public class Forum2Controller implements Forum2Operation {
  @Autowired
  private Forum2Service forum2Service;

  @Override
  public List<UserEntity> getUsers() {
    return this.forum2Service.getUsers();
  }

  @Override
  public UserEntity getUser(Long userId) {
    return this.forum2Service.getUser(userId)
        .orElseThrow(() -> new RuntimeException("User ID Not Found."));
  }

  @Override
  public UserEntity updateUser(Long userId, UserEntity userEntity) {
    return this.forum2Service.updateUser(userId, userEntity);
  }

  @Override
  public List<PostEntity> getPosts() {
    return this.forum2Service.getPosts();
  }

  @Override
  public List<PostEntity> getPosts(Long userId) {
    return this.forum2Service.getPosts(userId);
  }

  @Override
  public PostEntity createPost(Long userId, PostEntity postEntity) {
    return this.forum2Service.createPost(userId, postEntity);
  }

  @Override
  public void deletePost(Long postId) {
    this.forum2Service.deletePost(postId);
  }

  @Override
  public List<CommentEntity> getComments() {
    return this.forum2Service.getComments();
  }

  @Override
  public List<CommentEntity> getComments(Long postId) {
    return this.forum2Service.getComments(postId);
  }

  @Override
  public CommentEntity createComment(Long postId, CommentEntity commentEntity) {
    return this.forum2Service.createComment(postId, commentEntity);
  }

  @Override
  public CommentEntity updateCommentBody(Long commentId,
      CommentReq commentReq) {
    return this.forum2Service.updateCommentBody(commentId, commentReq);
  }

  @Override
  public void deleteComment(@PathVariable Long commentId) {
    this.forum2Service.deleteComment(commentId);
  }
}