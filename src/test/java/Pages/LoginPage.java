package Pages;

import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {
    private final String userInput = "#user-name";
    private final String passInput = "#password";
    private final String loginBtn  = "#login-button";
    private final String genericPasswordUsernameError  = "h3[data-test='error']";
    private final String lockedOutError  = "h3[data-test='error']";

    public LoginPage(Page page) { super(page); }

    public void login(String username, String password) {
        type(userInput, username);
        type(passInput, password);
        click(loginBtn);
    }

    public void isLockedOutErrorVisible() {
        shouldSee(lockedOutError);
    }
}