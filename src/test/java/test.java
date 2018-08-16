
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.xcm.Leo.Launch.Browsers;
import com.xcm.Leo.Launch.BrowsersType;
import com.xcm.Leo.pages.XcmLoginPage;
import com.xcm.Leo.util.SwitchWindow;
import com.xcm.Leo.util.Tools;


public class test {
	public static  WebDriver driver = null;
	private static String username_text="id=kw";
	
	public static void main(String[] args) {
//		Browsers browsers = new Browsers(BrowsersType.chrome);
//		driver=browsers.driver;
		//XcmLoginPage xcmlogin = new XcmLoginPage(driver);
		//xcmlogin.login("15216782602","abcd1234");
		FirefoxDriver driver = new FirefoxDriver();
		driver.get("https://www.baidu.com/");
		Tools.sendkeys(username_text, "nihao", driver);
	}
}
