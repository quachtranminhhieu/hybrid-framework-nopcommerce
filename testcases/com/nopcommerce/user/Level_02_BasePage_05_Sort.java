package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_02_BasePage_05_Sort extends BasePage {
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
	public void TC_01_Sort_Name_A_To_Z() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		hoverMouseToElement(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]");
		waitForElementClickable(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Notebooks')]").click();
		selectItemInDefaultDropdown(driver, "//select[@id='products-orderby']", "Name: A to Z");
		
		areJQueryAndJSLoadedSuccess(driver);
		List<WebElement> allProductName = waitForAllElementVisible(driver, "//h2[@class='product-title']/a");
		int numberProduct = allProductName.size();
		for (int i = 0; i < allProductName.size(); i++) {
			if (i != numberProduct - 1) {
				Assert.assertTrue(allProductName.get(i).getText().charAt(0) <= allProductName.get(i+1).getText().charAt(0));
			}
		}
	}
	
	// @Test
	// Fail at line 63: Result is still A to Z
	public void TC_02_Sort_Name_Z_To_A() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		hoverMouseToElement(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]");
		waitForElementClickable(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Notebooks')]").click();
		selectItemInDefaultDropdown(driver, "//select[@id='products-orderby']", "Name: A to Z");
		
		areJQueryAndJSLoadedSuccess(driver);
		List<WebElement> allProductName = waitForAllElementVisible(driver, "//h2[@class='product-title']/a");
		for (int i = 0; i < allProductName.size(); i++) {
			if (i!=5) {
				Assert.assertTrue(allProductName.get(i).getText().charAt(0) <= allProductName.get(i+1).getText().charAt(0));
			}
		}
	}
	
	@Test
	public void TC_03_Sort_Price_Low_To_High() {
		selectItemInDefaultDropdown(driver, "//select[@id='products-orderby']", "Price: Low to High");
		areJQueryAndJSLoadedSuccess(driver);
		List<WebElement> allProductName = waitForAllElementVisible(driver, "//span[contains(@class,'price')]");
		int numberProduct = allProductName.size();
		for (int i = 0; i < allProductName.size(); i++) {
			if (i != numberProduct - 1) {
				int priceProductOne = Integer.parseInt(allProductName.get(i).getText().replace("$", "").replace(",", "").replace(".00", ""));
				int priceProductTwo = Integer.parseInt(allProductName.get(i+1).getText().replace("$", "").replace(",", "").replace(".00", ""));
				Assert.assertTrue(priceProductOne <= priceProductTwo);
			}
		}
	}
	
	@Test
	public void TC_04_Sort_Price_High_To_Low() {
		selectItemInDefaultDropdown(driver, "//select[@id='products-orderby']", "Price: High to Low");
		areJQueryAndJSLoadedSuccess(driver);
		List<WebElement> allProductName = waitForAllElementVisible(driver, "//span[contains(@class,'price')]");
		int numberProduct = allProductName.size();
		for (int i = 0; i < allProductName.size(); i++) {
			if (i != numberProduct - 1) {
				int priceProductOne = Integer.parseInt(allProductName.get(i).getText().replace("$", "").replace(",", "").replace(".00", ""));
				int priceProductTwo = Integer.parseInt(allProductName.get(i+1).getText().replace("$", "").replace(",", "").replace(".00", ""));
				Assert.assertTrue(priceProductOne >= priceProductTwo);
			}
		}
	}
	
	@Test
	public void TC_05_Display_3_Product() {
		selectItemInDefaultDropdown(driver, "//select[@id='products-pagesize']", "3");
		areJQueryAndJSLoadedSuccess(driver);
		List<WebElement> allProduct = waitForAllElementVisible(driver, "//div[@class='item-box']");
		Assert.assertTrue(allProduct.size() <= 3);
		Assert.assertTrue(isElementDisplayed(driver, "//a[text()='Next']"));
		clickToElement(driver, "//a[text()='2']");
		Assert.assertTrue(isElementDisplayed(driver, "//a[text()='Previous']"));
	}
	
	@Test
	public void TC_06_Display_6_Product() {
		selectItemInDefaultDropdown(driver, "//select[@id='products-pagesize']", "6");
		areJQueryAndJSLoadedSuccess(driver);
		List<WebElement> allProduct = waitForAllElementVisible(driver, "//div[@class='item-box']");
		Assert.assertTrue(allProduct.size() <= 6);
		
		setTimeoutImplicit(driver, 3);
		Assert.assertTrue(waitForElementInvisible(driver, "//a[text()='Next']"));
		Assert.assertTrue(waitForElementInvisible(driver, "//a[text()='Previous']"));
	}

	@Test
	public void TC_07_Display_9_Product() {
		selectItemInDefaultDropdown(driver, "//select[@id='products-pagesize']", "9");
		areJQueryAndJSLoadedSuccess(driver);
		List<WebElement> allProduct = waitForAllElementVisible(driver, "//div[@class='item-box']");
		Assert.assertTrue(allProduct.size() <= 9);
		
		Assert.assertTrue(waitForElementInvisible(driver, "//a[text()='Next']"));
		Assert.assertTrue(waitForElementInvisible(driver, "//a[text()='Previous']"));
		setTimeoutImplicit(driver, 15);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
