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
@Table(name = "role")
public class RoleEntity {

    @Id
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    //Relations
    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL , mappedBy = "roleEntity")
    @JsonIgnore
    private Set<UserRoleEntity> userRoleEntities;
}
