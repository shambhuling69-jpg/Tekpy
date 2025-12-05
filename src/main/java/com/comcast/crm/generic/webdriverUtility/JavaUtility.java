package com.comcast.crm.generic.webdriverUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility 
{
	public int getRandomNumber()
	{
		Random random=new Random();
		int randomnumber = random.nextInt(5000);
		return randomnumber;	
	}
	public String getSysetmdateYYYYDDMM()
	{
	Date dateobj=new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-DD");
	String date = sdf.format(dateobj);
	return date;
	}
	public String getRequireddateYYYYDDMM(int days)
	{
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal= sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH,30);
		String reqdate = sim.format(cal.getTime());
		return reqdate;
		
	}
}
