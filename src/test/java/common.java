import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import javafx.scene.image.PixelReader;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.lept;
import org.bytedeco.javacpp.tesseract;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.server.SystemClock;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * Created by Srikrishna.N on 15-03-2016.
 */
public class common {
    static AndroidDriver driver;
    DesiredCapabilities capabilities = new DesiredCapabilities();
    String Build_name = "CGB";
    String Package = "com.msf.currenex.mobile.cgb";
    String ScreenshotPath = System.getProperty("user.dir")+"\\target\\Screenshot\\";
    public static int count = 0;
     static ExtentReports extent;
     static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
     static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();



    @BeforeSuite
    public void beforeSuite() throws IOException, InterruptedException {
        startappiumserver();
        Thread.sleep(20000L);

        extent = ExtentManager.createInstance(System.getProperty("user.dir") + "\\Reports\\" + "Test.html");
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Reports\\" + "Test.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        htmlReporter.config().setReportName(Build_name+" Test Report");

    }

    @BeforeClass
    public synchronized void beforeClass() {
        String class_name = getClass().getName();
        String classname = class_name.replace("_", " ");
        ExtentTest parent = extent.createTest(classname);
        parentTest.set(parent);
    }

    @BeforeMethod
    public synchronized void beforeMethod(Method method) {

        String method_name = method.getName();
        String methodname = method_name.replace("_", " ");
        ExtentTest child = parentTest.get().createNode(methodname);
        test.set(child);
    }

    @AfterMethod
    public synchronized void afterMethod(ITestResult result)throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            Utility.captureScreenshot(driver,result.getName());
            test.get().log(Status.FAIL, MarkupHelper.createLabel(result.getName()
                    + " Test case Failed due to below issues:", ExtentColor.RED)).addScreenCaptureFromPath(ScreenshotPath+result.getName()+".png");
            test.get().fail(result.getThrowable());
        } else if
            (result.getStatus() == ITestResult.SKIP){
            test.get().log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " Test case Skipped due to below reason : ", ExtentColor.ORANGE));
            test.get().skip(result.getThrowable());
        }
        else
        test.get().log(Status.PASS,MarkupHelper.createLabel(result.getName()+" Test case Passed",ExtentColor.GREEN));
    }

    @AfterSuite
    public synchronized void after_suite() throws Exception {
        extent.flush();
        //driver.quit();
        //email.execute("Test.html");
    }

    @BeforeTest
    public void setup() {

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");

        /*capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0");
        capabilities.setCapability(MobileCapabilityType.UDID, "192.168.188.101:5555");//Office Machine*/
        try {
            serverConnection();
        } catch (Exception e) {
        }
    }

    public void startappiumserver() throws IOException {
        Runtime.getRuntime().exec("cmd /c start C:\\Users\\Srikrishna.N\\startappium.bat");
    }

    public void serverConnection() throws MalformedURLException {

        File location = new File("src");
        File AppLocation = new File(location,"CGB.apk");
        capabilities.setCapability(MobileCapabilityType.APP, AppLocation.getAbsolutePath());
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public String readXML(String attribute) {

        String value=null;

        try {

            File fXmlFile = new File(System.getProperty("user.dir")+"\\BuildConfig.xml");
            //File fXmlFile = new File("E:\\Automation\\qa2.automation\\BuildConfig.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();


            // Assigns the Parent tag Builds to nList
            NodeList nList = doc.getElementsByTagName("build");


            //Gets the count and will find the list of builds available
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    if (eElement.getAttribute("name").trim().equalsIgnoreCase(Build_name)) {
                        value =  eElement.getElementsByTagName(attribute).item(0).getTextContent();

                        break;
                    } else {
                        continue;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return value;
    }
}




