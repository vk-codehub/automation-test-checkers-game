package vk.demo.checkers.test;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	
	WebDriver driver;
	
	
	@BeforeTest
	public void setup() throws InterruptedException
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions cOptions = new ChromeOptions();
		// Inheriting NORMAL page loading strategy
		cOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
	    driver=new ChromeDriver(cOptions);
	    driver.get("https://www.gamesforthebrain.com/game/checkers/");
	    driver.manage().window().maximize();
		
	}
	
		
	@AfterTest
	public void close() throws InterruptedException
	{
		Thread.sleep(5000); //Intentionally calling sleep for page to hibernate before doing a RESTART. 
		driver.close();
		driver.quit();
	}
	

}
