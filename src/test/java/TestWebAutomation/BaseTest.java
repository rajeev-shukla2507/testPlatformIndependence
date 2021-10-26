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
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");
		
		String chromedriverPath=System.getProperty("user.dir")+"/src/test/resources/chromedriver";
		
		System.out.println(System.getProperty("os.name"));
		if(System.getProperty("os.name").toLowerCase().contains("win")){
			   chromedriverPath+=".exe";
				
		}else if (System.getProperty("os.name").toLowerCase().contains("linux")){
			   chromedriverPath+="_linux";
		}
			
		System.out.println("chromedriverPath: "+chromedriverPath);
		System.setProperty("webdriver.chrome.driver",chromedriverPath);
		ChromeOptions options = new ChromeOptions();
	    options.addArguments("headless");

		this.driver= new ChromeDriver(options);
		this.driver.manage().window().maximize();
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
