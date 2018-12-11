package goibibo.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class goibiboTests {
	WebDriver driver;

	public goibiboTests(WebDriver driver) {
		super();
		this.driver = driver;
	}

	public void clickThis(String xPath) {
		driver.findElement(By.xpath(xPath)).click();
	}

	public void typeThis(String inputStr, String xPath) {
		driver.findElement(By.xpath(xPath)).sendKeys(inputStr);

	}

	public void selectClass(String name, String xPath1) {
		Select drpClass=new Select(driver.findElement(By.xpath(xPath1)));
		drpClass.selectByValue(name);
	}
	
}
