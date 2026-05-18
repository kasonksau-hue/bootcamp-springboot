package com.bootcamp.demo.bc_forum.service.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.bootcamp.demo.bc_forum.entity.CommentEntity;
import com.bootcamp.demo.bc_forum.entity.PostEntity;
import com.bootcamp.demo.bc_forum.entity.UserEntity;
import com.bootcamp.demo.bc_forum.model.CommentDto;
import com.bootcamp.demo.bc_forum.model.PostDto;
import com.bootcamp.demo.bc_forum.model.UserDto;
import com.bootcamp.demo.bc_forum.repository.CommentRepository;
import com.bootcamp.demo.bc_forum.repository.PostRepository;
import com.bootcamp.demo.bc_forum.repository.UserRepository;
import com.bootcamp.demo.bc_forum.service.ForumService;

@Service
public class ForumServiceImpl implements ForumService {


  private final CommentRepository commentRepository;
  private final PostRepository postRepository;
  private final UserRepository userRepository;
  @Autowired
  private RestTemplate restTemplate;

  ForumServiceImpl(UserRepository userRepository, PostRepository postRepository,
      CommentRepository commentRepository) {
    this.userRepository = userRepository;
    this.postRepository = postRepository;
    this.commentRepository = commentRepository;
  }

  // ! Beans (error if the path not exists in yml)
  @Value("${api.jsonplaceholder.domain}")
  private String domain;
  @Value("${api.jsonplaceholder.path.user}")
  private String userPath;
  @Value("${api.jsonplaceholder.path.post}")
  private String postPath;
  @Value("${api.jsonplaceholder.path.comment}")
  private String commentPath;

  public List<UserDto> getUsers() {
    String url = UriComponentsBuilder.newInstance() //
        .scheme("https") //
        .host(this.domain) //
        // .pathSegment("/v1") // ! api version if any
        .path(this.userPath) //
        .build() //
        .toUriString(); //
    System.out.println("url=" + url);
    UserDto[] userDtos = this.restTemplate.getForObject(url, UserDto[].class);
    return Arrays.asList(userDtos);
  }

  public List<PostDto> getPosts() {
    String url = UriComponentsBuilder.newInstance() //
        .scheme("https") //
        .host(this.domain) //
        .path(this.postPath) //
        .build() //
        .toUriString(); //
    System.out.println("url=" + url);
    PostDto[] userDtos = this.restTemplate.getForObject(url, PostDto[].class);
    return Arrays.asList(userDtos);
  }

  public List<CommentDto> getComments() {
    String url = UriComponentsBuilder.newInstance() //
        .scheme("https") //
        .host(this.domain) //
        .path(this.commentPath) //
        .build() //
        .toUriString(); //
    System.out.println("url=" + url);
    CommentDto[] userDtos =
        this.restTemplate.getForObject(url, CommentDto[].class);
    return Arrays.asList(userDtos);
  }

  @Override
  public List<UserEntity> insertUsers(List<UserEntity> userEntities) {
    return this.userRepository.saveAll(userEntities);
  }

  @Override
  public List<PostEntity> insertPosts(List<PostEntity> postEntities) {
    return this.postRepository.saveAll(postEntities);
  }

  @Override
  public List<CommentEntity> insertComments(
      List<CommentEntity> commentEntities) {
    return this.commentRepository.saveAll(commentEntities);
  }

  @Override
  public UserEntity insertUser(UserEntity userEntity) {
    return this.userRepository.save(userEntity);
  }

  @Override
  public PostEntity insertPost(PostEntity postEntity) {
    return this.postRepository.save(postEntity);
  }

  @Override
  public CommentEntity insertComment(CommentEntity commentEntity) {
    return this.commentRepository.save(commentEntity);
  }

  @Override
  public void deleteAllUsers() {
    this.userRepository.deleteAll();
  }

  @Override
  public void deleteAllPosts() {
    this.postRepository.deleteAll();
  }

  @Override
  public void deleteAllComments() {
    this.commentRepository.deleteAll();
  }
}