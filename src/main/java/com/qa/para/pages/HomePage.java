package com.qa.para.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.para.base.BasePage;
import com.qa.para.utils.Constants;
import com.qa.para.utils.ElementUtil;

public class HomePage extends BasePage  {
	
	private WebDriver driver;
	private ElementUtil elementutil;
	
	//By locators..
	
	By accountnumber=By.linkText("18561");
	By requestloan=By.linkText("Request Loan");
		
	//create constructor 

	public HomePage(WebDriver driver){
		this.driver=driver;
		elementutil=new ElementUtil(driver);
	}

	// page actions 
	
	public String getHomePageTitle(){
		return elementutil.waitForTitleToBePresent(Constants.HOME_PAGE_TITLE, 10);
		}
	
	public String getAccountNumber(){
		return elementutil.doGetText(accountnumber);
	}
	
	public boolean getRequestLoanLink() {
	return	elementutil.doIsDisplayed(requestloan);
	}

}
