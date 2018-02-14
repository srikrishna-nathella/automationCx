import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

/**
 * Created by Srikrishna.N on 09-05-2017.
 */
public class oneClickTradingPage extends common{

    By menu,settings,oneclicktradingoption,oneclick,zero,one,two,three,four,five,six,seven,eight,nine,doublezero,dot,K,M,backbtn,done,rates_txt_field,bid_rates_button,offer_rates_button,ratestext;

    public oneClickTradingPage()
    {
        ratestext = By.id(Package+":id/pageTitleTextView");
        menu = By.id(Package+":id/mainMenuImageView");
        oneclicktradingoption = By.id(Package + ":id/SET_ONE_CLICK_TRADING_LBL");
        oneclick = By.id(Package + ":id/SET_OCT_ENABLE_LBL");
        settings = By.xpath("//android.widget.TextView[@text='Settings']");
        rates_txt_field = By.id(Package + ":id/ratesAmountTxt");
        zero = By.id(Package + ":id/numZeroBtn");
        one = By.id(Package + ":id/numOneBtn");
        two = By.id(Package + ":id/numTwoBtn");
        three = By.id(Package + ":id/numThreeBtn");
        four = By.id(Package + ":id/numFourBtn");
        five = By.id(Package + ":id/numFiveBtn");
        six = By.id(Package + ":id/numSixBtn");
        seven = By.id(Package + ":id/numSevenBtn");
        eight = By.id(Package + ":id/numEightBtn");
        nine = By.id(Package + ":id/numNineBtn");
        doublezero = By.id(Package + ":id/numDoubleZeroBtn");
        done = By.id(Package + ":id/numDoneBtn");
        backbtn = By.id(Package + ":id/numBackBtn");
        dot = By.id(Package + ":id/numDotBtn");
        K = By.id(Package + ":id/numKBtn");
        M = By.id(Package + ":id/numMBtn");
        bid_rates_button = By.id(Package + ":id/ratesBidTxt");
        offer_rates_button = By.id(Package + ":id/ratesOfferTxt");

    }


        public void click_menu(){
            driver.findElement(menu).click();
        }

        public void select_settings(){
            driver.findElement(settings).click();
        }

        public void click_oneclicktradingoption() throws InterruptedException{
            driver.findElement(oneclicktradingoption).click();
        }

        public void Enableoneclick() throws InterruptedException{
            driver.findElement(oneclick).click();
        }

        public void rates_txt_field() throws InterruptedException{
            driver.findElement(rates_txt_field).click();
        }

        public void enter_zero() throws InterruptedException{
            driver.findElement(zero).click();
        }
        public void enter_one() throws InterruptedException{
            driver.findElement(one).click();
        }
        public void enter_two() throws InterruptedException{
            driver.findElement(two).click();
        }
        public void enter_three() throws InterruptedException{
            driver.findElement(three).click();
        }
        public void enter_four() throws InterruptedException{
            driver.findElement(four).click();
        }
        public void enter_five() throws InterruptedException{
            driver.findElement(five).click();
        }
        public void enter_six() throws InterruptedException{
            driver.findElement(six).click();
        }
        public void enter_seven() throws InterruptedException{
            driver.findElement(seven).click();
        }
        public void enter_eight() throws InterruptedException{
            driver.findElement(eight).click();
        }
        public void enter_nine() throws InterruptedException{
            driver.findElement(nine).click();
        }
        public void enter_doublezero() throws InterruptedException{
            driver.findElement(doublezero).click();
        }
        public void enter_done() throws InterruptedException{
            driver.findElement(done).click();
        }
        public void enter_backbtn() throws InterruptedException{
            driver.findElement(backbtn).click();
        }
        public void enter_dot() throws InterruptedException{
            driver.findElement(dot).click();
        }
        public void enter_K() throws InterruptedException{
            driver.findElement(K).click();
            }
        public void enter_M() throws InterruptedException{
            driver.findElement(M).click();
        }

    public void doubletapBidorOffer(String side,String pair) throws InterruptedException{
        Point point = driver.findElementByXPath("//android.widget.TextView[@text='"+pair+"']").getLocation();
        TouchAction t = new TouchAction(driver);
        if(side.equalsIgnoreCase("bid"))
        {
            t.tap(point.x+300,point.y).release().perform().press(point.x+300,point.y).release().perform();
        }
        else if(side.equalsIgnoreCase("offer"))
        {
            t.tap(point.x-50,point.y).release().perform().press(point.x-50,point.y).release().perform();
        }
    }

    public void doubletap_rates_button() throws InterruptedException {
        String pair = readXML("ccypair");
        driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+pair+"\"));");
        System.out.println("CCY Pair : "+driver.findElementByXPath("//android.widget.TextView[@text='"+pair+"']").getText());
        doubletapBidorOffer(readXML("ratesSide"),pair);
        System.out.println("Double Tapped successfully");
    };

}