package com.comcast.crm.basetest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.fileUtility.ExcelUtility;
import com.comcast.crm.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;
import com.comcastcCRM_ObjectRepositooryUtility.Homepage;
import com.comcastcCRM_ObjectRepositooryUtility.Loginpage;

public class BaseClassMain 
{
	public ExcelUtility elib=new ExcelUtility();
	public FileUtility flib=new FileUtility();
	public JavaUtility jlib=new JavaUtility();
	public static WebDriver driver=null;
	public ExtentSparkReporter spark;
	public ExtentReports report;

	@BeforeSuite
	public void configureBS() throws EncryptedDocumentException, IOException
	{
		System.out.println("====Connect to DB,Report Confi====");
		elib.getdatafromExcel("Sheet1", 1, 2);
		
		//sparkn report config
		ExtentSparkReporter spark=new ExtentSparkReporter("./AdvanceReport/report.html");
		spark.config().setDocumentTitle("CRM TEST SUIT RESULT");
		spark.config().setReportName("CRM REPORT");
		spark.config().setTheme(Theme.DARK);
		
		//add env information & create Test
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS","windows-10");
		report.setSystemInfo("Browser","Chrome-100");
		
	}

	@BeforeClass
	public void configBC() throws IOException
	{
		System.out.println("Launch the Browser");
		String Browser = flib.getdatafrompropertiesfile("browser");
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
	}
	@BeforeMethod
	public void configBM() throws IOException
	{
		System.out.println("==Login==");
		String URl = flib.getdatafrompropertiesfile("URL");
		String USERNAME = flib.getdatafrompropertiesfile("username");
		String PASSWORD = flib.getdatafrompropertiesfile("password");
		Loginpage lp=new Loginpage(driver);
		lp.loginToapp(URl, USERNAME, PASSWORD);
		lp.logginbtn.click();
		
	}
	@AfterMethod
	public void configAM()
	{
		System.out.println("==logout==");
		Homepage hp=new Homepage(driver);
		hp.logout();
	}
	@AfterClass
	public void configAc()
	{
		System.out.println("==Close the Browser==");
		driver.quit();
	}
	@AfterSuite
	public void configureAS()
	{
		System.out.println("====Close DB,Report Backup====");
		report.flush();
	}
	

}
