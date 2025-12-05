package practise_testng;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.fileUtility.ExcelUtility;

public class GetProductinfoTest 
{
	@Test(dataProvider = "getdata")
	public void getproductInfo(String proname ,String productname)
	{
		WebDriver driver=new ChromeDriver();
		driver.get("http://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		//search product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(proname,Keys.ENTER);
		
		//capyure product info
		//String price = driver.findElement(By.xpath("(//span[text()='"+productname+"'])[1]/../../../../div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span[1]/span[2]/span[2]")).getText();
		
		String x = "//span[text()='"+productname+"'])[1]/../../../../div[3]/div[1]/div/div[1]/div[1]/div[1]/a/span[1]/span[2]/span[2]";
		String price = driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		
		driver.quit();
		
	}
		@DataProvider
		public Object[][] getdata() throws Throwable
		{
			ExcelUtility elib=new ExcelUtility();
			int rowcount = elib.getRowCount("product");
			Object[][] objarr=new Object[rowcount][2];
			
		
		  for(int i=0;i<rowcount;i++)
		  {
				objarr[i][0]=elib.getdatafromExcel("product", i+1, 0);
				objarr[i][1]=elib.getdatafromExcel("product", i+1, 1);
		  }
			
//			objarr[1][0]="iphone";
//			objarr[1][1]="iPhone 16 128 GB: 5G Mobile Phone with Camera Control, A18 Chip and a Big Boost in Battery Life. Works with AirPods; Pink";
//		
//			
//			objarr[2][0]="iphone";
//			objarr[2][1]="iPhone 16 128 GB: 5G Mobile Phone with Camera Control, A18 Chip and a Big Boost in Battery Life. Works with AirPods; Teal";
//		   
			return objarr;
		}
	  
	}


