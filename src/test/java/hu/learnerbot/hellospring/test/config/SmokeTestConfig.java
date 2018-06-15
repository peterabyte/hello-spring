package hu.learnerbot.hellospring.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

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

    public SmokeTestConfig() {
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getBrowser() {
        return browser;
    }
}
