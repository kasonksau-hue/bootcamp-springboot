package com.bootcamp.demo.bc_forum.config;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.bootcamp.demo.bc_forum.entity.CommentEntity;
import com.bootcamp.demo.bc_forum.entity.PostEntity;
import com.bootcamp.demo.bc_forum.entity.UserEntity;
import com.bootcamp.demo.bc_forum.mapper.EntityMapper;
import com.bootcamp.demo.bc_forum.model.CommentDto;
import com.bootcamp.demo.bc_forum.model.PostDto;
import com.bootcamp.demo.bc_forum.model.UserDto;
import com.bootcamp.demo.bc_forum.service.ForumService;

@Component
public class AppStarter implements CommandLineRunner {
  @Autowired
  private ForumService forumService;
  @Autowired
  private EntityMapper entityMapper;


  @Override
  public void run(String... args) throws Exception {
    // ! Clear All Data
    this.forumService.deleteAllComments();
    this.forumService.deleteAllPosts();
    this.forumService.deleteAllUsers();

    // ! Call data from JSON PlaceHolder
    List<UserDto> userDtos = this.forumService.getUsers();
    List<PostDto> postDtos = this.forumService.getPosts();
    List<CommentDto> commentDtos = this.forumService.getComments();

    // ! Insert Data into DB
    for (UserDto userDto : userDtos) {
      UserEntity userEntity = this.entityMapper.map(userDto);
      this.forumService.insertUser(userEntity);

      for (PostDto postDto : postDtos) {
        // ! Find the posts for Current User
        if (postDto.getUserId().equals(userDto.getId())) {
          PostEntity postEntity = this.entityMapper.map(postDto);
          postEntity.setUserEntity(userEntity); // FK
          this.forumService.insertPost(postEntity);

          for (CommentDto commentDto : commentDtos) {
            // ! Find the comments for Current Post
            if (commentDto.getPostId().equals(postDto.getId())) {
              CommentEntity commentEntity = this.entityMapper.map(commentDto);
              commentEntity.setPostEntity(postEntity); // FK
              this.forumService.insertComment(commentEntity);
            }
          }
        }
      }
    }
  }
}