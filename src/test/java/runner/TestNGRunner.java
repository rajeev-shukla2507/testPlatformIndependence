package runner;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestNGListener;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import reporting.ReportingUtility;

public class TestNGRunner {

	public static void main(String[] args) {
		List<XmlSuite> suiteList = new ArrayList<XmlSuite>();
		List<XmlClass> classList = new ArrayList<XmlClass>();
		
		List<Class<? extends ITestNGListener>> listnerList = new ArrayList<Class<? extends ITestNGListener>>();
		
		XmlSuite suiteName =new XmlSuite();
		suiteName.setName("DemoSuite");
		
		XmlTest testName=new XmlTest(suiteName);
		testName.setName("DemoTest");
		
		classList.add(new XmlClass ("TestWebAutomation.TestSearchFeature"));
		classList.add(new XmlClass ("TestWebAutomation.TestStockUpdatedAfterCheckoutFeature"));
		classList.add(new XmlClass ("TestWebAutomation.TestUploadFunctionalityUsingSendKeys"));
		
		List<String> groups=new ArrayList<String>();
		groups.add("Regression");
		
		TestNG TestNGRun = new TestNG();
		testName.setIncludedGroups(groups);
		testName.setXmlClasses(classList);
		
		listnerList.add(ReportingUtility.class);
		suiteList.add(suiteName);
		
		TestNGRun.setXmlSuites(suiteList);
		TestNGRun.setListenerClasses(listnerList);
		
		TestNGRun.run();
	}

}
