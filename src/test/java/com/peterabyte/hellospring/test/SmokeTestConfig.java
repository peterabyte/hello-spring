package com.peterabyte.hellospring.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@PropertySource({
        "classpath:${hellospring.test.env:local}.properties"
})
public class SmokeTestConfig {
    public static final String BROWSER_CHROME = "chrome";

    @Value("${hellospring.url}")
    private String url;

    @Value("chrome")
    private String browser;

    @Value("${hellospring.user.name}")
    private String userName;

    @Value("${hellospring.user.password}")
    private String userPassword;

    @Value("${hellospring.chrome.driver}")
    private String seleniumChromeDriver;

    public SmokeTestConfig() {
    }

    public String getUrl() {
        return url;
    }

    public String getBrowser() {
        return browser;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    @PostConstruct
    public void setSystemProperties() {
        System.setProperty("webdriver.chrome.driver", seleniumChromeDriver);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SmokeTestConfig{");
        sb.append("url='").append(url).append('\'');
        sb.append(", browser='").append(browser).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", seleniumChromeDriver='").append(seleniumChromeDriver).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
