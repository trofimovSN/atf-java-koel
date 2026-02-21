package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends BasePage {
    //Selectors put here:
    private By saveButton = By.xpath("//button[@class='btn-submit']");
    private By newNameField = By.cssSelector("#inputProfileName");
    private By currentPasswordField = By.cssSelector("#inputProfileCurrentPassword");
    private By profileNameField = By.cssSelector(".view-profile .name");

    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    //Methods put here:

    public ProfilePage provideNewProfileName(String newName) {
        click(newNameField);
        typeAndSubmit(newNameField, newName);
        return this;

    }

    public ProfilePage currentPass(String pass) {
        click(currentPasswordField);
        clearAndType(currentPasswordField, pass);
        return this;
    }
    public ProfilePage clickSaveButton() {
        click(saveButton);
        return this;
    }
    public String getProfileName() {
     return wait.until(ExpectedConditions
             .visibilityOfElementLocated(profileNameField))
             .getText();
//return this.driver.
//        findElement(profileNameField).getText();


    }
}
