package pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Cartpage {
	private WebDriver driver;
	
	public Cartpage(WebDriver driver) {
		this.driver = driver;
	    PageFactory.initElements(driver, this);
	    }
               //xpath for cart icon
	      @FindBy(xpath = "//a[@class=\"shopping_cart_link\"]")
	      WebElement carticon;
              //xpath for checkout button
         @FindBy(xpath = "//button[text()=\"Checkout\"]")
         WebElement checkoutbutton;
	
	 
	 public void clickoncarticon() throws InterruptedException, IOException {
		      
		    //click on cart icon 
	           carticon.click();
	           Thread.sleep(2000);
	 }
	 
	 public Checkoutpage itemscart() throws InterruptedException, IOException {
	      
	        // click checkout button
	      checkoutbutton.click();
	      
	      return new Checkoutpage(driver);

		}
}