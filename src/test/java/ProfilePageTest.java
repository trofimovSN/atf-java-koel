import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.LoginPage;
import pages.ProfilePage;
import utils.ConfigReader;

public class ProfilePageTest extends BaseTest {
    @Test
    public void updateProfileName() {
        String newName = BasePage.generateRandomName();
        ProfilePage profilePage = new LoginPage(driver)
                .openPage()
                .loginAsValidUser()
                .getProfile()
                .currentPass(ConfigReader.getProperty("user.password"))
                .provideNewProfileName(newName)
                .clickSaveButton();
        String actualName = profilePage.getProfileName();
        Assert.assertEquals(actualName, newName);
    }
}
