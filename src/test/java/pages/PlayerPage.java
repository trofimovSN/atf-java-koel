package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PlayerPage extends BasePage {
    private By playPauseButton = By.cssSelector("span[data-testid='play-btn']");
    public PlayerPage(WebDriver driver) {
        super(driver);
    }
    public PlayerPage hoverPlayButton() {
        WebElement playButton = driver.findElement(playPauseButton);
        actions.moveToElement(playButton).perform();
        return this;
    }
    public String isPlayPauseButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(playPauseButton))
                .getAttribute("title");
    }
}
