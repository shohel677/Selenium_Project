package GS.SeleniumFramework.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import GS.SeleniumFramework.Abstractcomponents.AbstractComponents;

public class ThankYouPage extends AbstractComponents{
	
	WebDriver driver;
	
	public ThankYouPage(WebDriver driver){
		super(driver);
		//Driver initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	By waitForHeroThankYou = By.cssSelector(".hero-primary");
	@FindBy(css=".hero-primary")
	WebElement thankYouMsg;
	
	
	public String closeOrder(){
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
//		 String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
//		 Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		waitForApplication(waitForHeroThankYou);
		String confirmationMessage = thankYouMsg.getText();
		return confirmationMessage;
		 
	
		
	}
	
	
	

}
