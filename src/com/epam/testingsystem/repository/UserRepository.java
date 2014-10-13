package com.epam.testingsystem.repository;

import com.epam.testingsystem.domain.User;

public class UserRepository extends AbstractRepository<User> {
	private static UserRepository instance;

	public static UserRepository getInstance() {
		if (instance == null) {
			instance = new UserRepository();
		}
		return instance;
	}
	
	public void create(String nickName, String password, String name) {
		User user = new User();
		user.setId(ID++);
		user.setNickName(nickName);
		user.setPassword(password);
		user.setName(name);
		user.setRole("user");
		create(user);
	}
}
