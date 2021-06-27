package com.fashioncloud.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
    private static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest logger;

    /**
     * method to set-up browser and extent report
     **/
    @BeforeClass
    public static void setUp() {

        // To set-up chrome driver
        WebDriverManager.chromedriver().setup();
        setDriver(new ChromeDriver());

        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        // To open url in chrome browser
        getDriver().get("http://localhost:8080/");

        // To set-up extent report
        setExtent(new ExtentReports(System.getProperty("user.dir") + "/Report/ExtentReport.html", true));
        getExtent().addSystemInfo("Host Name", "Fashion Cloud").addSystemInfo("User Name", "Rohit")
                .addSystemInfo("OS", System.getProperty("os.name")).addSystemInfo("Chrome", "80");

    }

    /**
     * method to close browser and extent report
     **/
    @AfterClass
    public static void tearDown() {
        
        getExtent().flush();
        getExtent().close();
        getDriver().quit();
    }

    public static ExtentTest getLogger() {
        return logger;
    }

    public static void setLogger(ExtentTest logger) {
        BaseClass.logger = logger;
    }

    public static ExtentReports getExtent() {
        return extent;
    }

    public static void setExtent(ExtentReports extent) {
        BaseClass.extent = extent;
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setDriver(WebDriver driver) {
        BaseClass.driver = driver;
    }

}
