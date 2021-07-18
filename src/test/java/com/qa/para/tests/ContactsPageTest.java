package com.qa.para.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.para.base.BaseTest;
import com.qa.para.pages.ContactsPage;
import com.qa.para.pages.HomePage;
import com.qa.para.utils.Constants;

public class ContactsPageTest extends BaseTest {
	HomePage homepage;
	ContactsPage contactsPage;
    
	@BeforeClass
	public void ContactsPageSetUp() {
    homepage=loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
    contactsPage=homepage.goToContactsPage();
	};
	@Test(priority = 1)
	public void verifyContactsPageTitleTest() {
		String title = contactsPage.getContactsPageTitle();
		System.out.println("contacts page title is : " + title);
		Assert.assertEquals(title, Constants.CONTACTS_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void billpayment() {
		contactsPage.doBillPayment("seiko", "577elgin", "winnipeg", "manitoba", "12500", "6473036588", "13647", "150", "19782");
	}
	@Test(priority=3 ,dependsOnMethods={"verifyContactsPageTitleTest"})
	
	public void verifyConfirmation() {
		contactsPage.paymentConfirmation();
	}	
	
	
	
}
