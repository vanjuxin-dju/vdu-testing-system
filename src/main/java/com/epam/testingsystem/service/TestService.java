package com.epam.testingsystem.service;

import java.util.List;

import com.epam.testingsystem.domain.Subject;
import com.epam.testingsystem.domain.Test;

public interface TestService {
	void saveTest(Test test);
	
	void deleteTest(Test test);
	
	Test getTestById(Integer id);
	
	List<Test> getTests();
	
	void updateTest(Test test);
	
	List<Test> getTestsBySubject(Subject subject);
}
