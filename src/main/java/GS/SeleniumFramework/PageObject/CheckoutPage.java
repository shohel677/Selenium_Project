package GS.SeleniumFramework.PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GS.SeleniumFramework.Abstractcomponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver){
		super(driver);
		//Driver initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	@FindBy(css="input[placeholder='Select Country']")
	WebElement CountryName;
	
	@FindBy(css="button.ta-item span")
	List<WebElement> countryList;
	
	@FindBy(css=".action__submit")
	WebElement submitButton;

	public void fillCountryData(String countryName) throws InterruptedException {
		Thread.sleep(1000);
		//driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("india");
		CountryName.sendKeys(countryName);
		Thread.sleep(1000);
	}
	
	public List<WebElement> getCoutryFromDropDown() {
//		List<WebElement> country = driver.findElements(By.cssSelector("button.ta-item span"));
		 return countryList;
		
	}
	
	public void submitOrder() {
		//driver.findElement(By.cssSelector(".action__submit")).click();
		submitButton.click();
		
	}
	
}
