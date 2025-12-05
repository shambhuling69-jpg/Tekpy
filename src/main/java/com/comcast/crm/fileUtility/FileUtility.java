package com.comcast.crm.fileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility 
{
	public String getdatafrompropertiesfile(String key) throws IOException
	{
		FileInputStream fis=new FileInputStream("./ConfigureAppdata/Common data.properties");
		Properties pobj=new Properties();
		pobj.load(fis);
		String data=pobj.getProperty(key);
		return data;
	}

}
