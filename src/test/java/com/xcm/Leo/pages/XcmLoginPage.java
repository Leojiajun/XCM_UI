package com.xcm.Leo.pages;

import org.openqa.selenium.WebDriver;

import com.xcm.Leo.util.Tools;
import com.xcm.Leo.util.Wait;

public class XcmLoginPage extends PageBase{
	private WebDriver driver;
	private Wait wait;
	
	public XcmLoginPage(WebDriver driver){
		super(driver,"xcmlogin");
		this.driver=driver;
		wait = new Wait(driver);
	}
	
	public void login(String username,String password){
	    String url = getLocValue("websiteurl");
		driver.get(url);
		wait.waitForElementPresent(getLocValue("loginEle"));
		getLoc("loginEle").click();
		wait.waitForElementPresent(getLocValue("username"));
		getLoc("username").sendKeys(username);
		getLoc("password").sendKeys(password);
		getLoc("submit").click();
	}
}
