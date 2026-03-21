package pages;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.openqa.selenium.JavascriptExecutor;

public class Checkoutpage {
    private WebDriver driver;

    public Checkoutpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Checkout Step-1
    @FindBy(id = "first-name")
    private WebElement firstname;
    @FindBy(id = "last-name")
    private WebElement lastname;
    @FindBy(id = "postal-code")
    private WebElement postalcode;
    @FindBy(id = "continue")
    private WebElement continuebutton;

    public void checkoutForm(String FirstName, String LastName, String PostCode) throws InterruptedException, IOException {
 
            firstname.sendKeys(FirstName);
            Thread.sleep(500);
            lastname.sendKeys(LastName);
            Thread.sleep(500);
            postalcode.sendKeys(PostCode);
            Thread.sleep(500);
            continuebutton.click();
            }

    // Checkout Step-2 Verification
    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement carticon;
    @FindBy(xpath = "//div[@data-test='subtotal-label']")
    private WebElement itemcost;

    @FindBy(xpath = "//div[@data-test='tax-label']")
    private WebElement taxcost;

    @FindBy(xpath = "//div[@data-test='total-label']")
    private WebElement totalcost;
    @FindBy(id = "finish")
    private WebElement finishbutton;

    public void checkoutStep2() throws InterruptedException, IOException {
    	
        String itemtotalRaw = itemcost.getText();
        String taxRaw = taxcost.getText();
        String totalRaw = totalcost.getText();

        // Extract numeric parts only
        String itemNumeric = itemtotalRaw.replaceAll("[^0-9.]", "");
        String taxNumeric = taxRaw.replaceAll("[^0-9.]", "");
        String totalNumeric = totalRaw.replaceAll("[^0-9.]", "");

        double itemTotalValue = Double.parseDouble(itemNumeric);
        double taxValue = Double.parseDouble(taxNumeric);
        double totalDispValue = Double.parseDouble(totalNumeric);

        System.out.println("Item total is " + itemTotalValue);
        System.out.println("Tax amount is " + taxValue);
        System.out.println("Total amount is " + totalDispValue);

        Assert.assertEquals(totalDispValue, itemTotalValue + taxValue, 0.01);

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", finishbutton);
        finishbutton.click();
    }

    // Checkout page-3 Complete Verification
    @FindBy(xpath = "//h2[text()=\"Thank you for your order!\"]")
    private WebElement successpopup;
    @FindBy(name = "back-to-products")
    private WebElement homebutton;

    public Logoutpage checkoutStep3() throws InterruptedException, IOException {

        String successmsg = successpopup.getText();
        System.out.println(successmsg + " is displayed");

        homebutton.click();
        
        return new Logoutpage(driver);
    }
}
