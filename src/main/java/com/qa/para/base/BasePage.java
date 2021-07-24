package com.qa.para.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.para.utils.ElementUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	WebDriver driver;
	public Properties prop;
	public ElementUtil elementutil;
	
	public static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>(); 
	
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	/**
	 * this method is used to initialize the wed driver on the bases of browser..
	 * @param driverName
	 * @return driver 
	 */
	
public WebDriver init_driver(Properties prop) {
	
	String browsername=prop.getProperty("browser");
	
	if(browsername.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		//driver=new ChromeDriver();
		tlDriver.set(new ChromeDriver());
	}
	else if(browsername.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		//driver=new FirefoxDriver();
		tlDriver.set(new FirefoxDriver());
	}
	else if(browsername.equalsIgnoreCase("safari")) {
		WebDriverManager.getInstance(SafariDriver.class).setup();;
		driver=new SafariDriver();
	}
	
	getDriver().manage().deleteAllCookies();
	getDriver().manage().window().maximize();
	//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	getDriver().get(prop.getProperty("url"));
	
	return getDriver();
	
}	


/**
 * this method is to initialize properties file from config.properties..
 * @return properties 
 */
public Properties init_prop() {
	
	prop= new Properties();
	try {
		FileInputStream ip= new FileInputStream("./src/main/java/com/qa/para/config/config.properties");
	    prop.load(ip);
	
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return prop;
}	
	
/**
 * this method will take the screen shot	
 */
	
public String getScreenshot() {
	
	File scr=((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE); 
	String path= System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
	File destination=new File(path);
	try {
		FileUtils.copyFile(scr, destination);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	return path;
}	

}
