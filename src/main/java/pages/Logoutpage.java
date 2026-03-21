package pages;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Logoutpage {
    @SuppressWarnings("unused")
	private WebDriver driver;

    public Logoutpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[text()=\"Open Menu\"]")
    private WebElement menubutton;

    @FindBy(xpath = "//a[text()=\"Logout\"]")
    private WebElement logoutbutton;

    @FindBy (name="login-button") // Click on login button	
	private WebElement loginclick;
    
    public void logout() throws InterruptedException, IOException {

        menubutton.click();
        Thread.sleep(1000);
        logoutbutton.click();
        Thread.sleep(1000);
        
        if (loginclick.isDisplayed()) {
			System.out.println("Logout successful, login button is displayed");
		} else {
			System.out.println("Logout failed, login button is not displayed");
		}
    }
}
