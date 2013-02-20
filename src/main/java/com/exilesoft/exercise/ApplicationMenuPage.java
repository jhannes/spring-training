package com.exilesoft.exercise;

import javax.inject.Inject;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;


public class ApplicationMenuPage extends WebPage {

	@Inject
	private ApplicationInfo applicationInfo;

	public ApplicationMenuPage() {
		add(new Label("title", getTitle()));
		add(new Label("heading", getTitle()));
	}

	private String getTitle() {
		return applicationInfo.getName() + " :: Menu";
	}
}
