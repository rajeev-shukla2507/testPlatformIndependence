package pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage {

	public ShoppingCartPage(WebDriver rdriver) {
		super(rdriver);		
	}
	
	@FindBy(xpath="//*[@id='cart_summary']//td[@class='cart_quantity text-center']/input[1]")
	WebElement 	txtQuantityAdded;
	
	@FindBy(xpath="//p/a/span[contains(text(),'Proceed to checkout')]")
	WebElement 	btnSmryCheckout;

	@FindBy(xpath="//button/span[contains(text(),'Proceed to checkout')]")
	WebElement 	btnAddCheckout;
	
	@FindBy(xpath="//button/span[contains(text(),'Proceed to checkout')]")
	WebElement 	btnShpCheckout;
	
	@FindBy(xpath="//*[@id='cgv']")
	WebElement 	chkboxTermsNCond;
	
	
	@FindBy(xpath="//a[contains(@title,'Pay by check')]")
	WebElement 	lnkPayByCheck;
	
	@FindBy(xpath="//*[@id='cart_navigation']/button")
	WebElement 	btnConfirmOrder;
	
	@FindBy(xpath="//*[@id='center_column']/p[1]")
	WebElement 	txtConfirmationMsg; //Your order on My Store is complete.
	
	@FindBy(xpath="//*[@id='search_query_top']")
	WebElement 	boxSearch;
	
	@FindBy(xpath="//*[@id='searchbox']/button")
	WebElement btnSearch;
	
	@FindBy(xpath="//li[contains(@class,'step_current')]/span")
	WebElement 	lblCurrStep;
	
	public int getProdQuantityAdded(){
		explicitWaitForElementToBeVisisble(btnSmryCheckout);
		return Integer.parseInt(txtQuantityAdded.getAttribute("value"));
	}
	
	public String getCurrentStep(){
		explicitWaitForElementToBeVisisble(lblCurrStep);
		String currStep = lblCurrStep.getText().trim().toUpperCase();		
		return currStep;
	}
	
	public void naviagteToPaymentNSubmitOrder(){
		
		String currStep=getCurrentStep();
		
		while (!currStep.contains("PAYMENT")){
			if (currStep.contains("SUMMARY")){
				movetoElementNClick(btnSmryCheckout);
			}else if (currStep.contains("ADDRESS")){
				movetoElementNClick(btnAddCheckout);
			}else if (currStep.contains("SHIPPING")){
				chkboxTermsNCond.click();
				movetoElementNClick(btnShpCheckout);
			}
			currStep=getCurrentStep();			
		}
		
		explicitWaitForElementToBeVisisble(lnkPayByCheck);
		lnkPayByCheck.click();
		explicitWaitForElementToBeVisisble(btnConfirmOrder);
		btnConfirmOrder.click();
	}
	
	public String getConfirmationMsg(){
		explicitWaitForElementToBeVisisble(txtConfirmationMsg);
		return txtConfirmationMsg.getText().trim();
	}
	
	public SearchResultPage SearchForKeyword(String searchKeyword){
		boxSearch.clear();
		boxSearch.sendKeys(searchKeyword);
		btnSearch.click();
		return (new SearchResultPage(driver));
		
	}
	
	public void movetoElementNClick(WebElement elem){
		Actions act=new Actions(driver);
		act.moveToElement(elem).click().perform();
		
	}
}
