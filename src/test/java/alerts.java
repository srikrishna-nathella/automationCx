/*
import org.apache.log4j.Priority;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

*/
/**
 * Created by Sree on 19-01-2017.*//*



public class alerts extends common {

@Test(priority = 1)
    public void signin() throws Throwable {
        driver.findElement(repo.bret).click();//selects BRET stack
        driver.findElement(repo.username).sendKeys("fastcxmobileA");
        driver.findElement(repo.password).sendKeys("Test1234");
        driver.findElement(repo.lgnbtn).click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, 50);
            WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(repo.eula_donebtn));
            element1.click();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("User logged in successfully.");
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }


    @Test(priority = 1)
    public void place_alert_from_alerticon()throws Throwable{
    driver.findElement(repo.alertpopup).click();
        driver.findElement(repo.managealert).click();
        WebDriverWait wait = new WebDriverWait(driver,50);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(repo.createnewalert)));
        driver.findElement(repo.createnewalert).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(repo.createalert)));
        driver.findElement(repo.createalert).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(repo.okbtn)));
        System.out.println("Alert placed successfully.");
        driver.findElement(repo.okbtn).click();
        driver.navigate().back();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(repo.menu)));
        System.out.println("In Rates screen srikrishna");

    }
}
*/
