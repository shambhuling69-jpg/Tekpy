package com.comcastcCRM_ObjectRepositooryUtility;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.fileUtility.ExcelUtility;
import com.comcast.crm.fileUtility.FileUtility;
import com.comcast.crm.generic.webdriverUtility.JavaUtility;

public class CreateOrganizationpageMain_Test
{
	public static void main(String[] args) throws IOException, InterruptedException {
		FileUtility flib=new FileUtility();
		ExcelUtility elib=new ExcelUtility();
		JavaUtility jlib=new JavaUtility();	
		String Browser = flib.getdatafrompropertiesfile("browser");
		String URL = flib.getdatafrompropertiesfile("url");
		String USERNAME =flib.getdatafrompropertiesfile("username");
		String PASSWORD = flib.getdatafrompropertiesfile("password");
			

		 String orgname = elib.getdatafromExcel("Sheet2", 1, 2)+jlib.getRandomNumber();
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
	 Loginpage lp=new Loginpage(driver);
	//lp.loginToapp(USERNAME,PASSWORD);
	Thread.sleep(4000);
	//step2 NAVIGATE TO ORGANIZATION MODULE
	Homepage hp=new Homepage(driver);
    hp.getOrglink().click();
    Thread.sleep(4000);
	//step3 CLICK ON CREATION ORGANIZATION button
    Organizationspage og=new Organizationspage(driver);
    og.getCreateNewOtgbtn().click();
    Thread.sleep(4000);
    //Step4 Enter all the details & create new Organization
     CreatingNewOragnizationpage cnop=new CreatingNewOragnizationpage(driver);
     cnop.createorg(orgname);
     Thread.sleep(4000);
    //verify the header msg Expected Result
    OrganizationInfopage oip=new OrganizationInfopage(driver);
    String actorgname = oip.getHeadermsg().getText();
    if(actorgname.contains(orgname))
    {
    	System.out.println(orgname+"is verified == PASS");
    }else {
    	System.out.println(orgname+"is not verified===FAIL");
    }
		hp.logout();
	}

}
