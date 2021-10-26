package pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver rdriver) {
		super(rdriver);
	}
	
	@FindBy(xpath="//*[@title='Log in to your customer account']")
	WebElement lnkSignIn;
	
	@FindBy(id="search_query_top")
	WebElement txtSearchBox;
	
	@FindBy(xpath="//*[@id='searchbox']/button")
	WebElement btnSearch;
	
	
	public LoginPage clickSignIn(){
		lnkSignIn.click();
		return (new LoginPage(driver));
		
	}
	
	public SearchResultPage SearchForKeyword(String searchKeyword){
		txtSearchBox.clear();
		txtSearchBox.sendKeys(searchKeyword);
		btnSearch.click();
		return (new SearchResultPage(driver));
		
	}

}
