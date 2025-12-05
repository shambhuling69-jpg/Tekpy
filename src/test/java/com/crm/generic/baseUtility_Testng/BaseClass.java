package com.crm.generic.baseUtility_Testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class BaseClass 
{
	@BeforeSuite
	public void configureBS()
	{
		System.out.println("====Connect to DB,Report Confi====");
	}

	@BeforeClass
	public void configBC()
	{
		System.out.println("Launch the Browser");
	}
	@BeforeMethod
	public void configBM()
	{
		System.out.println("==Login==");
	}
	@AfterMethod
	public void configAM()
	{
		System.out.println("==logout==");
	}
	@AfterClass
	public void configAc()
	{
		System.out.println("==Close the Browser==");
	}
	@AfterSuite
	public void configureAS()
	{
		System.out.println("====Close DB,Report Backup====");
	}
	

}
