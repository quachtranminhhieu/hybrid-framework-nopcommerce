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

public class Level_02_BasePage_04_Search extends BasePage {
	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	String browserName = "Chrome";

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
	}
	
	@Test
	public void TC_01_Search_Empty_Data() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		clickToElement(driver, "//button[text()='Search']");
		
		Assert.assertEquals(getAlertText(driver), "Please enter some search keyword");
		acceptAlert(driver);
		
		sendkeyToElement(driver, "//input[@id='small-searchterms']", "12");
		clickToElement(driver, "//button[text()='Search']");
		
		Assert.assertEquals(getElementText(driver, "//div[@class='warning']"), "Search term minimum length is 3 characters");
	}
	
	@Test
	public void TC_02_Search_With_Data_Not_Exist() {
		clearTextbox(driver, "//input[@class='search-text']");
		sendkeyToElement(driver, "//input[@class='search-text']", "Macbook Pro 2050");
		clickToElement(driver, "//button[@class='button-1 search-button']");
		
		Assert.assertEquals(getElementText(driver, "//div[@class='no-result']"), "No products were found that matched your criteria.");
	}
	
	@Test
	public void TC_03_Search_With_Relative_Data() {
		String relativeName = "Lenovo";
		
		clearTextbox(driver, "//input[@class='search-text']");
		sendkeyToElement(driver, "//input[@class='search-text']", relativeName);
		clickToElement(driver, "//button[@class='button-1 search-button']");
		
		Assert.assertEquals(getElementSize(driver, "//div[@class='item-box']"), 2);
		
		List<WebElement> allProductName= waitForAllElementVisible(driver, "//h2[@class='product-title']/a");
		for (WebElement productName : allProductName) {
			Assert.assertTrue(productName.getText().contains(relativeName));
		}
	}
	
	@Test
	public void TC_04_Search_With_Absolute_Name() {
		String absoluteName = "Thinkpad X1 Carbon";
		
		clearTextbox(driver, "//input[@class='search-text']");
		sendkeyToElement(driver, "//input[@class='search-text']", absoluteName);
		clickToElement(driver, "//button[@class='button-1 search-button']");
		
		Assert.assertEquals(getElementSize(driver, "//div[@class='item-box']"), 1);
		Assert.assertTrue(getElementText(driver, "//h2[@class='product-title']/a").contains(absoluteName));
	}
	
	@Test
	public void TC_05_Advanced_Search_With_Parent_Categories() {
		
		clearTextbox(driver, "//input[@class='search-text']");
		sendkeyToElement(driver, "//input[@class='search-text']", "Apple MacBook Pro");
		checkToCheckboxRadio(driver, "//input[@id='advs']");
		selectItemInDefaultDropdown(driver, "//select[@id='cid']", "Computers");
		if (isElementSelected(driver, "//input[@id='isc']")) {
			checkToCheckboxRadio(driver, "//input[@id='isc']");
		}
		Assert.assertFalse(isElementSelected(driver, "//input[@id='isc']"));
		clickToElement(driver, "//button[@class='button-1 search-button']");
		
		Assert.assertEquals(getElementText(driver, "//div[@class='no-result']"), "No products were found that matched your criteria.");
	}
	
	@Test
	public void TC_06_Advanced_Search_With_Sub_Categories() {
		String macbookName = "Apple MacBook Pro";
		
		clearTextbox(driver, "//input[@class='search-text']");
		sendkeyToElement(driver, "//input[@class='search-text']", macbookName);
		checkToCheckboxRadio(driver, "//input[@id='advs']");
		selectItemInDefaultDropdown(driver, "//select[@id='cid']", "Computers");
		if (!isElementSelected(driver, "//input[@id='isc']")) {
			checkToCheckboxRadio(driver, "//input[@id='isc']");
		}
		Assert.assertTrue(isElementSelected(driver, "//input[@id='isc']"));
		
		clickToElement(driver, "//button[@class='button-1 search-button']");
		
		Assert.assertEquals(getElementSize(driver, "//div[@class='item-box']"), 1);
		Assert.assertTrue(getElementText(driver, "//h2[@class='product-title']/a").contains(macbookName));
	}
	
	@Test
	public void TC_07_Advanced_Search_With_Incorrect_Manufacture() {
		String macbookName = "Apple MacBook Pro";
		
		clearTextbox(driver, "//input[@class='search-text']");
		sendkeyToElement(driver, "//input[@class='search-text']", macbookName);
		checkToCheckboxRadio(driver, "//input[@id='advs']");
		selectItemInDefaultDropdown(driver, "//select[@id='cid']", "Computers");
		if (!isElementSelected(driver, "//input[@id='isc']")) {
			checkToCheckboxRadio(driver, "//input[@id='isc']");
		}
		Assert.assertTrue(isElementSelected(driver, "//input[@id='isc']"));
		selectItemInDefaultDropdown(driver, "//select[@id='mid']", "HP");
		clickToElement(driver, "//button[@class='button-1 search-button']");
		
		Assert.assertEquals(getElementText(driver, "//div[@class='no-result']"), "No products were found that matched your criteria.");
	}

	@Test
	public void TC_08_Advanced_Search_With_Correct_Manufacture() {
		String macbookName = "Apple MacBook Pro";
		
		clearTextbox(driver, "//input[@class='search-text']");
		sendkeyToElement(driver, "//input[@class='search-text']", macbookName);
		checkToCheckboxRadio(driver, "//input[@id='advs']");
		selectItemInDefaultDropdown(driver, "//select[@id='cid']", "Computers");
		if (!isElementSelected(driver, "//input[@id='isc']")) {
			checkToCheckboxRadio(driver, "//input[@id='isc']");
		}
		Assert.assertTrue(isElementSelected(driver, "//input[@id='isc']"));
		selectItemInDefaultDropdown(driver, "//select[@id='mid']", "Apple");
		clickToElement(driver, "//button[@class='button-1 search-button']");
		
		Assert.assertEquals(getElementSize(driver, "//div[@class='item-box']"), 1);
		Assert.assertTrue(getElementText(driver, "//h2[@class='product-title']/a").contains(macbookName));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
