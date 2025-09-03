package Steps;

import Utils.ConfigManager;
import Utils.PlaywrightManager;
import Pages.*;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.*;

import java.util.List;
import java.util.Map;

public class CommonSteps {
    private final Page pwPage = PlaywrightManager.page();
    private final LoginPage login = new LoginPage(pwPage);
    private final ProductsPage products = new ProductsPage(pwPage);
    private final CartPage cart = new CartPage(pwPage);
    private final CheckoutYourInfoPage info = new CheckoutYourInfoPage(pwPage);
    private final CheckoutOverviewPage overview = new CheckoutOverviewPage(pwPage);

    @Given("the user logs in as {string}")
    public void userLogsInAs(String userType) {
        login.login(ConfigManager.username(userType), ConfigManager.password());
    }

    @Then("the locked out error page is displayed")
    public void lockedOutPageDisplayed() {
        login.isLockedOutErrorVisible();
    }

    @When("the user adds {string} to the cart")
    public void addItem(String name) {
        products.addItemByName(name);
    }

    @And("navigates to the cart")
    public void goToCart() {
        products.goToCart();
    }

    @Then("the user is on the {string} page")
    public void theUserIsOnThePage(String page) {
        switch (page.toLowerCase()) {
            case "inventory": products.shouldBeOnProductsPage(page);
                break;

            case "cart": cart.shouldBeOnCartsPage(page);
                break;

            default:
                throw new IllegalArgumentException("Unknown page: " + page);
        }
    }

    @And("the user clicks on {string}")
    public void theUserClicksOn(String element) {
        switch (element.toLowerCase()) {
            case "the cart": products.goToCart();
                break;

            case "continue":info.goToStepTwoOfCheckOut();
                break;

            case "finish":overview.placeOrder();
                break;

            case "checkout": cart.goToStepOneOfCheckOut();
                break;

            default:
                throw new IllegalArgumentException("Unknown element to click: " + element);
        }


    }

    @Given("the user is on {string} of the check out flow")
    public void theUserIsOnOfTheCheckOutFlow(String step) {
        switch (step.toLowerCase()) {
            case "step-one": info.shouldBeOnStepOneOfCheckOut(step);
                break;

            case "step-two": overview.shouldBeOnStepTwoOfCheckOut(step);
                break;

            default:
                throw new IllegalArgumentException("Unknown checkout step: " + step);
        }

    }

    @And("they enter their details:")
    public void theyEnterTheirDetails(io.cucumber.datatable.DataTable dataTable) {
        // Convert table to a map
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> r = rows.get(0); // only one row expected

        String firstName = r.get("Name").trim();
        String lastName  = r.get("Last Name").trim();
        String zip       = r.get("Zip or Postal Code").trim();

        Page p = PlaywrightManager.page();
        p.fill("[data-test='firstName']", firstName);
        p.fill("[data-test='lastName']",  lastName);
        p.fill("[data-test='postalCode']", zip);

        info.fillInYourInformation(firstName, lastName, zip);

    }

    @Then("the cart should show the following items:")
    public void theCartShouldShowTheFollowingItems(io.cucumber.datatable.DataTable dataTable) {
        // Convert table into a list of maps
        List<Map<String, String>> items = dataTable.asMaps(String.class, String.class);

        for (Map<String, String> item : items) {
            String qty = item.get("Qty");
            String description = item.get("Description");
            String price = item.get("Price");

            // TODO: Call your CartPage methods here
            cart.assertLineItems(qty, description, price);

        }
    }

    @And("the Price total is correct")
    public void thePriceTotalIsCorrect() {

        overview.assertSubtotal(cart.subtotalfromCart());
    }

    @Then("they see an order confirmation with the message {string}")
    public void theySeeAnOrderConfirmation(String orderCompleteMessage) {

        overview.assertOrderPlaced(orderCompleteMessage);
    }
}
