package Pages;

import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {
    private final String userInput = "#user-name";
    private final String passInput = "#password";
    private final String loginBtn  = "#login-button";
    private final String errorBox  = ".error-message-container";

    public LoginPage(Page page) { super(page); }

    public void login(String username, String password) {
        page.locator(userInput).fill(username);
        page.locator(passInput).fill(password);
        page.locator(loginBtn).click();
    }

    public void isErrorVisible() {
        shouldSee(errorBox);
    }
}