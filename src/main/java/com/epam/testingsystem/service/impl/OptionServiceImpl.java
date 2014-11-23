package com.epam.testingsystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.testingsystem.domain.Option;
import com.epam.testingsystem.domain.User;
import com.epam.testingsystem.repository.OptionDao;
import com.epam.testingsystem.service.OptionService;

@Service
@Transactional
public class OptionServiceImpl implements OptionService {
	
	@Autowired
	private OptionDao optionRepository;

	@Override
	public void saveOption(Option option) {
		optionRepository.create(option);
	}

	@Override
	public void deleteOption(Option option) {
		optionRepository.deleteById(option.getId());
	}

	@Override
	public Option getOptionById(Integer id) {
		return optionRepository.findById(id);
	}

	@Override
	public List<Option> getOptions() {
		return optionRepository.findAll();
	}

	@Override
	public void updateOption(Option option) {
		optionRepository.update(option);
	}

	@Override
	public void selectOption(Option option, User user) {
		optionRepository.selectOption(option, user);
	}
}
