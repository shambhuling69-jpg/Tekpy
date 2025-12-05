package com.comcastcCRM_ObjectRepositooryUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOragnizationpage
{
	WebDriver driver;
	public CreatingNewOragnizationpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
       @FindBy(name="accountname")
       private WebElement orgnamedt;
       
     	@FindBy(xpath="(//input[@title='Save [Alt+S]'])[2]")
        private WebElement savebtn;
       
      	@FindBy(name="Industry")
        private WebElement industrydb;
      	
       public WebElement getOrgnamedt() {
		return orgnamedt;
	}

	    public WebElement getSavebtn() {
		return savebtn;
	}
    public void createorg(String orgname)
    {
    	orgnamedt.sendKeys(orgname);
    	savebtn.click();
    }
    public void createorg(String orgname,String Industry)
    {
    	orgnamedt.sendKeys(orgname);
    	Select sel=new Select(industrydb);
    	sel.selectByVisibleText(Industry);
    	savebtn.click();
    }
    
	
}


