package com.exilesoft.exercise.company;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class CompanyPage extends WebPage {

    public static BookmarkablePageLink<Void> link(String id, Long companyId, String companyName) {
        PageParameters parameters = new PageParameters();
        parameters.add("id", companyId);
        BookmarkablePageLink<Void> link = new BookmarkablePageLink<>(id, CompanyPage.class, parameters);
        link.add(new Label("companyName", companyName));
        return link;
    }

}
