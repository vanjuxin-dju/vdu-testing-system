package com.epam.testingsystem.service;

import java.util.List;

import com.epam.testingsystem.domain.Question;

public interface QuestionService {
	void saveQuestion(Question question);
	
	void deleteQuestion(Question question);
	
	Question getQuestionById(Integer id);
	
	List<Question> getQuestions();
	
	void updateQuestion(Question question);
}
