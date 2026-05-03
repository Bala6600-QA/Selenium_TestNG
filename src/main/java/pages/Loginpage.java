package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Loginpage {
  
	@SuppressWarnings("unused")
	private WebDriver driver;

    public Loginpage(WebDriver driver) {
    	this.driver = driver;
	   PageFactory.initElements(driver, this);
		}
	  
	   @FindBy(className ="user-name") // change to name attribute to className for healenium compatibility
		private WebElement loginname;
	   @FindBy (name="password") // Enter password 
	   private WebElement loginpassword;
	   @FindBy (name="login-button") // Click on login button	
	   private WebElement loginclick;                                   
	    @FindBy(xpath = "//h3[@data-test='error']")
	    private WebElement errormessage; // Error message locator for failed login
		public Inventorypage logininputs(String username, String password) {
		   loginname.sendKeys(username);
		   loginpassword.sendKeys(password);
		   loginclick.click();
		   
		   return new Inventorypage(driver);
	   }
		// Returns error message text for negative test verification
	    public String getErrorMessage() {
	        return errormessage.getText();
	    }
	}
