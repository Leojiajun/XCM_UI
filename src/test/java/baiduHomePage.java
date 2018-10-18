import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.xcm.Leo.util.Tools;

public class baiduHomePage {
	private WebDriver driver;
	private  String search;
	private String button;
	
	public baiduHomePage(WebDriver driver){
		this.driver = driver;
		search=PageLocator.baidu_searchinput;
		button=PageLocator.baidu_searchbutton;
	}
	
	public   void openBaidu(WebDriver driver){
		driver.get("https://www.baidu.com/");	
	}
	
	public  void searchInput(String test,WebDriver driver){
		WebElement element = Tools.findElement(search, driver);
		element.sendKeys(test);
	}
	public void clicksearch(WebDriver driver){
		WebElement element = Tools.findElement(button, driver);
		element.click();
	}
}
