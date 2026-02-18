import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.ConfigReader;

import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.UUID;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    protected Actions actions;


    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void launchBrowser() throws MalformedURLException {
        String url = ConfigReader.getProperty("base.url");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");

        String browser = System.getProperty("browser");
        if (browser == null) {
            browser = "chrome";
        }
        driver = pickBrowser(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }


    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    protected void clickOnAvatarIcon() {

        WebElement avatarIcon = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[@data-testid='view-profile-link']")));
        avatarIcon.click();

    }

    public String generateRandomName() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);

    }

    public void deleteAddedSong() {
        wait.until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//section[@id='playlists']//a[contains(text(), 'Playlist2')]")))
                .click();
        WebElement addedSong = wait.until
                (ExpectedConditions.elementToBeClickable(
                        By.xpath("//section[@id='playlistWrapper']" +
                                "//table[@class='items']//td[contains(text(), 'HoliznaCC0 - Way Of The Samurai')]")));
        addedSong.click();
        Actions action = new Actions(driver);
        action.sendKeys(Keys.DELETE).perform();
        wait.until(ExpectedConditions.invisibilityOfElementLocated
                (By.xpath("//div[@class='success show']")));
    }

    protected void selectNextSong() {
        WebElement nextSong = driver.findElement(By.cssSelector("[data-testid='play-next-btn']"));
        nextSong.click();
    }

    protected void clickPlayButton() {
        WebElement playButton = driver.findElement(By.cssSelector(".album-thumb-wrapper [role='button']"));
        playButton.click();
    }

    protected void createPlaylist(String playListName) {
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector
                        ("i[data-testid='sidebar-create-playlist-btn']")))
                .click();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector
                        ("li[data-testid='playlist-context-menu-create-simple']")))
                .click();

        WebElement inputNewPlayListName = wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector(".create input[name='name']")));
        inputNewPlayListName.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
        inputNewPlayListName.sendKeys(playListName);
        inputNewPlayListName.sendKeys(Keys.ENTER);
    }

    protected void deletePlaylist(String playListName) {
        waitInvisibilityOfSuccess();
        WebElement playListContext = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//section[@id='playlists']" +
                "//a[contains(text(), '" + playListName + "')]")));
        actions.contextClick(playListContext).perform();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[contains(text(), 'Delete')]"))).click();
    }

    protected WebElement checkSuccess() {

        WebElement noticeMessage = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//div[@class='success show']")));
        Assert.assertTrue(noticeMessage.isDisplayed());
        return noticeMessage;
    }

    protected void chooseAllSongList() {
        wait.until(ExpectedConditions.elementToBeClickable
                (By.cssSelector("a[href='#!/songs']"))).click();
    }

    protected boolean songPlayingCheck() {
        WebElement soundBarImage = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.cssSelector("div[data-testid='sound-bar-play']")));
        return soundBarImage.isDisplayed();
    }

    protected void waitInvisibilityOfSuccess() {
        try {
            WebElement clickToClose = driver.findElement(By.xpath("//div[@class='success show']"));
            clickToClose.click();
        } catch (Exception e) {
        }
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[@class='success show']")));
    }

    protected void doubleClickOnPlaylist(String playListName) {
        WebElement playListRenamer = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(),'" + playListName + "')]")));
        actions.doubleClick(playListRenamer).perform();
    }

    public WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridURL = "http://localhost:4444";
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            case "MicrosoftEdge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--remote-all-origins=*");
                return driver = new EdgeDriver();
            case "grid-edge":
                caps.setCapability("browserName", "MicrosoftEdge");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "grid-firefox":
                caps.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "grid-chrome":
                caps.setCapability("browserName", "chrome");
                return driver = new RemoteWebDriver(URI.create(gridURL).toURL(), caps);
            case "grid-ie":
                InternetExplorerOptions ieOptions = new InternetExplorerOptions();
                ieOptions.introduceFlakinessByIgnoringSecurityDomains();
                ieOptions.ignoreZoomSettings();
                driver = new RemoteWebDriver(URI.create(gridURL).toURL(), ieOptions);
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-infobars");

                return driver = new ChromeDriver(chromeOptions);
        }
    }
}

