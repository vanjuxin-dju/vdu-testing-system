package com.epam.testingsystem.service;

import java.util.List;

import com.epam.testingsystem.domain.Option;
import com.epam.testingsystem.domain.User;

public interface OptionService {
	void saveOption(Option option);
	
	void deleteOption(Option option);
	
	Option getOptionById(Integer id);
	
	List<Option> getOptions();
	
	void updateOption(Option option);
	
	void selectOption(Option option, User user);
}
