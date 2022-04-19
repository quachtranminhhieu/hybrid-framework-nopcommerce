package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_02_BasePage_01_Register extends BasePage {
	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	String browserName = "Chrome";

	String randomEmail;
	String password = "123456";
	String firstName = "Automation";
	String lastName = "FC";
	
	@BeforeClass
	public void beforeClass() {
		switch (browserName) {
		case "Firefox":
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		default:
			new RuntimeException("Please input the browser name.");
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		randomEmail = "autoclass" + getRandomNumber() + "@gmail.com";
	}
	
	@Test
	public void TC_01_Register_Empty_Data() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		waitForElementClickable(driver, "//a[text()='Register']").click();

		Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
	}

	@Test
	public void TC_02_Invalid_Email() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		waitForElementClickable(driver, "//a[text()='Log in']").click();
		waitForElementClickable(driver, "//button[text()='Register']").click();
		
		sendkeyToElement(driver, "//input[@id='Email']", "123@456#");
		waitForElementClickable(driver, "//button[text()='Register']").click();
		
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}

	@Test
	public void TC_03_Register_Success() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		waitForElementClickable(driver, "//a[text()='Log in']").click();
		waitForElementClickable(driver, "//button[text()='Register']").click();
		
		sendkeyToElement(driver, "//input[@id='FirstName']", firstName);
		sendkeyToElement(driver, "//input[@id='LastName']", lastName);
		sendkeyToElement(driver, "//input[@id='Email']", randomEmail);
		sendkeyToElement(driver, "//input[@id='Password']", password);
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", password);
		waitForElementClickable(driver, "//button[text()='Register']").click();
		
		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
		
		waitForElementClickable(driver, "//a[text()='Log out']").click();
	}
	
	@Test
	public void TC_04_Register_Existing_Email() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		waitForElementClickable(driver, "//a[text()='Log in']").click();
		waitForElementClickable(driver, "//button[text()='Register']").click();
		
		sendkeyToElement(driver, "//input[@id='FirstName']", firstName);
		sendkeyToElement(driver, "//input[@id='LastName']", lastName);
		sendkeyToElement(driver, "//input[@id='Email']", randomEmail);
		sendkeyToElement(driver, "//input[@id='Password']", password);
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", password);
		waitForElementClickable(driver, "//button[text()='Register']").click();
		
		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
	}
	
	@Test
	public void TC_05_Register_Password_Less_Than_6_Characters() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		waitForElementClickable(driver, "//a[text()='Log in']").click();
		waitForElementClickable(driver, "//button[text()='Register']").click();

		sendkeyToElement(driver, "//input[@id='Password']", "123");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123");
		waitForElementClickable(driver, "//button[text()='Register']").click();
		
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
	}
	
	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		waitForElementClickable(driver, "//a[text()='Log in']").click();
		waitForElementClickable(driver, "//button[text()='Register']").click();

		sendkeyToElement(driver, "//input[@id='Password']", password);
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "12345");
		waitForElementClickable(driver, "//button[text()='Register']").click();
		
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
	}
	
	@Test
	public void TC_07_Login_With_Empty_Data() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		clickToElement(driver, "//a[text()='Log in']");
		clickToElement(driver, "//button[text()='Log in']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Please enter your email");
		
	}
	
	@Test
	public void TC_08_Login_With_Invalid_Email() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		clickToElement(driver, "//a[text()='Log in']");
		sendkeyToElement(driver, "//input[@class='email']", "123@456#");
		clickToElement(driver, "//button[text()='Log in']");
		
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}
	
	@Test
	public void TC_09_Login_With_Email_Not_Exist() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		clickToElement(driver, "//a[text()='Log in']");
		sendkeyToElement(driver, "//input[@class='email']", "123@gmail.com");
		sendkeyToElement(driver, "//input[@class='password']", password);
		clickToElement(driver, "//button[text()='Log in']");
		
		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]"), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}
	
	@Test
	public void TC_10_Login_With_Email_Exist_And_Empty_Password() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		waitForElementClickable(driver, "//a[text()='Log in']").click();
		waitForElementClickable(driver, "//button[text()='Register']").click();
		
		waitForElementClickable(driver, "//a[text()='Log in']").click();
		sendkeyToElement(driver, "//input[@class='email']", randomEmail);
		waitForElementClickable(driver, "//button[text()='Log in']").click();
		
		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]"), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void TC_11_Login_With_Email_Exist_And_Invalid_Password() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		waitForElementClickable(driver, "//a[text()='Log in']").click();
		sendkeyToElement(driver, "//input[@class='email']", randomEmail);
		sendkeyToElement(driver, "//input[@class='password']", "abcxyz");
		waitForElementClickable(driver, "//button[text()='Log in']").click();
		
		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]"), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}
	
	@Test
	public void TC_12_Login_With_Correct_Account() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
	
		waitForElementClickable(driver, "//a[text()='Log in']").click();
		sendkeyToElement(driver, "//input[@class='email']", randomEmail);
		sendkeyToElement(driver, "//input[@class='password']", password);
		waitForElementClickable(driver, "//button[text()='Log in']").click();
		
		Assert.assertEquals(getPageURL(driver), "https://demo.nopcommerce.com/");
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
