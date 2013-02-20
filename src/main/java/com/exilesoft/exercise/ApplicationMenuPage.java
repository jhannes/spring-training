package com.exilesoft.exercise;

import javax.servlet.ServletContext;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.protocol.http.WebApplication;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.exilesoft.training.ApplicationInfo;

public class ApplicationMenuPage extends WebPage {

	public ApplicationMenuPage() {
		add(new Label("title", getTitle()));
		add(new Label("heading", getTitle()));
	}

	private String getTitle() {
		ServletContext context = WebApplication.get().getServletContext();
		WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		ApplicationInfo applicationInfo = applicationContext.getBean(ApplicationInfo.class);

		return applicationInfo.getName() + " :: Menu";
	}
}
