package com.epam.testingsystem.repository;

import com.epam.testingsystem.domain.Option;
import com.epam.testingsystem.domain.User;
import com.epam.testingsystem.repository.base.GenericDao;

public interface OptionDao extends GenericDao<Option, Integer> {
	void selectOption(Option option, User user);
}
