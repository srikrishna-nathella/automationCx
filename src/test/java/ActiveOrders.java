import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Srikrishna.N on 12-01-2018.
 */
public class ActiveOrders extends ActiveordersPage {

   /* @Test(priority = 1)
    public void login() throws IOException
    {
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
        driver.findElementById(Package+":id/demoBtn").click(); //BRET
        //driver.findElementById(Package+":id/liveBtn").click(); //INT2
        driver.findElementByXPath("//android.widget.EditText[@text='USER NAME']").sendKeys("MSF3000_viking");
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
    public void Active_Orders_Count() throws InterruptedException {
    click_menu();
    Thread.sleep(3000L);
    ActOrderBatchCount();
    click_activeordermenu();
    Assert.assertEquals(CheckActOrders(),ActOrderBatchCount());
}

@Test(priority = 3)
    public void Cancel_Active_Order() {
    CancelActOrder();
}

@Test(priority = 4)
    public void Modify_Active_Order(){
    ModifyOrder();

}
}
