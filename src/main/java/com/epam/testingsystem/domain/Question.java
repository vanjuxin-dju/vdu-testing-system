package com.epam.testingsystem.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Question")
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idQuestion")
	private Integer id;
	
	@Column(name = "_question")
	private String questionContainer;
	
	@ManyToOne(targetEntity = Test.class, fetch = FetchType.LAZY, 
			cascade = CascadeType.ALL)
	@JoinColumn(name = "idTest")
	private Test test;
	
	@OneToMany(targetEntity = Option.class, mappedBy = "question",
			fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Option> options;

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
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

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}
}
