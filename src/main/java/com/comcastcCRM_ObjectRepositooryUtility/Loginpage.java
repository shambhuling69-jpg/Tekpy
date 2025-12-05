package com.comcastcCRM_ObjectRepositooryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Loginpage //RULE1---cretae seperate class
                         //object creation
                        
{
	WebDriver driver;
	public Loginpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(name="user_name")
	public WebElement usernametb;
	
	@FindBy(name="user_password")
	public WebElement passwordtb;
	
	@FindBy(id="submitButton")
	public WebElement logginbtn;

	public WebElement getUsername() {
		return usernametb;
	}

	public WebElement getPassword() {
		return passwordtb;
	}

	public WebElement getLogginbtn() {
		return logginbtn;
	}
    //provide data
	public void loginToapp(String url,String username,String password) {
		driver.manage().window().maximize();
		driver.get(url);
		usernametb.sendKeys(username);
		passwordtb.sendKeys(password);
		logginbtn.click();
	
		
	}
	
	
	
	

}
