package Pages;

import com.microsoft.playwright.Page;

public class ProductsPage extends BasePage {
    private final  String shoppingCartLink = "a[data-test='shopping-cart-link']";

    public ProductsPage(Page page) { super(page); }
    public void shouldBeOnProductsPage(String path) {
        shouldBeOnPath(path);
    }

    public void addItemByName(String name) {
        page.locator(".inventory_item:has-text('" + name + "') button:has-text('Add to cart')").first().click();
    }
    public void goToCart() { click(shoppingCartLink);}
}