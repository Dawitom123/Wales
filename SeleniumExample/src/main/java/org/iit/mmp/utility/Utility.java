package org.iit.mmp.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utility {

	
	int i = 0;
	WebDriver driver;
	/*String uName = "jasmine";
	String pword = "9ol.<KI*";*/

	public Utility(WebDriver driver)
	{
		System.out.println("selenium "+ i +" webdriver");
		
		this.driver = driver;
	}
	public void moduleNavigation(String moduleName)
	{
		driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]")).click();
	}
	 
	public void launchApplicationURL(String url)
	{
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 
	}
	public void login(String uName, String password){
		
		System.out.println("login");
		//driver.findElement(By.linkText("Patient Login")).click();
		//driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("username")).sendKeys(uName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("submit")).click();
	}
	public void AdminLogin(String uName, String password){

		driver.findElement(By.id("username")).sendKeys(uName);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("admin")).click();

	}
	public void AdminModuleNavigation(String moduleName){
		
		driver.findElement(By.xpath("//span[contains(text(),'"+moduleName+"')]")).click();
		
	}
	public void takeScreenShot(String fLocation){

		TakesScreenshot ts = (TakesScreenshot) driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		System.out.println(srcFile.getAbsolutePath());
		File destFile = new File(fLocation);
		try {
			FileHandler.copy(srcFile, destFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
	} 
	public WebDriver switchToAFrameAvailable(String frameId,int timeinSecs)
	{
		WebDriverWait wait = new WebDriverWait(driver,timeinSecs);
		driver = wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameId));
		return driver;
	}
	public String getFutureDate(int days,String pattern)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String date = sdf.format(d);
		return date;
	}
	public String getFutureDate(int days)
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
		String date = sdf.format(d);
		return date;
	}
	
}