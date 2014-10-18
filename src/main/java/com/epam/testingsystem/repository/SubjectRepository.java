package com.epam.testingsystem.repository;

import com.epam.testingsystem.domain.Subject;

public class SubjectRepository extends AbstractRepository<Subject> {
	private static SubjectRepository instance;

	public static SubjectRepository getInstance() {
		if (instance == null) {
			instance = new SubjectRepository();
		}
		return instance;
	}
	
	public void create(String name) {
		Subject subject = new Subject();
		subject.setId(ID++);
		subject.setName(name);
		create(subject);
	}
}
