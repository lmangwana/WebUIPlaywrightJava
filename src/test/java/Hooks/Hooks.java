package Hooks;

import Utils.PlaywrightManager;
import Utils.ConfigManager;
import com.microsoft.playwright.Page;
import io.cucumber.java.*;

public class Hooks {
    @Before
    public void setUp() {
        PlaywrightManager.start();
        Page page = PlaywrightManager.page();
        page.navigate(ConfigManager.baseUrl());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] bytes = PlaywrightManager.page().screenshot(new Page.ScreenshotOptions().setFullPage(true));
            scenario.attach(bytes, "image/png", "failure-screenshot");
        }
        PlaywrightManager.stop();
    }
}