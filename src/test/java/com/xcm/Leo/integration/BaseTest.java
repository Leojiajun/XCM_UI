package com.xcm.Leo.integration;

import java.sql.SQLException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.xcm.Leo.Launch.Browsers;
import com.xcm.Leo.Launch.BrowsersType;
import com.xcm.Leo.util.TestngRetryListener;
import com.xcm.Leo.util.Tools;

@Listeners({ TestngRetryListener.class })
public class BaseTest {
	public WebDriver driver;
	@BeforeClass
	public void loadDriver(){
		Browsers browser = new Browsers(BrowsersType.chrome);
		driver = browser.driver;
	}
	@BeforeClass
	public void conect() throws ClassNotFoundException, SQLException{
		Tools.connection();
	}
	
	/*
	 * 登录平台
	 */
	@Test
	public void loginXCM() throws SQLException, InterruptedException{
		driver.get(Tools.getdbData("xcmURL", "login"));
		Tools.waitForElementPresent("loginele_homepage", "login", driver);
		Tools.button("loginele_homepage", "login", driver);
		Tools.input("username", "login", Tools.getdbData("usernamevalue", "login"), driver);
		Tools.input("password", "login", Tools.getdbData("passwordvalue", "login"), driver);
		Tools.waitForElementIsEnable("loginele_loginpage", "login", driver);
		Thread.sleep(5000);
		//Tools.button("loginele_loginpage", "login", driver);
		Tools.JavaScriptClick(Tools.getelement("loginele_loginpage", "login", driver), driver);
		Thread.sleep(2000);
		Assert.assertTrue(driver.getPageSource().contains("管理我的账户"));		
	}
	
	@AfterClass
	public void closeBrowser(){
		driver.quit();
		System.out.println("浏览器关闭成功");
	}
	
	@AfterClass
	public void closeDB() throws ClassNotFoundException, SQLException{
		Tools.shutdown();
		System.out.println("数据库关闭成功");
	}
}
