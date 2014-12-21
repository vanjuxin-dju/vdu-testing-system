package com.epam.testingsystem.web.responses;

import com.epam.testingsystem.domain.Option;

public class OptionWrapper {
	private Integer id;
	private String optionContent;
	private Boolean correct;
	
	public OptionWrapper(Option option, boolean isAdmin) {
		this.id = option.getId();
		this.optionContent = option.getOptionContent();
		if (isAdmin) {
			this.correct = option.getCorrect();
		} else {
			this.correct = null;
		}
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getOptionContent() {
		return optionContent;
	}
	
	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}
	
	public Boolean getCorrect() {
		return correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}
}
