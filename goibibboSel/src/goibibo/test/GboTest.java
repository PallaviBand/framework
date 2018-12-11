package goibibo.test;

import java.io.File;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GboTest {
	
	public WebDriver driver;
	Document document;
	File src;
	HtmlReport report;
	File srcP; 
	CrossBrowser cb;

	Logger logger;
	@BeforeClass
	public void driverOpen() throws Exception
	{
		
		cb = new CrossBrowser();
	}
	@Test(priority = 2)
	public void driverScript() throws Exception
	{
		try{
		
		
		cb.openBrowser();
		cb.selectOneWayTrip();
		cb.testInput();
		cb.testDate();
		cb.testTravellers();
		cb.testSelect();
		//cb.testCheckBox();
		cb.testBtn();
		Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println("Exception in test 1: "+e);
		}
	}
	@Test(priority = 1)
	public void driverScript2() throws Exception
	{
		try{
			
		cb.openBrowser();
		cb.selectRoundTrip();
		cb.testInput();
		cb.testDate();
		cb.testTravellers();
		cb.testSelect();
		//cb.testCheckBox();
		cb.testBtn();
		Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println("Exception in test 2: "+e);
		}
	}
	@Test(priority = 0)
	public void driverScript3() throws Exception
	{
		try{
			
		cb.openBrowser();
		cb.selectRoundTrip();
		cb.testInput();
		cb.testDate();
		cb.testTravellersNaN();
		cb.testSelect();
		cb.testBtn();
		Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println("Exception in test 2: "+e);
		}
	}
	
	
	@AfterClass
	public void driverClose() throws Exception
	{
		cb.Close();

	}
	
  
}
