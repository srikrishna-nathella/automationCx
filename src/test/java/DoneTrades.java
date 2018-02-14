import com.aventstack.extentreports.Status;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by Srikrishna.N on 24-01-2018.
 */
public class DoneTrades extends DoneTradePage {

/*@Test(priority = 0)
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
        driver.findElementByXPath("//android.widget.Button[@resource-id='"+Package+":id/loginBtn']").click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//android.widget.Button[@text='ACCEPT']"))).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if(driver.findElementByXPath("//android.widget.TextView[@text='EULA']").isDisplayed()){
            driver.findElementByXPath("//android.widget.Button[@text='ACCEPT']").click();
            System.out.println("In Login screen");
        }
    }*/


@Test(priority = 1)
public void Done_Trade_count() throws InterruptedException {
 click_menu();
    Thread.sleep(3000L);
    DoneTradeBatchCount();
    clickdoneTrademenu();
    Assert.assertEquals(CheckDoneTradeOrders(),DoneTradeBatchCount());
}

@Test(priority = 2)
    public void Done_Trade_swipe(){
    if(!CheckDoneTradeOrders().isEmpty()) {
        System.out.println("We can swipe");
        TouchAction t = new TouchAction(driver);


        WebElement ccypair = driver.findElementByXPath("(//android.widget.LinearLayout[@index=1]" +
                "/android.widget.ListView[@index=1]/android.widget.RelativeLayout[@index=0]/android.widget.LinearLayout[@index=1]/android.widget.TextView[@index=0])");
        WebElement amt = driver.findElementByXPath("(//android.widget.LinearLayout[@index=1]" +
                "/android.widget.ListView[@index=1]/android.widget.RelativeLayout[@index=0]/android.widget.LinearLayout[@index=1]/android.widget.TextView[@index=1])");
        t.press(ccypair).waitAction(2000).moveTo(amt).release().perform();

        //String amountAsString = amt.getText();
        float amount = Float.parseFloat(amt.getText());
        System.out.println("The amount is: " + amount);

        int count = driver.findElementsById(Package + ":id/activeOrderMainLayout").size();
        System.out.println("Count after swipe : " + count);
        if (count > 1) {
            for (int i = 1; i <= count - 1; i++) {
                WebElement ccypair1 = driver.findElementByXPath("(//android.widget.LinearLayout[@index=1]" +
                        "/android.widget.ListView[@index=1]/android.widget.RelativeLayout[@index=" + i + "]/android.widget.LinearLayout[@index=1]/android.widget.TextView[@index=0])");
                WebElement amt1 = driver.findElementByXPath("(//android.widget.LinearLayout[@index=1]" +
                        "/android.widget.ListView[@index=1]/android.widget.RelativeLayout[@index=" + i + "]/android.widget.LinearLayout[@index=1]/android.widget.TextView[@index=1])");
                t.press(ccypair1).waitAction(2000).moveTo(amt1).release().perform();
                float tAmount = Float.parseFloat(amt1.getText());
                System.out.println("The amount is: " + tAmount);
                amount = amount+tAmount;
                System.out.println("Updated amount is : "+amount);
            }
            System.out.println("Final amount is : "+amount);
            float netAmount = Float.parseFloat(driver.findElementById(Package+":id/netAmountTxt").getText());
            Assert.assertEquals(amount,netAmount);
            Assert.assertEquals(ccypair.getText(), driver.findElementById(Package + ":id/symbolTxt").getText());
        }else {
            float netAmount = Float.parseFloat(driver.findElementById(Package+":id/netAmountTxt").getText());
            Assert.assertEquals(amount,netAmount);
        }
    }else {
        driver.pressKeyCode(AndroidKeyCode.BACK);
        test.get().log(Status.SKIP, "No Done trades");
    }
}

}
