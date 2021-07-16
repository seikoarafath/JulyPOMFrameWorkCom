package com.qa.para.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.para.base.BasePage;
import com.qa.para.utils.Constants;
import com.qa.para.utils.ElementUtil;

public class LoginPage extends BasePage {
	
private WebDriver driver;
private ElementUtil elementutil;
	
//By locators..
	
By username=By.name("username");
By password=By.name("password");
By loginbutton=By.cssSelector("input[value='Log In']");
By forgotpwdLink=By.linkText("Forgot login info?");
	
//create constructor 

public LoginPage(WebDriver driver){
	this.driver=driver;
	elementutil=new ElementUtil(driver);
}

// page actions 

public String getLoginPageTitle(){
return elementutil.waitForTitleToBePresent(Constants.LOGIN_PAGE_TITLE, 10);
}

public boolean getForgotPWDLink(){
return elementutil.doIsDisplayed(forgotpwdLink);
}
 
public HomePage doLogin(String username,String password) {
elementutil.waitForElementToBeVisible(this.username, 10);
elementutil.doSendKeys(this.username, username);
elementutil.doSendKeys(this.password, password);
elementutil.doActionsClick(loginbutton);
	return new HomePage(driver);
	
}








}
