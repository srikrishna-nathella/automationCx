import com.aventstack.extentreports.Status;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.internal.DynamicGraph;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public class oneclicktrading extends oneClickTradingPage{

    /*@Test(priority = 0)
    public void indicativerates(){
        driver.findElementByXPath("//android.widget.RelativeLayout[@index='0']/android.widget.Button[@index='13']").click();

    }*/
    /*@Test(priority = 0)
    public void login()
    {
        driver.findElementById("com.msf.currenex.mobile:id/demoBtn").click();
        driver.findElementByXPath("//android.widget.EditText[@text='USER NAME']").sendKeys("MSF3000");
        driver.findElementByXPath("//android.widget.EditText[@index='7']").sendKeys("test1234");
        driver.findElementByXPath("//android.widget.Button[@resource-id='com.msf.currenex.mobile:id/loginBtn']").click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//android.widget.Button[@text='ACCEPT']"))).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if(driver.findElementByXPath("//android.widget.TextView[@text='EULA']").isDisplayed()){
            driver.findElementByXPath("//android.widget.Button[@text='ACCEPT']").click();
            System.out.println("In Login screen");
        }
    }*/
        @Test(priority = 2)
        public void enable_oneclickorder()throws Throwable {
            WebDriverWait wait = new WebDriverWait(driver, 25);
            WebElement el = wait.until(ExpectedConditions.visibilityOf(driver.findElement(ratestext)));
            System.out.println("User logged in successfully");
            //System.out.println("Menu displayed : "+driver.findElement(menu).isDisplayed());
                //driver.findElement(By.id(Package + ":id/mainMenuImageView")).click();
                click_menu();
                select_settings();
                click_oneclicktradingoption();
            if (readXML("oct").equalsIgnoreCase("Y")) {
                if (!driver.findElement(oneclick).isSelected()) {
                    Enableoneclick();
                    System.out.println("One Click Enabled");
                    test.get().log(Status.PASS,"Test Pass");
                }if (readXML("oct").equalsIgnoreCase("N")) {
                    if (driver.findElement(oneclick).isDisplayed()) {
                        Utility.captureScreenshot(driver,"One_click_issue");
                        test.get().log(Status.FAIL,"Expected : Expected : Oneclick disabled, Actual : Oneclick enabled.")
                                .addScreenCaptureFromPath(ScreenshotPath+"One_click_issue.png");

                    }
                }
            }
        }
        @Test(priority = 3)
        public void place_oneclickorder() throws Throwable {
            if (readXML("oct").equalsIgnoreCase("Y")) {
                driver.navigate().back();
                driver.navigate().back();
                Thread.sleep(2000);
                doubletap_rates_button();
                //WebDriverWait wait = new WebDriverWait(driver, 50);
                /*trade.tap_rates_button();
                Assert.assertEquals(readXML("ccypair"),driver.findElementById("com.msf.currenex.mobile:id/ratesSymbolTxt"));*/
                 //WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(offer_rates_button)));
                /*Dimension size = driver.manage().window().getSize();
                int x = size.getWidth()/2;
                int starty = (int)(size.getHeight()*0.60);
                int endy = (int)(size.getHeight()*0.10);
                driver.swipe(x,starty,x,endy,2000);*/
                //TouchAction touch = new TouchAction((MobileDriver) driver);
                //touch.press(element1).release().press(element1).release().perform();


                try {

                    if (driver.findElementByXPath("//android.widget.TextView[@text='Placing order...']").isDisplayed()){
                        test.get().log(Status.PASS, "One click order placed successfully.");
                    }

                }
                catch(Exception e){
                    e.printStackTrace();
                }
                try{
                    if(driver.findElementByXPath("//android.widget.TextView[@text='Create Order']").isDisplayed()){
                        Utility.captureScreenshot(driver,"One_Click_not_placed");
                        test.get().log(Status.FAIL,"Expected : One click Toast message , Actual : One click order not placed - user in create order screen")
                                .addScreenCaptureFromPath(ScreenshotPath,"One_Click_notplaced");
                        driver.pressKeyCode(AndroidKeyCode.BACK);
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
