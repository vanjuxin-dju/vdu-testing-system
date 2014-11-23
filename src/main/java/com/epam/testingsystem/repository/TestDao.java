package com.epam.testingsystem.repository;

import java.util.List;

import com.epam.testingsystem.domain.Question;
import com.epam.testingsystem.domain.Subject;
import com.epam.testingsystem.domain.Test;
import com.epam.testingsystem.repository.base.GenericDao;

public interface TestDao extends GenericDao<Test, Integer> {
	void changeSubject(Test test, Subject subject);
	
	List<Question> findAllQuestions(Test test);
}
