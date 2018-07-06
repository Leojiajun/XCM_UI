package com.xcm.Leo.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {
	private WebDriver driver;
	private int timeout=10;
	
	//���캯����new����ʹ�ã�
	public Wait(WebDriver driver){
		this.driver=driver;
	}
	
	//�ȴ�Ԫ�س���
	public void waitForElementPresent(String locator){
		(new WebDriverWait(driver,timeout)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		
	}

	//�ȴ�Ԫ���ܲ���
	public void waitForElementIsEnable(String locator){
		(new WebDriverWait(driver,timeout)).until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
		
	}
	
	//����һ��ʱ��
	public void waitFor(long timeout){
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
