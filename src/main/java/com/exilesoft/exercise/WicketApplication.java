package com.exilesoft.exercise;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

public class WicketApplication extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return ApplicationMenuPage.class;
	}

	@Override
	protected void init() {
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
	}

}
