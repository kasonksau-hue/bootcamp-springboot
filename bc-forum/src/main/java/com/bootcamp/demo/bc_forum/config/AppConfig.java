package com.bootcamp.demo.bc_forum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration //Bean
public class AppConfig {

  @Bean
  public RestTemplate restTemplate(){
    //! You can configure your own RestTemplate Object; 
    return new RestTemplate(); 
  }
}
