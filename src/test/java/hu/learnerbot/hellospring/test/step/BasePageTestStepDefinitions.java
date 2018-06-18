package hu.learnerbot.hellospring.test.step;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import hu.learnerbot.hellospring.test.service.WebDriverService;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class BasePageTestStepDefinitions {
    @Autowired
    private WebDriverService webDriverService;

    @Before
    public void before() {
        webDriverService.init();
    }

    @After
    public void after() {
        webDriverService.close();
    }

    @Then("^page title should be '(.+)'$")
    public void then_page_title_should_be(String pageTitle) {
        assertEquals(pageTitle, webDriverService.getWebDriver().getTitle());
    }

    @When("^user clicks '(.+)' link$")
    public void when_user_clicks_link(String linkId) {
        webDriverService.getWebDriver().findElement(By.id(linkId)).click();
    }

    @When("^user clicks '(.+)' button$")
    public void when_user_clicks_button(String buttonId) {
        webDriverService.getWebDriver().findElement(By.id(buttonId)).click();
    }

    @When("^user enters '(.+)' into '(.+)' input$")
    public void when_user_enters_text_into_input(String text, String inputId) {
        webDriverService.getWebDriver().findElement(By.id(inputId)).sendKeys(text);
    }
}
