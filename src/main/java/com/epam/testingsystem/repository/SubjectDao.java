package com.epam.testingsystem.repository;

import java.util.List;

import com.epam.testingsystem.domain.Subject;
import com.epam.testingsystem.domain.Test;
import com.epam.testingsystem.domain.User;
import com.epam.testingsystem.repository.base.GenericDao;

public interface SubjectDao extends GenericDao<Subject, Integer> {
	void addAdmin(User user, Subject subject);
	
	List<Test> findAllTests(Subject subject);
}
