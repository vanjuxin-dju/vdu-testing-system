package com.epam.testingsystem.repository;

import com.epam.testingsystem.domain.User;
import com.epam.testingsystem.repository.base.GenericDao;

public interface UserDao extends GenericDao<User, Integer> {
	User findByCredentials(String login, String password);
}
