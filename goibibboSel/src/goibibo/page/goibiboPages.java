package goibibo.page;


import goibibo.test.goibiboTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class goibiboPages {
WebDriver driver;
goibiboTests gbt;
goibiboPages gbp;

	@BeforeClass
	public void initTestEle() {
				
		System.setProperty("webdriver.chrome.driver", "D:\\adselJAR\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.goibibo.com/");
		System.out.println("Starting Test");
		gbt=PageFactory.initElements(driver, goibiboTests.class);
		gbp=new goibiboPages();
	}
	
	@Test(priority=1)	
	public void testForDestination(){
		gbt.clickThis("//*[@id='gi_roundtrip_label']");
		gbt.typeThis("Mumbai","//*[@id='gosuggest_inputSrc']");
		gbp.sleep(2000);
		gbt.typeThis("Pune", "//*[@id='gosuggest_inputDest']");
		gbp.sleep(1000);
	}
	@Test(priority=2)	
	public void testForDate(){
		gbt.clickThis("//*[@id='searchWidgetCommon']/div/div[3]/div[1]/div[1]/div/i");
		gbp.sleep(3000);
		gbt.clickThis("//*[@id='fare_20181204']");
		gbt.clickThis("//*[@id='searchWidgetCommon']/div/div[3]/div[1]/div[2]/div/i");
		gbt.clickThis("//*[@id='fare_20181213']");
		gbp.sleep(3000);
	}
	@AfterClass
	public void endindTask(){
		gbt=null;
		driver.quit();
	}
	
	@Test(priority=3)	
	public void testForSelectClss()
   {
	gbt.selectClass("B","//*[@id='gi_class']");
	gbp.sleep(3000);
}
	@Test(priority=3)	
	public void testForSetBtn()
	{
		gbt.clickThis("//*[@id='gi_search_btn']");
		
		gbt.clickThis("//*[@id='fltTcktVoucher']/div[3]/div/div[2]/input");
		gbp.sleep(3000);
	}
	public void sleep(int timeForWait){
		try {
			Thread.sleep(timeForWait);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}