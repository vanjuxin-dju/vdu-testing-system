package com.epam.testingsystem.service;

import java.util.List;

import com.epam.testingsystem.domain.Subject;

public interface SubjectService {
	void saveSubject(Subject subject);
	
	void deleteSubject(Subject subject);
	
	Subject getSubjectById(Integer id);
	
	List<Subject> getSubjects();
	
	void updateSubject(Subject subject);
}
