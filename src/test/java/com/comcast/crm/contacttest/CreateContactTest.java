package com.comcast.crm.contacttest;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClassMain;
import com.comcast.crm.fileUtility.ExcelUtility;
import com.comcast.crm.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;

public class CreateContactTest extends BaseClassMain
{
	@Test
	public void CreateContactTest() throws IOException {
		
//		FileUtility flib=new FileUtility();
//		ExcelUtility elib=new ExcelUtility();
//		JavaUtility jlib=new JavaUtility();
		
		String Browser = flib.getdatafrompropertiesfile("browser");
		String URL = flib.getdatafrompropertiesfile("url");
		String USERNAME = flib.getdatafrompropertiesfile("username");
		String PASSWORD = flib.getdatafrompropertiesfile("password");
		
		//Read Test scriptdata from excel
	  String lastname = elib.getdatafromExcel("Sheet2", 1, 5)+jlib.getRandomNumber();
		
		WebDriver driver=null;
		if(Browser.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(Browser.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(Browser.equals("Edge"))
		{
			driver=new EdgeDriver();
		}
		else {
			driver=new ChromeDriver();	
		}
		//step1 login to app
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(lastname);
	   //driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

	   String actlastname = driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actlastname.equals(lastname))
		{
			System.out.println(lastname+" is verified ===pass");
		}
		else {
			System.out.println(lastname+"is not verified ===fail");
		}
	}
}
