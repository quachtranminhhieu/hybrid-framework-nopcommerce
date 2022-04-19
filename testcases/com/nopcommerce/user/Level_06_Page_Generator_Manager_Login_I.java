package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

public class Level_06_Page_Generator_Manager_Login_I extends BaseTest{
	private WebDriver driver;
	private String existedEmail, invalidEmail, notFoundEmail, firstName, lastName, password, incorrectPassword;

	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
		driver = getBrowserDriver(browserName);
		
		homePage = new UserHomePageObject(driver);

		existedEmail = "autoclass" + getRandomNumber() + "@gmail.com";
		invalidEmail = "123@456#";
		notFoundEmail = existedEmail + "m";
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
		incorrectPassword = password + "7";

		System.out.println("Pre-Condition - Step 01: Click to register link");
		homePage.clickToRegisterLink();

		// Cách 1
		registerPage = new UserRegisterPageObject(driver);
		System.out.println("Pre-Condition - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(existedEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfimPasswordTextbox(password);

		System.out.println("Pre-Condition - Step 03: Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Pre-Condition - Step 03: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Pre-Condition - Step 04: Click to logout link");
		registerPage.clickLogoutLink();

	}

	@Test
	public void Login_01_With_Empty_Data() {
		homePage.clickToLoginLink();

		loginPage = new UserLoginPageObject(driver);

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}

	@Test
	public void Login_02_With_Invalid_Data() {
		homePage.clickToLoginLink();

		loginPage = new UserLoginPageObject(driver);

		loginPage.inputToEmailTextbox(invalidEmail);

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_With_Email_Not_Exist() {
		homePage.clickToLoginLink();

		loginPage = new UserLoginPageObject(driver);

		loginPage.inputToEmailTextbox(notFoundEmail);

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_With_Email_Exist_And_Empty_Password() {
		homePage.clickToLoginLink();

		loginPage = new UserLoginPageObject(driver);

		loginPage.inputToEmailTextbox(existedEmail);
		
		loginPage.inputToPasswordTextbox("");

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_05_With_Email_Exist_And_Invalid_Password() {
		homePage.clickToLoginLink();

		loginPage = new UserLoginPageObject(driver);

		loginPage.inputToEmailTextbox(existedEmail);
		
		loginPage.inputToPasswordTextbox(incorrectPassword);

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageUnsuccessful(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_With_Correct_Account() {
		homePage.clickToLoginLink();

		loginPage = new UserLoginPageObject(driver);

		loginPage.inputToEmailTextbox(existedEmail);
		
		loginPage.inputToPasswordTextbox(password);

		loginPage.clickToLoginButton();
		
		homePage = new UserHomePageObject(driver);

		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
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
