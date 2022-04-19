package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageFactory.HomePageObject;
import pageFactory.RegisterPageObject;

public class Level_05_Page_Factory_Register extends BaseTest{
	private WebDriver driver;
	private String existingEmail, firstName, lastName, password;
	
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
		driver = getBrowserDriver(browserName);
		
		existingEmail = "autoclass" + getRandomNumber() + "@gmail.com";
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
	}
	
	@Test
	public void Register_01_Empty_Data() {
		
		homePage = new HomePageObject(driver);
		System.out.println("Register_01 - Step 01: Click to register link");
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject(driver);
		System.out.println("Register_01 - Step 02: Click to register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_01 - Step 03: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void Register_02_Invalid_Email() {
		
		homePage = new HomePageObject(driver);
		System.out.println("Register_02 - Step 01: Click to register link");
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject(driver);
		System.out.println("Register_02 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox("123@456#");
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfimPasswordTextbox(password);
		
		System.out.println("Register_02 - Step 03: Click to register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_02 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Register_03_Register_Success() {
		
		homePage = new HomePageObject(driver);
		System.out.println("Register_03 - Step 01: Click to register link");
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject(driver);
		System.out.println("Register_03 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfimPasswordTextbox(password);
		
		System.out.println("Register_03 - Step 03: Click to register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_03 - Step 03: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		System.out.println("Register_03 - Step 04: Click to logout link");
		registerPage.clickLogoutLink();
	}
	
	@Test
	public void Register_04_Register_Existing_Email() {
		
		homePage = new HomePageObject(driver);
		System.out.println("Register_04 - Step 01: Click to register link");
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject(driver);
		System.out.println("Register_04 - Step 02: Input to required fileds");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfimPasswordTextbox(password);
		
		System.out.println("Register_04 - Step 03: Click to register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_04 - Step 04: Verify error existing email message displayed");
		Assert.assertEquals(registerPage.getErrorExistingMessage(), "The specified email already exists");
	}
	
	@Test
	public void Register_05_Register_Password_Less_Than_6_Characters() {
		
		homePage = new HomePageObject(driver);
		System.out.println("Register_05 - Step 01: Click to register link");
		homePage.clickToRegisterLink();

		registerPage = new RegisterPageObject(driver);
		System.out.println("Register_05 - Step 02: Input to required fields");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox("123");
		registerPage.inputToConfimPasswordTextbox("123");
		
		System.out.println("Register_05 - Step 03: Click to register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_05 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
	}
	
	@Test
	public void Register_06_Register_Invalid_Confirm_Password() {
		
		homePage = new HomePageObject(driver);
		System.out.println("Register_06 - Step 01: Click to register link");
		homePage.clickToRegisterLink();

		registerPage = new RegisterPageObject(driver);
		System.out.println("Register_06 - Step 02: Input to required fileds");
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfimPasswordTextbox(password + "1");
		
		System.out.println("Register_06 - Step 03: Click to register button");
		registerPage.clickToRegisterButton();
		
		System.out.println("Register_06 - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
	}
	
	public int getRandomNumber() {
		Random rand  = new Random();
		return rand.nextInt(9999);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
