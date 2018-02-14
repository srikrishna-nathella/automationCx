import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

/**
 * Created by Srikrishna.N on 05-05-2017.
 */
public class SplashscreenPage extends common {
    By copyrights,version;

    public SplashscreenPage() {
        copyrights = By.id(Package+":id/copyRightsTxt");
        version = By.id(Package+":id/appVersionTxt");
    }

    public String verify_copyrights() throws InterruptedException{
       String copyright_text = driver.findElement(copyrights).getText();
        return copyright_text;
    }

    public String verify_version() throws InterruptedException{

        String version_number = driver.findElement(version).getText();
        return version_number;
    }
}
