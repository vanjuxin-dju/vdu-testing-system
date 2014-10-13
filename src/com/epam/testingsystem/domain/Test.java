package com.epam.testingsystem.domain;

public class Test extends AbstractObject {
	private String name;
	private Integer subjectId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
}
