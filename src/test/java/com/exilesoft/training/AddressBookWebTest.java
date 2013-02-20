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

public class AddressBookWebTest {

    private WebDriver browser;

    @Test
    public void fullScenarioTest() throws Exception {
        String url = startWebServer("Johannes's addresses");

        this.browser = createWebBrowser();
        browser.get(url);

        assertThat(browser.getTitle()).isEqualTo("Johannes's addresses :: Menu");

        addCompany("Visma", "http://www.visma.com/", "Software");
        addCompany("Exilesoft", "http://www.exilesoft.com/", "Offshoring");

        browser.findElement(By.linkText("List companies")).click();
        assertThat(browser.findElement(By.linkText("Visma"))).isNotNull();
        assertThat(browser.findElement(By.linkText("Exilesoft"))).isNotNull();
        browser.findElement(By.name("nameQuery")).sendKeys("XILE");
        browser.findElement(By.name("nameQuery")).submit();
        //assertThat(browser.findElements(By.linkText("Visma"))).isEmpty();
        browser.findElement(By.linkText("Exilesoft")).click();

        assertThat(browser.findElement(By.id("heading")).getText()).isEqualTo("Details for Exilesoft");
        assertThat(browser.findElement(By.id("type")).getText()).isEqualTo("Offshoring");

        addPerson("Johannes Brodwall", "jbr@exilesoft.com");

        browser.get(url);
        browser.findElement(By.linkText("List people")).click();
        assertThat(browser.findElement(By.id("people")).getText()).contains("Johannes Brodwall (Exilesoft)");
    }

    private void addPerson(String personName, String personEmail) {
        browser.findElement(By.name("personName")).sendKeys(personName);
        browser.findElement(By.name("personEmail")).sendKeys(personEmail);
        browser.findElement(By.name("personName")).submit();
    }

    private void addCompany(String companyName, String companyUrl, String companyType) {
        browser.findElement(By.linkText("Add company")).click();
        browser.findElement(By.name("companyName")).sendKeys(companyName);
        browser.findElement(By.name("companyUrl")).sendKeys(companyUrl);
        findSelectOptionWithText("companyType", companyType).setSelected();
        browser.findElement(By.name("companyName")).submit();
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
        Server server = new Server(0);
        WebAppContext webApplication = new WebAppContext("src/main/webapp", "/root");
        server.setHandler(webApplication);
        server.start();

        //WebApplicationContext applicationContext =
        //        WebApplicationContextUtils.getRequiredWebApplicationContext(webApplication.getServletContext());
        //applicationContext.getBean(ApplicationInfo.class).setName(applicationName);

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
