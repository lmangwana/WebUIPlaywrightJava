package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/login.feature",
        glue = {"Steps", "Hook"},
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class LoginRunner extends AbstractTestNGCucumberTests {}