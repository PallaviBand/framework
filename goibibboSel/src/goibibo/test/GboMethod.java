package goibibo.test;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GboMethod {
		
	static WebDriver driver;
	static ExtentReports report;
	static ExtentTest test;
	static File scrFile;
	String dir;
	static String image;
	
	
	public GboMethod(WebDriver driver) {
		super();
		GboMethod.driver = driver;
		 dir=System.getProperty("user.dir");
		report = new ExtentReports(dir+"\\ExtentReportResults.html",true);
		 
  		test = report.startTest("ExtentDemo");
	}
	
	
	public static void screenShots(String img) throws IOException
	{
		scrFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new
			     File("Screenshots\\"+img));
		 image= test.addScreenCapture("Screenshots\\"+img);
		 test.log(LogStatus.INFO, "verify logo of the application", image);
	}
		
	public  void input(By city,String fcity) throws Exception
	{		
		
		WebElement tcity=driver.findElement(city);
		try{
			if(tcity.isDisplayed())
			{
				tcity.sendKeys(fcity);
					
				test.log(LogStatus.PASS, fcity+" City is Inserted");
				test.log(LogStatus.FAIL, fcity+" City is not Inserted", "Expected");
				screenShots("input.jpeg");		
				Thread.sleep(1000);
			}
		}
		catch(Exception e)
		{
					
			test.log(LogStatus.FAIL, fcity+" City is not Inserted");
			test.log(LogStatus.FAIL, fcity+" City is not Inserted", "Expected");
			screenShots("input.jpeg");	
			System.out.println("Exception in Input Method : "+e);
		}
	}

	public void ClickDate(By tdate,String ddate) throws Exception
	{		
		try{
		WebElement date = driver.findElement(tdate);
		if(date.isDisplayed())
		{		
			date.click();
			Thread.sleep(1000);
			driver.findElement(By.id("fare_"+ddate)).click();
					
			test.log(LogStatus.PASS, "Date is Inserted");
			screenShots("date.jpeg");	
			 
		}
		}
		catch(Exception e)
		{	
					
			test.log(LogStatus.FAIL,"Date is not Inserted");
			screenShots("date.jpeg");	
		}
	}
	
	public void insertAdult(By minusad,By plusad,int adval) throws Exception
	{
		try{
			WebElement minus = driver.findElement(minusad);
			WebElement plus = driver.findElement(plusad);
			int actualval;
			
			for(int i=0;i<=adval;i++)
			{
				actualval=Integer.parseInt(driver.findElement(By.id("adultPaxBox")).getAttribute("value")); 
				if(actualval<=adval)
				{
					plus.click();
					Thread.sleep(1000);
				}
				else
				{
					minus.click();
					Thread.sleep(1000);
				}
					
				test.log(LogStatus.PASS, "Adult Persons is/are Inserted");
				screenShots("adulTraveller.jpeg");		
			}
		}
		catch(Exception e)
		{
			
			test.log(LogStatus.FAIL, "Adult Persons is/are not Inserted");
			screenShots("adulTraveller.jpeg");			
			System.out.println("Exception  in inserting adults : "+e);
		}
	}
	public void insertAdultNaN(By trav,String string)
	{
		WebElement trav1 = driver.findElement(trav);
		trav1.clear();
		trav1.sendKeys(string);
		System.out.println(" ");
	}
	public void insertChild(By minuschild,By pluschild,int childval) throws Exception
	{
		WebElement minus = driver.findElement(minuschild);
		WebElement plus = driver.findElement(pluschild);
		int actualval;
		try{
			for(int i=0;i<childval;i++)
			{
				actualval=Integer.parseInt(driver.findElement(By.id("childPaxBox")).getAttribute("value")); 
				if(actualval<=childval)
				{
					plus.click();
					Thread.sleep(1000);
				}
				else
				{
					minus.click();
					Thread.sleep(1000);
				}
					
				test.log(LogStatus.PASS, "Children is/are Inserted");
				screenShots("childTraveller.jpeg");		
			}
		}
		catch(Exception e)
		{
					
			test.log(LogStatus.FAIL, "Children  is/are not Inserted");
			screenShots("childTraveller.jpeg");	
			System.out.println("Exception in Inserting Child : "+e);
		}
	}
	
	
	public void selectStudent(By chk1,By trav) throws Exception
	{
		WebElement chkClick = driver.findElement(chk1);
		WebElement trav1=driver.findElement(trav);
		try{
			if(chkClick.isDisplayed())
			{
				chkClick.click();
				if(chkClick.isSelected())
				{
					trav1.click();
					Thread.sleep(2000);
					
					test.log(LogStatus.PASS, "verify checkbox of for the application");
					screenShots("selectStudent.jpeg");		
				}
			}
		}
		catch(Exception e)
		{
					
			test.log(LogStatus.PASS, "verification fail for checkbox of  the application");
			screenShots("selectStudent.jpeg");	
			System.out.println("Exception in selecting Student Travellers : "+e);
		}
	}
	
	
	public void selectClass(By class1) throws Exception
	{
		try{
			driver.findElement(class1).click();
			Thread.sleep(1000);
			Select c=new Select(driver.findElement(class1)); 
			c.selectByIndex(1);
			test.log(LogStatus.PASS, "Business class is selected");
				
			test.log(LogStatus.PASS, "verify class of  the application");
			screenShots("selectClass.jpeg");	
		}
		catch(Exception e)
		{
			test.log(LogStatus.FAIL, "Business class is failed to select");
			screenShots("selectClass.jpeg");	
			System.out.println("Exception in Selecting Class : "+e);
		}
		
	}
	public void ClickBtn(By btn) throws Exception
	{
		WebElement goBtn=driver.findElement(btn);
		if(goBtn.isDisplayed())
		{
			goBtn.click();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			test.log(LogStatus.PASS, "Button  is clicked");

		}

	
	}
	
	public void CheckBookElement(By bBtn)
	{

		WebDriverWait wait1 = new WebDriverWait(driver,10000);

		WebElement bookBtn=driver.findElement(bBtn);
		wait1.until(ExpectedConditions.elementToBeClickable(bookBtn));
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		test.log(LogStatus.PASS, "Book Button Verified");

		System.out.println("Book Button Verified");
	}
	
	public void endTest()
	{
		report.endTest(test);
		report.flush();
	}
	
	
}