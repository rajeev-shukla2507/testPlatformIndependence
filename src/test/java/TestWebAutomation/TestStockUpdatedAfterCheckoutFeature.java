package TestWebAutomation;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectModel.AccountPage;
import pageObjectModel.HomePage;
import pageObjectModel.LoginPage;
import pageObjectModel.ProductDetailsPage;
import pageObjectModel.SearchResultPage;
import pageObjectModel.ShoppingCartPage;
import testData.dataProvider;


public class TestStockUpdatedAfterCheckoutFeature extends BaseTest {
	
	//@Test(dataProvider="dataProviderForStockUpdateFunctionality",dataProviderClass=dataProvider.class,groups={"Regression"})
	public void verifyStockUpdatedAfterCheckoutFeature(String srchKeyword) throws Throwable{
		HomePage intHomePage = openApplication("http://automationpractice.multiformis.com/");
		
		LoginPage intLoginPage = intHomePage.clickSignIn();
		AccountPage accPage = intLoginPage.Login();
		SearchResultPage searchResultPage = accPage.SearchForKeyword(srchKeyword);		
		
		ProductDetailsPage ProductDetailsPage = searchResultPage.Naviagte2ProductDetailsPage();	
		int orgQuantityAvailable =ProductDetailsPage.getQuantityAvailable();		
		ShoppingCartPage shopCartPage = ProductDetailsPage.addProductNCheckout("2");		
		int productOrdered=shopCartPage.getProdQuantityAdded();
		
		shopCartPage.naviagteToPaymentNSubmitOrder();
		String confirmationMsg = shopCartPage.getConfirmationMsg();
		
		Assert.assertEquals(confirmationMsg, "Your order on My Store is complete.","Order not submitted");
		
		searchResultPage = shopCartPage.SearchForKeyword(srchKeyword);
		ProductDetailsPage = searchResultPage.Naviagte2ProductDetailsPage();	
		
		int actualQuantityAvailable =ProductDetailsPage.getQuantityAvailable();		
		ProductDetailsPage.logout();
		
		int expQuantityAvailable= orgQuantityAvailable-productOrdered;		
		Assert.assertEquals(actualQuantityAvailable, expQuantityAvailable);
		
	}
	
	
}
