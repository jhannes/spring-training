package com.exilesoft.exercise.person;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

public class ListPeoplePage extends WebPage {

    private transient PersonRepository personRepository = new InmemoryPersonRepository();

    public ListPeoplePage() {
        add(new ListView<Person>("people", personRepository.list()) {

            @Override
            protected void populateItem(ListItem<Person> item) {
                Person object = item.getModelObject();
                item.add(new Label("person", object.getPersonName() + " (" + object.getCompany().getCompanyName() + ")"));
            }
        });
    }

}
