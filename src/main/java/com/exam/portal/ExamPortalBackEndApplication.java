package com.exam.portal;

import com.exam.portal.entity.RoleEntity;
import com.exam.portal.entity.UserEntity;
import com.exam.portal.entity.UserRoleEntity;
import com.exam.portal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ExamPortalBackEndApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ExamPortalBackEndApplication.class, args);
		System.out.println("--------------- WelCome to Exam Portal -------------");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Start..");
//		UserEntity user = new UserEntity();
//
//		user.setId(11L);
//		user.setFirstname("deep ");
//		user.setLastname("kumar");
//		user.setUsername("Deepak");
//		user.setPassword("lohana");
//		user.setEmail("deepak@gmail.com");
//		user.setProfile("deep.png");
//
//		RoleEntity role1 =new RoleEntity();
//		role1.setId(13L);
//		role1.setRoleName("Admin");
//
//		Set<UserRoleEntity> userRoleSet = new HashSet<>();
//		UserRoleEntity userRole = new UserRoleEntity();
//
//		userRole.setId(123L);
//		userRole.setRoleEntity(role1);
//		userRole.setUserEntity(user);
//
//		userRoleSet.add(userRole);
//
//		UserEntity user1 = this.userService.createUser(user,userRoleSet);
//		System.out.println(user1.getUsername());


	}
}
