package com.qa.para.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.para.base.BasePage;

public class HomePage extends BasePage  {
	
	private WebDriver driver;
	
	//By locators..
	
	By accountnumber=By.linkText("18561");
	By requestloan=By.linkText("Request Loan");
		
	//create constructor 

	public HomePage(WebDriver driver){
		this.driver=driver;
	}

	// page actions 
	
	public String getHomePageTitle(){
		return driver.getTitle();
		}
	
	public String getAccountNumber(){
		return driver.findElement(accountnumber).getText();
	}
	
	public boolean getRequestLoanLink() {
	return	driver.findElement(requestloan).isDisplayed();
	}

}
