package Steps;

import Utils.ConfigManager;
import Utils.PlaywrightManager;
import Pages.*;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.*;

public class CommonSteps {
    private final Page pwPage = PlaywrightManager.page();
    private final LoginPage login = new LoginPage(pwPage);
    private final ProductsPage products = new ProductsPage(pwPage);
//    private final CartPage cart = new CartPage(pwPage);
//    private final CheckoutYourInfoPage info = new CheckoutYourInfoPage(pwPage);
//    private final CheckoutOverviewPage overview = new CheckoutOverviewPage(pwPage);

    @Given("the user logs in as {string}")
    public void userLogsInAs(String userType) {
        login.login(ConfigManager.username(userType), ConfigManager.password());
    }

    @Then("the locked out error page is displayed")
    public void lockedOutPageDisplayed() { login.isLockedOutErrorVisible();}


    @Then("the products page is displayed")
    public void productsPageDisplayed() { products.shouldBeOnProducts(); }

    @When("the user adds {string} to the cart")
    public void addItem(String name) { products.addItemByName(name); }

    @And("navigates to the cart")
    public void goToCart() { products.goToCart(); }

//    @Then("the cart shows {string}")
//    public void cartShows(String name) { cart.shouldSeeItem(name); }
//
//    @When("the user proceeds to checkout")
//    public void proceedToCheckout() { cart.checkout(); }
//
//    @And("enters shipping details {string} {string} {string}")
//    public void enterInfo(String first, String last, String postal) { info.fillYourInfo(first, last, postal); }
//
//    @And("finishes the order")
//    public void finishOrder() { overview.finish(); }
//
//    @Then("the order is confirmed")
//    public void orderConfirmed() { overview.shouldSeeThankYou(); }
}
