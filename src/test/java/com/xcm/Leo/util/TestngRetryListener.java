package com.xcm.Leo.util;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestngRetryListener extends TestListenerAdapter{
	public void onTestFailure(ITestResult result) {  
        try { 
        
        	ScreenShotOnFailure.takeScreentShot();
            System.out.println(result.getMethod().getMethodName() + " failed, the screenshot saved in "  
                    + ScreenShotOnFailure.getScreenShotPath() + " screenshot name : "  
                    + ScreenShotOnFailure.getScreenShotName()); 
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
	  public void onTestStart(ITestResult result) {  
	        // TODO Auto-generated method stub  
	  
	    }  
	  
	    public void onTestSuccess(ITestResult result) {  
	        // TODO Auto-generated method stub  
	    }  
	  
	    public void onTestSkipped(ITestResult result) {  
	        // TODO Auto-generated method stub  
	  
	    }  
	  
	    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {  
	        // TODO Auto-generated method stub  
	  
	    }  
	  
	    public void onStart(ITestContext context) {  
	        // TODO Auto-generated method stub  
	  
	    }  
  
   
}  
