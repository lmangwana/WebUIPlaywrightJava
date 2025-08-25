package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"Steps", "Hook"},
        plugin = {"pretty", "html:target/cucumber-report.html"},
        tags = "@smoke"

)
public class SmokeRunner extends AbstractTestNGCucumberTests {}