package GS.SeleniumFramework;

import java.time.Duration;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumStandAlone2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.findElement(By.id("userEmail")).sendKeys("shohel@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("mKb6m2vD_Fgjze-");
		driver.findElement(By.id("login")).click();
		//.ng-tns-c4-6.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-error")));
		String expectedErrorMsg = driver.findElement(By.cssSelector(".toast-error")).getText();
		String actualErrorMsg ="Incorrect email or password.";
		System.out.println(expectedErrorMsg);
		Assert.assertEquals(expectedErrorMsg, actualErrorMsg);
						
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button.btn:last-child")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		WebElement prodSecond = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals("IPHONE 13 PRO")).findFirst().orElse(null);
		prodSecond.findElement(By.cssSelector(".card-body button.btn:last-child")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase("ADIDAS ORIGINAL"));
		Assert.assertTrue(match);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 250);");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector(".subtotal ul li button")).click();
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		Thread.sleep(1000);
		List<WebElement> country = driver.findElements(By.cssSelector("button.ta-item span"));
		 int countryCount = country.size();
		 for (int i = 0; i<countryCount; i++) {
			 String countryName = country.get(i).getText();
			 if(countryName.equalsIgnoreCase("India")) {
				 country.get(i).click();
				 break;
			 }
			 
		 }
		 driver.findElement(By.cssSelector(".action__submit")).click();
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		 String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		 Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		 
		
		
		

	}

}
