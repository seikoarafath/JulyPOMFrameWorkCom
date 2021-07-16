package com.qa.para.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.para.pages.LoginPage;

public class BaseTest{
	
	WebDriver driver;
	public BasePage basepage;
	public LoginPage loginpage;
	public Properties prop;
	
	
	
	@BeforeTest
	public void setUp(){
	basepage=new BasePage();
	prop=basepage.init_prop();
    driver=basepage.init_driver(prop);
	loginpage=new LoginPage(driver);
	}
	
	@AfterTest
	public void tearDown(){
	driver.quit();
    }

}
