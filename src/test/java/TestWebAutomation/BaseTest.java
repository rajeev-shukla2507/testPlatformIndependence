package TestWebAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import pageObjectModel.HomePage;

@Listeners(reporting.ReportingUtility.class)
public class BaseTest {
	protected WebDriver driver;
	
	@BeforeClass(alwaysRun=true)
	public void setUp(ITestContext testcontext){
        String chromedriverPath=System.getProperty("user.dir")+"/src/test/resources/";
		
		
		System.out.println(System.getProperty("os.name"));
		if(System.getProperty("os.name").toLowerCase().contains("win")){
			   chromedriverPath+="win/chromedriver.exe";
				
		}else if (System.getProperty("os.name").toLowerCase().contains("linux")){
			
				
			chromedriverPath+= "linux/chromedriver";
			//chromedriverPath = "/testPlatformIndependence/src/test/resources/linux/chromedriver";
			   
		}if  (System.getProperty("os.name").toLowerCase().contains("mac")){
			
			
			  chromedriverPath+= "mac/chromedriver";
			//chromedriverPath = "/usr/bin/chromedriver";
			   
		}
			
		System.out.println("chromedriverPath: "+chromedriverPath);
		System.setProperty("webdriver.chrome.driver",chromedriverPath);
		
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("disable-gpu");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
		testcontext.setAttribute("driver", driver);
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown(){
		this.driver.close();
		this.driver.quit();
	}
	
	public HomePage openApplication(String url){
		this.driver.get(url);
		return(new HomePage(driver));
	}
}
