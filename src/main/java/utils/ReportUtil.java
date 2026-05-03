package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.epam.healenium.SelfHealingDriver;

import base.Baseclass;

public class ReportUtil {
    static Date currentdate = new Date();
    static String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(currentdate);

    // -------------------- SCREENSHOT METHOD --------------------
    public String takeScreenshot(String testName) {
        String filePath = "";
        try {
            String baseDir = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator;
            File subFolder = new File(baseDir + timestamp);
            subFolder.mkdirs();

            filePath = subFolder + File.separator + testName + "_" + timestamp + ".png";

            /*TakesScreenshot ts = (TakesScreenshot) ((SelfHealingDriver) Baseclass.driver).getDriver();
            File src = ts.getScreenshotAs(OutputType.FILE);
            File dest = new File(filePath);
            FileUtils.copyFile(src, dest);
            System.out.println("Screenshot saved: " + filePath);  */
            
            byte[] srcBytes = ((TakesScreenshot) Baseclass.driver).getScreenshotAs(OutputType.BYTES);
            FileUtils.writeByteArrayToFile(new File(filePath), srcBytes);
            System.out.println("Screenshot saved: " + filePath);
            
        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Screenshot error: " + e.getMessage());
        }
        return filePath;
    }

    // -------------------- REPORT GENERATION --------------------
    private static ExtentReports extent; // The report object
    private static String reportPath;

    public static ExtentReports reportgenerate() {
        try {
            String baseDir = System.getProperty("user.dir") + File.separator + "reports" + File.separator;
            File reportsDir = new File(baseDir);
            reportsDir.mkdirs();

            reportPath = baseDir + "Report_" + timestamp + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle("Automation Report - Saucedemo");
            spark.config().setReportName("Execution Report - " + timestamp);
            spark.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("QA","Bala");
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));

            System.out.println("Report initialized at: " + reportPath);
        } catch (Exception e) {
            System.out.println("Report init failed: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize report", e);
        }

        return extent;
    }

    public static ExtentTest createTest(String testName) {
        if (extent == null) {
            reportgenerate();
        }
        return extent.createTest(testName);
    }

    // -------------------- FLUSH REPORT --------------------
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
            System.out.println("Report generated successfully: " + reportPath);
        }
    }
}


/*		int random = (int) (Math.random() * 9999+9999);
File target = new File("./screenshot/test"+random+".jpg");
FileUtils.copyFile(src, target);  */
	   