import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Sree on 09-01-2017.
 */
public class IndicativeRates extends IndicativeRatespage {

    String el1;

    @Test(priority = 1)
    public void indicative_rates() throws Exception {
        System.out.println("====== In indicative rates screen====");
        if(readXML("INDICATIVE_RATES").equalsIgnoreCase("N")){
            if(driver.findElement(indicative_rates_btn).isDisplayed()){
                Utility.captureScreenshot(driver,"Indicative_rates_available");
                test.get().log(Status.FAIL,"Expected : No Indicative rates, Actual : Indicative rates is present")
                        .addScreenCaptureFromPath(ScreenshotPath+"Indicative_rates_available.png");
            }
        }
        if(readXML("INDICATIVE_RATES").equalsIgnoreCase("Y")) {
            if(driver.findElement(indicative_rates_btn).isDisplayed()){
            click_Indicativerates_button();
                try {
                    WebDriverWait wait = new WebDriverWait(driver, 25);
                    WebElement el1 = wait.until(ExpectedConditions.visibilityOf(driver.findElement(indicative_rates_header)));
                    System.out.println("Indicative Rates screen");
                    test.get().pass("Test Passed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String el = driver.findElement(bid_rates_button).getText();
                System.out.println(el);

                int count = 0;
                try {
                    do {
                        el1 = driver.findElement(bid_rates_button).getText();
                        System.out.println(el1);
                        Thread.sleep(5000);
                        count = count + 1;
                        System.out.println("Count = " + count);
                        if (count == 5) {
                            System.err.println("Stream is not happening...");
                            Utility.captureScreenshot(driver,"Indicative_rates_Unavailable");
                            test.get().log(Status.FAIL,"Expected : Streaming , Actual : No Streaming")
                                    .addScreenCaptureFromPath(ScreenshotPath+"Indicative_rates_Unavailable.png");
                            break;
                        }
                    } while (el1.equals(el));
                    if (!el1.equals(el) && count != 5) {
                        System.out.println("Streaming is happening....");
                        test.get().pass("Test Passed");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            }else {
                Utility.captureScreenshot(driver,"Indicative_rates_Unavailable");
                test.get().log(Status.FAIL,"Expected : Expected : Indicative rates button, Actual : Indicative rates unavailable.")
                        .addScreenCaptureFromPath(ScreenshotPath+"Indicative_rates_Unavailable.png");
        }
        }


    @Test(priority = 2)
    public void login() throws Exception {
        driver.findElement(menu).click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(login)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.findElement(login).click();
    }

    @Test(priority = 3)
    public void contactus() throws Exception {
        driver.findElement(contacticon).click();
      /*  Set <String> s = driver.getContextHandles();
        for(String frames : s){
            System.out.println(frames);
        }*/
        try {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElementByClassName("android.webkit.WebView")));            //Thread.sleep(20000);
            System.out.println("Contact us page is loaded");
            Thread.sleep(4000);
            //driver.navigate().back();
            driver.pressKeyCode(AndroidKeyCode.BACK);
            test.get().pass("Test Pass");
        } catch (Exception e) {
            e.printStackTrace();
            Utility.captureScreenshot(driver,"contactus_icon_not_available");
            test.get().log(Status.FAIL,"Contact us icon unavailable").addScreenCaptureFromPath(ScreenshotPath+"contactus_icon_not_available");
        }

        WebDriverWait wait = new WebDriverWait(driver,50);
        WebElement loginbutton =  wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(lgnbtn)));
        if (!loginbutton.isDisplayed()){
            driver.pressKeyCode(AndroidKeyCode.BACK);
            //driver.navigate().back();
        }
        System.out.println("User in login page");
    }
}



