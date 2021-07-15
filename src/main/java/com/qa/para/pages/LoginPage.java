package com.qa.para.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.para.base.BasePage;

public class LoginPage extends BasePage {
	
private WebDriver driver;
	
//By locators..
	
By username=By.name("username");
By password=By.name("password");
By loginbutton=By.cssSelector("input[value='Log In']");
By forgotpwdLink=By.linkText("Forgot login info?");
	
//create constructor 

public LoginPage(WebDriver driver){
	this.driver=driver;
}

// page actions 

public String getLoginPageTitle(){
	return driver.getTitle();
}

public boolean getForgotPWDLink(){
	return driver.findElement(forgotpwdLink).isDisplayed();
}
 
public HomePage doLogin(String username,String password) {
	driver.findElement(this.username).sendKeys(username);
	driver.findElement(this.password).sendKeys(password);
	driver.findElement(this.loginbutton).click(); 
	
	return new HomePage(driver);
	
}








}
