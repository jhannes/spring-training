package com.exilesoft.exercise;

import org.springframework.stereotype.Component;


@Component
public class ApplicationInfo {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
