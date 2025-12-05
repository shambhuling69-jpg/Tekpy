package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.fileUtility.ExcelUtility;
import com.comcast.crm.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;

public class Createorganizationwith_industry_TC2 
{
	public static void main(String[] args) throws IOException {
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		
		String Browser = flib.getdatafrompropertiesfile("browser");
		String URL = flib.getdatafrompropertiesfile("url");
		String USERNAME = flib.getdatafrompropertiesfile("username");
		String PASSWORD = flib.getdatafrompropertiesfile("password");
		
		//generate the RandomNumber
		Random random=new Random();
		int randomInt = random.nextInt(1000);
		
		//Test scriptdata from excel
		FileInputStream fis1=new FileInputStream("C:\\Users\\User\\Desktop\\Selenium Data\\Excelproperties.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
       Sheet sh = wb.getSheet("org");
         Row row = sh.getRow(5);
        String orgname = row.getCell(2).toString()+jlib.getRandomNumber();
        String industry = row.getCell(3).toString();
        String type = row.getCell(4).toString();
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
		
		WebElement wbsele1 = driver.findElement(By.name("industry"));
		Select s=new Select(wbsele1);
		s.selectByVisibleText(industry);
		
		WebElement wbsele2 = driver.findElement(By.name("accounttype"));
		Select s2=new Select(wbsele2);
		s2.selectByVisibleText(type);
		
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		//verify the industry dropdown & Type
		String actindustries = driver.findElement(By.id("dtlview_Industry")).getText();
		if(actindustries.equals(industry))
		{
			System.out.println(industry+" information is verified");
			}
		else {
			System.out.println(industry+" information is not verified");
		}
		String acttype = driver.findElement(By.id("dtlview_Type")).getText();
		if(acttype.equals(type)) {
			System.out.println(type+"information is not verified");
		}
		else {
			System.out.println(type+"information is not verified");
		}
		//Step5 logout
//		Actions act=new Actions(driver);
//		act.moveToElement(driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"))).perform();
//	     driver.findElement(By.linkText("Sign Out")).click();
//	     driver.quit();
	     
	}


}
