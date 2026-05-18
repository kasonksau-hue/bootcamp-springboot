package com.bootcamp.demo.bc_forum.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.demo.bc_forum.dto.CommentReq;
import com.bootcamp.demo.bc_forum.entity.CommentEntity;
import com.bootcamp.demo.bc_forum.entity.PostEntity;
import com.bootcamp.demo.bc_forum.entity.UserEntity;
import com.bootcamp.demo.bc_forum.repository.CommentRepository;
import com.bootcamp.demo.bc_forum.repository.PostRepository;
import com.bootcamp.demo.bc_forum.repository.UserRepository;
import com.bootcamp.demo.bc_forum.service.Forum2Service;

@Service
public class Forum2ServiceImpl implements Forum2Service {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private PostRepository postRepository;
  @Autowired
  private CommentRepository commentRepository;

  @Override
  public List<UserEntity> getUsers() {
    return this.userRepository.findAll();
  }

  @Override
  public Optional<UserEntity> getUser(Long userId) {
    return this.userRepository.findById(userId);
  }

  @Override
  public UserEntity updateUser(Long userId, UserEntity userEntity) {
    Optional<UserEntity> oUserEntity = this.userRepository.findById(userId);
    if (oUserEntity.isPresent()) {
      return this.userRepository.save(userEntity);
    }
    throw new RuntimeException("User ID Not Found.");
  }

  @Override
  public List<PostEntity> getPosts() {
    return this.postRepository.findAll();
  }

  @Override
  public List<PostEntity> getPosts(Long userId) {
    Optional<UserEntity> oUserEntity = this.userRepository.findById(userId);
    if (oUserEntity.isPresent()) {
      return this.postRepository.findByUserEntity(oUserEntity.get());
    }
    throw new RuntimeException("User ID Not Found.");
  }

  @Override
  public PostEntity createPost(Long userId, PostEntity postEntity) {
    Optional<UserEntity> oUserEntity = this.userRepository.findById(userId);
    if (oUserEntity.isPresent()) {
      return this.postRepository.save(postEntity);
    }
    throw new RuntimeException("User ID Not Found.");
  }

  @Override
  public void deletePost(Long postId) {
    Optional<PostEntity> oPostEntity = this.postRepository.findById(postId);
    if (oPostEntity.isPresent()) {
      this.commentRepository.deleteByPostEntity(oPostEntity.get());
      this.postRepository.deleteById(postId);
      return;
    }
    throw new RuntimeException("Post ID Not Found.");
  }

  @Override
  public List<CommentEntity> getComments() {
    return this.commentRepository.findAll();
  }

  @Override
  public List<CommentEntity> getComments(Long postId) {
    Optional<PostEntity> oPostEntity = this.postRepository.findById(postId);
    if (oPostEntity.isPresent()) {
      return this.commentRepository.findByPostEntity(oPostEntity.get());
    }
    throw new RuntimeException("Post ID Not Found.");
  }

  @Override
  public CommentEntity createComment(Long postId, CommentEntity commentEntity) {
    Optional<PostEntity> oPostEntity = this.postRepository.findById(postId);
    if (oPostEntity.isPresent()) {
      return this.commentRepository.save(commentEntity);
    }
    throw new RuntimeException("Post ID Not Found.");
  }

  @Override
  public CommentEntity updateCommentBody(Long id, CommentReq commentReq) {
    Optional<CommentEntity> oCommentEntity =
        this.commentRepository.findById(id);
    if (oCommentEntity.isPresent()) {
      CommentEntity commentEntity = oCommentEntity.get();
      commentEntity.setBody(commentReq.getBody());
      return this.commentRepository.save(commentEntity); // SQL: Update
    }
    throw new IllegalArgumentException("Comment Not Found.");
  }

  @Override
  public void deleteComment(Long commentId) {
    this.commentRepository.deleteById(commentId);
  }
}