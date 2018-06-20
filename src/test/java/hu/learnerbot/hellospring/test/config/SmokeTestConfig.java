package hu.learnerbot.hellospring.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@PropertySource({
        "classpath:common.properties",
        "classpath:${learnerbot.test.env:local}.properties"
})
public class SmokeTestConfig {
    public static final String BROWSER_CHROME = "chrome";
    public static final String BROWSER_FIREFOX = "firefox";

    @Value("${learnerbot.test.baseUrl}")
    private String baseUrl;

    @Value("${learnerbot.test.browser:firefox}")
    private String browser;

    @Value("${learnerbot.test.user.name:user@example.com}")
    private String testUserName;

    @Value("${learnerbot.test.user.password:123456}")
    private String testUserPassword;

    @Value("${learnerbot.test.admin.name:admin@example.com}")
    private String testAdminName;

    @Value("${learnerbot.test.admin.password:123456}")
    private String testAdminPassword;

    @Value("${learnerbot.test.selenium.gecko.driver}")
    private String seleniumGeckoDriver;

    @Value("${learnerbot.test.selenium.chrome.driver}")
    private String seleniumChromeDriver;

    public SmokeTestConfig() {
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getBrowser() {
        return browser;
    }

    public String getTestUserName() {
        return testUserName;
    }

    public String getTestUserPassword() {
        return testUserPassword;
    }

    public String getTestAdminName() {
        return testAdminName;
    }

    public String getTestAdminPassword() {
        return testAdminPassword;
    }

    @PostConstruct
    public void setSystemProperties() {
        System.setProperty("webdriver.chrome.driver", seleniumChromeDriver);
        System.setProperty("webdriver.gecko.driver", seleniumGeckoDriver);
    }
}
