package com.bootcamp.demo.bc_forum.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.demo.bc_forum.entity.CommentEntity;
import com.bootcamp.demo.bc_forum.entity.PostEntity;
import com.bootcamp.demo.bc_forum.entity.UserEntity;
import com.bootcamp.demo.bc_forum.model.CommentDto;
import com.bootcamp.demo.bc_forum.model.PostDto;
import com.bootcamp.demo.bc_forum.model.UserDto;

//! Mapper can skip FK conversion
@Component
public class EntityMapper {
  public UserEntity map(UserDto userDto) {
    return UserEntity.builder() //
   // .id(userDto.getId())
    .name(userDto.getName())
    .username(userDto.getUsername())
    .email(userDto.getEmail())
    .phone(userDto.getPhone())
    .website(userDto.getWebsite())
        .build();
  }

  public PostEntity map(PostDto postDto) {
    return PostEntity.builder() //
   // .id(postDto.getId())
    .title(postDto.getTitle())
    .body(postDto.getBody())
        .build();
  }

  public CommentEntity map(CommentDto commentDto) {
    return CommentEntity.builder() //
   // .id(commentDto.getId())
    .name(commentDto.getName())
    .email(commentDto.getEmail())
    .body(commentDto.getBody())
        .build();
  }
}