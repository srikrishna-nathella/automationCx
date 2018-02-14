import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import javafx.scene.layout.Priority;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Srikrishna.N on 04-01-2018.
 */
public class Positions extends PositionsPage {

    /*@Test(priority = 1)
    public void login() throws IOException {
        try {
            System.out.println("In try block");
        WebDriverWait wait = new WebDriverWait(driver,120);
            System.out.println("In try block1");
        WebElement el = wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath
                ("//android.widget.TextView[@text='Server is not responding. Please try again later.']")));
            System.out.println("In try block2");
            el.getText();
            System.out.println("In try block3");
        driver.findElementById(Package + "id/DIALOG_OKAY_BUTTON").click();
            System.out.println("In try block4");
        test.get().log(Status.ERROR, "Server not responding").addScreenCaptureFromPath(ScreenshotPath + "Server_Error.png");
            System.out.println("In try block5");
        driver.quit();
    }catch(Exception e){
        System.out.println("No server error");
    }
        //driver.findElementById(Package+":id/demoBtn").click(); BRET
        driver.findElementById(Package+":id/liveBtn").click(); //INT2
        driver.findElementByXPath("//android.widget.EditText[@text='USER NAME']").sendKeys("MSF4001");
        driver.findElementByXPath("//android.widget.EditText[@index='7']").sendKeys("Test1234");
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
    public void positions_count() throws InterruptedException {
        System.out.println("Before clicking menu");
        clickMenu();
        System.out.println("After clicking menu, bfr clicking positions");
        Thread.sleep(3000L);
        PositionBatchCount();
        ClickPositions();

        System.out.println("Positions clicked");
         //cp=String.valueOf(checkPositions());
        System.out.println("position count in position screen cp : "+CheckPositions());
        System.out.println("Position batch count in menu : "+charCount);
        Assert.assertEquals(CheckPositions(),PositionBatchCount());


    }

    @Test(priority = 3)
    public void position_close() throws InterruptedException{
        ClosePosition();
    }



}
