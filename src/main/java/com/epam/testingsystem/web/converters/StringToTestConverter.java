package com.epam.testingsystem.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.epam.testingsystem.domain.Test;
import com.epam.testingsystem.service.TestService;

public class StringToTestConverter implements Converter<String, Test> {
	@Autowired
	private TestService testService;
	
	@Override
	public Test convert(String id) {
		return testService.getTestById(new Integer(id));
	}

}
