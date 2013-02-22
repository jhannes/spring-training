package com.exilesoft.exercise.company;

import java.util.ArrayList;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

@SuppressWarnings("serial")
public class CreateCompanyPage extends WebPage {

    public class CompanyForm extends Form<Company> {

        public CompanyForm(Company company) {
            super("companyForm", new CompoundPropertyModel<>(company));
            add(new TextField<>("companyName"));
            add(new TextField<>("companyUrl"));
            add(new DropDownChoice<>("companyType", new ArrayList<>()));
        }

        @Override
        protected void onSubmit() {
        	super.onSubmit();
        }

    }

    public CreateCompanyPage() {
        add(new CompanyForm(new Company()));
    }

}
