package com.greatlearning.week10assignment;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.greatlearning.week10assignment.exception.MailAlreadyExistsException;
import com.greatlearning.week10assignment.model.Item;
import com.greatlearning.week10assignment.model.Role;
import com.greatlearning.week10assignment.model.User;
import com.greatlearning.week10assignment.service.ItemService;
import com.greatlearning.week10assignment.service.UserService;

import lombok.extern.slf4j.Slf4j;

/*
 * PLEASE NOTE THE SAMPLE USER ID AND PASSWORD FOR ADMIN AND USER HAVE BEEN DEFINED BELOW YOU CAN USE THAT TO AUTHENTICATE IN ORDER 
 * TO USE SWAGGER APIS
 * 
 * http://localhost:8889/swagger-ui.html
 */

@SpringBootApplication
@Slf4j
public class AdminMicroServiceApplication  implements CommandLineRunner{
	
	@Autowired
	UserService userService;
	
	@Autowired
	ItemService itemService;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		SpringApplication.run(AdminMicroServiceApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// Creating one sample User and Admin User if not exists

		User adminUser = User.builder().email("admin@gmail.com").firstName("admin").lastName("last")
				.password(bCryptPasswordEncoder.encode("admin"))
				.roles(Arrays.asList(Role.builder().name("ROLE_ADMIN").build())).build();
		User sampleUser = User.builder().email("user@gmail.com").firstName("user").lastName("last")
				.password(bCryptPasswordEncoder.encode("user"))
				.roles(Arrays.asList(Role.builder().name("ROLE_USER").build())).build();
		
		//Saving a new Item on start for testing API's
		Item item = Item.builder().name("Item" + Math.random()).price(100.0).build();
		itemService.save(item);
		
		try {
			userService.save(adminUser);
			userService.save(sampleUser);
		} catch (MailAlreadyExistsException e) {
			log.error("Mail Already exists in Database");
		}
	}

}
