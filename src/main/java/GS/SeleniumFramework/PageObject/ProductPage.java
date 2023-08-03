package GS.SeleniumFramework.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import GS.SeleniumFramework.Abstractcomponents.AbstractComponents;

public class ProductPage extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductPage(WebDriver driver){
		super(driver);
		//Driver initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//	WebElement prod = products.stream().filter(product->
//	product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
//	prod.findElement(By.cssSelector(".card-body button.btn:last-child")).click();
//	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
	By productVisibilty = By.cssSelector(".mb-3");
	By waitForProductAddedMsgVisible = By.cssSelector("#toast-container");
	By waitForProductAddedMsgInvisible = By.cssSelector(".ng-animating");
	By productTitle = By.cssSelector("b");
	By addToCartButton = By.cssSelector(".card-body button.btn:last-child");
	@FindBy(css= ".mb-3")
	List<WebElement> productList;
	@FindBy(css="[routerlink*='cart']")
	WebElement clickCart;
	
	//Get product list on product page 
	public List<WebElement> getProductList() {
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
//		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		waitForApplication(productVisibilty);
		return productList;
	}
	
	//Get product name  from getProductList action above
	public WebElement getProductByName(String productName) {
//		WebElement prod = products.stream().filter(product->
//		product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
		WebElement prod = getProductList().stream().filter(product->
		product.findElement(productTitle).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	//Add to cart product for found product from above action 
	public void addProductToCart(String productName) {
//		prod.findElement(By.cssSelector(".card-body button.btn:last-child")).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		getProductByName(productName).findElement(addToCartButton).click();
		waitForApplication(waitForProductAddedMsgVisible);
		waitForApplicationForInvisibilityElement(waitForProductAddedMsgInvisible);
	}

	//Go to cart page by clicking cart icon from product page
	public void goToCartpage() {
		//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		clickCart.click();
	}


	
	

	
	
	

}
