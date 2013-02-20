package com.exilesoft.training;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class AddressBookWebTest {

    private WebDriver browser;
	private String url;

    @Test
    public void fullScenarioTest() throws Exception {
        this.url = startWebServer("Johannes's addresses");
        this.browser = createWebBrowser();

        browser.get(url);
        assertThat(browser.getTitle()).isEqualTo("Johannes's addresses :: Menu");

        addCompany("Visma", "http://www.visma.com/", "Software");
        addCompany("Exilesoft", "http://www.exilesoft.com/", "Offshoring");
        verifyCompanySearch("XILE", "Exilesoft", "Visma");
        verifyCompanyDetails("Exilesoft", "Details for Exilesoft", "Offshoring");

        addPersonToCompany("Exilesoft", "Johannes Brodwall", "jbr@exilesoft.com");
        verifyPersonPresent("Johannes Brodwall (Exilesoft)");
    }

	private void addCompany(String companyName, String companyUrl, String companyType) {
	    browser.findElement(By.linkText("Add company")).click();
	    browser.findElement(By.name("companyName")).sendKeys(companyName);
	    browser.findElement(By.name("companyUrl")).sendKeys(companyUrl);
	    findSelectOptionWithText("companyType", companyType).setSelected();
	    browser.findElement(By.name("companyName")).submit();
	}

	private void verifyCompanySearch(String searchTerm, String matchingCompany, String nonMatchingCompany) {
		browser.findElement(By.linkText("List companies")).click();
	    assertThat(browser.findElement(By.linkText(nonMatchingCompany))).isNotNull();
	    assertThat(browser.findElement(By.linkText(matchingCompany))).isNotNull();
	    browser.findElement(By.name("nameQuery")).sendKeys(searchTerm);
	    browser.findElement(By.name("nameQuery")).submit();
	    //assertThat(browser.findElements(By.linkText(nonMatchingCompany))).isEmpty();
	    browser.findElement(By.linkText(matchingCompany)).click();
	}

	private void verifyCompanyDetails(String company, String heading, String type) {
		navigateToCompany(company);
	    assertThat(browser.findElement(By.id("heading")).getText()).isEqualTo(heading);
	    assertThat(browser.findElement(By.id("type")).getText()).isEqualTo(type);
	}

	private void addPersonToCompany(String company, String personName, String personEmail) {
		navigateToCompany(company);
	    browser.findElement(By.name("personName")).sendKeys(personName);
	    browser.findElement(By.name("personEmail")).sendKeys(personEmail);
	    browser.findElement(By.name("personName")).submit();
	}

	private void verifyPersonPresent(String person) {
        browser.get(url);
        browser.findElement(By.linkText("List people")).click();
        assertThat(browser.findElement(By.id("people")).getText()).contains(person);
	}

	private void navigateToCompany(String company) {
		browser.findElement(By.linkText("List companies")).click();
	    browser.findElement(By.linkText(company)).click();
	}

	private WebElement findSelectOptionWithText(String selectName, String optionText) {
	    List<String> options = new ArrayList<>();
	    for (WebElement webElement : browser.findElement(By.name(selectName)).findElements(By.tagName("option"))) {
	        if (webElement.getText().equals(optionText)) return webElement;
	        options.add(webElement.getText());
	    }
	    fail("Can't find " + optionText + " in " + options);
	    return null;
	}

	private String startWebServer(String applicationName) throws Exception {
		System.setProperty("wicket.configuration", "DEPLOYMENT");

        Server server = new Server(0);
        WebAppContext webApplication = new WebAppContext("src/main/webapp", "/root");
        server.setHandler(webApplication);
        server.start();

        WebApplicationContext applicationContext =
                WebApplicationContextUtils.getRequiredWebApplicationContext(webApplication.getServletContext());
        applicationContext.getBean(ApplicationInfo.class).setName(applicationName);

        return new URL("http", "localhost", server.getConnectors()[0].getLocalPort(), "/root").toString();
    }

    private WebDriver createWebBrowser() {
        return new HtmlUnitDriver() {
            @Override
            public WebElement findElement(By by) {
                try {
                    return super.findElement(by);
                } catch (NoSuchElementException e) {
                    throw new NoSuchElementException("Can't find " + by + " in " + getPageSource());
                }
            }
        };
    }

}
