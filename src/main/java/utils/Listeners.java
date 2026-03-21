package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import base.Baseclass;

public class Listeners implements ITestListener {
    

    @Override
    public void onTestStart(ITestResult result) {
    	// Create a single ExtentTest object
        ExtentTest test = ReportUtil.createTest(result.getMethod().getMethodName());
        
        // Store it in the ITestResult for later retrieval
        result.setAttribute("extentTest", test);
        
        // Log test start
        test.log(Status.INFO, "Test Started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // need to change
        ExtentTest test = (ExtentTest) result.getAttribute("extentTest");
        if (test == null) {
            test = ReportUtil.createTest(result.getMethod().getMethodName());
            result.setAttribute("extentTest", test);
        }
        test.log(Status.PASS, "Test Passed: " + result.getMethod().getMethodName());
        attachScreenshot(result, test);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // need to change
        ExtentTest test = (ExtentTest) result.getAttribute("extentTest");
        if (test == null) {
            test = ReportUtil.createTest(result.getMethod().getMethodName());
            result.setAttribute("extentTest", test);
        }
        test.log(Status.FAIL, "Test Failed: " + result.getMethod().getMethodName());
        test.fail(result.getThrowable());
        attachScreenshot(result, test);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // need to change
        ExtentTest test = (ExtentTest) result.getAttribute("extentTest");
        if (test == null) {
            test = ReportUtil.createTest(result.getMethod().getMethodName());
            result.setAttribute("extentTest", test);
        }
        test.log(Status.SKIP, "Test Skipped: " + result.getMethod().getMethodName());
        attachScreenshot(result, test);
    }

    @Override
    public void onFinish(ITestContext context) {
    	ReportUtil.flushReport();
    }

    private void attachScreenshot(ITestResult result, ExtentTest test) {
        try {
            if (Baseclass.driver != null) {
                ReportUtil util = new ReportUtil();
                // takeScreenshot returns the saved path
                String screenshotPath = util.takeScreenshot(result.getMethod().getMethodName());
                if (screenshotPath != null && !screenshotPath.isEmpty()) {
                    test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
