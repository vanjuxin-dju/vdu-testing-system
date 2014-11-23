package com.epam.testingsystem.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import com.epam.testingsystem.domain.Subject;
import com.epam.testingsystem.service.SubjectService;

public class StringToSubjectConverter implements Converter<String, Subject> {
	@Autowired
	private SubjectService subjectService;

	@Override
	public Subject convert(String id) {
		return subjectService.getSubjectById(new Integer(id));
	}
}
