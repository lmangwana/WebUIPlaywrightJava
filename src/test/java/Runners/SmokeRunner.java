package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/placeOrderE2E.feature",
        glue = {"Steps", "Hooks"},
        plugin = {"pretty", "html:target/cucumber-report.html"}

)
public class SmokeRunner extends AbstractTestNGCucumberTests {}