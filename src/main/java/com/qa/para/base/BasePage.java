package com.qa.para.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.para.utils.ElementUtil;
import com.qa.para.utils.OptionsManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	WebDriver driver;
	public Properties prop;
	public ElementUtil elementutil;
	public OptionsManager optionsmanager;
	
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
	
	String browserName=null;
	if(System.getProperty("browser")==null) {
		browserName=prop.getProperty("browser");
	}else {
		browserName=System.getProperty("browser");
	}
	System.out.println("Running on --->"+browserName);
	optionsmanager=new OptionsManager(prop);
	//String browserName=prop.getProperty("browser");
	
	if(browserName.equalsIgnoreCase("chrome")) {
		WebDriverManager.chromedriver().setup();
	    //tlDriver.set(new ChromeDriver(optionsmanager.getChromeOptions()));
	DesiredCapabilities cap=DesiredCapabilities.chrome();
	cap.setCapability(ChromeOptions.CAPABILITY, optionsmanager.getChromeOptions());
	try {
		tlDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap));
	} catch (MalformedURLException e) {
		
		e.printStackTrace();
	}
	}
	else if(browserName.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();
		//tlDriver.set(new FirefoxDriver(optionsmanager.getFirefoxOptions()));
		DesiredCapabilities cap=DesiredCapabilities.firefox();
		cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionsmanager.getFirefoxOptions());
		try {
			tlDriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap));
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		}
	}
	else if(browserName.equalsIgnoreCase("safari")) {
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
 * this method is to initialize properties file from on the bases of env variable..
 * @return properties 
 */
public Properties init_prop() {
	prop= new Properties();
	  String path= null;
	  String env= null;
	    
	
	try {
		env=System.getProperty("env");
		System.out.println("env value is --->" +env);
		
		if(env==null) {
			path="./src/main/java/com/qa/para/config/config.properties";
		}else {
		switch (env) {
		case "qa":
			path="./src/main/java/com/qa/para/config/qa.config.properties";
			break;
		case "dev":
			path="./src/main/java/com/qa/para/config/dev.config.properties";
			break;
		case "stage":
			path="./src/main/java/com/qa/para/config/stage.config.properties";
			break;
        default:
        	System.out.println("please pass the correct env value----->" +env);
			break;
		}	
	}
		FileInputStream ip= new FileInputStream(path);
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
