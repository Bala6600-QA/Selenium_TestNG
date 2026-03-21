package testcase;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.Baseclass;
import pages.Cartpage;
import pages.Checkoutpage;
import pages.Inventoryitemspage;
import pages.Inventorypage;
import pages.Loginpage;
import pages.Logoutpage;

public class TC_001 extends Baseclass {
	
	 @Test (dataProvider = "Logindata")
	public void run_TC_001(HashMap<String, String> data) throws InterruptedException {
		 
		  // Test case for login functionality
        Loginpage loginTest = new Loginpage(driver);
        loginTest.logininputs(data.get("username"),data.get("password"));
        System.out.println("Login succeed with user: " + data.get("username"));
        
        Thread.sleep(3000);       
	}
	 
	 @Test
	    public void run_TC_002() throws InterruptedException, IOException {

	        // Test case for Inventory page functionality
	        Inventorypage inventoryTest = new Inventorypage(driver);
	        
	        String URL = driver.getCurrentUrl();
	        Assert.assertTrue(URL.contains("inventory"));
	        System.out.println("Logged in successfully. Inventory page is displayed");

	        // Verify inventory page and sort items
	        inventoryTest.inventorySort();

	        // Add item to cart and verify cart count
	        inventoryTest.moveToCart();
	    }
	 
	 @Test(dependsOnMethods = "run_TC_002")
		public void run_TC_003() throws InterruptedException, IOException {
		    	        
		        // Test case for Inventory items page functionality
		        Inventoryitemspage inventoryitemsTest = new Inventoryitemspage(driver);
		        inventoryitemsTest.itemslection();
		        
			    // Inventory item page Verification 
                String URL= driver.getCurrentUrl();
                Assert.assertTrue(URL.contains("inventory-item"));
	            System.out.println("Inventory items page is displayed");
	            
	            // Add the item to cart from inventory item page
		        inventoryitemsTest.addtheiteminitempage();
		}
	 
	 @Test(dependsOnMethods = "run_TC_003")
		public void run_TC_004() throws InterruptedException, IOException {
		    	        
		       // Test case for cart page functionality
		           Cartpage cartpageTest = new Cartpage(driver);
		           cartpageTest.clickoncarticon();
		        
		      // Cart page Verification 
			       String cartURL= driver.getCurrentUrl();
			       Assert.assertTrue(cartURL.contains("cart")); 
			     
			  // cart page navigation     
		          cartpageTest.itemscart();

	        }
	 
	 @Test (dataProvider = "Formdata", dependsOnMethods = "run_TC_004")
		public void run_TC_005(HashMap<String, String> data) throws InterruptedException, IOException {
		    	        
		       // Test case for Checkout page functionality
		        Checkoutpage checkoutpageTest = new Checkoutpage(driver);
		        
		       // Verify Checkout Step-1 page functionality
		        try {
		            String checkoutURL1 = driver.getCurrentUrl();
		            Assert.assertTrue(checkoutURL1.contains("checkout-step-one"));
		            System.out.println("Checkout Step 1 page is displayed");
		            
                    checkoutpageTest.checkoutForm(data.get("FirstName"), data.get("LastName"),data.get("PostCode"));
		        }
		        
	           catch (Exception e) {
                   System.out.println("This is existing user or an error occurred: " + e.getMessage());
                }
		        
		       // Verify Checkout Step-2 page functionality
		        String checkoutURL2 = driver.getCurrentUrl();
		        Assert.assertTrue(checkoutURL2.contains("checkout-step-two"));
		        System.out.println("Checkout Step 2 page is displayed");

	            checkoutpageTest.checkoutStep2();
	            
	           // Verify Checkout Step-3 page functionality
	            String checkoutpage3 = driver.getCurrentUrl();
	            Assert.assertTrue(checkoutpage3.contains("checkout-complete"));
	            System.out.println("Checkout Step 3 page is displayed");

	            checkoutpageTest.checkoutStep3();

	       }
	 
	  @Test(dependsOnMethods = "run_TC_005")
	    public void run_TC_006() throws InterruptedException, IOException {
	    	// Test case for Logout page functionality
	        Logoutpage logoutTest = new Logoutpage(driver);
	        
	        String URL = driver.getCurrentUrl();
	        Assert.assertTrue(URL.contains("inventory"), "Expected to be on inventory page but was: " + URL);
	        System.out.println("Navigated back to Inventory page");

	        logoutTest.logout();
	    }

}
