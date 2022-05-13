package com.nopcommerce.common;

import java.util.Random;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;

public class Common_01_Register_Cookies extends BaseTest {

	@Parameters("browser")
	@BeforeTest(description =  "Create new common user for all classes test")
	public void Register(String browserName) {
		driver = getBrowserDriver(browserName);

		homePage = PageGeneratorManager.getUserHomePage(driver);

		emailAddress = "autoclass" + getRandomNumber() + "@gmail.com";
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		
		log.info("Pre-condition - 01: Click To 'Register' Link");
		registerPage = homePage.clickToRegisterLink();
		
		log.info("Pre-condition - 02: Enter To Firstname textbox with value is '" + firstName + "'");
		registerPage.inputToFirstNameTextbox(firstName);

		log.info("Pre-condition - 03: Enter To Lastname textbox with value is '" + lastName + "'");
		registerPage.inputToLastNameTextbox(lastName);
		
		log.info("Pre-condition - 04: Enter To Email textbox with value is '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);
		
		log.info("Pre-condition - 05: Enter To Password textbox with value is '" + password + "'");
		registerPage.inputToPasswordTextbox(password);
		
		log.info("Pre-condition - 06: Enter To Confirm Password textbox with value is '" + password + "'");
		registerPage.inputToConfimPasswordTextbox(password);

		log.info("Pre-condition - 07: Click To 'Register' button");
		registerPage.clickToRegisterButton();

		log.info("Pre-condition - 08: Verify Successful Registration Message is displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		log.info("Pre-condition - 09: Click To 'Log out' Link");
		homePage = registerPage.clickLogoutLink();
		
		log.info("Pre-condition - 10: Click To 'Log in' Link");
		loginPage = homePage.clickToLoginLink();

		log.info("Pre-condition - 11: Enter To Email textbox with value is '" + emailAddress + "'");
		loginPage.inputToEmailTextbox(emailAddress);
		
		log.info("Pre-condition - 12: Enter To Password textbox with value is '" + password + "'");
		loginPage.inputToPasswordTextbox(password);

		log.info("Pre-condition - 13: Click To 'Log in' button");
		homePage = loginPage.clickToLoginButton();
		
		log.info("Pre-condition - 14: Get All Cookies");
		loggedCookies = homePage.getAllCookies(driver);
		
		driver.quit();
		
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
	
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	public static Set<Cookie> loggedCookies;
	
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;

}
