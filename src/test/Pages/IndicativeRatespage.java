import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Srikrishna.N on 04-05-2017.
 */
public class IndicativeRatespage extends common {

    By indicative_rates_btn,indicative_rates_header,bid_rates_button,offer_rates_button,menu,login,contacticon,lgnbtn;

    public IndicativeRatespage() {
        indicative_rates_btn = By.id(Package + ":id/ratesBtn1");
        indicative_rates_header = By.id(Package + ":id/pageTitleTextView");
        bid_rates_button = By.id(Package + ":id/ratesBidTxt");
        offer_rates_button = By.id(Package + ":id/ratesOfferTxt");
        menu = By.id(Package+":id/mainMenuImageView");
        login = By.xpath("//android.widget.TextView[@text='Login']");
        contacticon = By.id(Package+":id/contactImageView");
        lgnbtn = By.id(Package+":id/loginBtn1");
    }

    public void click_Indicativerates_button(){
        driver.findElement(indicative_rates_btn).click();
    }

}
