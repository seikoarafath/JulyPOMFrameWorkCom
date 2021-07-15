package com.qa.para.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.para.base.BasePage;
import com.qa.para.pages.LoginPage;
import com.qa.para.utils.Constants;

public class LoginPageTest {
	WebDriver driver;
	
	BasePage basepage;
	LoginPage loginpage;
	Properties prop;
	
	
	
	@BeforeTest
	public void setUp(){
	basepage=new BasePage();
	prop=basepage.init_prop();
    driver=basepage.init_driver(prop);
	loginpage=new LoginPage(driver);
		}
	
	@Test(priority=2)
	public void verifyLoginpageTitleTest(){
		String title=loginpage.getLoginPageTitle();
		System.out.println("loginpage title is :  "+title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE,"login page title is not matched ");
		}
	@Test(priority=1)
	public void verifyForgotPWDLinkTest(){
		Assert.assertTrue(loginpage.getForgotPWDLink(),"forget password link is not displayed");
		}
	@Test(priority=3)
	public void loginTest(){
		loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		}
	


   @AfterTest
   public void tearDown(){
	   driver.quit();
	   }






}
