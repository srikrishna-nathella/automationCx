import org.openqa.selenium.By;

/**
 * Created by Srikrishna.N on 22-01-2018.
 */
public class DoneTradePage extends common {

By menu,noDoneTrades,doneTradeMenu,doneTradelist;

    public DoneTradePage(){
        menu=By.id(Package+":id/mainMenuImageView");
        doneTradeMenu=By.xpath("//*[contains(@text,'Done Trades')]");
        doneTradelist=By.id(Package+":id/activeOrderMainLayout");
        noDoneTrades = By.xpath("//android.widget.TextView[@text = 'You have no Done Trades.']");

    }

    public void click_menu(){
        driver.findElement(menu).click();
    }
    String DoneBCount;
    String charCount;

    public String DoneTradeBatchCount() {
        DoneBCount = driver.findElement(doneTradeMenu).getText();
        System.out.println(DoneBCount.length());
        if ((DoneBCount.length() > 12) && (DoneBCount.length() <= 15)) {
            charCount = DoneBCount.substring(13, 14);
            System.out.println("Active order batch count : " + charCount);
        } else if ((DoneBCount.length() > 12) && (DoneBCount.length() <= 16)) {
            charCount = DoneBCount.substring(13, 15);
        }
        return charCount;
    }

    public void clickdoneTrademenu(){
        driver.findElement(doneTradeMenu).click();
    }

    public String  CheckDoneTradeOrders(){

        int count = driver.findElementsById(Package+":id/activeOrderMainLayout").size();
        //int count = driver.findElementsById(Package+":id/activeOrdersListView").size();
        System.out.println("Count : "+count);
        String cdt = String.valueOf(count);

        return cdt;

    }



}



