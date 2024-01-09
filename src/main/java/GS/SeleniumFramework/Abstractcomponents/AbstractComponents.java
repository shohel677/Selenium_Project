package GS.SeleniumFramework.Abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class AbstractComponents {
	
	WebDriver driver;



	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
//	public WebDriver getDriver() {
//		if (driver !=null ) {
//		return driver;
//		}
//		else {
//			return null;
//		}
//	}
	
	public void waitForApplication(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));		
	}
	public void waitForApplicationForInvisibilityElement(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(findBy)));
	}
	public void appJsExecutor(String excution) throws InterruptedException {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript(excution);
		Thread.sleep(1000);
	}
	
	

}
