package com.exilesoft.exercise;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.exilesoft.exercise.company.CreateCompanyPage;
import com.exilesoft.exercise.company.ListCompaniesPage;

public class WicketApplication extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return ApplicationMenuPage.class;
	}

	@Override
	protected void init() {
	    mountPage("company/create.html", CreateCompanyPage.class);
	    mountPage("company/index.html", ListCompaniesPage.class);

		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
	}

}
