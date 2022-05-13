package com.nopcommerce.user;


import java.lang.reflect.Method;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import reportConfig.ExtentTestManagerV5;

public class Level_15_ExtentV5_Screenshot extends BaseTest {
	private WebDriver driver;
	private String emailAddress, firstName, lastName, password;

	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {

		driver = getBrowserDriver(browserName);

		homePage = PageGeneratorManager.getUserHomePage(driver);

		emailAddress = "autoclass" + getRandomNumber() + "@gmail.com";
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
	}

	@Test
	public void User_01_Register(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "User_01_Register");	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Register - 01: Click To 'Register' Link");
		registerPage = homePage.clickToRegisterLink();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Register - 02: Enter To Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstNameTextbox(firstName);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Register - 03: Enter To Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastNameTextbox(lastName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Register - 04: Enter To Email textbox with value is '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Register - 05: Enter To Password textbox with value is '" + password + "'");
		registerPage.inputToPasswordTextbox(password);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Register - 06: Enter To Confirm Password textbox with value is '" + password + "'");
		registerPage.inputToConfimPasswordTextbox(password);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Register - 07: Click To 'Register' button");
		registerPage.clickToRegisterButton();

		// Must use AssertEquals not verifyEquals
		ExtentTestManagerV5.getTest().log(Status.INFO, "Register - 08: Verify Successful Registration Message is displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed...");
	}
	
	@Test
	public void User_02_Login(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "User_02_Login");	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Login - 01: Click To 'Log in' Link");
		homePage = registerPage.clickLogoutLink();
		loginPage = homePage.clickToLoginLink();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Login - 02: Enter To Email textbox with value is '" + emailAddress + "'");
		loginPage.inputToEmailTextbox(emailAddress);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Login - 03: Enter To Password textbox with value is '" + password + "'");
		loginPage.inputToPasswordTextbox(password);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Login - 04: Click To 'Log in' button");
		homePage = loginPage.clickToLoginButton();

		// Must use AssertTrue not verifyTrue
		ExtentTestManagerV5.getTest().log(Status.INFO, "Login - 05: Verify 'My Account' link is displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Login - 06: Click To 'My Account' Link");
		customerInforPage = homePage.clickToMyAccountLink();
		
		// Must use AssertTrue not verifyTrue
		ExtentTestManagerV5.getTest().log(Status.INFO, "Login - 07: Verify 'My Customer Infor' Page is displayed");
		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
		
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
