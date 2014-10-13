package com.epam.testingsystem.repository;

import com.epam.testingsystem.domain.Question;
import com.epam.testingsystem.domain.Test;

public class QuestionRepository extends AbstractRepository<Question> {
	private static QuestionRepository instance;

	public static QuestionRepository getInstance() {
		if (instance == null) {
			instance = new QuestionRepository();
		}
		return instance;
	}
	
	public void create(String questionContainer, Test test) {
		Question question = new Question();
		question.setId(ID++);
		question.setQuestionContainer(questionContainer);
		question.setTestId(test.getId());
		create(question);
	}
}
