package com.xcm.Leo.util;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.xcm.Leo.Launch.Browsers;


public class ScreenShotOnFailure {
    private final static String SCREEN_SHOT_PATH = "test-output/screen-shot";  
    private static String SCREEN_SHOT_NAME = null;  
  
    public static void takeScreentShot() throws IOException {  
        File screenShotDir = new File(SCREEN_SHOT_PATH);  
        if (!screenShotDir.exists()) {  
            screenShotDir.mkdirs();  
    }  
        
		String dateStr = Tools.getToday();
        SCREEN_SHOT_NAME = dateStr + ".jpg";  
        FileUtils.copyFile( ((TakesScreenshot) Browsers.driver).getScreenshotAs(OutputType.FILE), 
                new File(SCREEN_SHOT_PATH + "/" + SCREEN_SHOT_NAME));  
    }  
  
    public static String getScreenShotPath() {  
        return SCREEN_SHOT_PATH;  
    }  
  
    public static String getScreenShotName() {  
        return SCREEN_SHOT_NAME;  
    }  
}
