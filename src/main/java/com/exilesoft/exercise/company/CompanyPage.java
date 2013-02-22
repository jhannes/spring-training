package com.exilesoft.exercise.company;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.exilesoft.exercise.person.Person;
import com.exilesoft.exercise.person.PersonFormPanel;

public class CompanyPage extends WebPage {

    private transient final CompanyRepository repository = new InmemoryCompanyRepository();

    public CompanyPage(PageParameters parameters) {
        Company company = repository.find(parameters.get("id").toLong());
        add(new Label("heading", "Details for " + company.getCompanyName()));
        add(new Label("type", company.getCompanyType().getTypeName()));
        add(new PersonFormPanel("personForm", new Person(company)));

        add(new ListView<Person>("people", company.getPeople()) {
        	@Override
        	protected void populateItem(ListItem<Person> item) {
        		item.add(new Label("person", item.getModelObject().getPersonName()));
        	}
		});
    }

    public static BookmarkablePageLink<Void> link(String id, Long companyId, String companyName) {
        PageParameters parameters = new PageParameters();
        parameters.add("id", companyId);
        BookmarkablePageLink<Void> link = new BookmarkablePageLink<>(id, CompanyPage.class, parameters);
        link.add(new Label("companyName", companyName));
        return link;
    }

}
