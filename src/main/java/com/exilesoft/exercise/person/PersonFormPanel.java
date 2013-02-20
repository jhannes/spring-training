package com.exilesoft.exercise.person;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;

public class PersonFormPanel extends Panel {

    public class PersonForm extends Form<Person> {

        public PersonForm(String formId, Person person) {
            super(formId, new CompoundPropertyModel<>(person));

            add(new TextField<>("personName"));
            add(new TextField<>("emailAddress"));
        }

    }

    public PersonFormPanel(String id, Person person) {
        super(id);
        add(new PersonForm("personForm", person));
    }


}
