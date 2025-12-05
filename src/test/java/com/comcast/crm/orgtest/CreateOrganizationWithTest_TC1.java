package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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
/**
 * Shambhuling
 * 
 */
public class CreateOrganizationWithTest_TC1
{
	public static void main(String[] args) throws IOException {
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();	
		String Browser = flib.getdatafrompropertiesfile("browser");
		String URL = flib.getdatafrompropertiesfile("url");
		String USERNAME =flib.getdatafrompropertiesfile("username");
		String PASSWORD = flib.getdatafrompropertiesfile("password");
			
		//Test scriptdata from excel
//		FileInputStream fis1=new FileInputStream("C:\\Users\\User\\Desktop\\Selenium Data\\Excelproperties.xlsx");
//		Workbook wb = WorkbookFactory.create(fis1);
//       Sheet sh = wb.getSheet("org");
//         Row row = sh.getRow(1);
//        String orgname = row.getCell(2).toString();
//        wb.close();
		String orgname = elib.getdatafromExcel("org", 1, 2)+jlib.getRandomNumber();
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
		
		//verify headermsg
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
	     
	}


}
