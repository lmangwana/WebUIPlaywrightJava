package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {
                "src/test/resources/features/addItemsToCart.feature",
                "src/test/resources/features/viewCartAndVerifyItems.feature"
        },
        glue = {"Steps", "Hooks"},
        plugin = {"pretty", "html:target/cucumber-report.html"}
)
public class CartRunner extends AbstractTestNGCucumberTests {}