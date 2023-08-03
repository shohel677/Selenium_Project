package GS.SeleniumFramework;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import GS.SeleniumFramework.PageObject.LoginPage;
import GS.SeleniumFramework.TestComponents.BaseClass;
import GS.SeleniumFramework.TestComponents.Retry;


public class Login extends BaseClass{
	
	public Logger logger = LogManager.getLogger(this.getClass());

	@Test(groups= {"Login"}, retryAnalyzer=Retry.class)

	public void login() throws InterruptedException, IOException{	
		
		//Create object for login page 
		logger.info("################################################################################");
		logger.info("Positive login testcase started");
		LoginPage loginPage = new LoginPage(driver);
		//Login into application through method created on login page class
		loginPage.loginToApplication("shohel@gmail.com", "mKb6m2vD_Fgjze-");
		logger.info("Positive login testcase successfully executed");


	}
	@Test(groups= {"Login"}, retryAnalyzer=Retry.class)
	public void loginInvalidPassword() throws InterruptedException, IOException{
		//Create object for login page 
		logger.info("################################################################################");
		logger.info("Negative login testcase started");
		LoginPage loginPage = new LoginPage(driver);
		//Login into application through method created on login page class
		//Valid user name & invalid password
		logger.info("Inputting valid email and invalid password");
		String expectedErrMsg = loginPage.negetiveLoginToApplication("shohel@gmail.com", "ybtyuduyer");
		System.out.println(expectedErrMsg);
		logger.info("Capturing error message");
		String actualErrorMsg ="Incorrect email  password.";
		logger.info("Asserting negative login testcase error message");
		Assert.assertEquals(expectedErrMsg, actualErrorMsg);
		logger.info("Assertion passed");
		logger.info("Negative login testcase successfully executed");
		

	
		
		
		
	
		


	}

}
