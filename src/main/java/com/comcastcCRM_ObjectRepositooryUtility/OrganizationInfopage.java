package com.comcastcCRM_ObjectRepositooryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfopage 
{
	WebDriver driver;
	public OrganizationInfopage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	@FindBy(className ="dvHeaderText")
	private WebElement headermsg;
	
	public WebElement getHeadermsg() {
		return headermsg;
	}

}
