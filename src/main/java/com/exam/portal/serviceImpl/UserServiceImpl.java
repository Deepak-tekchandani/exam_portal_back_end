package com.exam.portal.serviceImpl;

import com.exam.portal.entity.UserEntity;
import com.exam.portal.entity.UserRoleEntity;
import com.exam.portal.repository.RoleRepository;
import com.exam.portal.repository.UserRepository;
import com.exam.portal.security.helper.UserFoundException;
import com.exam.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
            throw new UserFoundException();
        }else
        {
            //userCreate
            for(UserRoleEntity ur:userRoleEntities){
                System.out.println(" Service Ur : "+ur);
                roleRepository.save(ur.getRoleEntity());
            }
//            userEntity.getUserRoleEntities().addAll(userRoleEntities);
            userEntity.setUserRoleEntities(userRoleEntities);
//            System.out.println("Service getUserRole : "+userEntity.setUserRoleEntities(userRoleEntities));
            local=userRepository.save(userEntity);

        }

        return local;
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }


}
