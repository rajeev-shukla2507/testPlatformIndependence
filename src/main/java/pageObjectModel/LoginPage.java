package pageObjectModel;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import exceptions.LoginPageNotLoadedSuccessfullyException;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver rdriver) {
		super(rdriver);
	}
	
	@FindBy(id="email")
	WebElement boxEmailId;
	
	@FindBy(id="passwd")
	WebElement boxPasswd;

	@FindBy(id="SubmitLogin")
	WebElement btnSignIn;
	
	public AccountPage Login() throws Throwable{
		try{
			explicitWaitForElementToBeVisisble(boxEmailId);
			boxEmailId.clear();
			boxEmailId.sendKeys("rajeev.shukla.nit@gmail.com");
			boxPasswd.clear();
			boxPasswd.sendKeys("qJuyQCkY");
			btnSignIn.click();
		}catch(Exception e){
			throw new LoginPageNotLoadedSuccessfullyException("Login Page not loaded Successfully");
		}
		return (new AccountPage(driver));
	}

}
