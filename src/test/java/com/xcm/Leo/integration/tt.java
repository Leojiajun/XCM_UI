package com.xcm.Leo.integration;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.xcm.Leo.util.Tools;

public class tt {
	public static  WebDriver driver = null;
	private static String username_text="id=kw";
	private static String username_more="id=link2";
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
//		Browsers browsers = new Browsers(BrowsersType.chrome);
//		driver=browsers.driver;
		//XcmLoginPage xcmlogin = new XcmLoginPage(driver);
		//xcmlogin.login("15216782602","abcd1234");
		//Tools.connection();
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("file:///C:/Users/dell/Desktop/www.html");
		//Tools.sendkeys(username_text, "nihao", driver);
		//WebElement ele = Tools.findElement(username_text, driver);
		//System.out.println("*****"+ele+"****");
//		driver.get("http://192.168.3.240:8040/?spm=2018.0.1.0.0.0");
//		WebElement ele =Tools.getelement("loginele_homepage", "login", driver);
//		System.out.println("*****"+ele+"****");
//		Tools.shutdown();
		Tools.mouseOver(username_more, driver);
	}
}
