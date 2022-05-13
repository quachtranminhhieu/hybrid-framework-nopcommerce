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
import pageObjects.nopCommerce.user.UserAddressesPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

public class Level_14_Log_ReportNG extends BaseTest {
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
	public void User_01_Register() {
		log.info("Register - 01: Click To 'Register' Link");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Register - 02: Enter To Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstNameTextbox(firstName);

		log.info("Register - 03: Enter To Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastNameTextbox(lastName);
		
		log.info("Register - 04: Enter To Email textbox with value is '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);
		
		log.info("Register - 05: Enter To Password textbox with value is '" + password + "'");
		registerPage.inputToPasswordTextbox(password);
		
		log.info("Register - 06: Enter To Confirm Password textbox with value is '" + password + "'");
		registerPage.inputToConfimPasswordTextbox(password);

		log.info("Register - 07: Click To 'Register' button");
		registerPage.clickToRegisterButton();

		log.info("Register - 08: Verify Successful Registration Message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed...");

		log.info("Register - 09: Click To 'Log out' Link");
		homePage = registerPage.clickLogoutLink();
		
	}
	
	@Test
	public void User_02_Login() {
		log.info("Login - 01: Click To 'Log in' Link");
		loginPage = homePage.clickToLoginLink();

		log.info("Login - 01: Enter To Email textbox with value is '" + emailAddress + "'");
		loginPage.inputToEmailTextbox(emailAddress);
		
		log.info("Login - 01: Enter To Password textbox with value is '" + password + "'");
		loginPage.inputToPasswordTextbox(password);

		log.info("Login - 01: Click To 'Log in' button");
		homePage = loginPage.clickToLoginButton();

		log.info("Login - 01: Verify 'My Account' link is displayed");
		verifyTrue(homePage.isMyAccountLinkDisplayed());
		
		log.info("Login - 01: Click To 'My Account' Link");
		customerInforPage = homePage.clickToMyAccountLink();
		
		log.info("Login - 01: Verify 'My Customer Infor' Page is displayed");
		verifyTrue(customerInforPage.isCustomerInforPageDisplayed());
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
