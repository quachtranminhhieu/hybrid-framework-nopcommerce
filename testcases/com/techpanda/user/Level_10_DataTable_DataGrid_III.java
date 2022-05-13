package com.techpanda.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.techpanda.user.UserRegisterPageObject;
import pageUIs.techpanda.user.UserLoginPageUI;
import pageObjects.techpanda.admin.AdminCustomerPageObject;
import pageObjects.techpanda.admin.AdminLoginPageObject;
import pageObjects.techpanda.user.GlobalConstants;
import pageObjects.techpanda.user.PageGeneratorManager;
import pageObjects.techpanda.user.UserHomePageObject;
import pageObjects.techpanda.user.UserLoginPageObject;

public class Level_10_DataTable_DataGrid_III extends BaseTest{
	WebDriver driver;
	UserRegisterPageObject userRegisterPage;
	UserHomePageObject userHomePage;
	UserLoginPageObject userLoginPage;
	AdminLoginPageObject adminLoginPage;
	AdminCustomerPageObject adminCustomerPage;
	
	String firstName, lastName, fullName, emailAddress, password, adminUserName, adminPassword;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		firstName = "John";
		lastName = "Wick";
		fullName = firstName + " " + lastName;
		emailAddress = "johnwick" + getRandomNumber() + "@gmail.com";
		password = "123123";
		adminUserName = "user01";
		adminPassword = "guru99com";
		
		driver = getBrowserDriver(browserName, GlobalConstants.USER_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
	}
	
	@Test
	public void TC_01_Register_At_User_Page() {
		userRegisterPage = (UserRegisterPageObject) userHomePage.clickToLinkByText("Register");
		
		userRegisterPage.inputToRegisterTextboxByTitle("First Name", firstName);
		userRegisterPage.inputToRegisterTextboxByTitle("Last Name", lastName);
		userRegisterPage.inputToRegisterTextboxByTitle("Email Address", emailAddress);
		userRegisterPage.inputToRegisterTextboxByTitle("Password", password);
		userRegisterPage.inputToRegisterTextboxByTitle("Confirm Password", password);
		
		userHomePage = userRegisterPage.clickToRegisterButton();
		Assert.assertTrue(userHomePage.isSucessfullRegisterMessageDisplayed());
		
		userHomePage.clickToLinkByText("Log Out");
		
		userLoginPage = (UserLoginPageObject) userHomePage.clickToLinkByText("Log In");
		userHomePage = userLoginPage.loginAsUser(emailAddress, password);
		Assert.assertTrue(userHomePage.isMyDashboardHeaderDisplayed());
		
		userHomePage.clickToLinkByText("Log Out");
	}
	
	@Test
	public void TC_02_Switch_Admin_Page_And_Delete_Account() {
		userHomePage.openPageURL(driver, GlobalConstants.ADMIN_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		adminCustomerPage = adminLoginPage.loginAsAdmin(adminUserName, adminPassword);
		
		adminCustomerPage.closeMessagePopup();
		
		adminCustomerPage.inputToHeaderTextboxByName("Name", fullName);
		adminCustomerPage.inputToHeaderTextboxByName("Email", emailAddress);
		adminCustomerPage.clickToButtonByTitle("Search");
		
		Assert.assertTrue(adminCustomerPage.isMyAccountExisted(fullName, emailAddress));
		
		adminCustomerPage.clickToCheckboxAtRow("1");
		adminCustomerPage.selectHeaderDropdownActions("Delete");
		adminCustomerPage.clickToButtonByTitle("Submit");
		adminCustomerPage.acceptAlert(driver);
		
		Assert.assertTrue(adminCustomerPage.isNoRecordFoundMessageDisplayed());
		
		adminCustomerPage.clickToLogoutLinkAtAdminPage();
	}
	
	@Test
	public void TC_03_Login_With_Not_Exist_Account() {
		adminCustomerPage.openPageURL(driver, GlobalConstants.USER_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		
		userLoginPage = (UserLoginPageObject) userHomePage.clickToLinkByText("Log In");
		
		userLoginPage.loginAsUser(emailAddress, adminPassword);
		
		Assert.assertEquals(userLoginPage.getElementText(driver, UserLoginPageUI.UNSUCESSFUL_LOGIN_MESSAGE), "Invalid login or password.");
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
	}
	
	@AfterClass
	public void afterClass(){
		driver.quit();
	}
}
