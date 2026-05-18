package com.bootcamp.demo.bc_forum.controller.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bootcamp.demo.bc_forum.controller.ForumOperation;
import com.bootcamp.demo.bc_forum.dto.UserCommentDTO;
import com.bootcamp.demo.bc_forum.dto.UserDTO;
import com.bootcamp.demo.bc_forum.mapper.DtoMapper;
import com.bootcamp.demo.bc_forum.model.CommentDto;
import com.bootcamp.demo.bc_forum.model.PostDto;
import com.bootcamp.demo.bc_forum.model.UserDto;
import com.bootcamp.demo.bc_forum.service.ForumService;

@RestController // ! Bean
public class ForumController implements ForumOperation {
  @Autowired
  private ForumService forumService;
  @Autowired
  private DtoMapper dtoMapper;

  @Override
  public List<UserDTO> getUsers() {
    List<UserDto> userDtos = this.forumService.getUsers();
    List<PostDto> postDtos = this.forumService.getPosts();
    List<CommentDto> commentDtos = this.forumService.getComments();

    List<UserDTO> userDTOs = new ArrayList<>();
    for (UserDto userDto : userDtos) {
      UserDTO userDTO = this.dtoMapper.map(userDto);

      UserDTO.AddressDTO.GeoDTO geoDTO =
          this.dtoMapper.map(userDto.getAddressDto().getGeoDto());
      UserDTO.AddressDTO addressDTO =
          this.dtoMapper.map(userDto.getAddressDto());

      addressDTO.setGeo(geoDTO);
      userDTO.setAddress(addressDTO);

      UserDTO.CompanyDTO companyDTO =
          this.dtoMapper.map(userDto.getCompanyDto());
      userDTO.setComapany(companyDTO);

      List<UserDTO.PostDTO> postDTOs = new ArrayList<>();
      for (PostDto postDto : postDtos) {
        // ! Find the posts for Current User
        if (postDto.getUserId().equals(userDto.getId())) {
          List<UserDTO.PostDTO.CommentDTO> commentDTOs = new ArrayList<>();
          UserDTO.PostDTO postDTO = this.dtoMapper.map(postDto);
          for (CommentDto commentDto : commentDtos) {
            // ! Find the comments for Current Post
            if (commentDto.getPostId().equals(postDto.getId())) {
              UserDTO.PostDTO.CommentDTO commentDTO =
                  this.dtoMapper.map(commentDto);
              commentDTOs.add(commentDTO);
            }
          }
          postDTO.setComments(commentDTOs);
          postDTOs.add(postDTO);
        }
      }
      userDTO.setPosts(postDTOs);
      userDTOs.add(userDTO);
    }
    return userDTOs;
  }

  @Override
  public UserCommentDTO getCommentsByUserId(String userId) {
    Long uid = Long.valueOf(userId); // NumberFormatException (better to re-throw custom Exception)

    List<UserDto> userDtos = this.forumService.getUsers();
    List<PostDto> postDtos = this.forumService.getPosts();
    List<CommentDto> commentDtos = this.forumService.getComments();

    UserCommentDTO userCommentDTO = null;
    for (UserDto userDto : userDtos) {
      if (uid.equals(userDto.getId())) {
        userCommentDTO = this.dtoMapper.mapToUser(userDto);
        
        List<UserCommentDTO.CommentDTO> commentDTOs = new ArrayList<>();
        for (PostDto postDto : postDtos) {
          if (postDto.getUserId().equals(userDto.getId())) {
            for (CommentDto commentDto : commentDtos) {
              if (commentDto.getPostId().equals(postDto.getId())) {
                UserCommentDTO.CommentDTO commentDTO =
                    this.dtoMapper.mapToUserComment(commentDto);
                commentDTOs.add(commentDTO);
              }
            }
          }
        }
        userCommentDTO.setComments(commentDTOs);
      }
    }
    if (userCommentDTO == null) {
      throw new IllegalArgumentException("User Not Found."); // better to throw custom Exception
    }
    return userCommentDTO;
  }
}