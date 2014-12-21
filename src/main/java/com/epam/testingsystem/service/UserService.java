package com.epam.testingsystem.service;

import com.epam.testingsystem.domain.User;

public interface UserService {
	void saveUser(User user);
	
	User loadUser(String login, String password);
	
	User getUserById(Integer id);
	
	void updateUser(User user);
}
