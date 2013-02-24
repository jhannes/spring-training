package com.exilesoft.exercise.person;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

import com.exilesoft.exercise.company.CompanyRepository;

public class PersonFormPanel extends Panel {

    @Inject
    private PersonRepository personRepository;

    @Inject
    private CompanyRepository companyRepository;

    public class PersonForm extends Form<Person> {

        public PersonForm(String formId, Person person) {
            super(formId, new CompoundPropertyModel<>(person));

            add(new TextField<>("personName"));
            add(new TextField<>("emailAddress"));
        }

        @Override
        protected void onSubmit() {
            getModelObject().getCompany().getPeople().add(getModelObject());
            companyRepository.update(getModelObject().getCompany());

            personRepository.create(getModelObject());
        	super.onSubmit();
        }

    }

    public PersonFormPanel(String id, Person person) {
        super(id);
        add(new PersonForm("personForm", person));
    }


}
