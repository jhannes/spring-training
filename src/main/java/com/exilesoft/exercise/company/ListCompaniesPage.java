package com.exilesoft.exercise.company;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;

public class ListCompaniesPage extends WebPage {

    private transient final CompanyRepository repository = new InmemoryCompanyRepository();
    private final Model<String> queryModel = new Model<>();

    public ListCompaniesPage() {
        add(new ListView<Company>("companies", list()) {

            @Override
            protected void populateItem(ListItem<Company> item) {
                Company object = item.getModelObject();

                item.add(CompanyPage.link("url", object.getId(), object.getCompanyName()));
                item.add(new ExternalLink("companyUrl", object.getCompanyUrl(), object.getCompanyUrl()));
                item.add(new Label("companyType", object.getCompanyType().getTypeName()));
                item.add(new Label("employeeCount", object.getEmployeeCount()));
            }
        });

        add(new Form<String>("companySearch") {
            {
                add(new TextField<>("nameQuery", queryModel));
            }
        });
    }

	private List<Company> list() {
		return repository.list();
	}


}
