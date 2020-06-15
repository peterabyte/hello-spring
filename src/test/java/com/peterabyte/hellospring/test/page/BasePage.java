package com.peterabyte.hellospring.test.page;

import com.peterabyte.hellospring.test.SmokeTestConfig;
import com.peterabyte.hellospring.test.service.WebDriverService;

public abstract class BasePage {
    protected final WebDriverService webDriverService;
    protected final SmokeTestConfig smokeTestConfig;

    public BasePage(WebDriverService webDriverService, SmokeTestConfig smokeTestConfig) {
        this.webDriverService = webDriverService;
        this.smokeTestConfig = smokeTestConfig;
    }

    public abstract void navigateTo();
}
