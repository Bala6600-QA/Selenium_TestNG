package base;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import  org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.epam.healenium.SelfHealingDriver;

import utils.ExcelUtil;
import utils.ReportUtil;

public class Baseclass {

    public static WebDriver driver;
    public static final String url = "https://www.saucedemo.com/";
    public static final String openbrowser = "chrome";  // Change the browsers

    public Baseclass() {
        // no-arg constructor required for POM PageObjects
    }
    @BeforeSuite
    public void beforeSuite() {
        try {
            ReportUtil.reportgenerate(); // initialize report here
            System.out.println("BeforeSuite success: Report initialized");
        } catch (Throwable t) {
            System.out.println("BeforeSuite failed: Report not initialized");
            t.printStackTrace();
            throw t;
        }
    }
    @BeforeClass(alwaysRun = true)
    public void launchBrowser() {
        String browser = openbrowser.toLowerCase();
        switch (browser) {
            case "chrome":
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--disable-notifications");
            WebDriver chrome = new ChromeDriver(options);      //assigning to ChromeDriver
            driver = SelfHealingDriver.create(chrome);
            System.out.println(">>> SelfHealingDriver active: " + driver.getClass().getName());// wrap with SelfHealingDriver
                break;
            case "firefox":
            	FirefoxOptions ffoptions = new FirefoxOptions();
                ffoptions.addArguments("--incognito");
                ffoptions.addArguments("--disable-notifications");
                WebDriver firefox = new FirefoxDriver();           //assigning to FirefoxDriver
                driver = SelfHealingDriver.create(firefox);          // wrap with SelfHealingDriver
                break;
            case "edge":
            	EdgeOptions eoptions = new EdgeOptions();
                eoptions.addArguments("--incognito");
                eoptions.addArguments("--disable-notifications");
                WebDriver edge = new EdgeDriver();
            	driver = SelfHealingDriver.create(edge);
                break;
            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
    }
    

    @AfterClass(alwaysRun = true)
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("Browser closed successfully.");
        }
    }
    
    @AfterSuite
    public void tearDownReport() {
        ReportUtil.flushReport();
    }

    @DataProvider(name = "Logindata")
    public Object[][] logindata() throws IOException {
    	return ExcelUtil.getdata("LoginData");
 
    }
    @DataProvider(name = "NegativeLogindata")
    public Object[][] negativelogindata() throws IOException {
        return ExcelUtil.getdata("NegativeLoginData");
    }
    @DataProvider(name= "Formdata")
    public Object[][] checkoutformdata() throws IOException{
		return ExcelUtil.getdata("FormData");
    	
    }
  }
