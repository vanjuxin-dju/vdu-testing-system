package com.epam.testingsystem.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.epam.testingsystem.domain.User;
import com.epam.testingsystem.service.UserService;

@Component
public class StringToUserConverter implements Converter<String, User> {
	@Autowired
	private UserService userService;

	@Override
	public User convert(String idUser) {
		return userService.getUserById(new Integer(idUser));
	}
}
