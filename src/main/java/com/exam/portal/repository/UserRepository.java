package com.exam.portal.repository;

import com.exam.portal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    public UserEntity findByUsername(String username);

    List<UserEntity> findAll();


}
