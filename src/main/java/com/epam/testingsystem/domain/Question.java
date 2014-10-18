package com.epam.testingsystem.domain;

public class Question extends AbstractObject {
	private String questionContainer;
	private Integer testId;

	public String getQuestionContainer() {
		return questionContainer;
	}

	public void setQuestionContainer(String questionContainer) {
		this.questionContainer = questionContainer;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}
}
