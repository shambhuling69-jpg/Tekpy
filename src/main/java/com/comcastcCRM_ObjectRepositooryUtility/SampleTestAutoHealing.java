package com.comcastcCRM_ObjectRepositooryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class SampleTestAutoHealing 
{
	@FindBy(name="user_name")
	public WebElement usernametb;
	
	@FindBy(name="user_password")
	public WebElement passwordtb;
	
//	@FindBy(id="submitButton")
//	public WebElement logginbtn;
	
   @FindAll({@FindBy(id="sub"),@FindBy(xpath = "//input[@type='submit']")})
   public WebElement logginbtn;
	@Test
	public void SampleTest()
	{
		WebDriver driver=new ChromeDriver();
		driver.get("http://49.249.28.218:8888/");
		
		SampleTestAutoHealing s=PageFactory.initElements(driver,SampleTestAutoHealing.class);
		
		s.usernametb.sendKeys("admin");
		s.passwordtb.sendKeys("admin");
		s.logginbtn.click();
	
}}
