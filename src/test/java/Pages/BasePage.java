package Pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

public class BasePage {
    protected final Page page;
    public BasePage(Page page) { this.page = page; }

    protected void shouldSee(String selector) { PlaywrightAssertions.assertThat(page.locator(selector)).isVisible(); }

    protected void click(String selector) {
        page.locator(selector).click();
    }

    protected void type(String selector, String text) {
        page.locator(selector).fill(text);
    }
}