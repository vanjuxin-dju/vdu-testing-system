package com.epam.testingsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.testingsystem.domain.Subject;
import com.epam.testingsystem.repository.SubjectDao;
import com.epam.testingsystem.service.SubjectService;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {
	
	@Autowired
	private SubjectDao subjectRepository;

	@Override
	public void saveSubject(Subject subject) {
		subjectRepository.create(subject);
	}

	@Override
	public void deleteSubject(Subject subject) {
		subjectRepository.deleteById(subject.getId());
	}

	@Override
	public Subject getSubjectById(Integer id) {
		return subjectRepository.findById(id);
	}

	@Override
	public List<Subject> getSubjects() {
		return subjectRepository.findAll();
	}

	@Override
	public void updateSubject(Subject subject) {
		subjectRepository.update(subject);
	}
}
