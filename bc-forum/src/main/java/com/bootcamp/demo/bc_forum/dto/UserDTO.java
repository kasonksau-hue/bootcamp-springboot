package com.bootcamp.demo.bc_forum.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class UserDTO {
  private Long id;
  private String name;
  private String username;
  private String email;
  @Setter
  private AddressDTO address;
  private String phone;
  private String website;
  @Setter
  private CompanyDTO comapany;
  @Setter
  private List<PostDTO> posts;

  @Getter
  @Builder
  public static class PostDTO {
    private Long id;
    private String title;
    private String body;
    @Setter
    private List<CommentDTO> comments;

    @Getter
    @Builder
    public static class CommentDTO {
      private Long id;
      private String name;
      private String email;
      private String body;
    }
  }

  @Getter
  @Builder
  public static class AddressDTO {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    @Setter
    private GeoDTO geo;

    @Getter
    @Builder
    public static class GeoDTO {
      private String lat;
      private String lng;
    }
  }

  @Getter
  @Builder
  public static class CompanyDTO {
    private String name;
    private String catchPhrase;
    private String bs;
  }
}