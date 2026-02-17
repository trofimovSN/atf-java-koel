package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;

public class LoginPage extends BasePage {
    private String url = ConfigReader.getProperty("base.url");
    private By emailField = By.cssSelector("input[type='email']");
    private By passwordField = By.cssSelector("input[type='password']");
    private By submitButton = By.cssSelector("button[type='submit']");
    private By registrationButton = By.cssSelector("a[href='registration']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage openPage() {
        driver.get(url);
        return this;
    }

    public HomePage loginAsValidUser() {
        return login(ConfigReader.getProperty("user.email"),
                ConfigReader.getProperty("user.password"));
    }

    ;

    public LoginPage provideEmail(String email) {
        findElement(emailField).sendKeys(email);
        return this;
    }

    public LoginPage providePassword(String password) {
        findElement(passwordField).sendKeys(password);
        return this;
    }

    public void clickSubmitButton() {
        click(submitButton);
    }

    public void clickRegistrationButton() {
        click(registrationButton);
    }

    public HomePage login(String email, String password) {
        provideEmail(email);
        providePassword(password);
        clickSubmitButton();

        return new HomePage(driver);
    }
    public boolean isLoginPageDisplayed() {
        return driver.getCurrentUrl()
                .equals(ConfigReader.getProperty("base.url"));
    }

}
