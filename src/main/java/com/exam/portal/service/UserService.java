package com.exam.portal.service;

import com.exam.portal.entity.UserEntity;
import com.exam.portal.entity.UserRoleEntity;

import java.util.Set;

public interface UserService {

    public UserEntity createUser(UserEntity userEntity, Set<UserRoleEntity> userRoleEntities) throws Exception;
}
