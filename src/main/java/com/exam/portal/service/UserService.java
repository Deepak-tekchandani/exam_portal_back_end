package com.exam.portal.service;

import com.exam.portal.entity.UserEntity;
import com.exam.portal.entity.UserRoleEntity;

import java.util.List;
import java.util.Set;

public interface UserService {

    public UserEntity createUser(UserEntity userEntity, Set<UserRoleEntity> userRoleEntities) throws Exception;

    public UserEntity getUserByUsername(String username);

    public void deleteUser(Long userId);

    List<UserEntity> findAll();
}
