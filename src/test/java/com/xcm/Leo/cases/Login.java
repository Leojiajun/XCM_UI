package com.xcm.Leo.cases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.xcm.Leo.Launch.Browsers;
import com.xcm.Leo.Launch.BrowsersType;
import com.xcm.Leo.pages.XcmLoginPage;

public class Login {
	public static WebDriver driver;
	@Test
	public void loginXcm() throws InterruptedException{
		Browsers browsers = new Browsers(BrowsersType.chrome);
		driver=browsers.driver;
		XcmLoginPage xcmlogin = new XcmLoginPage(driver);
		xcmlogin.login("15216782602", "abcd1234");
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("管理我的账户"));
	}
	@AfterClass
	public void release(){
		driver.quit();
	}
}
  