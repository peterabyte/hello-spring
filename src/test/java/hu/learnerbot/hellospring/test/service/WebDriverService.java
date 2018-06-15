package hu.learnerbot.hellospring.test.service;

import hu.learnerbot.hellospring.test.config.SmokeTestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebDriverService {
    private WebDriver webDriver;
    @Autowired
    private SmokeTestConfig testConfig;

    public void init() {
        if (SmokeTestConfig.BROWSER_CHROME.equals(testConfig.getBrowser())) {
            webDriver = new ChromeDriver();
        } else if (SmokeTestConfig.BROWSER_FIREFOX.equals(testConfig.getBrowser())) {
            webDriver = new FirefoxDriver();
        } else {
            throw new IllegalStateException("Failed to create WebDriver due to unsupported browser '" + testConfig.getBrowser() + "'! ");
        }
    }

    public void close() {
        if (webDriver != null) {
            webDriver.close();
            webDriver = null;
        }
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }
}
