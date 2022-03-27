package com.greatlearning.week12assignment.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.greatlearning.week12assignment.model.User;

public interface UserService extends UserDetailsService {

	User findByEmail(String email);

	User save(User user);

	List<User> getUsers();

	User updateUser(String email, User updatedUser);

	void deleteUser(String email);

}
