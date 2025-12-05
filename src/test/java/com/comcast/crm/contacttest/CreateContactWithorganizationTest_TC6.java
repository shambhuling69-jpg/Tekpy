package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.fileUtility.ExcelUtility;
import com.comcast.crm.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;

public class CreateContactWithorganizationTest_TC6 
{
	public static void main(String[] args) throws IOException {
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		
		String Browser = flib.getdatafrompropertiesfile("browser");
		String URL = flib.getdatafrompropertiesfile("url");
		String USERNAME = flib.getdatafrompropertiesfile("username");
		String PASSWORD = flib.getdatafrompropertiesfile("password");
		
		//Test scriptdata from excel
		FileInputStream fis1=new FileInputStream("C:\\Users\\User\\Desktop\\Selenium Data\\Excelproperties.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
       Sheet sh = wb.getSheet("org");
         Row row = sh.getRow(20);
        String orgname = row.getCell(2).toString()+jlib.getRandomNumber();
        String lastname = row.getCell(3).toString()+jlib.getRandomNumber();;
        wb.close();
		
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
		//STEP-2 navigate to organization module
		driver.findElement(By.linkText("Organizations")).click();
		// step =3CLICK ON + BUTTON
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		// Step=4ENTER THE DETAILS
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		 String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
			if(headerinfo.contains(orgname))
			{
				System.out.println(orgname+"is created pass");
			}
			else {
				System.out.println(orgname+"is not created fail");
			}
			//verify orgname info expected result
			String actorgname = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
			if(actorgname.equals(orgname)) {
				System.out.println(orgname+" is created pass");
			}
			else {
				System.out.println(orgname+"is created fail");
			}

		//navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();
		//click on create organization buton 
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		//enter all the details
		driver.findElement(By.name("lastname")).sendKeys(lastname);
		
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
	//SWITHCH TO CHILD 
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		while(it.hasNext()) {
			String windowid = it.next();
			driver.switchTo().window(windowid);
			
			String actUrl = driver.getCurrentUrl();
			if(actUrl.contains("module=Accounts")) {
				break;
		}
		driver.findElement(By.name("search_text")).sendKeys(orgname);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
		Set<String> set1 = driver.getWindowHandles();
		Iterator<String> it1 = set.iterator();
		while(it1.hasNext()) {
			String windowid1 = it1.next();
			driver.switchTo().window(windowid1);
			String actUrl1 = driver.getCurrentUrl();
			if(actUrl1.contains("contacts&action")) {
				break;
		}
		
	   driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
	  }
		}}
}
