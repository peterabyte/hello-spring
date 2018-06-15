package hu.learnerbot.hellospring.test.step;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import hu.learnerbot.hellospring.test.page.LoginPage;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class LoginTestStepDefinitions {
    @Autowired
    private LoginPage loginPage;

    @Given("^user visits login page$")
    public void given_user_visits_login_page() {
        loginPage.navigateTo();
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
