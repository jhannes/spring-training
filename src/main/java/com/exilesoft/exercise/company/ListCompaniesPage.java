package com.exilesoft.exercise.company;

import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;

public class ListCompaniesPage extends WebPage {

    @Inject
    private CompanyRepository repository;

    public ListCompaniesPage() {
    	this(null);
	}

    public ListCompaniesPage(List<Company> list) {
    	if (list == null) list = repository.list();
        add(new ListView<Company>("companies", list) {

            @Override
            protected void populateItem(ListItem<Company> item) {
                Company object = item.getModelObject();

                item.add(CompanyPage.link("url", object.getId(), object.getCompanyName()));
                item.add(new ExternalLink("companyUrl", object.getCompanyUrl(), object.getCompanyUrl()));
                item.add(new Label("companyType", object.getCompanyType().getTypeName()));
                item.add(new Label("employeeCount", object.getEmployeeCount()));
            }
        });

        final Model<String> queryModel = new Model<>();
        add(new Form<String>("companySearch") {
            {
                add(new TextField<>("nameQuery", queryModel));
            }

            @Override
            protected void onSubmit() {
            	setResponsePage(new ListCompaniesPage(repository.findByName(queryModel.getObject())));
            }
        });
    }


}
