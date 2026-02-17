import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.PlayerPage;

public class PlayerTests extends BaseTest {
    @Test
    public void playAnySong() {
//        navigatingToPage();
//        provideEmail(validEmail);
//        providePassword(validPassword);
//        clickSubmit();
        selectNextSong();
        clickPlayButton();
        Assert.assertTrue(songPlayingCheck());
    }
//
//    @Test
//    public void playSongFromAllSongList() {

    /// /        navigatingToPage();
    /// /        provideEmail(validEmail);
    /// /        providePassword(validPassword);
    /// /        clickSubmit();
//        chooseAllSongList();
//        contextClickFirstSong();
//        clickPlaySong();
//        Assert.assertTrue(songPlayingCheck());
//    }
//
    @Test
    public void hoverCheck() {
        PlayerPage playerPage = new LoginPage(driver)
                .openPage()
                .loginAsValidUser()
                .getPlayer();
playerPage.hoverPlayButton();
String playButtonTooltip = playerPage.isPlayPauseButtonDisplayed();
        Assert.assertEquals(playButtonTooltip, "Play or resume");
    }


//
//    private void clickPlaySong() {
//        wait.until(ExpectedConditions.elementToBeClickable(
//                By.xpath("//li[@class='playback']"))).click();
//    }
//
//    private void contextClickFirstSong() {
//        WebElement firstSongElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath
//                ("//section[@id='songsWrapper']//table[@class='items']//tr[1]")));
//        actions.contextClick(firstSongElement).perform();
//    }
}