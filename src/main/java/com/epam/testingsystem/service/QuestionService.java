package com.epam.testingsystem.service;

import java.util.List;

import com.epam.testingsystem.domain.Question;
import com.epam.testingsystem.domain.Test;

public interface QuestionService {
	Integer saveQuestion(Question question);
	
	void deleteQuestion(Question question);
	
	Question getQuestionById(Integer id);
	
	List<Question> getQuestions();
	
	void updateQuestion(Question question);
	
	List<Question> getQuestionsByTest(Test test);
}
