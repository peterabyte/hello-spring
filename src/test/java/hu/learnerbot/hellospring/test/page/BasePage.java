package hu.learnerbot.hellospring.test.page;

import hu.learnerbot.hellospring.test.config.SmokeTestConfig;
import hu.learnerbot.hellospring.test.service.WebDriverService;

public abstract class BasePage {
    protected final WebDriverService webDriverService;
    protected final SmokeTestConfig smokeTestConfig;

    public BasePage(WebDriverService webDriverService, SmokeTestConfig smokeTestConfig) {
        this.webDriverService = webDriverService;
        this.smokeTestConfig = smokeTestConfig;
    }

    public abstract void navigateTo();
}
