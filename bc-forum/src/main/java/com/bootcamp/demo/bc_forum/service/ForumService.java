package com.bootcamp.demo.bc_forum.service;

import java.util.List;
import com.bootcamp.demo.bc_forum.entity.CommentEntity;
import com.bootcamp.demo.bc_forum.entity.PostEntity;
import com.bootcamp.demo.bc_forum.entity.UserEntity;
import com.bootcamp.demo.bc_forum.model.CommentDto;
import com.bootcamp.demo.bc_forum.model.PostDto;
import com.bootcamp.demo.bc_forum.model.UserDto;

public interface ForumService {
  List<UserDto> getUsers();

  List<PostDto> getPosts();

  List<CommentDto> getComments();

  // ! Save DB for CommandLineRunner
  List<UserEntity> insertUsers(List<UserEntity> userEntities);

  List<PostEntity> insertPosts(List<PostEntity> postEntities);

  List<CommentEntity> insertComments(List<CommentEntity> commentEntities);

  UserEntity insertUser(UserEntity userEntity);

  PostEntity insertPost(PostEntity postEntity);

  CommentEntity insertComment(CommentEntity commentEntity);

  void deleteAllUsers();
  void deleteAllPosts();
  void deleteAllComments();
}