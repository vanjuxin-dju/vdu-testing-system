package com.epam.testingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.testingsystem.domain.User;
import com.epam.testingsystem.repository.UserDao;
import com.epam.testingsystem.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userRepository;

	@Override
	public void saveUser(User user) {
		userRepository.create(user);
	}

	@Override
	public User loadUser(String login, String password) {
		return userRepository.findByCredentials(login, password);
	}

	@Override
	public User getUserById(Integer id) {
		return userRepository.findById(id);
	}

	@Override
	public void logOutUser(Integer id) {
		// TODO add method to logout user
		
	}
	
}
