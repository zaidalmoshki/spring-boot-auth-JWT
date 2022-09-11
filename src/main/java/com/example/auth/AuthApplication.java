package com.example.auth;

import com.example.auth.domain.Role;
import com.example.auth.domain.User;
import com.example.auth.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return  new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService){
		return  args -> {
			userService.saveRole(new Role(null , "ROLE_USER"));
			userService.saveRole(new Role(null , "ROLE_MANAGER"));
			userService.saveRole(new Role(null , "ROLE_ADMIN"));
			userService.saveRole(new Role(null , "ROLE_SUPER_ADMIN"));

			userService.saveUser(new User(null ,"John Travolte" , "john" , "123456" , new ArrayList<>()));
			userService.saveUser(new User(null ,"will smith" , "will" , "123456" , new ArrayList<>()));
			userService.saveUser(new User(null ,"Jim carry" , "jim" , "123456" , new ArrayList<>()));
			userService.saveUser(new User(null ,"Arnold anything" , "arnold" , "123456" , new ArrayList<>()));

			userService.addRoleToUser("john","ROLE_USER");
			userService.addRoleToUser("john","ROLE_MANAGER");
			userService.addRoleToUser("will","ROLE_MANAGER");
			userService.addRoleToUser("jim","ROLE_ADMIN");
			userService.addRoleToUser("Arnold","ROLE_SUPER_ADMIN");
			userService.addRoleToUser("Arnold","ROLE_ADMIN");
			userService.addRoleToUser("Arnold","ROLE_USER");
		};
	}

}
