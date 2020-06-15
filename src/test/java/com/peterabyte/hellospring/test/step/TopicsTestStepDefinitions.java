package com.peterabyte.hellospring.test.step;

import com.peterabyte.hellospring.test.page.TopicsPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import com.peterabyte.hellospring.test.service.WebDriverService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class TopicsTestStepDefinitions {
    @Autowired
    private TopicsPage topicsPage;

    @Autowired
    private WebDriverService webDriverService;

    @Given("^user visits topics page$")
    public void given_user_visits_login_page() {
        topicsPage.navigateTo();
    }

    @Then("^topic page should contain '(\\d+)' comments$")
    public void then_topic_page_should_contain_number_of_comments(int numberOfComments) {
        assertEquals(numberOfComments, topicsPage.getNumberOfComments());
    }
}
