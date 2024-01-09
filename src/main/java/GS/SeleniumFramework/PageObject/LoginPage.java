package GS.SeleniumFramework.PageObject;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import GS.SeleniumFramework.Abstractcomponents.AbstractComponents;

public class LoginPage extends AbstractComponents{
	
	WebDriver driver;
	//private static Logger log = Logger.getLogger(LoginPage.class);
//    @BeforeClass
//    public static void setup() {
//        PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\main\\java\\GS\\SeleniumFramework\\resources\\log4j.properties");
//        // Other setup code for Selenium WebDriver and other configurations
//    }
	public LoginPage(WebDriver driver){
		super(driver);
		//Driver initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement userEmail;
	@FindBy(id="userPassword")
	WebElement userPassword;
	@FindBy(id="login")
	WebElement login;
	By waitErrorMsg = By.cssSelector(".toast-error");
	@FindBy(css=".toast-error")
	WebElement errMsg;
	
	public void loginToApplication(String email,String pasword){
//		driver.findElement(By.id("userEmail")).sendKeys("shohel@gmail.com");
//		driver.findElement(By.id("userPassword")).sendKeys("mKb6m2vD_Fgjze-");
//		driver.findElement(By.id("login")).click();
		//log.info("Enter valid email");
		userEmail.sendKeys(email);
		//log.info("Enter valid Password");
		userPassword.sendKeys(pasword);	
		//log.info("Click login button");
		login.click();
		
		
		
	}
	public String negetiveLoginToApplication(String email,String pasword){
//		driver.findElement(By.id("userEmail")).sendKeys("shohel@gmail.com");
//		driver.findElement(By.id("userPassword")).sendKeys("mKb6m2vD_Fgjze-");
//		driver.findElement(By.id("login")).click();
		//log.info("Enter wrong email");
		userEmail.sendKeys(email);
		//log.info("Enter Password");
		userPassword.sendKeys(pasword);	
		//log.info("Click login button");
		login.click();
	
		waitForApplication(waitErrorMsg);
		
		String expectedErrorMsg = errMsg.getText();
		return expectedErrorMsg;
		
	}
	
	
	
	
	

}
