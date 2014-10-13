package com.epam.testingsystem.repository;

import java.util.ArrayList;
import java.util.List;

import com.epam.testingsystem.domain.Option;
import com.epam.testingsystem.domain.User;
import com.epam.testingsystem.domain.UserOption;

public class UserOptionRepository {
	private static UserOptionRepository instance;

	public static UserOptionRepository getInstance() {
		if (instance == null) {
			instance = new UserOptionRepository();
		}
		return instance;
	}
	
	private List<UserOption> userOptions;
	
	public UserOptionRepository() {
		userOptions = new ArrayList<UserOption>();
	}
	
	public void create(User user, Option option) {
		UserOption userOption = new UserOption();
		userOption.setIdUser(user.getId());
		userOption.setIdOption(option.getId());
		create(userOption);
	}
	
	public void create(UserOption userOption) {
		if (userOption != null) {
			userOptions.add(userOption);
		}
	}
	
	public UserOption[] allUserOptions() {
		return userOptions.toArray(new UserOption[userOptions.size()]);
	}
}
