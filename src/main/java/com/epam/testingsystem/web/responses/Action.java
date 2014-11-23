package com.epam.testingsystem.web.responses;

public class Action {
	private boolean ok;
	
	public Action(boolean ok) {
		this.ok = ok;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}
}
