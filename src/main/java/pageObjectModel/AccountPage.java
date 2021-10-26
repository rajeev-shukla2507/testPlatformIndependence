package pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage {

	public AccountPage(WebDriver rdriver) {
		super(rdriver);
	}
	
	@FindBy(xpath="//*[@id='center_column']//h1[text()='My account']")
	WebElement header;
	
	@FindBy(xpath="//*[@id='search_query_top']")
	WebElement txtSearchBox;
	
	@FindBy(xpath="//*[@id='searchbox']/button")
	WebElement btnSearch;
	
	public SearchResultPage SearchForKeyword(String searchKeyword){
		explicitWaitForElementToBeVisisble(btnSearch);
		txtSearchBox.clear();
		txtSearchBox.sendKeys(searchKeyword);
		btnSearch.click();
		return (new SearchResultPage(driver));
		
	}
	

}
