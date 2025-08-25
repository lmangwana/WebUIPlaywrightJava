package Pages;

import com.microsoft.playwright.Page;

public class ProductsPage extends BasePage {
    private final String productPageTitle = ".title";

    public ProductsPage(Page page) { super(page); }
    public void shouldBeOnProducts() {
        shouldSee(productPageTitle);
    }
    public void addItemByName(String name) {
        page.locator(".inventory_item:has-text('" + name + "') button:has-text('Add to cart')").first().click();
    }
    public void goToCart() { page.locator(".shopping_cart_link").click(); }
}