package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_02_Apply_BasePage_III extends BasePage{
	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	String randomEmail;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		randomEmail = "autoclass" + getRandomNumber() + "@gmail.com";
	}
	
	@Test
	public void TC_01_Register_Empty_Data() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		waitForElementClickable(driver, "//a[text()='Register']").click();
		waitForElementClickable(driver, "//button[@id='register-button']").click();

		Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
	}

	@Test
	public void TC_02_Invalid_Email() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		waitForElementClickable(driver, "//a[text()='Register']").click();
		
		sendkeyToElement(driver, "//input[@id='Email']", "123@456#");
		waitForElementClickable(driver, "//button[text()='Register']").click();
		
		Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	}

	@Test
	public void TC_03_Register_Success() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		waitForElementClickable(driver, "//a[text()='Register']").click();
		
		sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendkeyToElement(driver, "//input[@id='LastName']", "FC");
		sendkeyToElement(driver, "//input[@id='Email']", randomEmail);
		sendkeyToElement(driver, "//input[@id='Password']", "123456");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		waitForElementClickable(driver, "//button[text()='Register']").click();
		
		Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
		
		waitForElementClickable(driver, "//a[text()='Log out']").click();
	}
	
	@Test
	public void TC_04_Register_Existing_Email() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		waitForElementClickable(driver, "//a[text()='Register']").click();
		
		sendkeyToElement(driver, "//input[@id='FirstName']", "Automation");
		sendkeyToElement(driver, "//input[@id='LastName']", "FC");
		sendkeyToElement(driver, "//input[@id='Email']", randomEmail);
		sendkeyToElement(driver, "//input[@id='Password']", "123456");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		waitForElementClickable(driver, "//button[text()='Register']").click();
		
		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]//li"), "The specified email already exists");
	}
	
	@Test
	public void TC_05_Register_Password_Less_Than_6_Characters() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		waitForElementClickable(driver, "//a[text()='Register']").click();

		sendkeyToElement(driver, "//input[@id='Password']", "123");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123");
		waitForElementClickable(driver, "//button[text()='Register']").click();
		
		Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password must meet the following rules:\nmust have at least 6 characters");
	}
	
	@Test
	public void TC_06_Register_Invalid_Confirm_Password() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		waitForElementClickable(driver, "//a[text()='Register']").click();

		sendkeyToElement(driver, "//input[@id='Password']", "123456");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "12345");
		waitForElementClickable(driver, "//button[text()='Register']").click();
		
		Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "The password and confirmation password do not match.");
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
