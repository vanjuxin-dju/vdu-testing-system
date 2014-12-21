package com.epam.testingsystem.web.responses;

import java.util.List;

import com.epam.testingsystem.domain.Test;

public class TestWrapper {
	private Integer id;
	private String name;
	
	public TestWrapper(Test test) {
		this.id = test.getId();
		this.name = test.getName();
	}
	
	public static TestWrapper[] tests(List<Test> tests) {
		TestWrapper[] result = new TestWrapper[tests.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = new TestWrapper(tests.get(i));
		}
		return result;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
