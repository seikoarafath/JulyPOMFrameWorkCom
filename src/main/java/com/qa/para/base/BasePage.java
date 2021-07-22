package com.qa.para.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	WebDriver driver;
	public Properties prop;
	
	/**
	 * this method is used to initialize the wed driver on the bases of browser..
	 * @param driverName
	 * @return driver 
	 */
	
public WebDriver init_driver(Properties prop) {
	
	String browsername=prop.getProperty("browser");
	
	if(browsername.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
	}
	else if(browsername.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		driver=new FirefoxDriver();
	}
	else if(browsername.equalsIgnoreCase("safari")) {
		WebDriverManager.getInstance(SafariDriver.class).setup();;
		driver=new SafariDriver();
	}
	
	driver.manage().deleteAllCookies();
	driver.manage().window().maximize();
	//driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	driver.get(prop.getProperty("url"));
	
	return driver;
	
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
	
	
	
	

}
