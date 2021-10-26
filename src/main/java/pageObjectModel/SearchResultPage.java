package pageObjectModel;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import exceptions.NoProductFoundException;

public class SearchResultPage extends BasePage {

	public SearchResultPage(WebDriver rdriver) {
		super(rdriver);
	}
	
	@FindBy(xpath="//*[@id='product_list']/li")
	List<WebElement> searchResult;
	
	@FindBy(xpath="//*[@id='center_column']/p")
	WebElement 	txtSearchResultFailMessage;
	
	@FindBy(xpath="//*[@id='center_column']/h1/span[@class='heading-counter']")
	WebElement 	txtSearchResultPassMessage;
	
	@FindBy(xpath="//*[@id='center_column']/h1/span[@class='lighter']")
	WebElement 	txtSearchKeyword;
	
	@FindBy(xpath="//*[@id='product_list']/li[1]//a[@class='product-name']")
	WebElement 	lnkFirstProductDetails;
	
	
	public int getSearchResultCountFrmGrid(){
		explicitWaitForElementToBeVisisble(txtSearchResultPassMessage);
		return (searchResult.size());
		
	}
	
	public int getSearchResultCountFrmMsg(){
		explicitWaitForElementToBeVisisble(txtSearchResultPassMessage);
		String resultMsg=txtSearchResultPassMessage.getText().trim();
		int resultCount=Integer.parseInt(resultMsg.split(" ")[0]);
		return resultCount;
		
	}
	
	public List<String> getSearchResultProductName(){
		int SearchResultCount= getSearchResultCountFrmGrid();
		List<String> productNames = new ArrayList<String>();
		for(int i=1;i<=SearchResultCount;i++){
			String productName = driver.findElement(By.xpath("//*[@id='product_list']/li["+i+"]/div/div[2]/h5/a")).getText().trim();
			productNames.add(productName);
		}
		return productNames;
		
	}
	
	public String getFailureMessage(){
		return (txtSearchResultFailMessage.getText().trim());
	}
	
	public ProductDetailsPage Naviagte2ProductDetailsPage() throws Throwable{
		int searchResultCountFrmMsg = getSearchResultCountFrmMsg();
		if(searchResultCountFrmMsg == 0){
			throw new NoProductFoundException("No Product was found for given keyword");
		}		
		lnkFirstProductDetails.click();		
		return (new ProductDetailsPage(driver));
	}
	

	
}
