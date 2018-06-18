package hu.learnerbot.hellospring.test.page;

import hu.learnerbot.hellospring.test.config.SmokeTestConfig;
import hu.learnerbot.hellospring.test.service.WebDriverService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.Assert.assertEquals;

@Component
public class TopicsPage extends BasePage {
    
    @Autowired
    public TopicsPage(WebDriverService webDriverService, SmokeTestConfig smokeTestConfig) {
        super(webDriverService, smokeTestConfig);
    }

    @Override
    public void navigateTo() {
        WebDriver webDriver = webDriverService.getWebDriver();
        webDriver.navigate().to(smokeTestConfig.getBaseUrl() + "/topics");
        assertEquals("Hello Spring Boot - Topics", webDriver.getTitle());
        PageFactory.initElements(webDriver, this);
    }
}
