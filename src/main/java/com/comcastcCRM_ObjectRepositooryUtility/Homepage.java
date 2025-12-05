package com.comcastcCRM_ObjectRepositooryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage 
{
	WebDriver driver;
	public Homepage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(linkText = "Organizations")
	private WebElement orglink;
	
//	@FindBy(linkText = "Contacts")
//	private WebElement contactlink;
	
	@FindBy(linkText = "Products")
	private WebElement Productlink;
	
	public WebElement getProductlink() {
		return Productlink;
	}

	@FindBy(linkText = "Campaigns")
	private WebElement Campaignslink;
	
	@FindBy(linkText = "More")
	private WebElement MoreLink;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement adminImg;

	@FindBy(linkText = "Sign Out")
	private WebElement signoutink;
	
	public WebElement getOrglink() {
		return orglink;
	}

//	public WebElement getContactlink() {
//		return contactlink;
	//}

	public WebElement getCampaignslink() {
		return Campaignslink;
	}

	public WebElement getMoreLink() {
		return MoreLink;
	}

	public void navigateCampaginpage() {
		Actions act=new Actions(driver);
		act.moveToElement(MoreLink).perform();
		Campaignslink.click();
	}
	public void logout()
	{
		Actions act=new Actions(driver);
		act.moveToElement(adminImg).perform();
		signoutink.click();
	}

}
