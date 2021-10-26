package pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends BasePage {

	public ProductDetailsPage(WebDriver rdriver) {
		super(rdriver);
	}
	
	@FindBy(xpath="//*[@id='quantityAvailable']")
	WebElement 	txtQuantityAvailable;
	
	@FindBy(xpath="//*[@id='quantity_wanted']")
	WebElement 	txtQuantityWanted;
	
	@FindBy(xpath="//*[@id='add_to_cart']/button")
	WebElement 	btnAddToCart;
	
	@FindBy(xpath="//*[@id='bigpic']")
	WebElement 	imgProduct;
	
	@FindBy(xpath="//a[contains(@title,'View my shopping cart')]")
	WebElement 	lnkMovetoCart;
	
	@FindBy(xpath="//a[contains(@title,'Log me out')]")
	WebElement 	lnkSignout;
	
	
	public int getQuantityAvailable(){	
		explicitWaitForElementToBeVisisble(imgProduct);
		return Integer.parseInt(txtQuantityAvailable.getText().trim());
	}
	
	
	public ShoppingCartPage addProductNCheckout(String cnt){
		txtQuantityWanted.clear();
		txtQuantityWanted.sendKeys(cnt);
		btnAddToCart.click();
		movetoElementNClick(lnkMovetoCart);		
		return (new ShoppingCartPage(driver));
	}
	
	public void movetoElementNClick(WebElement elem){
		Actions act=new Actions(driver);
		act.moveToElement(elem).click().perform();
		
	}
	
	public void logout(){
		lnkSignout.click();
	}


}
