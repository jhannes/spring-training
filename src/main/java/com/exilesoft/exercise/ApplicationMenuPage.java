package com.exilesoft.exercise;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;


public class ApplicationMenuPage extends WebPage {

	public ApplicationMenuPage() {
		add(new Label("title", getTitle()));
		add(new Label("heading", getTitle()));
	}

	private String getTitle() {
		//WebApplicationContext appContext = WebApplicationContextUtils.getRequiredWebApplicationContext(WebApplication.get().getServletContext());
		return null + " :: Menu";
	}
}
