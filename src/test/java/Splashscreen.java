import io.appium.java_client.android.AndroidDriver;
import javafx.scene.layout.Priority;
import org.apache.xpath.SourceTree;
import org.testng.*;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by Srikrishna.N on 05-05-2017.
 */
public class Splashscreen extends SplashscreenPage {

@Test (priority = 0)
    public void check_copyrights()throws Throwable {
    System.out.println("====== In Splash screen====");
        System.out.println("The splash screen value  " + verify_copyrights());//Will chk the value in the App
        System.out.println(readXML("Copy_Right"));//Will check the value from the XML sheet
    Assert.assertEquals(verify_copyrights(),readXML("Copy_Right"));

    }
    @Test (priority = 1)
    public void check_build_version() throws Throwable {
        Assert.assertEquals(verify_version(), readXML("Version_No"));
    }

}
