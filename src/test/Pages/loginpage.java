import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

/**
 * Created by Sree on 18-04-2017.
 */
public class loginpage extends common{


   // String Package = "jp.co.yjfx.cnex"; //yjfx
    By username,password,BRETstack,int2stack,lgnbtn=null,errormsg,okbtn,disclaimer,disclaimer_donebtn,disclaimer_declinebtn,tkfxstack,ratestext;


    public loginpage()
    {
        username = By.id(Package+":id/LOGIN_USERNAME_LBL");
        password = By.id(Package+":id/LOGIN_PASSWORD_LBL");
        lgnbtn=By.id(Package+":id/loginBtn1");
        errormsg = By.id(Package+":id/DIALOG_CONTENT");
        okbtn = By.id(Package+":id/DIALOG_OKAY_BUTTON");
        disclaimer = By.id(Package+":id/pageTitleTextView");
        disclaimer_donebtn = By.id(Package+":id/BOTTOM_DONE_BTN");
        disclaimer_declinebtn = By.id(Package+":id/BOTTOM_CANCEL_BTN");
        BRETstack = By.id(Package+":id/demoBtn");
        int2stack = By.id(Package+":id/liveBtn");
        tkfxstack = By.id(Package+":id/betaBtn");
        ratestext = By.id(Package+":id/pageTitleTextView");
    }
    public void setBRETStack(){
        driver.findElement(BRETstack).click();
    }

    public void setint2Stack(){
        driver.findElement(int2stack).click();
    }

    public void setTKFXStack(){
        driver.findElement(tkfxstack);
    }

    public void setStack(){

        String stack = readXML("stack_to_login");
        System.out.println("stack is : "+stack);
        driver.findElementByXPath("//android.widget.Button[@text='"+stack+"']").click();
    }

    public void setUsername(String uName)
    {
        driver.findElement(username).sendKeys(uName);
    }

    public void setPassword(String pword) {
        driver.findElement(password).sendKeys(pword);
    }

    public void clickLoginbtn() {

        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            System.out.println(driver.findElement(lgnbtn).isDisplayed());
            driver.findElement(lgnbtn).click();
        } catch (Exception e) {
            lgnbtn = By.id(Package + ":id/loginBtn");
            clickLoginbtn();
        }
    }

    public void clickalertmsgOKbtn(){
        driver.findElement(okbtn).click();
    }

    public String view_disclaimer() throws InterruptedException {
        Thread.sleep(10000);
        String disclaimer_msg = driver.findElement(disclaimer).getText();
        return disclaimer_msg;
    }

    public String view_eula() throws InterruptedException {
        Thread.sleep(10000);
        String eula_msg = driver.findElement(disclaimer).getText();
        return eula_msg;
    }
    public String viewerrormsg() throws InterruptedException {
        Thread.sleep(10000);
        String error_msg="null";
                try {error_msg= driver.findElement(errormsg).getText();}catch (Exception e){}
        //driver.findElement(okbtn).click();
        return error_msg;
    }

    public void disclaimeracceptbtn(){
        driver.findElement(disclaimer_donebtn).click();
    }

    public void disclaimerdeclinebtn(){
        driver.findElement(disclaimer_declinebtn).click();
    }

    public void euladeclinebtn(){
        driver.findElement(disclaimer_declinebtn).click();
    }

    public void eulaacceptbtn(){
        driver.findElement(disclaimer_donebtn).click();
    }

    public String ratestext(){
        String rates_text = driver.findElement(ratestext).getText();
        return rates_text;
    }
}
