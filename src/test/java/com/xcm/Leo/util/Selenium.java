package com.xcm.Leo.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.xcm.Leo.Launch.Browsers;

public class Selenium {
	public static int  timeout=5;
	
	/**
	 * 
	 * @param locator
	 * @param driver
	 * @return element
	 */
	public static WebElement findElement(String locator,WebDriver driver){
		By by = parseLocator(locator);
		WebElement element = null;
		try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            element = wait.until(ExpectedConditions
                    .presenceOfElementLocated(by));
        } catch (Exception e) {
            e.printStackTrace();
        }
		return element;	
		
	}
	
    /**
     * 根据元素路径，返回WebElement组件
     *
     * @param locator 元素路径
     * @return WebElement
     */
    public static List<WebElement> findElements(String locator,WebDriver driver) {
        By by = parseLocator(locator);
        List<WebElement> elements = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        } catch (Exception e) {
            e.printStackTrace();
//           logger.error("Can't locate the web element by the locator "
 //                   + locator);
        }
        return elements;
    }
	
    
    
	/**
	 * selenium原生输入内容
	 * @param locator
	 * @param value
	 * @param driver
	 */
	public static void input(String locator,String value,WebDriver driver){
		WebElement element = findElement(locator,driver);
		element.sendKeys(Keys.BACK_SPACE);
		element.clear();
		element.sendKeys(value);
	}
	
	
	/**
	 * selenium原生点击
	 * @param locator
	 * @param driver
	 */
	public static void button(String locator,WebDriver driver){
		WebElement element = findElement(locator,driver);
		element.click();
	}
	
	/**
	 * 调用javascript点击
	 * @param locator
	 * @param driver
	 */
	public static void JavaScriptClick(String locator,WebDriver driver){
		WebElement element = findElement(locator,driver);
		if (element.isEnabled() && element.isDisplayed()){
			((JavascriptExecutor) driver ).executeScript("arguments[0].click();",element);
		}else{
			System.out.println("页面上的元素无法进行单击操作");		
		}
	}
	
	
	/**
	 * 调用js鼠标点击操作
	 * @param locator
	 * @param driver
	 */
    public static void mouseClick(String locator,WebDriver driver) {
    	WebElement element = findElement(locator,driver);
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        rb.mouseMove(0, 0);

        String code = "var fireOnThis = arguments[0];"
                + "var evObj = document.createEvent('MouseEvents');"
                + "evObj.initEvent('click',true,true);"
                + "fireOnThis.dispatchEvent(evObj);";
        if (null != element) {
            try {
                ((JavascriptExecutor) driver).executeScript(code, element);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	
	
	
    /**
     * 通过调用js，使光标悬浮在某个元素上方
     *
     * @param locator
     */
    public static void mouseOver(String locator,WebDriver driver) {
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        rb.mouseMove(0, 0);

        WebElement element = findElement(locator,driver);
        String code = "var fireOnThis = arguments[0];"
                + "var evObj = document.createEvent('MouseEvents');"
                + "evObj.initEvent('mouseover',true,true);"
                + "fireOnThis.dispatchEvent(evObj);";
        if (null != element) {
            try {
                ((JavascriptExecutor) driver).executeScript(code, element);
               // logger.info("Mouse over the page element " + locator);
            } catch (Exception e) {
                e.printStackTrace();
                //handleFailure("Failed to mouseover the page element " + locator);
            }
        }
    }
	
	
    
    /**
     * 切换window
     *
     * @param partialTitleName 页面标题
     */
    public static void toSpecificWindow(String partialTitleName,WebDriver driver){
    	Set<String> handles = driver.getWindowHandles();
		String titlename;
		for(String handle:handles){
			titlename = driver.switchTo().window(handle).getTitle();
			if(titlename.contains(partialTitleName));
			break;
		}	
    }
    
    
    
    /**
     * 切换到Frame
     */
    public void accessFrame(String nameOrId,WebDriver driver) {
        driver.switchTo().frame(nameOrId);
        //logger.info("Entered iframe " + nameOrId);
    }
    
    
    /**
     * 截图，保存在   项目目录/test-output/screen-shot下
     * @throws IOException 
     * @throws WebDriverException 
     */
    public static void takesScreenshot(WebDriver driver) throws WebDriverException, IOException  {
       String SCREEN_SHOT_PATH = "test-output/screen-shot";  
        String SCREEN_SHOT_NAME = null; 
        File screenShotDir = new File(SCREEN_SHOT_PATH);  
        if (!screenShotDir.exists()) {  
            screenShotDir.mkdirs();  
    }  
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = df.format(new Date());//取当前时间的年月日时分秒为截图的名称
        SCREEN_SHOT_NAME = dateStr + ".jpg";  
        FileUtils.copyFile( ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE), 
                new File(SCREEN_SHOT_PATH + "/" + SCREEN_SHOT_NAME));
    }  
 
    
	
	public static By parseLocator(String locator) {
        String lowerLocator = locator.toLowerCase();
        String actualLocator;
        By by = null;
        if (lowerLocator.startsWith("id=")) {
            actualLocator = locator.substring(3);
            by = By.id(actualLocator);
        } else if (lowerLocator.startsWith("name=")) {
            actualLocator = locator.substring(5);
            by = By.name(actualLocator);
        } else if (lowerLocator.startsWith("class=")) {
            actualLocator = locator.substring(6);
            by = By.className(actualLocator);
        } else if (lowerLocator.startsWith("tag=")) {
            actualLocator = locator.substring(4);
            by = By.tagName(actualLocator);
        } else if (lowerLocator.startsWith("link=")) {
            actualLocator = locator.substring(5);
            by = By.partialLinkText(actualLocator);
        } else if (lowerLocator.startsWith("css=")) {
            actualLocator = locator.substring(4);
            by = By.cssSelector(actualLocator);
        } else if (lowerLocator.startsWith("xpath=")) {
            actualLocator = locator.substring(6);
            by = By.xpath(actualLocator);
        } else {
            //logger.error("Format Error: id=,class=,tag=,name=,link=,css=,xpath=");
        }
        return by;
    }
}
