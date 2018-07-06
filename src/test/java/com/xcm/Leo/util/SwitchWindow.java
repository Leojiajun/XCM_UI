package com.xcm.Leo.util;

import java.util.Set;

import org.openqa.selenium.WebDriver;

public class SwitchWindow {
	private WebDriver driver;
	private String currentwindow;
	
	/*
	 * 构造函数
	 */
	public SwitchWindow(WebDriver driver){
		this.driver = driver;
		currentwindow = driver.getWindowHandle();
	}
	/*
	 * 获取所有页面的句柄，根据不同的Title选择不同的页面
	 */
	public void toSpecificWindow(String partialTitleName){
		Set<String> handles = driver.getWindowHandles();
		String titlename;
		for(String handle:handles){
			titlename = driver.switchTo().window(handle).getTitle();
			if(titlename.contains(partialTitleName));
			break;
		}
		
	}
	
	/*
	 * 返回当前页
	 */
	public void backcurrentwindow(){
		driver.switchTo().window(currentwindow);
	}
}
