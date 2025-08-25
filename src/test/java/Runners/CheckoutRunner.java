package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {
                "src/test/resources/features/addYourInformation.feature",
                "src/test/resources/features/placeOrder.feature"
        },
        glue = {"Steps", "Hooks"},
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class CheckoutRunner extends AbstractTestNGCucumberTests {}