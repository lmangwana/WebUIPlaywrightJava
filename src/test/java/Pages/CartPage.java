package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import java.math.BigDecimal;

public class CartPage extends BasePage {

    private final String checkoutButton = "button[data-test='checkout']";
    private final String ROW   = "[data-test='inventory-item']";
    private final String QTY   = "[data-test='item-quantity']";
    private final String NAME  = "[data-test='inventory-item-name']";
    private final String PRICE = "[data-test='inventory-item-price']";

    public CartPage(Page page) { super(page); }


    public void shouldBeOnCartsPage(String path) {
        shouldBeOnPath(path);
    }

    public void goToStepOneOfCheckOut()
    {
        click(checkoutButton);
    }

    /**
     * Look for a row that matches the given description.
     * When found, compare its qty and price as plain strings.
     * If not found or mismatched, throw an AssertionError.
     */
    public void assertLineItems(String expectedQty, String expectedDescription, String expectedPrice) {
        Locator rows = page.locator(ROW);
        int count = rows.count();
        boolean found = false;

        for (int i = 0; i < count; i++) {
            Locator row = rows.nth(i);

            String nameText  = row.locator(NAME).innerText().trim();
            if (!nameText.equals(expectedDescription)) {
                continue; // not the row we want; keep looking
            }

            found = true;

            String qtyText   = row.locator(QTY).innerText().trim();
            String priceText = row.locator(PRICE).innerText().trim();

            if (!qtyText.equals(expectedQty)) {
                throw new AssertionError("Qty mismatch for '" + expectedDescription +
                        "'. Expected=" + expectedQty + " actual=" + qtyText);
            }

            if (!priceText.equals(expectedPrice)) {
                throw new AssertionError("Price mismatch for '" + expectedDescription +
                        "'. Expected=" + expectedPrice + " actual=" + priceText);
            }

            // matched this line item fully; we can stop
            break;
        }

        if (!found) {
            throw new AssertionError("Item not found in cart: '" + expectedDescription + "'");
        }
    }

    private static BigDecimal toMoney(String s) {
        return new BigDecimal(s.replaceAll("[^0-9.\\-]", "")); // "$29.99" -> 29.99
    }

    /** Sum of qty * unit price for all items currently shown in the cart. */
    public BigDecimal subtotalfromCart() {
        Locator rows = page.locator(ROW);
        int count = rows.count();
        BigDecimal sum = BigDecimal.ZERO;

        for (int i = 0; i < count; i++) {
            Locator row = rows.nth(i);
            String qtyText = row.locator(QTY).innerText().trim();
            String priceText = row.locator(PRICE).innerText().trim();

            BigDecimal qty = new BigDecimal(qtyText);
            BigDecimal unit = toMoney(priceText);

            sum = sum.add(unit.multiply(qty));
        }
        return sum;
    }





}
