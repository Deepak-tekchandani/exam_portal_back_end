package com.exam.portal.controller;

import com.exam.portal.entity.RoleEntity;
import com.exam.portal.entity.UserEntity;
import com.exam.portal.entity.UserRoleEntity;
import com.exam.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/")
    public UserEntity create(@RequestBody UserEntity userEntity) throws Exception {

        userEntity.setProfile("default.png");
        //encodeing the password by BCryptPasswordEncoder;
        userEntity.setPassword(this.passwordEncoder.encode(userEntity.getPassword()));

        System.out.println("userEntity : "+userEntity);

        Set<UserRoleEntity> roles = new HashSet<>();
        RoleEntity role = new RoleEntity();
        role.setId(114L);
        role.setRoleName("NORMAL");

        UserRoleEntity userRole = new UserRoleEntity();
        System.out.println("Controller UserEntity : "+userEntity);
        userRole.setUserEntity(userEntity);
        userRole.setRoleEntity(role);

        roles.add(userRole);
        System.out.println(" Controller Roles : "+roles);

        return userService.createUser(userEntity,roles);
    }

    @GetMapping("/{username}")
    public UserEntity getUserByUsername(@PathVariable("username") String username){
        return this.userService.getUserByUsername(username);

    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") Long userId){
        this.userService.deleteUser(userId);
        return "Deleted";
    }

    @GetMapping("/getAll")
    public List<UserEntity> getAll(){
        List<UserEntity> userEntities =userService.findAll();
        return userEntities;
    }


}
