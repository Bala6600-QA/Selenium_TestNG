package pages;

import java.io.IOException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Inventorypage {
    private WebDriver driver;

    public Inventorypage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//select[@class=\"product_sort_container\"]")
    private WebElement sortdropdown;

    @FindBy(xpath = "//span[@class=\"active_option\"]")
    private WebElement sortedoption;

    @FindBy(xpath = "(//button[text()='Add to cart'])[3]")
    private WebElement addtocart;

    @FindBy(xpath = "//span[@class=\"shopping_cart_badge\"]")
    private WebElement cartbadge;

    public void inventorySort() throws InterruptedException, IOException {
    	
        // sorting by low to high
        Select sorting = new Select(sortdropdown);
        sorting.selectByValue("lohi"); // low to high

        String selectedsort = sortedoption.getText();
        System.out.println("Sort by: " + selectedsort);
    }

    public Inventoryitemspage moveToCart() throws InterruptedException, IOException {
        addtocart.click();
        String cartcount = cartbadge.getText();
        System.out.println("No of items in the cart: " + cartcount);
        
        return new Inventoryitemspage(driver);
   
    }
}
