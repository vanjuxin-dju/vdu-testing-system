package com.epam.testingsystem.repository;

import com.epam.testingsystem.domain.Option;
import com.epam.testingsystem.domain.Question;

public class OptionRepository extends AbstractRepository<Option> {
	private static OptionRepository instance;

	public static OptionRepository getInstance() {
		if (instance == null) {
			instance = new OptionRepository();
		}
		return instance;
	}
	
	public void create(String optionContent, boolean correct, Question question) {
		Option option = new Option();
		option.setId(ID++);
		option.setOptionContent(optionContent);
		option.setCorrect(correct);
		option.setQuestionId(question.getId());
		create(option);
	}
}
