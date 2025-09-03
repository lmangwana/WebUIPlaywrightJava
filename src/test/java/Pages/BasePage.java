package Pages;

import Utils.ConfigManager;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class BasePage {
    protected final Page page;
    public BasePage(Page page) { this.page = page; }

    protected void shouldSee(String selector) {
       PlaywrightAssertions.assertThat(page.locator(selector)).isVisible();
    }

    protected void shouldBeOnPath(String expectedPath) {
        String fullUrl = ConfigManager.baseUrl() + expectedPath +".html";
        PlaywrightAssertions.assertThat(page).hasURL(fullUrl);
    }

    protected void shouldBeOnCheckout(String expectedPath) {
        String fullUrl = ConfigManager.baseUrl() + "checkout-"+ expectedPath +".html";
        PlaywrightAssertions.assertThat(page).hasURL(fullUrl);
    }

    protected void click(String selector) {
        page.locator(selector).click();
    }

    protected void type(String selector, String text) {
        page.locator(selector).fill(text);
    }
}