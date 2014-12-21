package com.epam.testingsystem.web.responses;

import java.util.List;

import com.epam.testingsystem.domain.Option;
import com.epam.testingsystem.domain.Question;

public class QuestionWrapper {
	private Integer id;
	private String questionContainer;
	private OptionWrapper[] options;
	
	public QuestionWrapper(Question question, boolean isAdmin) {
		this.id = question.getId();
		this.questionContainer = question.getQuestionContainer();
		List<Option> opts = question.getOptions();
		this.options = new OptionWrapper[opts.size()];
		for (int i = 0; i < this.options.length; i++) {
			this.options[i] = new OptionWrapper(opts.get(i), isAdmin);
		}
	}
	
	public static QuestionWrapper[] questions(List<Question> questions, boolean isAdmin) {
		QuestionWrapper[] result = new QuestionWrapper[questions.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = new QuestionWrapper(questions.get(i), isAdmin);
		}
		return result;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getQuestionContainer() {
		return questionContainer;
	}
	
	public void setQuestionContainer(String questionContainer) {
		this.questionContainer = questionContainer;
	}
	
	public OptionWrapper[] getOptions() {
		return options;
	}
	
	public void setOptions(OptionWrapper[] options) {
		this.options = options;
	}
}
