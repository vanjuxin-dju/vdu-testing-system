package com.epam.testingsystem.repository;

import com.epam.testingsystem.domain.Subject;
import com.epam.testingsystem.domain.Test;

public class TestRepository extends AbstractRepository<Test> {
	private static TestRepository instance;

	public static TestRepository getInstance() {
		if (instance == null) {
			instance = new TestRepository();
		}
		return instance;
	}
	
	public void create(String name, Subject subject) {
		Test test = new Test();
		test.setId(ID++);
		test.setName(name);
		test.setSubjectId(subject.getId());
		create(test);
	}
}
