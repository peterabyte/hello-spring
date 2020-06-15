package com.peterabyte.hellospring.test.service;

import com.peterabyte.hellospring.test.SmokeTestConfig;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebDriverService {
    private WebDriver webDriver;
    @Autowired
    private SmokeTestConfig testConfig;

    public void init() {
        if (SmokeTestConfig.BROWSER_CHROME.equals(testConfig.getBrowser())) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("headless");
            webDriver = new ChromeDriver(options);
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

    public byte[] takeScreenshotAsBytes() {
        if (webDriver instanceof TakesScreenshot) {
            return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        }
        throw new IllegalStateException(String.format("Failed to take screenshot! Current web driver '%s' does not support screenshots.", webDriver));
    }
}
