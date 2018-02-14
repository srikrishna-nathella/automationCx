import io.appium.java_client.android.AndroidKeyCode;
import junit.framework.Assert;
import net.sourceforge.htmlunit.corejs.javascript.regexp.SubString;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by Srikrishna.N on 04-01-2018.
 */
public class PositionsPage extends common {
    By menu,positionMenu,openPositionOrders,protectPositionbtn,closePositionbtn,openPositionDialogbox,OkBtn,
            YesBtn,NoBtn,finalPositionClosebtn,NoRatesAvailable,SettingsIcon;

public PositionsPage(){
        menu = By.id(Package+":id/mainMenuImageView");
        positionMenu = By.xpath("//*[contains(@text,'Positions')]");
        openPositionOrders = By.id(Package+":id/activeOrderMainLayout");
        protectPositionbtn = By.xpath("//android.widget.Button[@text='PROTECT']");
        closePositionbtn = By.xpath("//android.widget.Button[@text='CLOSE']");
        openPositionDialogbox = By.xpath("//*[contains(@text,'There are active order for this currency')]");
        YesBtn = By.xpath("//android.widget.Button[@text='Yes']");
        NoBtn = By.xpath("//android.widget.Button[@text='No']");
        finalPositionClosebtn = By.id(Package+":id/TRADE_PLACE_ORDER_BTN");
        NoRatesAvailable = By.xpath("//*[contains(@text,'No rates available')]");
        OkBtn = By.xpath("//android.widget.Button[@text='OK']");
        SettingsIcon = By.id(Package+":id/rowIcon");
    }

public void clickMenu(){
    driver.findElement(menu).click();
}

    String posBCount;
    String charCount;

    public String PositionBatchCount() {
        posBCount = driver.findElement(positionMenu).getText();
        System.out.println(posBCount.length());
        if ((posBCount.length() > 8) && (posBCount.length() <= 13)) {
            charCount = posBCount.substring(11, 12);
            System.out.println("Position count : " + charCount);
        } else if ((posBCount.length() > 8) && (posBCount.length() <= 14)) {
            charCount = posBCount.substring(11, 13);
        }
        return charCount;
    }
    public void ClickPositions() {
            driver.findElement(positionMenu).click();
        }
    public String CheckPositions(){

    int count = driver.findElementsById(Package+":id/activeOrderMainLayout").size();
    System.out.println("Count : "+count);
    String cp = String.valueOf(count);
    return cp;

}
    public void ClosePosition() throws InterruptedException {

    int count = driver.findElementsById(Package+":id/activeOrderMainLayout").size();

    if (count > 0) {
        driver.findElement(openPositionOrders).click();
        driver.findElement(closePositionbtn).click();
        try {
            if (driver.findElement(openPositionDialogbox).isDisplayed()) {
                driver.findElement(YesBtn).click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try{
            for (int i=0;i<=4;i++){
                System.out.println("Iteration number"+i);
                if(i==4){
                    Assert.fail("No Rates available to place order");
                }
                if(!driver.findElement(NoRatesAvailable).isDisplayed()){
                    break;
                }else {
                    System.out.println("In First try else statement");
                    driver.findElement(OkBtn).click();
                    driver.findElement(closePositionbtn).click();
                }
                    try {
                        System.out.println("In Second try if statement");
                        if (driver.findElement(openPositionDialogbox).isDisplayed()) {
                            driver.findElement(YesBtn).click();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("In if condition of closing position");
        Thread.sleep(3000L);
        driver.findElement(finalPositionClosebtn).click();
        try{
            for (int j=0;j<=4;j++){
                System.out.println("Iteration number"+j);
                if(j==4){
                    Assert.fail("No Streaming rates available to place order");
                }
                if(!driver.findElement(NoRatesAvailable).isDisplayed()) {
                    break;
                }else {
                    System.out.println("In First try else statement");
                    driver.findElement(OkBtn).click();
                    driver.findElement(finalPositionClosebtn).click();
                }
            }
        }catch(Exception e){
                    e.printStackTrace();}

        System.out.println("Close clicked");
    }
    if(!driver.findElementByXPath("//android.widget.TextView[contains(@text,'Order Detail')]").isDisplayed()){
        Assert.fail();
    }  driver.pressKeyCode(AndroidKeyCode.BACK);
        System.out.println("In positions screen");
}
}
