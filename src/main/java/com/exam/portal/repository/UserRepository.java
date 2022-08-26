package com.exam.portal.repository;

import com.exam.portal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {

    public UserEntity findByUsername(String username);


}
