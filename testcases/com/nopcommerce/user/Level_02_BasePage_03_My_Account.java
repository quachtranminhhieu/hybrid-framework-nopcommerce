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

public class Level_02_BasePage_03_My_Account extends BasePage {
	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	String browserName = "Chrome";

	String randomEmail;
	String password = "123456";
	String newPassword = "1234567";
	String firstName = "Automation";
	String lastName = "FC";
	String newEmail;
	String dobDay = "1";
	String dobMonth = "January";
	String dobYear = "1999";
	String companyName = "Automation FC";
	String city = "Da Nang";
	String address1 = "123/04 Le Lai";
	String address2 = "234/05 Hai Phong";
	String zipCode = "550000";
	String country = "Viet Nam";
	String phoneNumber = "0123456789";
	String faxNumber = "0987654321";
	
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
		newEmail = "automationfc" + getRandomNumber() + "@gmail.com";
	}
	
	@Test
	public void TC_01_Register_And_Log_In() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		waitForElementClickable(driver, "//a[text()='Register']").click();
		
		sendkeyToElement(driver, "//input[@id='FirstName']", firstName);
		sendkeyToElement(driver, "//input[@id='LastName']", lastName);
		sendkeyToElement(driver, "//input[@id='Email']", randomEmail);
		sendkeyToElement(driver, "//input[@id='Password']", password);
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", password);
		waitForElementClickable(driver, "//button[text()='Register']").click();
	}
	
	@Test
	public void TC_02_Update_Customer_Info() {
		waitForElementClickable(driver, "//div[@class='header']//a[text()='My account']").click();
		
		checkToCheckboxRadio(driver, "//input[@id='gender-female']");
		
		clearTextbox(driver, "//input[@id='FirstName']");
		sendkeyToElement(driver, "//input[@id='FirstName']", firstName);
		
		clearTextbox(driver, "//input[@id='LastName']");
		sendkeyToElement(driver, "//input[@id='LastName']", lastName);
		
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthDay']", dobDay);
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthMonth']", dobMonth);
		selectItemInDefaultDropdown(driver, "//select[@name='DateOfBirthYear']", dobYear);
		
		clearTextbox(driver, "//input[@id='Email']");
		sendkeyToElement(driver, "//input[@id='Email']", newEmail);
		
		clearTextbox(driver, "//input[@id='Company']");
		sendkeyToElement(driver, "//input[@id='Company']", companyName);
		
		clickToElement(driver, "//button[text()='Save']");
		
		Assert.assertTrue(isElementSelected(driver, "//input[@id='gender-female']"));
		
		areJQueryAndJSLoadedSuccess(driver);
		
		Assert.assertEquals(getElementAttribute(driver, "//input[@id='FirstName']", "value"), firstName);
		Assert.assertEquals(getElementAttribute(driver, "//input[@id='LastName']", "value"), lastName);
		Assert.assertEquals(getSelectedItemDefaultDropdown(driver, "//select[@name='DateOfBirthDay']"), dobDay);
		Assert.assertEquals(getSelectedItemDefaultDropdown(driver, "//select[@name='DateOfBirthMonth']"), dobMonth);
		Assert.assertEquals(getSelectedItemDefaultDropdown(driver, "//select[@name='DateOfBirthYear']"), dobYear);
		Assert.assertEquals(getElementAttribute(driver, "//input[@id='Email']", "value"), newEmail);
		Assert.assertEquals(getElementAttribute(driver, "//input[@id='Company']", "value"), companyName);
	}
	
	@Test
	public void TC_03_Add_New_Address() {
		clickToElement(driver, "//div[@class='listbox']//a[text()='Addresses']");
		waitForElementClickable(driver, "//button[text()='Add new']").click();
		
		sendkeyToElement(driver, "//input[@id='Address_FirstName']", firstName);
		sendkeyToElement(driver, "//input[@id='Address_LastName']", lastName);
		sendkeyToElement(driver, "//input[@id='Address_Email']", newEmail);
		sendkeyToElement(driver, "//input[@id='Address_Company']", companyName);
		selectItemInDefaultDropdown(driver, "//select[@id='Address_CountryId']", country);
		sendkeyToElement(driver, "//input[@id='Address_City']", city);
		sendkeyToElement(driver, "//input[@id='Address_Address1']", address1);
		sendkeyToElement(driver, "//input[@id='Address_Address1']", address1);
		sendkeyToElement(driver, "//input[@id='Address_ZipPostalCode']", zipCode);
		sendkeyToElement(driver, "//input[@id='Address_PhoneNumber']", phoneNumber);
		sendkeyToElement(driver, "//input[@id='Address_FaxNumber']", faxNumber);
		
		clickToElement(driver, "//button[text()='Save']");
		
		Assert.assertEquals(getElementText(driver, "//li[@class='name']"), firstName + " " + lastName);
		Assert.assertEquals(getElementText(driver, "//li[@class='email']"), "Email: " + newEmail);
		Assert.assertEquals(getElementText(driver, "//li[@class='phone']"), "Phone number: " + phoneNumber);
		Assert.assertEquals(getElementText(driver, "//li[@class='fax']"), "Fax number: " + faxNumber);
		Assert.assertEquals(getElementText(driver, "//li[@class='company']"), companyName);
		Assert.assertEquals(getElementText(driver, "//li[@class='address1']"), address1);
		Assert.assertTrue(getElementText(driver, "//li[@class='city-state-zip']").contains(city));
		Assert.assertTrue(getElementText(driver, "//li[@class='city-state-zip']").contains(zipCode));
		Assert.assertEquals(getElementText(driver, "//li[@class='country']"), country);
	}
	
	@Test
	public void TC_04_Update_New_Password() {
		clickToElement(driver, "//a[text()='Change password']");
		
		sendkeyToElement(driver, "//input[@id='OldPassword']", password);
		sendkeyToElement(driver, "//input[@id='NewPassword']", newPassword);
		sendkeyToElement(driver, "//input[@id='ConfirmNewPassword']", newPassword);
		
		clickToElement(driver, "//button[text()='Change password']");
		waitForElementVisible(driver, "//div[@class='bar-notification success']");
		Assert.assertEquals(getElementText(driver, "//p[@class='content']"), "Password was changed");
		clickToElement(driver, "//span[@class='close']");
		sleepInSecond(1);
		
		waitForElementClickable(driver, "//a[text()='Log out']").click();
		waitForElementClickable(driver, "//a[text()='Log in']").click();
		
		sendkeyToElement(driver, "//input[@id='Email']", newEmail);
		sendkeyToElement(driver, "//input[@id='Password']", password);
		clickToElement(driver, "//button[text()='Log in']");
		areJQueryAndJSLoadedSuccess(driver);
		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]"), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
		
		areJQueryAndJSLoadedSuccess(driver);
		clearTextbox(driver, "//input[@id='Email']");
		sendkeyToElement(driver, "//input[@id='Email']", randomEmail);
		clearTextbox(driver, "//input[@id='Password']");
		sendkeyToElement(driver, "//input[@id='Password']", newPassword);
		waitForElementClickable(driver, "//button[text()='Log in']").click();
		areJQueryAndJSLoadedSuccess(driver);
		Assert.assertEquals(getElementText(driver, "//div[contains(@class,'message-error')]"), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
		
		areJQueryAndJSLoadedSuccess(driver);
		clearTextbox(driver, "//input[@id='Email']");
		sendkeyToElement(driver, "//input[@id='Email']", newEmail);
		clearTextbox(driver, "//input[@id='Password']");
		sendkeyToElement(driver, "//input[@id='Password']", newPassword);
		waitForElementClickable(driver, "//button[text()='Log in']").click();
		areJQueryAndJSLoadedSuccess(driver);
		Assert.assertEquals(getPageURL(driver), "https://demo.nopcommerce.com/");
	}
	
	@Test
	public void TC_05_My_Product_Reviews() {
		String randomNumber = getRandomNumber() + "";
		String reviewTitle = "Order Success " + randomNumber;
		String reviewContent = "Order Product " + randomNumber;
		hoverMouseToElement(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]");
		clickToElement(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Desktops')]");
		waitForElementClickable(driver, "//a[text()='Build your own computer']").click();
		waitForElementClickable(driver, "//a[text()='Add your review']").click();
		
		areJQueryAndJSLoadedSuccess(driver);
		sendkeyToElement(driver, "//input[@class='review-title']", reviewTitle);
		sendkeyToElement(driver, "//textarea[@class='review-text']", reviewContent);
		checkToCheckboxRadio(driver, "//input[@aria-label='Good']");
		
		clickToElement(driver, "//button[text()='Submit review']");
		
		Assert.assertEquals(getElementText(driver, "//span[text()='"+ firstName + "']/ancestor::div[@class='review-content']/preceding-sibling::div/div[@class='review-title']"), reviewTitle);
		Assert.assertEquals(getElementText(driver, "//span[text()='"+ firstName + "']/ancestor::div[@class='review-content']/div[@class='review-text']"), reviewContent);
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
