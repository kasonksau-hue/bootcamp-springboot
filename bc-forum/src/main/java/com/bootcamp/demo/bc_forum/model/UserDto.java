package com.bootcamp.demo.bc_forum.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UserDto {
  private Long id;
  private String name;
  private String username;
  private String email;
  @JsonProperty(value = "address")
  private AddressDto addressDto;
  private String phone;
  private String website;
  @JsonProperty(value = "company")
  private CompanyDto companyDto;

  @Getter
  public static class AddressDto {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    @JsonProperty(value = "geo")
    private GeoDto geoDto; 

    @Getter
    public static class GeoDto {
      private String lat;
      private String lng;
    }
  }

  @Getter
  public static class CompanyDto {
    private String name;
    private String catchPhrase;
    private String bs;
  }
}