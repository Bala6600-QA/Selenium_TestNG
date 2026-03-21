package pages;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Inventoryitemspage {
		private WebDriver driver;
		
	public Inventoryitemspage(WebDriver driver) {
		   this.driver = driver;
	       PageFactory.initElements(driver, this);
	    }
	@FindBy(xpath="(//div[@class=\"inventory_item_name \"])[2]/parent::a")
	private WebElement selectitem;

	@FindBy(id="add-to-cart")
	private WebElement addtocartbutton;

	@FindBy(xpath="//span[@class=\"shopping_cart_badge\"]")
	private WebElement cartbadge;
	
    @FindBy(xpath = "(//button[text()='Add to cart'])[3]")
    private WebElement addtocart;

   
	public void itemslection() {
		   
		    // Select the item from inventory page
		          selectitem.click();
	  }
	  
   public Cartpage addtheiteminitempage() throws InterruptedException, IOException {
		 
	  	   // adding the item to cart from inventory item page
	     
	             addtocartbutton.click();
	
	       // no of items displayed in cart icon
	     
	             String cartcount = cartbadge.getText();
	             System.out.println("No of items in the cart:"+ cartcount);
	 
	        
	             return new Cartpage(driver);
	    }
	   
	}