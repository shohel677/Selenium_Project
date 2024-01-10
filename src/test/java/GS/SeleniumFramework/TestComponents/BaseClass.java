package GS.SeleniumFramework.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public WebDriver driver;
	public WebDriver initializeDriver() throws IOException {
		

		//properties class
		Properties prop =  new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\GS\\SeleniumFramework\\resources\\GlobalData.properties");
		prop.load(fis);
		//String BrowserName = prop.getProperty("browser");
		String BrowserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		if (BrowserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			WebDriverManager.chromedriver().setup();
			
			if(BrowserName.contains("headless")){
			options.addArguments("headless");
			}		
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));//full screen
		}
		else if (BrowserName.equalsIgnoreCase("firefox")) {
			//firefox browser initialization 
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (BrowserName.equalsIgnoreCase("edge")) {
			//firefox browser initialization 
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.manage().window().maximize();
		return driver;
		


	}
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
		
	}
	@BeforeMethod(alwaysRun=true)
	public void launchApplication() throws IOException {
		WebDriver driver= initializeDriver();
		
	
		driver.get("https://rahulshettyacademy.com/client");
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown(ITestResult result) throws InterruptedException {
		
		if(result.getStatus()==ITestResult.FAILURE) {
			System.out.println(result.getMethod());
			System.out.println(result.getThrowable());
			System.out.println("FAILED");
		}
		if(result.getStatus()==ITestResult.SUCCESS) {
			System.out.println(result.getMethod());
			System.out.println("PASSED");
		}

		Thread.sleep(1000);
		driver.quit();
	}




}
