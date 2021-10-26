package TestWebAutomation;

import java.util.List;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjectModel.HomePage;
import pageObjectModel.SearchResultPage;
import testData.dataProvider;


public class TestSearchFeature extends BaseTest {
	
	//@Test(dataProvider="dataProviderForSearchFunctionality",dataProviderClass=dataProvider.class,groups={"Regression"})
	public void verifySearchFunctionality(String searchCriteria) throws Throwable{
		
		HomePage intHomePage = openApplication("http://automationpractice.multiformis.com/");
		
		SearchResultPage intSearchResultPage = intHomePage.SearchForKeyword(searchCriteria);

		int resultcountDispalyed=intSearchResultPage.getSearchResultCountFrmMsg();
		
		if (searchCriteria.isEmpty()){
			Assert.assertEquals(0, resultcountDispalyed,"No Search Keyword given. Result should give zero count");
			String errMsg=intSearchResultPage.getFailureMessage();
			Assert.assertEquals(errMsg, "Please enter a search keyword", "Message 'Please enter a search keyword' not displayed.");
		}else if(resultcountDispalyed==0){
			String expErrMsg="No results were found for your search \""+searchCriteria+"\"";
			String actualErrMsg=intSearchResultPage.getFailureMessage();
			Assert.assertEquals(actualErrMsg, expErrMsg,"No Product found actual Error Message should be '"+expErrMsg+"' but '"+actualErrMsg+"' was found.");
		}else{
			int resultcountinGrid=intSearchResultPage.getSearchResultCountFrmGrid();
			Assert.assertEquals(resultcountinGrid, resultcountDispalyed,"Result count displayed does not match the number of product displayed in Grid.");
			
			List<String> searchResultProductName = intSearchResultPage.getSearchResultProductName();
			for(String ProductName:searchResultProductName){
				String[] keywordArr = searchCriteria.split(" ");
				for(int i=0;i<keywordArr.length;i++){
					boolean keywordPresent=ProductName.trim().toUpperCase().contains(keywordArr[i].trim().toUpperCase());
					Assert.assertTrue(keywordPresent,"Product '"+ProductName+"' should not be in result for keyword '"+searchCriteria+"'.");
				}
				
			}
			
			
		}
		
		
		
	}

}
