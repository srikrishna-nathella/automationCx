/*

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

*/
/**
 * Created by Sree on 18-01-2017.*//*



public class charts extends common {

@Test(priority = 1)
    public void signin() throws Throwable {
        driver.findElement(bret).click();//selects BRET stack
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
        public void chart_stream_bid ()throws Throwable {
            driver.findElement(repo.menu).click();
            driver.findElement(repo.charts).click();
            String currencypair = driver.findElement(By.id("com.msf.currenex.mobile:id/ratesSymbolTxt")).getText();
            System.out.println("currencypair is : " + currencypair);
            String bidvalue1;
            String bidvalue = driver.findElement(repo.bid_rates_button).getText();
            System.out.println(bidvalue);
            int count = 0;
            try {
                do {
                    bidvalue1 = driver.findElement(repo.bid_rates_button).getText();
                    System.out.println(bidvalue1);
                    Thread.sleep(2000);
                    count = count + 1;
                    System.out.println("Count = " + count);
                    if (count == 5) {
                        System.err.println("Stream is not happening in charts");
                        break;
                    }
                } while (bidvalue1.equals(bidvalue));
                if (!bidvalue1.equals(bidvalue) && count != 5) {
                    System.out.println("Streaming is happening in charts");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        @Test(priority = 2)
        public void chart_stream_offer ()throws Throwable {
            driver.findElement(By.id("com.msf.currenex.mobile:id/symbolsSpinner")).click();
            driver.scrollTo("USD/JPY");
            driver.findElement(By.name("USD/JPY")).click();
            WebDriverWait wait = new WebDriverWait(driver, 50);
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//android.widget.Button[@text='1m']"))));
            driver.findElement(By.xpath("//android.widget.Button[@text='5m']")).click();
            String currencypair = driver.findElement(By.id("com.msf.currenex.mobile:id/ratesSymbolTxt")).getText();
            System.out.println("currencypair is : " + currencypair);
            String offervalue = driver.findElement(repo.offer_rates_button).getText();
            String offervalue1;
            System.out.println(offervalue);
            int count = 0;
            try {
                do {
                    offervalue1 = driver.findElement(repo.offer_rates_button).getText();
                    System.out.println(offervalue1);
                    Thread.sleep(2000);
                    count = count + 1;
                    System.out.println("Count = " + count);
                    if (count == 5) {
                        System.err.println("Stream is not happening in charts.");
                        break;
                    }
                } while (offervalue1.equals(offervalue));
                if (!offervalue1.equals(offervalue) && count != 5) {
                    System.out.println("Streaming is happening in charts.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            driver.navigate().back();//navigates to rates screen
        }
    }

*/
