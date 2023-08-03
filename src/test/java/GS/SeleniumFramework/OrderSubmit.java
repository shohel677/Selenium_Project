package GS.SeleniumFramework;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import GS.SeleniumFramework.PageObject.CartPage;
import GS.SeleniumFramework.PageObject.CheckoutPage;
import GS.SeleniumFramework.PageObject.LoginPage;
import GS.SeleniumFramework.PageObject.ProductPage;
import GS.SeleniumFramework.PageObject.ThankYouPage;
import GS.SeleniumFramework.TestComponents.BaseClass;
import GS.SeleniumFramework.TestComponents.Retry;

public class OrderSubmit extends BaseClass{

	public Logger logger = LogManager.getLogger(this.getClass());
	

	@Test(groups= {"Order"}, retryAnalyzer=Retry.class)
	public void submitOrder() throws InterruptedException, IOException{
		//launchApplication();
		//Create object for login page 
		logger.info("################################################################################");
		LoginPage loginPage = new LoginPage(driver);
		logger.info("Submit order testcase stared");
		logger.info("Login into site");
		//Login into application through method created on login page class
		loginPage.loginToApplication("shohel@gmail.com", "mKb6m2vD_Fgjze-");
		//Create object of ProductPage class 
		ProductPage productPage = new ProductPage(driver);
		//Add to cart product. ProductPage action "addProductToCrt" is being called
		logger.info("Clicking add to cart button for ADIDAS ORIGINAL");
		productPage.addProductToCart("ADIDAS ORIGINAL");
		logger.info("Clicking add to cart button for IPHONE 13 PRO");
		productPage.addProductToCart("IPHONE 13 PRO");
		//Click add to cart. Action define on product page 
		Thread.sleep(1000);
		logger.info("Clicking button for going to cart page ");
		productPage.goToCartpage();
		
		//Verify product exist on cart
		CartPage cartPage = new CartPage(driver);
		//Get cart product list. Action define on cart page object
		cartPage.getProductListFromCart();
		//Verify. Action define on cart page object
		logger.info("Verifying product existence in cart page ");
		Boolean match = cartPage.verifyProductExistOnCart("ADIDAS ORIGINAL");
		//Assert product
		Assert.assertTrue(match);
		logger.info("Assertion passed");
		//Click checkout button. action define in cart page object 
		logger.info("Clicking checkout button ");
		cartPage.clickCheckoutButton();
		
		//Fill up details on checkout page 
		//Send keys to country field
		logger.info("Landed on checkout page");
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		logger.info("Selecting country India in checkout page");
		checkoutPage.fillCountryData("india");
		//Iterate through country list. Action define on checkout page object
		List<WebElement> country = checkoutPage.getCoutryFromDropDown();
		int countryCount = country.size();
		 for (int i = 0; i<countryCount; i++) {
			 String countryName = country.get(i).getText();
			 if(countryName.equalsIgnoreCase("India")) {
				 country.get(i).click();
				 break;
			 }			 
		 }
		 logger.info("Country india selected from dropdown");
		 //Order submitted
		 logger.info("Clicking submit button");
		 checkoutPage.submitOrder();
		 logger.info("Order submitted");
		 // Thank you message verification in thank you page 
		 
		 ThankYouPage thankYouPage = new ThankYouPage(driver);
		 //Get thank you message 
		 logger.info("Capturing thank you message from thank you page");
		 String confirmationMessage = thankYouPage.closeOrder();
		 //Verify thank you message 
		 Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		 logger.info("Thank you message assertion pass");
		 
		
		
		

	}

}
