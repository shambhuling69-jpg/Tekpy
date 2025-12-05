package com.comcastcCRM_ObjectRepositooryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Organizationspage
{
	WebDriver driver;
	public Organizationspage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="search_text")
	private WebElement searchtxt;
	
	@FindBy(name="search_field")
	private WebElement searchdd;
	public WebElement getSearchtxt() {
		return searchtxt;
	}

	public WebElement getSearchdd() {
		return searchdd;
	}

	@FindBy(xpath = "//img[@alt='Create Organization...']")
	private WebElement createNewOtgbtn;

	public WebElement getCreateNewOtgbtn() {
		return createNewOtgbtn;
	}

}
