package com.epam.testingsystem.repository;

import java.util.List;

import com.epam.testingsystem.domain.Question;
import com.epam.testingsystem.domain.Test;
import com.epam.testingsystem.repository.base.GenericDao;

public interface QuestionDao extends GenericDao<Question, Integer> {
	void changeTest(Question question, Test test);
	
	List<Question> findQuestionsByTest(Test test);
}
