package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class test {
	protected WebDriver driver;
	
	@Test
	public void testremote() {
		
		
		String chromedriverPath=System.getProperty("user.dir")+"/src/test/resources/chromedriver";
		System.out.println(System.getProperty("os.name"));
		if(System.getProperty("os.name").toLowerCase().contains("win"))
			   chromedriverPath+=".exe";
		
		System.setProperty("webdriver.chrome.driver",chromedriverPath);
		ChromeOptions options = new ChromeOptions();
	    options.setExperimentalOption("useAutomationExtension", false);
	    options.addArguments("disable-infobars");    
		//options.setHeadless(true);

	    WebDriver driver= new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.google.co.in/");
		String title=driver.getTitle();
		System.out.println(title);
		Assert.assertEquals(title, title);

	}

}
