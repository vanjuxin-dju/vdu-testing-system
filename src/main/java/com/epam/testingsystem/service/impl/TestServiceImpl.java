package com.epam.testingsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.testingsystem.domain.Subject;
import com.epam.testingsystem.domain.Test;
import com.epam.testingsystem.repository.TestDao;
import com.epam.testingsystem.service.TestService;

@Service
@Transactional
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestDao testRepository;

	@Override
	public void saveTest(Test test) {
		testRepository.create(test);
	}

	@Override
	public void deleteTest(Test test) {
		testRepository.deleteById(test.getId());
	}

	@Override
	public Test getTestById(Integer id) {
		return testRepository.findById(id);
	}

	@Override
	public List<Test> getTests() {
		return testRepository.findAll();
	}

	@Override
	public void updateTest(Test test) {
		testRepository.update(test);
	}

	@Override
	public List<Test> getTestsBySubject(Subject subject) {
		return testRepository.findTestsBySubject(subject);
	}
}
