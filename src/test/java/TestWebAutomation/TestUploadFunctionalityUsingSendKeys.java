package TestWebAutomation;

import java.awt.Robot;
import java.io.File;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import testData.dataProvider;


public class TestUploadFunctionalityUsingSendKeys extends BaseTest{
	
	@Test(dataProvider="dataProviderForUploadFunctionalityUsingSendKeys",dataProviderClass=dataProvider.class,groups={"Regression"})
	public void verifyUploadaFileFeature(String file) throws Throwable{
		
		File path=new File(file);
		
		String expFileName=path.getName();
		
		driver.get("https://fineuploader.com/demos.html");
		
		WebElement input=driver.findElement(By.xpath("//*[@id='fine-uploader-gallery']/div/div[3]/input"));
		input.sendKeys(file);
		
		
		String actualFileName=driver.findElement(By.xpath("//*[@id='fine-uploader-gallery']/div/ul/li/div[3]/div/span[1]")).getText();;
		
		System.out.println(actualFileName);
		Assert.assertEquals(actualFileName, expFileName);
		
		
        }
        
		
		
	}
