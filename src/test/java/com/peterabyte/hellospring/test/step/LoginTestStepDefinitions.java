package com.peterabyte.hellospring.test.step;

import com.peterabyte.hellospring.test.page.LoginPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.peterabyte.hellospring.test.SmokeTestConfig;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class LoginTestStepDefinitions {
    @Autowired
    private LoginPage loginPage;
    @Autowired
    private SmokeTestConfig smokeTestConfig;

    @Given("^user visits login page$")
    public void given_user_visits_login_page() {
        loginPage.navigateTo();
    }

    @Given("^default user logs in")
    public void given_logged_in_user() {
        loginPage.navigateTo();
        loginPage.loginAs(smokeTestConfig.getUserName(), smokeTestConfig.getUserPassword());
    }

    @When("^user logs in with username '(.+)' and password '(.+)'$")
    public void when_user_logs_in_with_username_and_password(String userName, String password) {
        loginPage.loginAs(userName, password);
    }

    @Then("^user should see login error$")
    public void then_user_should_see_login_error() {
        assertTrue(loginPage.textLoginError.isDisplayed());
    }
}
