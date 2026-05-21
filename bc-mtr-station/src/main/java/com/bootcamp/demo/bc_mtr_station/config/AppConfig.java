package com.bootcamp.demo.bc_mtr_station.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.client.RestTemplate;
import com.bootcamp.demo.bc_mtr_station.lib.RedisHelper;
import tools.jackson.databind.ObjectMapper;

@Configuration
public class AppConfig {
  @Bean
  RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }

  // ! pom add dependency -> Default Spring Bean -> RedisConnectionFactory
  // Key, Value
  // @Bean
  // RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
  // RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
  // redisTemplate.setConnectionFactory(factory);
  // redisTemplate.setKeySerializer(RedisSerializer.string());
  // redisTemplate.setValueSerializer(RedisSerializer.json());
  // redisTemplate.afterPropertiesSet();
  // return redisTemplate;
  // }

  @Bean
  RedisHelper redisHelper(RedisConnectionFactory factory,
      ObjectMapper objectMapper) {
    return new RedisHelper(factory, objectMapper);
  }
}