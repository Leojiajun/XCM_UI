import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.xcm.Leo.Launch.Browsers;
import com.xcm.Leo.Launch.BrowsersType;
import com.xcm.Leo.util.RedisUtil;
import com.xcm.Leo.util.Selenium;
import com.xcm.Leo.util.Tools;

public class testBaidu {
	public static WebDriver driver;
	static String destination="xpath=//div[2]/header/ul/li[2]/a";
	
	@BeforeClass
	public void loadDriver(){
		Browsers browser = new Browsers(BrowsersType.chrome);
		driver = browser.driver;
	}
	//@Test
	public static void test() throws WebDriverException, IOException, InterruptedException{
		 baiduHomePage bdhp = new baiduHomePage(driver);
		 bdhp.openBaidu(driver);
		 bdhp.searchInput("ceshi", driver);
		 bdhp.clicksearch(driver);
		 Thread.sleep(2000);
		 Selenium.takesScreenshot(driver);
		 

	}
	
	//@Test
	public static void test2() throws InterruptedException, WebDriverException, IOException{
		driver.get("https://www.thomascook.com.cn/");
		(new WebDriverWait(driver,15)).until(ExpectedConditions.presenceOfElementLocated((Tools.parseLocator(destination))));
		Tools.mouseOver(destination, driver);
		Thread.sleep(3000);
		Selenium.takesScreenshot(driver);
		
	}
	
	@Test
	public void test3(){
		RedisUtil dd = new RedisUtil();
		String aa =dd.getValue("112853115322");
		System.out.println(aa);
	}
   
	@AfterClass
	public static void quit(){
		driver.quit();
	}
	
}
