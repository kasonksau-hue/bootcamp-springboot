package com.bootcamp.demo.bc_forum.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bootcamp.demo.bc_forum.entity.PostEntity;
import com.bootcamp.demo.bc_forum.entity.UserEntity;


@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
  // Find By FK

  // select * from posts where user_id = ?
  List<PostEntity> findByUserEntity(UserEntity userEntity);

  // delete from posts where user_id = ?
  void deleteByUserEntity(UserEntity userEntity);
}