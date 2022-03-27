package com.greatlearning.week12assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * PLEASE NOTE THE SAMPLE USER ID AND PASSWORD FOR ADMIN AND USER HAVE BEEN DEFINED BELOW YOU CAN USE THAT TO AUTHENTICATE IN ORDER 
 * TO USE SWAGGER APIS
 * 
 * http://localhost:8889/swagger-ui.html
 */

@SpringBootApplication
public class AdminMicroServiceApplication{

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		SpringApplication.run(AdminMicroServiceApplication.class, args);
	}

}
