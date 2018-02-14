import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.time.Duration;

/**
 * Created by Srikrishna.N on 16-10-2017.
 */
public class Tradepage extends common {

    public void tap_rates_button() throws InterruptedException {

        //driver.scrollTo(readXML("ccypair"));
        //driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"NZD/USD\"));");
        //driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(readXML(\"ccypair\");))");
        String pair = readXML("ccypair");
        driver.findElementsByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+pair+"\"));");
        System.out.println("CCY Pair : "+driver.findElementByXPath("//android.widget.TextView[@text='"+pair+"']").getText());
        tapBidorOffer(readXML("ratesSide"),pair);
        //t.tap(point.x,point.y).perform();
        System.out.println("Tapped successfully");
    };

    public void tapBidorOffer(String side,String pair) throws InterruptedException {
        Point point = driver.findElementByXPath("//android.widget.TextView[@text='"+pair+"']").getLocation();
        TouchAction t = new TouchAction(driver);
        if(side.equalsIgnoreCase("bid"))
        {
            t.tap(point.x+300,point.y).perform();
        }
        else if(side.equalsIgnoreCase("offer"))
        {
            t.tap(point.x-50,point.y).perform();
        }
    }

    By createorder_header,market,limit,stop,oco,gtc,ioc,gtd,gts,day,secondstextbox,placeorder_btn,rates_txt_field,zero,one,two,doublezero,done;

    public Tradepage(){
        createorder_header = By.xpath("//android.widget.TextView[@text='Create Order']");
        market = By.xpath("//android.widget.Button[@text='MARKET']");
        limit = By.xpath("//android.widget.Button[@text='LIMIT']");
        stop = By.xpath("//android.widget.Button[@text='STOP']");
        oco = By.xpath("//android.widget.Button[@text='OCO']");
        gtc = By.xpath("//android.widget.Button[@text='GTC']");
        ioc = By.xpath("//android.widget.Button[@text='IOC']");
        gtd = By.xpath("//android.widget.Button[@text='GTD']");
        gts = By.xpath("//android.widget.Button[@text='GTS']");
        day = By.xpath("//android.widget.Button[@text='DAY']");
        rates_txt_field = By.id(Package + ":id/ratesAmountTxt");
        zero = By.id(Package + ":id/numZeroBtn");
        one = By.id(Package + ":id/numOneBtn");
        two = By.id(Package + ":id/numTwoBtn");
        doublezero = By.id(Package + ":id/numDoubleZeroBtn");
        done = By.id(Package + ":id/numDoneBtn");
        secondstextbox = By.xpath("//android.widget.EditText[@resource-id='com.msf.currenex.mobile:id/TRADE_ET_TIMED_EDITTEXT']");

    }
    public void setMarkettab(){
        driver.findElement(market).click();

    }

    public void setLimittab(){
        driver.findElement(limit).click();
    }

    public void setStopttab(){
        driver.findElement(stop).click();
    }

    public void setOcotab(){
        driver.findElement(oco).click();
    }

    public void setGtc() {
            driver.findElement(gtc).click();
        }

    public void setIoc() {
        driver.findElement(ioc).click();
    }

    public void setGtd() {
        driver.findElement(gtd).click();
    }

    public void setGts() {
        driver.findElement(gts).click();
        if (driver.findElement(secondstextbox).isEnabled()) {
            driver.findElement(secondstextbox).sendKeys("20");
        }
    }

    public void setDay() {
        driver.findElement(day).click();
    }


    public void placeorder()  {
        driver.findElementByAndroidUIAutomator("new UiScrollable(UiSelector()).scrollIntoView(text(\"PLACE ORDER\"));").click();

        try {
            System.out.println("Try catch block");
            if (driver.findElementById(Package + ":id/dialogBtnLayout").isDisplayed()) {
                System.out.println("Imm. execution Dialogue displayed");
                driver.findElementById(Package + ":id/DIALOG_CANCEL_BUTTON").click();
            }

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Exception occurred");
           /* driver.pressKeyCode(AndroidKeyCode.BACK);*/
        }

        try{
            if(driver.findElementByXPath("//android.widget.TextView[@text='invalid amount']").isDisplayed()){
                driver.findElementByXPath("//android.widget.Button[@text='OK']").click();

                oneClickTradingPage oct = new oneClickTradingPage();
                oct.rates_txt_field();
                oct.enter_two();
                oct.enter_doublezero();
                oct.enter_doublezero();
                oct.enter_done();
                driver.findElementByAndroidUIAutomator("new UiScrollable(UiSelector()).scrollIntoView(text(\"PLACE ORDER\"));").click();
            }

        }catch(Exception e){

        }

    }

}


