package com.exam.portal.serviceImpl;

import com.exam.portal.entity.UserEntity;
import com.exam.portal.entity.UserRoleEntity;
import com.exam.portal.repository.RoleRepository;
import com.exam.portal.repository.UserRepository;
import com.exam.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserEntity createUser(UserEntity userEntity, Set<UserRoleEntity> userRoleEntities) throws Exception {

        UserEntity local = this.userRepository.findByUsername(userEntity.getUsername());
        if (local!=null){
            System.out.println("User is Already there !!");
            throw new Exception("User Already Present !!");
        }else
        {
            //userCreate
            for(UserRoleEntity ur:userRoleEntities){
                roleRepository.save(ur.getRoleEntity());
            }
            userEntity.getUserRoleEntities().addAll(userRoleEntities);
            local=this.userRepository.save(userEntity);

        }

        return local;
    }
}
