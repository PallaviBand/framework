package goibibo.test;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class CrossBrowser {
	
	public WebDriver driver;
	Document document;
	File src;
	GboMethod gmethod;
	File srcP; 
	Scanner sc;
	String tripstatus;
	HtmlReport report;
	Logger logger;
	int ch = 0;
	
	public void openBrowser() throws Exception{
	  	 	
			logger = Logger.getLogger("CrossBrowser");
			PropertyConfigurator.configure("./Configure/log4j.properties");
		
			File srcP = new File("./Configure/gbo.property");
		    FileInputStream fisP = new FileInputStream(srcP);
			Properties pro = new Properties();
			pro.load(fisP);
			
			sc = new Scanner(System.in);
			String path,url;
			if (ch == 0)
			{
				
				int choice;
				System.out.println("\n Which browser would you like to use? \n 1. Google Chrome \n 2. Mozilla Firefox \n 3. Internet Explorer \n");
				choice = Integer.parseInt(sc.next());
				switch(choice)
				{
				case 1 : 	
							
							url = pro.getProperty("Chromeurl");
							System.out.println(url);
							path=pro.getProperty("Chromepath");
							System.setProperty(url,path);
							driver=new ChromeDriver();
							logger.info("Chrome Browser Opened");
							ch = 1;
							break;
							
				case 2 : 	
							
							driver = new FirefoxDriver();
							logger.info("FireFox Browser Opened");
							ch = 1;
							break;
							
				case 3 : 	
							url = pro.getProperty("IEurl");
							System.out.println(url);
							path=pro.getProperty("path");
							System.setProperty(url,path);
							driver=new InternetExplorerDriver();
							logger.info("Internet Explorer Opened");
							ch = 1;
							break;
					
				default : System.out.println("\n Invalid Choice \n");
				
				}
			}
			
			driver.get("https://www.goibibo.com/flights/");
			logger.info("Loading Website");
			src = new File("./Configure/gbo.xml");
			FileInputStream fis = new FileInputStream(src);
			gmethod = new GboMethod(driver);
			SAXReader sax=new SAXReader();
			document = sax.read(fis);
			
		}
  
  
	  	public void selectOneWayTrip() throws Exception
	  	{
	  		tripstatus = "One Way";
	  		String path="//Details/trip/";
	  		By tripBtn;
	  		tripBtn=By.id(document.selectSingleNode(path+"oneway").getText());
	  		gmethod.ClickBtn(tripBtn);
	  		logger.info("One Way Trip Selected");
	  		
	  	}
	  	public void selectRoundTrip() throws Exception
	  	{
	  		tripstatus = "Round Trip";
	  		String path="//Details/trip/";
	  		By tripBtn;
	  		tripBtn=By.id(document.selectSingleNode(path+"roundtrip").getText());
	  		gmethod.ClickBtn(tripBtn);
	  		logger.info("Round Trip Selected");
	  		
	  	}
	  	
	  	
	  public void testInput() throws Exception {

		  //to read the property file create an obj of properties class
		  String path="//Details/";	 	
		 	By from=By.id(document.selectSingleNode(path+"fromcity/path").getText());
			String fcity=document.selectSingleNode(path+"fromcity/value").getStringValue();
			
		 	By to=By.id(document.selectSingleNode(path+"tocity/path").getText());
		 	String tcity=document.selectSingleNode(path+"tocity/value").getText();
		 	 		 		 	
		 	gmethod.input(from,fcity);
		 	logger.info("Source is " + fcity);
		 	gmethod.input(to,tcity);
		 	logger.info("Destination is " + tcity);
	  }
	  
	  	public void testDate() throws Exception {
		  
	  		String path="//Details/";	
	  		By depart =By.xpath(document.selectSingleNode(path+"departdate/path").getText());
			String ddate=document.selectSingleNode(path+"departdate/value").getStringValue();
			
			By ret =By.xpath(document.selectSingleNode(path+"returndate/path").getText());
			String rdate=document.selectSingleNode(path+"returndate/value").getStringValue();
			
			gmethod.ClickDate(depart,ddate);
			logger.info("Departure date entered");
			if(tripstatus.equals("Round Trip"
					+ ""))
			{
				gmethod.ClickDate(ret,rdate);
				logger.info("Return date entered");
			}
			Thread.sleep(1000);
	  	}
	  
	  	public void testTravellers() throws Exception {
	  		
	  		String path="//Details/travellers/";
	  		String patha="//Details/travellers/adult/";	
	  		String pathc="//Details/travellers/child/";
	  		
	  		By trav =By.xpath(document.selectSingleNode(path+"path").getText());
			driver.findElement(trav).click();
		 	By adpathmin =By.xpath(document.selectSingleNode(patha+"adultpathminus").getText());
		 	By adpathplus =By.xpath(document.selectSingleNode(patha+"adultpathplus").getText());
			String adval=document.selectSingleNode(patha+"adultval").getText();
			int ad=Integer.parseInt(adval);
			
			gmethod.insertAdult(adpathmin, adpathplus, ad);
			
			By childpathmin =By.xpath(document.selectSingleNode(pathc+"childpathminus").getText());
		 	By childpathplus =By.xpath(document.selectSingleNode(pathc+"childpathplus").getText());
			String childval=document.selectSingleNode(pathc+"childval").getText();
			int child=Integer.parseInt(childval);
			gmethod.insertChild(childpathmin, childpathplus, child);
			logger.info("Travellers are : Adults - " + ad + "\t Children - " + child);
	   	}
	  	
	  	public void testTravellersNaN() throws Exception {
	  		
	  		String path="//Details/travellers/";
	  		String patha="//Details/travellers/adult/adultNaN";	
	  		 		
	  		By trav =By.xpath(document.selectSingleNode(path+"path").getText());
			driver.findElement(trav).click();
			By pathNaN=By.xpath(document.selectSingleNode(patha).getText());
		 	gmethod.insertAdultNaN(pathNaN,"0");
			
	   	}
	  	
	  	public void testCheckBox() throws Exception {
	  		
	  		By trav =By.xpath(document.selectSingleNode("//Details/travellers/path").getText());
	  		By ckbx1=By.id(document.selectSingleNode("//Details/chkbx1").getText());
			gmethod.selectStudent(ckbx1,trav);
			/*By ckbx2=By.id(document.selectSingleNode("//Details/chkbx2").getText());
			gmethod.dispAlert(driver,ckbx2);*/
	  	}
	  	
	  	public void testSelect() throws Exception {
	  		
	  		gmethod.selectClass(By.xpath(document.selectSingleNode("//Details/class/path").getText()));
			Thread.sleep(3000);
			logger.info("Business Class selected from Drop Down List");
			
	  	}
	  	
	  	public void testBtn() throws Exception {
	  		
	  		By btn =By.xpath(document.selectSingleNode("//Details/GoBtn").getText());
	  		By bBtn =By.xpath(document.selectSingleNode("//Details/bookbtn").getText());
	  		gmethod.ClickBtn(btn);
	  		logger.info("Get Set Go button clicked");
	  		if(tripstatus.equals("Round Trip"
					+ ""))
			{
	  			gmethod.CheckBookElement(bBtn);
		  		logger.info("Book Button Verified");

			}
	  			  		
	  	}
	  	
	  	public void Close() throws Exception
	  	{  		
	  		
	  		driver.close();
	  		driver=null;
	  		sc.close();
	  		gmethod.endTest();
	  		System.out.println("Exit");
	  	}
  
}
