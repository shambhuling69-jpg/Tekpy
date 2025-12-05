package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateContactwithSupportDateTC_5 
{
	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\User\\Desktop\\Selenium Data\\Common data.properties");
		Properties pobj=new Properties();
		pobj.load(fis);
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();
		String Browser = flib.getdatafrompropertiesfile("browser");
		String URL = flib.getdatafrompropertiesfile("url");
		String USERNAME = flib.getdatafrompropertiesfile("username");
		String PASSWORD =flib.getdatafrompropertiesfile("password");
		
		//generate the RandomNumber
		Random random=new Random();
		int randomInt = random.nextInt(1000);
		
		//Test scriptdata from excel
		FileInputStream fis1=new FileInputStream("C:\\Users\\User\\Desktop\\Selenium Data\\Excelproperties.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
       Sheet sh = wb.getSheet("org");
         Row row = sh.getRow(5);
        String  Contactlastname= row.getCell(5).toString()+jlib.getRandomNumber();
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
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		String startdate = jlib.getSysetmdateYYYYDDMM();
		String enddate = jlib.getRequireddateYYYYDDMM(30);
//		Date dateobj=new Date();
//		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
//		String startdate = sim.format(dateobj);
//	
//		Calendar cal= sim.getCalendar();
//		cal.add(Calendar.DAY_OF_MONTH,30);
//		String afterdate = sim.format(cal.getTime());
		
		driver.findElement(By.name("lastname")).sendKeys(Contactlastname);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startdate);
	
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(enddate);
		
	   driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		String actstartdate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actstartdate.equals(startdate))
		{
			System.out.println(startdate+" is verified ===pass");
		}
		else {
			System.out.println(startdate+"is not verified ===fail");
		}
		String actenddate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actenddate.equals(enddate))
		{
			System.out.println(enddate+" is verified ===pass");
		}
		else {
			System.out.println(enddate+"is not verified ===fail");
		}
		
	
	}

}
