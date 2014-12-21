package com.epam.testingsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.testingsystem.domain.Question;
import com.epam.testingsystem.domain.Test;
import com.epam.testingsystem.repository.QuestionDao;
import com.epam.testingsystem.service.QuestionService;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionDao questionRepository;

	@Override
	public Integer saveQuestion(Question question) {
		return questionRepository.create(question);
	}

	@Override
	public void deleteQuestion(Question question) {
		questionRepository.deleteById(question.getId());
	}

	@Override
	public Question getQuestionById(Integer id) {
		return questionRepository.findById(id);
	}

	@Override
	public List<Question> getQuestions() {
		return questionRepository.findAll();
	}

	@Override
	public void updateQuestion(Question question) {
		questionRepository.update(question);
	}

	@Override
	public List<Question> getQuestionsByTest(Test test) {
		return questionRepository.findQuestionsByTest(test);
	}
}
