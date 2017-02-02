package com.testCases;


import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Pages.Screenshot;
import Pages.Xls_Reader;
import Pages.homepage;


public class TestCase1 {
    String homeurl; 
    String browserType;
    WebDriver driver ;
    String imagepath1;
    String chromeDriverPath; 
    String firefoxDriverPath;
    String ieDriverPAth;
    String excelfilepath;
   	String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("C:\\Users\\ahmed aboukoura\\Desktop\\project\\theproject\\src\\com\\utilites\\new testCase1(+timeStamp+).html");
   	ExtentReports extent = new ExtentReports();
   	Screenshot sc = new Screenshot();
	//method to switching between browser , simply change the "browser type" in properties file .
	//to "chrome" or "firefox or "ie" to switch between browser .
   	//and please write the drivers path in the properties file also .
    @BeforeMethod
	public void openthebrowser(){
    	extent.attachReporter(htmlReporter);
        ExtentTest test = extent.createTest("opening the browsers");
    	if(browserType.contains("chrome")){
    	    System.setProperty("webdriver.chrome.driver",chromeDriverPath);
   	        driver = new ChromeDriver();	
   	        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
   	        test.log(Status.INFO, "opening the chrome browser.");
            driver.get(homeurl);
    	}else if(browserType.contains("firefox")){
    		System.setProperty("webdriver.gecko.driver",firefoxDriverPath);
   	        driver = new FirefoxDriver();	
   	        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
   	     test.log(Status.INFO, "opening firefox browser");
            driver.get(homeurl);
    	}else if(browserType.contains("ie")){
    		System.setProperty("webdriver.ie.driver",ieDriverPAth);
   	        driver = new InternetExplorerDriver();	
   	        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
   	     test.log(Status.INFO, "opening edge browser");
            driver.get(homeurl);
    	}
	}

	@Test(priority = 1)
		public void TestCase12() throws IOException, InterruptedException{
           driver.manage().window().maximize();

			homepage objhomepage = new homepage(driver);
			
			boolean match = objhomepage.DidThePriceAndProductMatch(1);
			if (match == true ){
			System.out.println("it's true");
			}else {
			System.out.println("it's false");
		    }
			
			
	}
	//after method for closing the browser and putting.flush for extent report.
	@AfterMethod
	public void closingthebrowser(){
		extent.flush();
		driver.close();
	
	
	}
	//loading the properties file
	@BeforeSuite
	  public void LoadPropFile() {
	      try{
		      Properties prop = new Properties();
		      FileInputStream Fs = new FileInputStream("C:\\Users\\ahmed aboukoura\\Desktop\\project\\ahmed'ssecondproject\\src\\Pages\\url&browser.properties");
		      prop.load(Fs);
		      browserType = prop.getProperty("browser");
		      firefoxDriverPath=prop.getProperty("firefoxdriverpath");
		      chromeDriverPath=prop.getProperty("chromedriverpath");
		      ieDriverPAth=(String) prop.get("iedriverpath");
		      homeurl=prop.getProperty("homeurl");
		      excelfilepath=prop.getProperty("excelfilepath");
		      imagepath1=prop.getProperty("imagepath1");
	      }catch(Exception e){
	      }
	 }
}

			
 
