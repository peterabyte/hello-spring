package hu.learnerbot.hellospring.test.step;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import hu.learnerbot.hellospring.test.service.WebDriverService;
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

    @Then("^page title should be '(.+)'")
    public void then_page_title_should_be(String pageTitle) {
        assertEquals(pageTitle, webDriverService.getWebDriver().getTitle());
    }
}
