package testcase;

import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.Baseclass;
import pages.Loginpage;

public class TC_001_Negative extends Baseclass {

    @Test(dataProvider = "NegativeLogindata")
    public void run_TC_001_Negative(HashMap<String, String> data) throws InterruptedException {

        // Loginpage - enter invalid credentials
        Loginpage loginTest = new Loginpage(driver);
        loginTest.logininputs(data.get("username"), data.get("password"));
        Thread.sleep(1000);

        // Verify error message is displayed
        String errorMsg = loginTest.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("Username and password do not match"),
                "Expected error message not displayed. Got: " + errorMsg);
        System.out.println("Negative login verified. Error message: " + errorMsg);
    }
}