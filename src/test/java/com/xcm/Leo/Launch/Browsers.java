package com.xcm.Leo.Launch;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


public class Browsers {

	public static WebDriver driver =null;
	private FirefoxProfile firefoxprofile = null;
	private static DesiredCapabilities caps = null;
	private String projectpath = System.getProperty("user.dir");
	
    /**
     * 
     * 选择不同的浏览器
     * */
	
	public Browsers(BrowsersType browserstype){
		switch(browserstype){
			case firefox:
				File firebug =new File(projectpath+"/Tools/firebug@software.joehewitt.com.xpi");
				File fireXpath = new File(projectpath+"/Tools/FireXPath@pierre.tholence.com.xpi");
				firefoxprofile = new FirefoxProfile();
			try {
				firefoxprofile.addExtension(firebug);
				firefoxprofile.addExtension(fireXpath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				firefoxprofile.setPreference("extensions.firebug.currentVersion", "2.0.17");
				firefoxprofile.setPreference("extensions.firebug.allPagesActivation", "on");
				driver = new FirefoxDriver(firefoxprofile);
				driver.manage().window().maximize();
				break;
			case chrome:
				//设定连接Chrome浏览器驱动程序所在的磁盘位置，并添加为系统属性值
				System.setProperty("webdriver.chrome.driver",projectpath+"/Tools/chromedriver.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
				capabilities.setCapability("chrome.switches", Arrays.asList("--incognito"));
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--test-type");
				capabilities.setCapability("chrome.binary","src/ucBrowserDrivers/chromedriver.exe");
				capabilities.setCapability(ChromeOptions.CAPABILITY,options);
				driver = new ChromeDriver(capabilities);
				driver.manage().window().maximize();
				break;
			case ie:
				System.setProperty("webdriver.ie.driver", projectpath+"/Tools/IEDriverServer.exe");
				caps =DesiredCapabilities.internetExplorer();
				caps.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, false);
				caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);//���ⰲȫ�Լ�������
				caps.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");//ÿ������IE���Զ����cookie����¼
				caps.setCapability("ignoreZoomSetting", true);
				driver = new InternetExplorerDriver(caps);
				driver.manage().window().maximize();
				break;
						
			}
			
		}

}
