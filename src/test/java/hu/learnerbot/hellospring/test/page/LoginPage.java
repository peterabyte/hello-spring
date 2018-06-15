package hu.learnerbot.hellospring.test.page;

import hu.learnerbot.hellospring.test.config.SmokeTestConfig;
import hu.learnerbot.hellospring.test.service.WebDriverService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.Assert.assertEquals;

@Component
public class LoginPage extends BasePage {

    @FindBy(how = How.ID, using = "username")
    public WebElement inputUserName;

    @FindBy(how = How.ID, using = "password")
    public WebElement inputPassword;

    @FindBy(how = How.ID, using = "login")
    public WebElement buttonLogin;

    @FindBy(how = How.ID, using = "loginError")
    public WebElement textLoginError;

    @FindBy(how = How.ID, using = "logoutSuccess")
    public WebElement textLogoutSuccess;

    @Autowired
    public LoginPage(WebDriverService webDriverService, SmokeTestConfig smokeTestConfig) {
        super(webDriverService, smokeTestConfig);
    }

    @Override
    public void navigateTo() {
        WebDriver webDriver = webDriverService.getWebDriver();
        webDriver.navigate().to(smokeTestConfig.getBaseUrl() + "/login");
        assertEquals("Hello Spring Boot - Login", webDriver.getTitle());
        PageFactory.initElements(webDriver, this);
    }

    public void loginAs(String userName, String password) {
        inputUserName.sendKeys(userName);
        inputPassword.sendKeys(password);
        buttonLogin.click();
    }
}
