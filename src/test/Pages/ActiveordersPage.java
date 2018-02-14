import com.aventstack.extentreports.Status;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

/**
 * Created by Srikrishna.N on 12-01-2018.
 */
public class ActiveordersPage extends common {
By menu,activeOrderMenu,activeorderlist,cancelactiveorder,YesBtn,modifyOrderBtn;
 common com = new common();
    public ActiveordersPage() {
    menu=By.id(Package+":id/mainMenuImageView");
    activeOrderMenu=By.xpath("//*[contains(@text,'Active Orders')]");
    activeorderlist=By.id(Package+":id/activeOrderMainLayout");
    cancelactiveorder=By.xpath("//*[contains(@text,'CANCEL ORDER')]");
    YesBtn=By.xpath("//android.widget.Button[@text='Yes']");
    modifyOrderBtn=By.xpath("//android.widget.Button[@text='MODIFY ORDER']");
}

    public void click_menu(){
        driver.findElement(menu).click();
    }
    String ActBCount;
    String charCount;

    public String ActOrderBatchCount() {
        ActBCount = driver.findElement(activeOrderMenu).getText();
        System.out.println(ActBCount.length());
        if ((ActBCount.length() > 12) && (ActBCount.length() <= 17)) {
            charCount = ActBCount.substring(15, 16);
            System.out.println("Active order batch count : " + charCount);
        } else if ((ActBCount.length() > 12) && (ActBCount.length() <= 18)) {
            charCount = ActBCount.substring(15, 17);
        }
        return charCount;
    }

    public void click_activeordermenu(){
        driver.findElement(activeOrderMenu).click();
    }

    public String CheckActOrders(){

        int count = driver.findElementsById(Package+":id/activeOrderMainLayout").size();
        //int count = driver.findElementsById(Package+":id/activeOrdersListView").size();
        System.out.println("Count : "+count);
        String ca = String.valueOf(count);

        return ca;

    }

    public void CancelActOrder(){
        int count = driver.findElementsById(Package+":id/activeOrderMainLayout").size();

        if(count>=1){
            driver.findElement(activeorderlist).click();
            driver.findElement(cancelactiveorder).click();
            driver.findElement(YesBtn).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            try{
                WebDriverWait wait = new WebDriverWait(driver,10);
                WebElement el= wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//*[contains(@text,'Ok')]")));
                el.click();
            }catch (Exception e){
                e.printStackTrace();
            }
            int count1 = driver.findElementsById(Package+":id/activeOrderMainLayout").size();
            Assert.assertNotEquals(count1,count);
        }
    }

    public void ModifyOrder(){
        int count = driver.findElementsById(Package+":id/activeOrderMainLayout").size();
        if(count>0) {
            for (int i = 0; i <= count-1; i++) {
                driver.findElementByXPath("(//android.widget.LinearLayout[@index=1]" +
                        "/android.widget.ListView[@index=1]/android.widget.RelativeLayout[@index="+i+"])").click();
                if (driver.findElement(modifyOrderBtn).isEnabled()) {
                    System.out.println("Modify Enabled");
                    driver.findElement(modifyOrderBtn).click();
                    System.out.println("Before final modify btn");
                    driver.findElementById(Package+":id/TRADE_PLACE_ORDER_BTN").click();
                    System.out.println("Final modify btn");
                    try{
                        if((driver.findElementByXPath("//*contains[@text,'Ok']")).isDisplayed()){
                            driver.findElementByXPath("//*contains[@text,'Ok']").click();
                            test.get().log(Status.SKIP,"Unable to place modified order");

                        }
                    }
                    catch(Exception e){e.printStackTrace();}
                    break;
                }
                driver.pressKeyCode(AndroidKeyCode.BACK);
                System.out.println("No Modified orders enabled in row : "+i);
                System.out.println("value of i : "+i +" , "+ "value of count : "+count);

                if(i==count-1){
                    System.out.println("No Modified orders enabled");
                    test.get().log(Status.SKIP,"No modified orders");
            }

            }

            }
        }
    }

