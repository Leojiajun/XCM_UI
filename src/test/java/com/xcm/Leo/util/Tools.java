package com.xcm.Leo.util;
import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class Tools {
	public static Connection conn=null;
	 public static ResultSet rs=null;
	 public static int  timeout=5;
	 public static WebDriver alldriver;

	//文本框输入内容
	public static void input(By by,String value,WebDriver driver){
		WebElement input=driver.findElement(by);
		input.sendKeys(Keys.BACK_SPACE);
		input.clear();
		input.sendKeys(value);
	}
	
	//文本框输入内容
	public static void input(String keyname,String tablename,String value,WebDriver driver) throws SQLException {
		try{
			input(By.xpath(getdbData(keyname, tablename)),value,driver);
		}catch(NoSuchElementException e){
			input(By.cssSelector(getdbData(keyname, tablename)),value,driver);
		}
	
	}
		
	
	//按钮点击，selenium原生点击
	public static void button(By by,WebDriver driver){
		WebElement button=driver.findElement(by);
		button.click();
		  }
	
	//按钮点击，selenium原生点击
	public static void button(String keyname,String tablename,WebDriver driver) throws SQLException {
		try{
			button(By.xpath(getdbData(keyname, tablename)),driver);
		}catch(NoSuchElementException e){
			button(By.cssSelector(getdbData(keyname, tablename)),driver);
		}
	}
	
	//JavaScript点击
	public static void JavaScriptClick(WebElement element,WebDriver driver){
		if (element.isEnabled() && element.isDisplayed()){
			((JavascriptExecutor) driver ).executeScript("arguments[0].click();",element);
		}else{
			System.out.println("页面上的元素无法进行单击操作");		
		}
	}
	
	 /**
     * 调用js，鼠标点击操作
     *
     * @param
     */
    public static void mouseClick(WebElement element,WebDriver driver) {
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
	
	public static WebElement getelement(String keyname,String tablename,WebDriver driver) throws SQLException{
		try{
			waitForElementPresent(keyname, tablename, driver);
			return driver.findElement(By.xpath(getdbData(keyname, tablename)));
		}catch(NoSuchElementException e){
			waitForElementPresent(keyname, tablename, driver);
			return driver.findElement(By.cssSelector(getdbData(keyname, tablename)));
		}
		
		
	}
	
	//当前时间加1天
		public static String getNetDay(Date date){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar calendar=Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, +1);//+1今天的时间加一天
			date=calendar.getTime();
			return df.format(date);
			}
		//当前时间
				public static String getToday(){
					SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
					return df.format(new Date());
					}
		
		//随机生成字符串
		public static String getRandomString(int length) {//length表示生成字符串的长度
			String base = "abcdefghijklmnopqrstuvwxyz0123456789";
			Random random = new Random();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < length; i++) {
				int number = random.nextInt(base.length());
				sb.append(base.charAt(number));
			}
			return sb.toString();
		}
		
//链接数据库
		public static void connection()
			      throws ClassNotFoundException, SQLException {
			    // 设定mysql驱动
			    Class.forName("com.mysql.jdbc.Driver");
			    // 建立数据库连接
			    conn = DriverManager.getConnection(
			        "jdbc:mysql://localhost:3306/xcm", "root", "123456");
			    // 判断数据库连接是否成功
			    if (!conn.isClosed()) {
			      System.out.println("数据库连接成功");
			    } else {
			      System.out.println("数据库连接失败");
			    }
		}
			   
//关闭链接
		public static void shutdown()
			      throws ClassNotFoundException, SQLException {
			 // 关闭数据集
		    rs.close();
		    System.out.println("关闭数据集");
			
		    // 关闭连接
		    conn.close();
		    System.out.println("关闭链接");
			   
		}
//从表中获取数据
		public static String getdbData(String localtername,String tablename) throws SQLException{
			String elementpath=null;
		 // 创建Statement对象可以用对应的方法executeQuery(sql语句)获取测试数据
	    Statement sta = conn.createStatement();
	    // 创建一个结果集存放数据库执行完sql的数据
	    rs = sta.executeQuery("select * from " + tablename
	        + " where keyname = '" + localtername + "';");

	    while (rs.next()) {
	        elementpath = rs.getString("path");// 获取“XpathOrCss”字段的值
	    }
	   
	    return elementpath;
	  
		}

		//等待元素出现
	public static void waitForElementPresent(String keyname, String tablename,WebDriver driver) throws SQLException{
		try{
			(new WebDriverWait(driver,timeout)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(getdbData(keyname, tablename))));
		}catch(TimeoutException e){
			(new WebDriverWait(driver,timeout)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(getdbData(keyname, tablename))));
		}	
	}
	
	//等待元素可操作
	public static void waitForElementIsEnable(String keyname, String tablename,WebDriver driver) throws SQLException{
		try{
			(new WebDriverWait(driver,timeout)).until(ExpectedConditions.elementToBeClickable(By.xpath(getdbData(keyname, tablename))));
		}catch(TimeoutException e){
			(new WebDriverWait(driver,timeout)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(getdbData(keyname, tablename))));
		}	
	}
	
	
	  /**
     * 根据元素路径，返回WebElement组件
     *
     * @param locator 元素路径
     * @return WebElement
     */
    public static WebElement findElement(String locator,WebDriver driver) {
        By by = parseLocator(locator);
        WebElement element = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            element = wait.until(ExpectedConditions
                    .presenceOfElementLocated(by));
        } catch (Exception e) {
            e.printStackTrace();
//            logger.error("Can't locate the web element by the locator "
//                    + locator);
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
//            logger.error("Can't locate the web element by the locator "
//                    + locator);
        }
        return elements;
    }
    
    
    
    /**
     * 在文本输入框中输入内容
     *
     * @param locator
     * @param text
     */
    public static void sendkeys(String locator, String text,WebDriver driver) {
        WebElement element = findElement(locator,driver);
        if (null != element) {
            try {
                element.clear();
                element.sendKeys(text);
               // logger.info("Type " + text + " in the page element " + locator);
            } catch (Exception e) {
                e.printStackTrace();
               // handleFailure("Failed to type " + text
                       // + " in the page element " + locator);
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
     * 路径解析器
     *
     * @param locator 元素路径
     * @return By
     */
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