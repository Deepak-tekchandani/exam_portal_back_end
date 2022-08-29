package com.exam.portal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "profile")
    private String profile;

    @Column(name = "enable")
    private Boolean enable=true;

    //Relations

    @OneToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL , mappedBy = "userEntity")
    @JsonIgnore
    private Set<UserRoleEntity> userRoleEntities;


}
