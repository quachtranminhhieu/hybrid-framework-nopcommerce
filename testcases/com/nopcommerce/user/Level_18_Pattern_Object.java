package com.nopcommerce.user;


import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_18_Pattern_Object extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {

		driver = getBrowserDriver(browserName);

		homePage = PageGeneratorManager.getUserHomePage(driver);

		emailAddress = "autoclass" + getRandomNumber() + "@gmail.com";
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		dobDay = "17";
		dobMonth = "October";
		dobYear = "2000";
	}

	@Test
	public void User_01_Register() {
		log.info("Register - 01: Click To 'Register' Link");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Register - 02: Check To 'Female' Radio Button");
		registerPage.clickToRadioButtonByLabel(driver, "Female");
		
		log.info("Register - 03: Enter To Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToTextboxByID(driver, "FirstName", firstName);

		log.info("Register - 04: Enter To Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToTextboxByID(driver, "LastName", lastName);
		
		log.info("Register - 05: Select To Date of Birth Day with value is '" + dobDay + "'");
		registerPage.selectToDropdownByName(driver, "DateOfBirthDay", dobDay);
		
		log.info("Register - 06: Select To Date of Birth Month with value is '" + dobMonth + "'");
		registerPage.selectToDropdownByName(driver, "DateOfBirthMonth", dobMonth);
		
		log.info("Register - 07: Select To Date of Birth Year with value is '" + dobYear + "'");
		registerPage.selectToDropdownByName(driver, "DateOfBirthYear", dobYear);
		
		log.info("Register - 08: Enter To Email textbox with value is '" + emailAddress + "'");
		registerPage.inputToTextboxByID(driver, "Email", emailAddress);
		
		log.info("Register - 09: Check To 'Newsletter' Checkbox");
		registerPage.clickToCheckboxByLabel(driver, "Newsletter");
		
		log.info("Register - 10: Enter To Password textbox with value is '" + password + "'");
		registerPage.inputToTextboxByID(driver, "Password", password);
		
		log.info("Register - 11: Enter To Confirm Password textbox with value is '" + password + "'");
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);

		log.info("Register - 12: Click To 'Register' button");
		registerPage.clickToButtonByText(driver, "Register");

		// Must use AssertEquals not verifyEquals
		log.info("Register - 13: Verify Successful Registration Message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
	}
	
	@Test
	public void User_02_Login() {
		log.info("Login - 01: Click To 'Log out' Link");
		homePage = registerPage.clickLogoutLink();
		
		log.info("Login - 02: Click To 'Log in' Link");
		loginPage = homePage.clickToLoginLink();

		log.info("Login - 03: Enter To Email textbox with value is '" + emailAddress + "'");
		loginPage.inputToTextboxByID(driver, "Email", emailAddress);
		
		log.info("Login - 04: Enter To Password textbox with value is '" + password + "'");
		loginPage.inputToTextboxByID(driver, "Password", password);

		log.info("Login - 05: Click To 'Log in' button");
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getUserHomePage(driver);

		// Must use AssertTrue not verifyTrue
		log.info("Login - 06: Verify 'My Account' link is displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
	}
	
	@Test
	public void User_03_My_Account() {
		log.info("My Account - 01: Click To 'My Account' Link");
		customerInforPage = homePage.clickToMyAccountLink();
		
		// Must use AssertTrue not verifyTrue
		log.info("My Account - 02: Verify 'My Customer Infor' Page is displayed");
		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
		
		log.info("My Account - 03: Verify 'First Name' value is correct");
		Assert.assertEquals(customerInforPage.getTextboxValueByID(driver, "FirstName"), firstName);
		
		log.info("My Account - 04: Verify 'Last Name' value is correct");
		Assert.assertEquals(customerInforPage.getTextboxValueByID(driver, "LastName"), lastName);
		
		log.info("My Account - 05: Verify 'Email' value is correct");
		Assert.assertEquals(customerInforPage.getTextboxValueByID(driver, "Email"), emailAddress);
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	private String emailAddress, firstName, lastName, password, dobDay, dobMonth, dobYear;

	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;

}
