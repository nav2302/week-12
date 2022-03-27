package com.greatlearning.week10assignment.controller;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greatlearning.week10assignment.config.SwaggerConfig;
import com.greatlearning.week10assignment.model.Role;
import com.greatlearning.week10assignment.model.User;
import com.greatlearning.week10assignment.model.UserDto;
import com.greatlearning.week10assignment.response.UserResponse;
import com.greatlearning.week10assignment.service.UserService;

import io.swagger.annotations.Api;

@Api(tags = { SwaggerConfig.RegisterController_TAG })
@RestController
@RequestMapping("/api")
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PostMapping(value = "/register")
	public ResponseEntity<UserResponse> registerUser(@Valid @RequestBody UserDto user) {
		User savedUser = userService.save(User.builder().firstName(user.getFirstName()).lastName(user.getLastName())
				.email(user.getEmail())
				.password(bCryptPasswordEncoder.encode(user.getPassword()))
				.roles(Arrays.asList(Role.builder().name("ROLE_USER").build()))
				.build());
		return new ResponseEntity<>(UserResponse.builder().firstName(savedUser.getFirstName())
				.lastName(savedUser.getLastName()).email(savedUser.getEmail()).build(), HttpStatus.CREATED);
	}

}
