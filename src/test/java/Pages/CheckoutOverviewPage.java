package Pages;

import com.microsoft.playwright.Page;

import java.math.BigDecimal;

public class CheckoutOverviewPage extends BasePage{

    private final String finishButtonSelector = "button[data-test='finish']";
    private final String SUBTOTAL = "[data-test='subtotal-label']"; // "Item total: $39.98"
    private final String completeHeaderSelector = "h2[data-test='complete-header']";

    public CheckoutOverviewPage(Page page) { super(page); }

    public void placeOrder()
    {
        click(finishButtonSelector);
    }
    public void shouldBeOnStepTwoOfCheckOut(String path) {
        shouldBeOnCheckout(path);
    }


    private static BigDecimal toMoney(String s) {
        return new BigDecimal(s.replaceAll("[^0-9.\\-]", ""));
    }

    public void assertSubtotal(BigDecimal expected) {
        String label = page.locator(SUBTOTAL).innerText();
        BigDecimal uiSubtotal = toMoney(label);
        if (uiSubtotal.compareTo(expected) != 0) {
            throw new AssertionError("Subtotal mismatch. Expected=" + expected + " but UI shows=" + uiSubtotal);
        }
    }

    public void assertOrderPlaced(String expectedMessage) {
        String actualText = page.locator(completeHeaderSelector).innerText().trim();

        if (!actualText.equals(expectedMessage)) {
            throw new AssertionError("Order confirmation message mismatch. " +
                    "Expected='" + expectedMessage + "' but found='" + actualText + "'");
        }
    }
}
