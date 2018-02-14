import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by Srikrishna.N on 06-11-2017.
 */
public class Trades extends Tradepage{

    @Test(priority = 1)
    public void login()
    {
        loginpage lp = new loginpage();
        lp.setStack();
        driver.findElementByXPath("//android.widget.EditText[@text='USER NAME']").sendKeys("MSF100001");
        driver.findElementByXPath("//android.widget.EditText[@index='7']").sendKeys("test1234");
        driver.findElementByXPath("//android.widget.Button[@resource-id='com.msf.currenex.mobile.cgb:id/loginBtn1']").click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//android.widget.Button[@text='ACCEPT']"))).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if(driver.findElementByXPath("//android.widget.TextView[@text='EULA']").isDisplayed()){
            driver.findElementByXPath("//android.widget.Button[@text='ACCEPT']").click();
            System.out.println("In Login screen");
        }
    }

    @Test(priority = 1)
    public void place_market_order_with_GTSexpiry() throws Throwable{
        WebDriverWait wait = new WebDriverWait(driver,50);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById(Package+":id/ratesOfferTxt")));
        tap_rates_button();
        if(driver.findElement(createorder_header).isDisplayed()){
        }
        setMarkettab();
        setGtc();
        placeorder();
        System.out.println("after Placing order");

        if(driver.findElementByXPath("//android.widget.TextView[contains(@text,'Order Detail')]").isDisplayed())
        {
            System.out.println("In if statement Market order");
            test.get().log(Status.PASS,"Market order placed");//pass("Market order placed");
            driver.pressKeyCode(AndroidKeyCode.BACK);
            return;
        }
        if(driver.findElementByXPath("//android.widget.TextView[@text = 'No rates available for this currency']").isDisplayed())
        {
            System.out.println("In Else-if statement Market order");
            Utility.captureScreenshot(driver,"No streaming available for the currency pair");
            test.get().log(Status.ERROR,"No streaming to place orders");
            driver.findElementByXPath("//android.widget.Button[@text = 'OK']").click();
            driver.pressKeyCode(AndroidKeyCode.BACK);
            return;
        }
        else{
            System.out.println("In else statement Market order");
            Utility.captureScreenshot(driver,"Market_Order_not_placed");
        test.get().log(Status.FAIL,"Expected : Market should be successfully placed, Actual : Market order not placed")
                .addScreenCaptureFromPath(ScreenshotPath+"Market_Order_not_placed.png");
        }
    }

    @Test(priority = 2)
    public void place_limit_order() throws Throwable{
        WebDriverWait wait = new WebDriverWait(driver,50);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementById(Package+":id/ratesOfferTxt")));
        System.out.println("In order details screen");
        Thread.sleep(3000L);
        tap_rates_button();
        tap_rates_button();
        if(driver.findElement(createorder_header).isDisplayed()){
            System.out.println("User is in create order screen");
        }
        setLimittab();
        driver.findElementById(Package+":id/TRADE_LIMIT_DISCRETION_CHECKBOX").click();
            driver.findElementById(Package+":id/TRADE_LIMIT_DISCRETION_EDITTEXT").click();
        oneClickTradingPage oct = new oneClickTradingPage();
        oct.enter_two();
        oct.enter_zero();
        oct.enter_done();
        setGtc();
        placeorder();
    if(driver.findElementByXPath("//android.widget.TextView[contains(@text,'Order Detail')]").isDisplayed())
    {
        test.get().log(Status.PASS,"Limit order placed");//pass("Limit order placed");
        driver.pressKeyCode(AndroidKeyCode.BACK);

    }
    else if(driver.findElementByXPath("//android.widget.TextView[@text = 'No rates available for this currency']").isDisplayed())
    {
        Utility.captureScreenshot(driver,"No streaming available for the currency pair");
        test.get().log(Status.ERROR,"No streaming to place orders");
        driver.findElementByXPath("//android.widget.Button[@text = 'OK']").click();
        driver.pressKeyCode(AndroidKeyCode.BACK);
    }
    else {
        Utility.captureScreenshot(driver, "Limit_Order_not_placed");
        test.get().log(Status.FAIL, "Expected : Limit order should be successfully placed, Actual : limit order not placed")
                .addScreenCaptureFromPath(ScreenshotPath + "Limit_Order_not_placed.png");
    }

    }

}

