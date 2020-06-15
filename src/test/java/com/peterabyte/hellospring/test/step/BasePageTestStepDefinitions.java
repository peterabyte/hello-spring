package com.peterabyte.hellospring.test.step;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.peterabyte.hellospring.test.service.WebDriverService;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class BasePageTestStepDefinitions {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasePageTestStepDefinitions.class);
    private static final String MIME_TYPE_IMAGE = "image/png";

    @Autowired
    private WebDriverService webDriverService;

    @Before
    public void before() {
        webDriverService.init();
    }

    @After
    public void after(Scenario scenario) {
        takeScreenshotIfFailed(scenario);
        webDriverService.close();
    }

    private void takeScreenshotIfFailed(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                LOGGER.debug("Scenario failed. Taking screenshot...");
                byte[] screenshot = webDriverService.takeScreenshotAsBytes();
                scenario.embed(screenshot, MIME_TYPE_IMAGE);
            } catch (Exception ex) {
                LOGGER.error(String.format("Failed to take screenshot from failing scenario '%s'!", scenario.getName()), ex);
            }
        }
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
