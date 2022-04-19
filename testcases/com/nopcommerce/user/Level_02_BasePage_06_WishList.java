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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_02_BasePage_06_WishList extends BasePage {
	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	String firstName = "Automation";
	String lastName = "FC";
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
	public void TC_01_Register_And_Log_In() {
		openPageURL(driver, "https://demo.nopcommerce.com/");
		
		waitForElementClickable(driver, "//a[text()='Register']").click();
		
		sendkeyToElement(driver, "//input[@id='FirstName']", firstName);
		sendkeyToElement(driver, "//input[@id='LastName']", lastName);
		sendkeyToElement(driver, "//input[@id='Email']", "autoclass" + getRandomNumber() + "@gmail.com");
		sendkeyToElement(driver, "//input[@id='Password']", "123456");
		sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
		waitForElementClickable(driver, "//button[text()='Register']").click();
	}
	
	@Test
	public void TC_02_Add_To_WishList() {
		String productName = "Lenovo";
		hoverMouseToElement(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]");
		waitForElementClickable(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Desktops')]").click();
		
		waitForElementClickable(driver, "//a[contains(text(),'"+ productName + "')]").click();
		waitForElementClickable(driver, "//button[@id='add-to-wishlist-button-3']").click();
		Assert.assertEquals(getElementText(driver, "//p[@class='content']"), "The product has been added to your wishlist");
		clickToElement(driver, "//span[@class='close']");
		sleepInSecond(1);
		
		waitForElementClickable(driver, "//span[text()='Wishlist']").click();
		waitForElementVisible(driver, "//div[@class='page-title']");
		List<WebElement> allProduct = waitForAllElementVisible(driver, "//tbody//td[@class='product']/a");
		Assert.assertTrue(allProduct.size() == 1 && allProduct.get(0).getText().contains(productName));
		clickToElement(driver, "//span[text()='Your wishlist URL for sharing:']/following-sibling::a");
		Assert.assertEquals(getElementText(driver, "//div[@class='page-title']/h1"), "Wishlist of " + firstName + " " + lastName);
	}
	
	// @Test
	// Fail at line 83: Quantity of Wishlist not decrease (Still 1 not 0)
	public void TC_03_Add_Product_To_Cart_From_WishList() {
		clickToElement(driver, "//button[text()='Add to cart']");
		Assert.assertEquals(getElementText(driver, "//div[@class='bar-notification warning']/p"), "No products selected to add to cart.");
		checkToCheckboxRadio(driver, "//input[@name='addtocart']");
		clickToElement(driver, "//button[text()='Add to cart']");
		waitForElementVisible(driver, "//div[@class='page-title']/h1");
		Assert.assertTrue(getElementText(driver, "//span[@class='wishlist-qty']").equals("(0)"));
		Assert.assertTrue(getElementText(driver, "//span[@class='cart-qty']").equals("(1)"));
	}
	
	@Test
	public void TC_04_Remove_Product_In_WishList() {
		hoverMouseToElement(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]");
		waitForElementClickable(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Desktops')]").click();
		
		waitForElementClickable(driver, "//h2/a[contains(text(),'Lenovo')]").click();
		waitForElementClickable(driver, "//button[@id='add-to-wishlist-button-3']").click();
		clickToElement(driver, "//span[@class='close']");
		sleepInSecond(1);
		waitForElementClickable(driver, "//span[text()='Wishlist']").click();
		
		waitForElementVisible(driver, "//div[@class='page-title']");
		checkToCheckboxRadio(driver, "//input[@name='addtocart']");
		clickToElement(driver, "//button[@class='remove-btn']");
		Assert.assertTrue(getElementText(driver, "//div[@class='no-data']").contains("The wishlist is empty!"));
		Assert.assertTrue(getElementText(driver, "//span[@class='wishlist-qty']").equals("(0)"));
	}
	
	@Test
	public void TC_05_Add_Product_To_Compare() {
		String productNameOne = "Build your own computer";
		String productNameTwo = "Lenovo IdeaCentre 600 All-in-One PC";
		
		hoverMouseToElement(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]");
		waitForElementClickable(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Desktops')]").click();
		
		String productPriceOne = getElementText(driver, "//a[text()='"+ productNameOne +"']/parent::h2/following-sibling::div//span");
		clickToElement(driver, "//a[text()='" + productNameOne + "']/parent::h2/following-sibling::div//button[text()='Add to compare list']");
		waitForElementInvisible(driver, "//div[@class='ajax-loading-block-window']");
		Assert.assertEquals(getElementText(driver, "//p[@class='content']"), "The product has been added to your product comparison");
		
		String productPriceTwo = getElementText(driver, "//a[text()='"+ productNameTwo +"']/parent::h2/following-sibling::div//span");
		clickToElement(driver, "//a[text()='" + productNameTwo + "']/parent::h2/following-sibling::div//button[text()='Add to compare list']");
		waitForElementInvisible(driver, "//div[@class='ajax-loading-block-window']");
		Assert.assertEquals(getElementText(driver, "//p[@class='content']"), "The product has been added to your product comparison");
		clickToElement(driver, "//a[text()='Compare products list']");
		
		Assert.assertTrue(isElementDisplayed(driver, "//a[text()='Clear list']"));
		List<WebElement> allRemoveButton = waitForAllElementVisible(driver, "//button[text()='Remove']");
		Assert.assertTrue(allRemoveButton.size() == 2);
		
		List<WebElement> allProductName = waitForAllElementVisible(driver, "//tr[@class='product-name']/td/a");
		Assert.assertTrue(allProductName.size() == 2);
		for (WebElement productName : allProductName) {
			Assert.assertTrue(productName.getText().equals(productNameOne) || productName.getText().equals(productNameTwo));
		}
		
		List<WebElement> allProductPrice = waitForAllElementVisible(driver, "//tr[@class='product-price']/td[@style]");
		Assert.assertTrue(allProductName.size() == 2);
		for (WebElement productPrice : allProductPrice) {
			Assert.assertTrue(productPrice.getText().equals(productPriceOne) || productPrice.getText().equals(productPriceTwo));
		}
		
		clickToElement(driver, "//a[text()='Clear list']");
		Assert.assertEquals(getElementText(driver, "//div[@class='no-data']"), "You have no items to compare.");
	}

	@Test
	public void TC_06_Recently_Review_Product() {
		hoverMouseToElement(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]");
		waitForElementClickable(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Notebooks')]").click();
		
		areJQueryAndJSLoadedSuccess(driver);
		String viewProduct[] = new String[3];
		int j = 0;
		for (int i = 0; i < Integer.parseInt(getSelectedItemDefaultDropdown(driver, "//select[@id='products-pagesize']")); i++) {
			int productIndex = i + 1;
			WebElement productName = waitForElementClickable(driver, "//div[@class='item-box'][" + productIndex + "]//h2[@class='product-title']/a");
			if (i <= 4) {
				if (i >= 2) {
					viewProduct[j] = productName.getText();
					j++;
				}
				productName.click();
				areJQueryAndJSLoadedSuccess(driver);
				backToPage(driver);
				areJQueryAndJSLoadedSuccess(driver);
			}
		}
		
		clickToElement(driver, "//a[text()='Recently viewed products']");
		areJQueryAndJSLoadedSuccess(driver);
		
		List<WebElement> allProductName = waitForAllElementVisible(driver, "//h2[@class='product-title']/a");
		for (int i = 0; i < allProductName.size(); i++) {
			Assert.assertEquals(allProductName.get(i).getText(), viewProduct[allProductName.size() - 1- i]);
		}
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
