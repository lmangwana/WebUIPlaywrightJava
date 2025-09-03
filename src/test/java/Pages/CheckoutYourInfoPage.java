package Pages;

import com.microsoft.playwright.Page;

public class CheckoutYourInfoPage extends BasePage{
    private final String continueButtonSelector = "input[data-test='continue']";

    private String firstNameInput = "input[data-test='firstName']";
    private String lastNameInput = "input[data-test='lastName']";
    private String zipCodeInput = "input[data-test='postalCode']";

    public CheckoutYourInfoPage(Page page) { super(page); }

    public void goToStepTwoOfCheckOut()
    {
        click(continueButtonSelector);
    }

    public void shouldBeOnStepOneOfCheckOut(String path) {
        shouldBeOnCheckout(path);
    }

    public void fillInYourInformation(String name, String lastname, String zip)
    {
        type(firstNameInput, name);
        type(lastNameInput, lastname);
        type(zipCodeInput, zip);
    }
}
