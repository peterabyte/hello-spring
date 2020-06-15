package com.peterabyte.hellospring.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = {
                "pretty",
                "html:build/cucumber-report/html"
        })
public class SmokeTestRunner {
}

