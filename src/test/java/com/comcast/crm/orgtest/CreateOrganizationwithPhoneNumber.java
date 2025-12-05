package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;

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

public class CreateOrganizationwithPhoneNumber 
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
         Row row = sh.getRow(9);
        String orgname = row.getCell(2).toString()+jlib.getRandomNumber();
        String phoneno = row.getCell(3).getStringCellValue();
       
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
		driver.findElement(By.id("phone")).sendKeys(phoneno);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		String actphonenum = driver.findElement(By.id("dtlview_Phone")).getText();
	   if(actphonenum.equals(phoneno))
	   {
		   System.out.println(phoneno+"information is verified pass");
	   }else {
		   System.out.println(phoneno+"information is not verified fail");
	   }
		}
}
