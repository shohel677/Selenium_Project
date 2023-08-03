package GS.SeleniumFramework.PageObject;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GS.SeleniumFramework.Abstractcomponents.AbstractComponents;

public class CartPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CartPage(WebDriver driver){
		super(driver);
		//Driver initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productInCart;
	
	@FindBy(css=".subtotal ul li button")
	WebElement clickCheckOutIcon;
	
	public List<WebElement> getProductListFromCart() {
		//List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		return productInCart;
	}

	public Boolean verifyProductExistOnCart(String productName) {
		//Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
		return getProductListFromCart().stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
	}

	public void clickCheckoutButton() throws InterruptedException {
//		JavascriptExecutor jse = (JavascriptExecutor)driver;
//		jse.executeScript("scroll(0, 250);");
//		Thread.sleep(1000);
//		driver.findElement(By.cssSelector(".subtotal ul li button")).click();		
		//Scroll down to checkout button on cart page & set scrolling value
		appJsExecutor("scroll(0, 250);");
		clickCheckOutIcon.click();
		
	}
	

}
