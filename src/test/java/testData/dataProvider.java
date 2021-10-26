package testData;

import org.testng.annotations.DataProvider;

public class dataProvider {

	
	@DataProvider(name="dataProviderForSearchFunctionality")
	public static Object[][]testDataforVerifySearchFunctionality() throws Throwable{
		String [][] searchCriteria ={{""},{"short"},{"printed"},{"printed dress"},{"Cannon"}};
		return searchCriteria;
		
	}
	
	
	@DataProvider(name="dataProviderForStockUpdateFunctionality")
	public static Object[][]testDataforVerifyStockUpdateFunctionality() throws Throwable{
		String [][] searchCriteria ={{"Blouse"},{"printed dress"}};
		return searchCriteria;
		
	}
	

	
	@DataProvider(name="dataProviderForUploadFunctionalityUsingSendKeys")
	public static Object[][]testDataforVerifyUploadFunctionalityUsingSendKeys() throws Throwable{
		String [][] searchCriteria ={{System.getProperty("user.dir")+"\\test.png"}};
		return searchCriteria;
		
	}
	
	
}
