package com.exilesoft.exercise.company;

import javax.inject.Inject;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;

import com.exilesoft.exercise.ApplicationMenuPage;
import com.exilesoft.exercise.company.type.CompanyTypeRepository;

@SuppressWarnings("serial")
public class CreateCompanyPage extends WebPage {

    @Inject
    private CompanyTypeRepository companyTypeRepository;

    @Inject
    private CompanyRepository companyRepository;


    public class CompanyForm extends Form<Company> {

        public CompanyForm(Company company) {
            super("companyForm", new CompoundPropertyModel<>(company));
            add(new TextField<>("companyName"));
            add(new TextField<>("companyUrl"));
            add(new DropDownChoice<>("companyType", companyTypeRepository.list(), new ChoiceRenderer<>("typeName", "id")));
        }

        @Override
        protected void onSubmit() {
            companyRepository.create(getModelObject());
            setResponsePage(ApplicationMenuPage.class);
        }

    }

    public CreateCompanyPage() {
        add(new CompanyForm(new Company()));
    }

}
