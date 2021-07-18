package com.qa.para.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.para.base.BasePage;
import com.qa.para.utils.Constants;
import com.qa.para.utils.ElementUtil;

public class ContactsPage extends BasePage {

	private WebDriver driver;
	private ElementUtil elementutil;
		
	//By locators..
		
	By name=By.xpath("//input[@name='payee.name']");
	By address=By.xpath("//input[@name='payee.address.street']");
	By city=By.xpath("//input[@name='payee.address.city']");
	By state=By.xpath(" //input[@name='payee.address.state']");
	By zipcode =By.xpath("//input[@name='payee.address.zipCode']");
	By phonenumber=By.xpath("//input[@name='payee.phoneNumber']");
	By account=By.xpath("//input[@name='payee.accountNumber']");
	By verifyaccount=By.xpath("//input[@name='verifyAccount']");
	By amount=By.xpath("//input[@name='amount']");
	By fromaccount=By.xpath("//select[@name='fromAccountId']");
	By submit=By.xpath("//input[@value='Send Payment']");
	By confirmation=By.xpath("//h1[normalize-space()='Bill Payment Complete']");
	
	
		
	//create constructor 

	public ContactsPage(WebDriver driver){
		this.driver=driver;
		elementutil=new ElementUtil(driver);
	}

	// page actions 

	public String getContactsPageTitle(){
	return elementutil.waitForTitleToBePresent(Constants.CONTACTS_PAGE_TITLE, 10);}
	
	public void doBillPayment(String name,String address,String city,String state, 
			String zipcode, String phonenumber ,String account ,
			String amount, String fromaccount) {
		
		elementutil.waitForElementPresent(this.name, 5);
		elementutil.doSendKeys(this.name, name);
		elementutil.doSendKeys(this.address, address);
		elementutil.doSendKeys(this.city, city);
		elementutil.doSendKeys(this.state, state);
		elementutil.doSendKeys(this.zipcode, zipcode);
		elementutil.doSendKeys(this.phonenumber, phonenumber);
		elementutil.doSendKeys(this.account, account);
		elementutil.doSendKeys(this.verifyaccount, account);
		elementutil.doSendKeys(this.amount, amount);
		elementutil.doSelectByValue(this.fromaccount, fromaccount);
		
		elementutil.waitForElementPresent(submit, 5);
		elementutil.doActionsClick(submit);
		}

	
	public boolean paymentConfirmation() {
		elementutil.waitForElementPresent(confirmation, 20);
		return elementutil.doIsDisplayed(confirmation);
	}
	
	
	
}
