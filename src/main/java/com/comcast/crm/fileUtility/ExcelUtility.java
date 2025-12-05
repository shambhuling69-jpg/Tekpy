package com.comcast.crm.fileUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility 
{
	public String getdatafromExcel(String Sheetname,int rownum ,int cellnum ) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./Testdata/Excelproperties.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(Sheetname).getRow(rownum).getCell(cellnum).getStringCellValue();
		return data;
	}
	public int getRowCount(String Sheetname) throws Throwable {
		FileInputStream fis=new FileInputStream("./Testdata/Excelproperties.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		int rownum = wb.getSheet(Sheetname).getLastRowNum();
		return rownum;	
	}
	public void setdataintoexcel(String sheetname,int rownum ,int cellnum,String data) throws Throwable
	{
		FileInputStream fis=new FileInputStream("./Testdata/Excelproperties.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetname).getRow(rownum).createCell(cellnum);
		
		FileOutputStream fos=new FileOutputStream("./Testdata/Excelproperties.xlsx");
	wb.write(fos);
	wb.close();
	}
	

}
