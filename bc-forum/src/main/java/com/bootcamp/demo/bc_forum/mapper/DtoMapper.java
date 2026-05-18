package com.bootcamp.demo.bc_forum.mapper;

import org.springframework.stereotype.Component;
import com.bootcamp.demo.bc_forum.dto.UserCommentDTO;
import com.bootcamp.demo.bc_forum.dto.UserDTO;
import com.bootcamp.demo.bc_forum.model.CommentDto;
import com.bootcamp.demo.bc_forum.model.PostDto;
import com.bootcamp.demo.bc_forum.model.UserDto;

@Component
public class DtoMapper {

  // User
  public UserDTO map(UserDto userDto) {
    return UserDTO.builder() //
        .id(userDto.getId())
        .name(userDto.getName())
        .username(userDto.getUsername()) 
        .website(userDto.getWebsite())
        .phone(userDto.getPhone())
        .email(userDto.getEmail())
        .build();
  }
  // Geo
  public UserDTO.AddressDTO.GeoDTO map(UserDto.AddressDto.GeoDto geoDto) {
    return UserDTO.AddressDTO.GeoDTO.builder() //
        .lat(geoDto.getLat())//
        .lng(geoDto.getLng()) //
        .build();
  }
  // Address
  public UserDTO.AddressDTO map(UserDto.AddressDto addressDto) {
    return UserDTO.AddressDTO.builder() //
        .city(addressDto.getCity())
        .street(addressDto.getStreet())
        .suite(addressDto.getSuite())
        .zipcode(addressDto.getZipcode())
        .build();
  } 
  // Company
  public UserDTO.CompanyDTO map(UserDto.CompanyDto companyDto) {
    return UserDTO.CompanyDTO.builder() //
        .bs(companyDto.getBs())
        .name(companyDto.getName())
        .catchPhrase(companyDto.getCatchPhrase())
        .build();
  } 
  // Post
  public UserDTO.PostDTO map(PostDto postDto) {
    return UserDTO.PostDTO.builder() //
        .title(postDto.getTitle())
        .body(postDto.getBody())
        .id(postDto.getId())
        .build();
  } 
  // Comment
  public UserDTO.PostDTO.CommentDTO map(CommentDto commentDto) {
    return UserDTO.PostDTO.CommentDTO.builder() //
        .email(commentDto.getEmail())
        .body(commentDto.getBody())
        .name(commentDto.getName())
        .id(commentDto.getId())
        .build();
  } 

  // ! Question2
  // UserCommentDTO
  public UserCommentDTO mapToUser(UserDto userDto) {
    return UserCommentDTO.builder() //
      .username(userDto.getUsername())
      .id(userDto.getId())
      .build();
  }
  // CommentDTO
  public UserCommentDTO.CommentDTO mapToUserComment(CommentDto commentDto) {
    return UserCommentDTO.CommentDTO.builder() //
      .email(commentDto.getEmail())
      .name(commentDto.getName())
      .body(commentDto.getBody())
      .build();
  }
}