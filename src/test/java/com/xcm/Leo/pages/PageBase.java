package com.xcm.Leo.pages;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.xcm.Leo.util.DBOp;

public class PageBase {
	private WebDriver driver;
	private DBOp db;
	private Map<String,String> sf = new HashMap();
	
	public PageBase(WebDriver driver,String tablename){
		this.driver=driver;
		db = new DBOp(tablename);
		db.connect();	
	}
	
	public WebElement getLoc(String loc){
		WebElement we = null;
		if(db.getLocatorXpath(loc)!=null){
		try{	
			we = driver.findElement(By.xpath(db.getLocatorXpath(loc)));
		}catch(Exception e){
			if(db.getLocatorXpath(loc)!=null){
			we = driver.findElement(By.cssSelector(db.getLocatorCss(loc)));
			}
		}
		}else{
			we = driver.findElement(By.cssSelector(db.getLocatorCss(loc)));
		}
		return we;
	}
	
	public String getLocValue(String loc){
		sf.clear();
		if(db.getLocatorXpath(loc)!=null){
			sf.put(loc, db.getLocatorXpath(loc));
		}else{
			sf.put(loc, db.getLocatorCss(loc));
		}
		return sf.get(loc);
	}
}
