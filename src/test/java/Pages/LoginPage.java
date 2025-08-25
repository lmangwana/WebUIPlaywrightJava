package Pages;

import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {
    private final String userInput = "#user-name";
    private final String passInput = "#password";
    private final String loginBtn  = "#login-button";
    private final String errorBox  = ".error-message-container";

    public LoginPage(Page page) { super(page); }

    public void login(String username, String password) {
        type(userInput, username);
        type(passInput, password);
        click(loginBtn);
    }

    public void isErrorVisible() {
        shouldSee(errorBox);
    }
}