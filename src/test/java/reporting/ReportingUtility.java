package reporting;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

public class ReportingUtility extends TestListenerAdapter{
	
	public ExtentSparkReporter sprkReport;
	public ExtentReports extentReport;
	public ExtentTest extentTest;
	String fullReportName;
	
	private static ThreadLocal<ExtentTest> methodTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> dataProviderTest = new ThreadLocal<>();
	
	private int count=1;
	
	public void onStart(ITestContext testContent){
		String timeStamp=new SimpleDateFormat("dd MMM_yyyy-k_mm_ss a").format(new Date());
		String reportName= "Test-Report "+timeStamp+".html";
		String outFolder="/Test-Reports/";
		Path path= Paths.get(System.getProperty("user.dir")+outFolder);
		
		if(!Files.exists(path)){
			try{
				Files.createDirectories(path);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		fullReportName=path+"/"+reportName;
		sprkReport=new ExtentSparkReporter(fullReportName).viewConfigurer().viewOrder().as(new ViewName[]{ViewName.TEST,ViewName.DASHBOARD,ViewName.DEVICE,ViewName.LOG,ViewName.EXCEPTION,ViewName.AUTHOR}).apply();
		
		extentReport = new ExtentReports();		
		extentReport.attachReporter(sprkReport);
		extentReport.setSystemInfo("Application", "Test Website");
		extentReport.setSystemInfo("User Name", "Rajeev");
		extentReport.setSystemInfo("Environment", "TEST");
		
		sprkReport.config().setCss("css-string");
		sprkReport.config().setDocumentTitle("Test Results");
		sprkReport.config().setTheme(Theme.STANDARD);
		sprkReport.config().setReportName("Test Report");
		sprkReport.config().setTimelineEnabled(true);
		sprkReport.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss a");
		
	}
	
	public void onTestStart(ITestResult result) {
       String methodName = result.getMethod().getMethodName();
        
        if(result.getParameters().length >0){
        	if(methodTest.get() != null && methodTest.get().getModel().getName().equals(methodName))
        		{}
        	else{
        		createTest(result);
        	}
        	String paramName= Arrays.asList(result.getParameters()).toString();
        	ExtentTest paramTest=methodTest.get().createNode("TestData-"+count+" : "+paramName);
        	dataProviderTest.set(paramTest);
        	count++;
        }else{
        	createTest(result);
        }
    }
	
	public void createTest(ITestResult result){
		String methodName = result.getMethod().getMethodName();
		ExtentTest test=extentReport.createTest(methodName);
		methodTest.set(test);
		
		String[] groups = result.getMethod().getGroups();
		if(groups.length>0){
			Arrays.asList(groups).forEach(x -> methodTest.get().assignCategory(x));
		}
		
	}
	
	public void onTestSuccess(ITestResult result) {
		getTest(result).pass(MarkupHelper.createLabel(result.getName()+" - Test Case Passed",ExtentColor.GREEN));
		getTest(result).pass("Test passed");	
	}
	
	

	private ExtentTest getTest(ITestResult result){
		ExtentTest t=result.getParameters() !=null && result.getParameters().length >0?dataProviderTest.get():methodTest.get();
		return t;
				
	}
	
	 public void onTestFailure(ITestResult result) {
	       ITestContext testContext = result.getTestContext();
	       WebDriver driver=(WebDriver) testContext.getAttribute("driver");
	       
	       getTest(result).fail(MarkupHelper.createLabel(result.getName()+" - Test case Failed", ExtentColor.RED));
	       getTest(result).fail(result.getThrowable());
	       
	       LocalDateTime currDateTime= LocalDateTime.now();
	       DateTimeFormatter dateFormat= DateTimeFormatter.ofPattern("dd-MMM-yyyy");
	       String formattedDate=currDateTime.format(dateFormat).toString();
	       
	       DateTimeFormatter timeFormat= DateTimeFormatter.ofPattern("hh-mm-ss a");
	       String formattedTime=currDateTime.format(timeFormat).toString();
	       
	       String ssPath=System.getProperty("user.dir")+"\\Screenshots\\"+formattedDate+"\\"+result.getName()+" "+formattedTime+".png";
	       File file=new File(ssPath);
	       
	       if(!file.exists()){
	    	   try{
	    		   takeScreenshot(ssPath,driver);
	    	   }catch(Throwable e){
	    		   e.printStackTrace();
	    	   }
	       }
	       
	       getTest(result).fail(result.getMethod().getMethodName()+" "+formattedTime,MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64(driver)).build());
	       
	       
	       
	 }

	private String getBase64(WebDriver driver) {
		return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
	}

	private void takeScreenshot(String sspath, WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor)driver;
	    jse.executeScript("window.scrollBy(0,450)", "");
	    
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try{
			FileUtils.copyFile(src, new File(sspath));
		}catch(IOException e){
			e.printStackTrace();
		}
		
		
		
	}
	
	public void onFinish(ITestContext textContext){
		extentReport.flush();
		methodTest.remove();
		try{
			Desktop.getDesktop().browse(new File(fullReportName).toURI());
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	 
	  
}
