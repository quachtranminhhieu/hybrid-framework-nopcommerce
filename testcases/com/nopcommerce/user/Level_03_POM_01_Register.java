package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;
import net.bytebuddy.dynamic.loading.ByteArrayClassLoader.ChildFirst;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_POM_01_Register {
	private String projectPath = System.getProperty("user.dir");
	private WebDriver driver;
	private String existingEmail, firstName, lastName, password;
	
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		
		existingEmail = "autoclass" + getRandomNumber() + "@gmail.com";
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
	}
	
	@Test
	public void Register_01_Empty_Data() {
		
		homePage = new UserHomePageObject(driver);
		System.out.println("Register_01 - Step 01: Click to register link");
		homePage.clickToRegisterLink();
		
		registerPage = new UserRegisterPageObject(driver);
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
		
		homePage = new UserHomePageObject(driver);
		System.out.println("Register_02 - Step 01: Click to register link");
		homePage.clickToRegisterLink();
		
		registerPage = new UserRegisterPageObject(driver);
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
		
		homePage = new UserHomePageObject(driver);
		System.out.println("Register_03 - Step 01: Click to register link");
		homePage.clickToRegisterLink();
		
		registerPage = new UserRegisterPageObject(driver);
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
		
		homePage = new UserHomePageObject(driver);
		System.out.println("Register_04 - Step 01: Click to register link");
		homePage.clickToRegisterLink();
		
		registerPage = new UserRegisterPageObject(driver);
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
		
		homePage = new UserHomePageObject(driver);
		System.out.println("Register_05 - Step 01: Click to register link");
		homePage.clickToRegisterLink();

		registerPage = new UserRegisterPageObject(driver);
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
		
		homePage = new UserHomePageObject(driver);
		System.out.println("Register_06 - Step 01: Click to register link");
		homePage.clickToRegisterLink();

		registerPage = new UserRegisterPageObject(driver);
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
