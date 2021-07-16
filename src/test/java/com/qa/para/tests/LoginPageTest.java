package com.qa.para.tests;
import org.testng.Assert;

import org.testng.annotations.Test;


import com.qa.para.base.BaseTest;

import com.qa.para.utils.Constants;

public class LoginPageTest extends BaseTest{

	
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
	








}
