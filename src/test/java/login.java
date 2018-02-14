import android.accounts.NetworkErrorException;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.NetworkConnectionSetting;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import javafx.scene.layout.Priority;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.mobile.NetworkConnection;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by Srikrishna.N on 03-03-2016.
 */

public class login extends loginpage {



    @Test (priority = 0)
    public void check_stack() throws Throwable{
        try{
        if  (readXML("INT2").equalsIgnoreCase("Y")){
            Assert.assertEquals(driver.findElement(int2stack).getText(),"INT2");
        }else if (readXML("INT2").equalsIgnoreCase("N")){
            if((driver.findElement(int2stack).getText().equalsIgnoreCase("INT2")))
            {
                Utility.captureScreenshot(driver,"INT2_stack_is_displayed");
                test.get().log(Status.FAIL,"Expected : INT2 should not be available, Actual : INT2 stack is displayed")
                        .addScreenCaptureFromPath(ScreenshotPath+"INT2_stack_is_displayed.png");
            }
        }
        if (readXML("BRET").equalsIgnoreCase("Y")){
            Assert.assertEquals(driver.findElement(BRETstack).getText(),"BRET");
        }
        else if (readXML("BRET").equalsIgnoreCase("N")){
            if((driver.findElement(BRETstack).getText().equalsIgnoreCase("BRET")))
            {
                Utility.captureScreenshot(driver,"BRET_stack_is_displayed");
                test.get().log(Status.FAIL,"Expected : BRET should not be available, Actual : BRET stack is displayed")
                        .addScreenCaptureFromPath(ScreenshotPath+"BRET_stack_is_displayed.png");
            }
        }
        if (readXML("TKFX").equalsIgnoreCase("Y")){
            Assert.assertEquals(driver.findElement(tkfxstack).getText(),"TKFX");
        }
        else if (readXML("TKFX").equalsIgnoreCase("N")){
            if((driver.findElement(tkfxstack).getText().equalsIgnoreCase("TKFX")))
            {
                Utility.captureScreenshot(driver,"TKFX_stack_is_displayed");
                test.get().log(Status.FAIL,"Expected : TKFX should not be available, Actual : TKFX stack is displayed")
                        .addScreenCaptureFromPath(ScreenshotPath+"TKFX_stack_is_displayed.png");

            }
        }
    }catch(Exception e){
            WebDriverWait wait = new WebDriverWait(driver,50);
            wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath
                    ("\\android.widget.TextView[@text='Server is not responding. Please try again later.']")));
            driver.findElementByXPath("\\android.widget.TextView[@text='Server is not responding. Please try again later.']").getText();
            driver.findElementById(Package+"id/DIALOG_OKAY_BUTTON").click();
            test.get().log(Status.ERROR,"Server not responding").addScreenCaptureFromPath(ScreenshotPath+"Server_Error.png");
            driver.quit();
        }
    }

        @Test (priority = 1)
        public void invalid_credentials()throws Throwable{
            if (readXML("BRET").equalsIgnoreCase("Y")) {
                setBRETStack();//selects BRET stack
            }
            setUsername(readXML("username"));
            setPassword("a1");
            clickLoginbtn();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Assert.assertEquals(viewerrormsg(), "Authentication Failure");
                clickalertmsgOKbtn();
            test.get().pass("Invalid credentials authentication pass");
        }

        @Test(priority = 2)
        public void valid_credentials()throws Throwable{
            setStack();
            setUsername(readXML("username"));
            setPassword(readXML("password"));
            clickLoginbtn();
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            if(readXML("DISCLAIMER").equalsIgnoreCase("N")){
                if(view_disclaimer().equalsIgnoreCase("DISCLAIMER")){
                    Utility.captureScreenshot(driver,"Disclaimer_Present");
                    test.get().log(Status.FAIL,"Expected : No Disclaimer, Actual : Disclaimer is present").addScreenCaptureFromPath(ScreenshotPath+"Disclaimer_Present");
                }
            }
            if (readXML("DISCLAIMER").equalsIgnoreCase("Y")){
                Assert.assertEquals(view_disclaimer(), "DISCLAIMER");
                disclaimeracceptbtn();
                test.get().pass("Disclaimer Test case Passed");
            }
            if (readXML("EULA").equalsIgnoreCase("Y")){
                Assert.assertEquals(view_eula(), "EULA");
                eulaacceptbtn();
                WebDriverWait wait = new WebDriverWait(driver, 25);
                WebElement el = wait.until(ExpectedConditions.visibilityOf(driver.findElement(ratestext)));
                System.out.println("In rates screen");
                test.get().pass("EULA Test case Passed");


            }else {
                WebDriverWait wait = new WebDriverWait(driver, 25);
                WebElement el = wait.until(ExpectedConditions.visibilityOf(driver.findElement(ratestext)));
                System.out.println("User logged in successfully");
                test.get().pass("In rates page : Test passed");
            }
    }

}


