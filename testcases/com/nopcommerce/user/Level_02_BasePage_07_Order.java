package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BasePage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

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

public class Level_02_BasePage_07_Order extends BasePage {
	String projectPath = System.getProperty("user.dir");
	WebDriver driver;
	String browserName = "Chrome";
	
	String productName;
	String processorName;
	String ramCapacity;
	String hddCapacity;
	String osName;
	String microsoftOffice;
	String acrobatReader;
	String totalCommander;
	
	String randomEmail;
	String firstName = "Automation";
	String lastName = "FC";
	String password = "123456";
	String newPassword = "1234567";
	String companyName = "Automation FC";
	String city = "Da Nang";
	String address1 = "123/04 Le Lai";
	String address2 = "234/05 Hai Phong";
	String zipCode = "550000";
	String country = "Viet Nam";
	String phoneNumber = "0123456789";
	String faxNumber = "0987654321";
	
	String payMethod;
	String shipMethod;
	String giftWrapping;
	String skuNumber;
	String orderNumber;
	String productQuantity;
	String unitPrice;
	String totalPriceBefore;
	String totalPriceAfter;
	Boolean orderedBefore = false;
	
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
	public void TC_02_Add_Product_To_Cart(){
		productQuantity = "1";
		productName = "Build your own computer";
		processorName = "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]";
		ramCapacity = "8GB [+$60.00]";
		hddCapacity = "400 GB [+$100.00]";
		osName = "Vista Premium [+$60.00]";
		microsoftOffice = "Microsoft Office [+$50.00]";
		acrobatReader = "Acrobat Reader [+$10.00]";
		totalCommander = "Total Commander [+$5.00]";
		
		hoverMouseToElement(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]");
		waitForElementClickable(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Desktops')]").click();
		
		waitForElementClickable(driver, "//h2//a[text()='" + productName + "']").click();
		areJQueryAndJSLoadedSuccess(driver);
		selectItemInDefaultDropdown(driver, "//select[@id='product_attribute_1']", processorName);
		selectItemInDefaultDropdown(driver, "//select[@id='product_attribute_2']", ramCapacity);
		checkToCheckboxRadio(driver, "//label[text()='" + hddCapacity + "']/preceding-sibling::input");
		checkToCheckboxRadio(driver, "//label[text()='" + osName + "']/preceding-sibling::input");
		checkToCheckboxRadio(driver, "//label[text()='" + microsoftOffice + "']/preceding-sibling::input");
		checkToCheckboxRadio(driver, "//label[text()='" + acrobatReader + "']/preceding-sibling::input");
		checkToCheckboxRadio(driver, "//label[text()='" + totalCommander + "']/preceding-sibling::input");
		clickToElement(driver, "//button[text()='Add to cart']");
		
		waitForElementVisible(driver, "//div[@class='bar-notification success']");
		Assert.assertEquals(getElementText(driver, "//div[@class='bar-notification success']/p"), "The product has been added to your shopping cart");
		clickToElement(driver, "//span[@class='close']");
		sleepInSecond(1);
		unitPrice = getElementText(driver, "//span[@id='price-value-1']").trim();
		totalPriceBefore = unitPrice;
		Assert.assertEquals(getElementText(driver, "//span[@class='cart-qty']"), "(" + productQuantity + ")");
		
		scrollToElement(driver, "//span[@class='cart-qty']");
		hoverMouseToElement(driver, "//span[@class='cart-qty']");
		Assert.assertEquals(getElementText(driver, "//div[@class='count']/a"), productQuantity + " item(s)");
		Assert.assertEquals(getElementText(driver, "//div[@class='name']/a"), productName);
		Assert.assertTrue(getElementText(driver, "//div[@class='attributes']").contains(processorName));
		Assert.assertTrue(getElementText(driver, "//div[@class='attributes']").contains(ramCapacity));
		Assert.assertTrue(getElementText(driver, "//div[@class='attributes']").contains(hddCapacity));
		Assert.assertTrue(getElementText(driver, "//div[@class='attributes']").contains(osName));
		Assert.assertTrue(getElementText(driver, "//div[@class='attributes']").contains(microsoftOffice));
		Assert.assertTrue(getElementText(driver, "//div[@class='attributes']").contains(acrobatReader));
		Assert.assertTrue(getElementText(driver, "//div[@class='attributes']").contains(totalCommander));
		Assert.assertEquals(getElementText(driver, "//div[@class='price']/span"), unitPrice);
		Assert.assertEquals(getElementText(driver, "//div[@class='quantity']/span"), productQuantity);
		Assert.assertEquals(getElementText(driver, "//div[@class='totals']/strong"), totalPriceBefore);
	}

	@Test
	public void TC_03_Edit_Product_In_Shopping_Cart() {
		processorName = "2.2 GHz Intel Pentium Dual-Core E2200";
		productQuantity = "2";
		ramCapacity = "4GB [+$20.00]";
		hddCapacity = "320 GB";
		osName = "Vista Home [+$50.00]";
		acrobatReader = "Acrobat Reader [+$10.00]";
		totalCommander = "Total Commander [+$5.00]";
		
		clickToElement(driver, "//span[@class='cart-qty']");
		waitForElementClickable(driver, "//a[text()='Edit']").click();
		
		selectItemInDefaultDropdown(driver, "//select[@id='product_attribute_1']", processorName);
		selectItemInDefaultDropdown(driver, "//select[@id='product_attribute_2']", ramCapacity);
		checkToCheckboxRadio(driver, "//label[text()='" + hddCapacity + "']/preceding-sibling::input");
		checkToCheckboxRadio(driver, "//label[text()='" + osName + "']/preceding-sibling::input");
		unCheckToCheckbox(driver, "//label[text()='" + acrobatReader + "']/preceding-sibling::input");
		unCheckToCheckbox(driver, "//label[text()='" + totalCommander + "']/preceding-sibling::input");
		clearTextbox(driver, "//input[@id='product_enteredQuantity_1']");
		sendkeyToElement(driver, "//input[@id='product_enteredQuantity_1']", productQuantity);
		clickToElement(driver, "//button[text()='Update']");
		
		waitForElementVisible(driver, "//div[@class='bar-notification success']");
		Assert.assertEquals(getElementText(driver, "//div[@class='bar-notification success']/p"), "The product has been added to your shopping cart");
		clickToElement(driver, "//span[@class='close']");
		unitPrice = getElementText(driver, "//span[@id='price-value-1']").trim();
		sleepInSecond(1);
		
		clickToElement(driver, "//span[@class='cart-qty']");
		areJQueryAndJSLoadedSuccess(driver);
		Assert.assertTrue(getElementText(driver, "//span[@class='cart-qty']").contains(productQuantity));
		Assert.assertTrue(getElementText(driver, "//td[@class='product']/a").contains(productName));
		Assert.assertTrue(getElementAttribute(driver, "//div[@class='product']//div[@class='attributes']", "textContent").contains(processorName));
		Assert.assertTrue(getElementAttribute(driver, "//div[@class='product']//div[@class='attributes']", "textContent").contains(ramCapacity));
		Assert.assertTrue(getElementAttribute(driver, "//div[@class='product']//div[@class='attributes']", "textContent").contains(hddCapacity));
		Assert.assertTrue(getElementAttribute(driver, "//div[@class='product']//div[@class='attributes']", "textContent").contains(osName));
		Assert.assertTrue(getElementAttribute(driver, "//div[@class='product']//div[@class='attributes']", "textContent").contains(microsoftOffice));
		Assert.assertTrue(getElementText(driver, "//span[@class='product-unit-price']").equals(unitPrice));
		Assert.assertTrue(getElementAttribute(driver, "//input[@class='qty-input']", "value").toString().equals(productQuantity));
		String stringTotalPrice = getElementText(driver, "//span[@class='product-subtotal']").replace("$", "").replace(",", "").replace(".00", "");
		int intTotalPrice = Integer.parseInt(stringTotalPrice);
		String stringUnitPrice = getElementText(driver, "//span[@class='product-unit-price']").replace("$", "").replace(",", "").replace(".00", "");
		int intUnitPrice = Integer.parseInt(stringUnitPrice);
		Assert.assertTrue(intTotalPrice / intUnitPrice == Integer.parseInt(productQuantity));
	}
	
	@Test
	public void TC_04_Remove_From_Cart() {
		clickToElement(driver, "//span[@class='cart-qty']");
		waitForElementClickable(driver, "//button[@class='remove-btn']").click();
		Assert.assertTrue(getElementText(driver, "//div[@class='no-data']").contains("Your Shopping Cart is empty!"));
		Assert.assertTrue(getElementText(driver, "//span[@class='cart-qty']").equals("(0)"));
	}
	
	@Test
	public void TC_05_Update_Shopping_Cart(){
		productName = "Lenovo IdeaCentre 600 All-in-One PC";
		productQuantity = "5";
		
		hoverMouseToElement(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]");
		waitForElementClickable(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Desktops')]").click();
		
		waitForElementClickable(driver, "//h2/a[text()='" + productName + "']").click();
		waitForElementClickable(driver, "//button[@id='add-to-cart-button-3']").click();
		
		clickToElement(driver, "//span[@class='close']");
		sleepInSecond(1);
		
		clickToElement(driver, "//span[@class='cart-qty']");
		clearTextbox(driver, "//input[@class='qty-input']");
		sendkeyToElement(driver, "//input[@class='qty-input']", productQuantity);
		clickToElement(driver, "//button[@id='updatecart']");
		areJQueryAndJSLoadedSuccess(driver);
		unitPrice = getElementText(driver, "//span[@class='product-unit-price']").trim();
		totalPriceBefore = getElementText(driver, "//span[@class='product-subtotal']");
		String stringUnitPrice = unitPrice.replace("$", "").replace(",", "").replace(".00", "");
		int intUnitPrice = Integer.parseInt(stringUnitPrice);
		String stringTotalPrice = totalPriceBefore.replace("$", "").replace(",", "").replace(".00", "");
		int intTotalPrice = Integer.parseInt(stringTotalPrice);
		Assert.assertTrue(intTotalPrice / intUnitPrice == Integer.parseInt(productQuantity));
		Assert.assertEquals(getElementText(driver, "//tr[@class='order-subtotal']//span"), totalPriceBefore);
		Assert.assertEquals(getElementText(driver, "//tr[@class='order-total']//strong"), totalPriceBefore);
		clickToElement(driver, "//button[@class='remove-btn']");
	}
	
	@Test
	public void TC_06_Checkout_Order_By_Credit_Card() {
		productName = "Apple MacBook Pro 13-inch";
		shipMethod = "2nd Day Air";
		payMethod = "credit";
		productQuantity = "2";
		giftWrapping = "Yes [+$10.00]";
		
		hoverMouseToElement(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]");
		waitForElementClickable(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Notebooks')]").click();
		
		waitForElementClickable(driver, "//h2//a[text()='" + productName + "']").click();
		waitForElementClickable(driver, "//div[@class='add-to-cart-panel']/button").click();
		
		clickToElement(driver, "//span[@class='close']");
		sleepInSecond(1);
		clickToElement(driver, "//span[@class='cart-qty']");

		waitForElementClickable(driver, "//a[@id='open-estimate-shipping-popup']").click();
		waitForElementVisible(driver, "//div[@id='estimate-shipping-popup']");
		selectItemInDefaultDropdown(driver, "//select[@id='CountryId']", country);
		selectItemInDefaultDropdown(driver, "//select[@id='StateProvinceId']", "Other");
		clearTextbox(driver, "//input[@id='ZipPostalCode']");
		sendkeyToElement(driver, "//input[@id='ZipPostalCode']", zipCode);
		waitForElementClickable(driver, "//div[text()='" + shipMethod + "']/preceding-sibling::div/label").click();
		clickToElement(driver, "//button[text()='Apply']");
		
		areJQueryAndJSLoadedSuccess(driver);
		skuNumber = getElementText(driver, "//span[@class='sku-number']");
		unitPrice = getElementText(driver, "//span[@class='product-unit-price']");
		totalPriceBefore = getElementText(driver, "//span[@class='product-subtotal']");
		selectItemInDefaultDropdown(driver, "//select[@id='checkout_attribute_1']", giftWrapping);
		sleepInSecond(1);
		totalPriceAfter = getElementText(driver, "//tr[@class='order-total']//strong");
		
		waitForElementClickable(driver, "//input[@id='termsofservice']").click();
		clickToElement(driver, "//button[@id='checkout']");
		
		if (!orderedBefore) {
			senkeyToBillingAddressForm();
		}
			
		waitForElementClickable(driver, "//div[@id='billing-buttons-container']/button[text()='Continue']").click();
		
		waitForElementClickable(driver, "//label[contains(text(),'" + shipMethod + "')]/preceding-sibling::input").click();
		clickToElement(driver, "//div[@id='shipping-method-buttons-container']//button[text()='Continue']");
		
		waitForElementClickable(driver, "//div[contains(text(),'" + payMethod + "')]/preceding-sibling::input").click();
		clickToElement(driver, "//div[@id='payment-method-buttons-container']//button[text()='Continue']");
		
		waitForElementVisible(driver, "//input[@id='CardholderName']");
		sendkeyToElement(driver, "//input[@id='CardholderName']", faxNumber);
		sendkeyToElement(driver, "//input[@id='CardNumber']", "4556593132732755");
		selectItemInDefaultDropdown(driver, "//select[@id='ExpireMonth']", "08");
		selectItemInDefaultDropdown(driver, "//select[@id='ExpireYear']", "2027");
		sendkeyToElement(driver, "//input[@id='CardCode']", "0123");
		clickToElement(driver, "//div[@id='payment-info-buttons-container']//button[text()='Continue']");
		
		waitForElementVisible(driver, "//div[@class='order-review-data']");
		
		verifyBillingAddress();
		Assert.assertTrue(getElementText(driver, "//li[@class='payment-method']/span[@class='value']").toLowerCase().contains(payMethod));
		
		verifyShippingAddress();
		Assert.assertTrue(getElementText(driver, "//li[@class='shipping-method']/span[@class='value']").equals(shipMethod));
		
		verifyProductInfo();
		Assert.assertEquals(getElementText(driver, "//label[text()='Total:']/parent::td/following-sibling::td//strong"), totalPriceAfter);
		
		handleCannotOrderConsecutively();
		
		areJQueryAndJSLoadedSuccess(driver);
		waitForElementVisible(driver, "//div[@class='page-title']/h1[text()='Thank you']");
		Assert.assertEquals(getElementText(driver, "//div[@class='section order-completed']//div[@class='title']/strong"), "Your order has been successfully processed!");
		orderNumber = getElementText(driver, "//div[@class='section order-completed']//div[@class='order-number']/strong");
		clickToElement(driver, "//button[text()='Continue']");
		
		waitForElementClickable(driver, "//div[@class='header']//a[text()='My account']").click();
		waitForElementClickable(driver, "//li[@class='customer-orders inactive']/a").click();
		
		Assert.assertTrue(getElementText(driver, "//div[@class='section order-item']//strong").equalsIgnoreCase(orderNumber));
		Assert.assertEquals(getElementText(driver, "//span[@class='order-total']"), totalPriceAfter);
		
		String[] number = orderNumber.split(" ");
		clickToElement(driver, "//strong[text()='Order Number: " + number[2]+ "']/parent::div/following-sibling::div/button[text()='Details']");
		
		Assert.assertEquals(getElementText(driver, "//li[@class='order-total']//strong"), totalPriceAfter);
		
		verifyBillingAddress();
		Assert.assertTrue(getElementText(driver, "//li[@class='payment-method']/span[@class='value']").toLowerCase().contains(payMethod));
		
		verifyShippingAddress();
		Assert.assertTrue(getElementText(driver, "//li[@class='shipping-method']/span[@class='value']").equals(shipMethod));
		
		verifyProductInfo();
		Assert.assertEquals(getElementText(driver, "//label[text()='Order Total:']/parent::td/following-sibling::td//strong"), totalPriceAfter);
	}
	
	@Test
	public void TC_07_Checkout_Order_By_Money() {
		productName = "Asus N551JK-XO076H Laptop";
		payMethod = "Money";
		productQuantity = "3";
		shipMethod = "Ground";
		giftWrapping = "No";
		
		hoverMouseToElement(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Computers')]");
		waitForElementClickable(driver, "//ul[@class='top-menu notmobile']//a[contains(text(),'Notebooks')]").click();
		
		waitForElementClickable(driver, "//h2//a[text()='" + productName + "']").click();
		waitForElementClickable(driver, "//div[@class='add-to-cart-panel']/button").click();
		
		clickToElement(driver, "//span[@class='close']");
		sleepInSecond(1);
		clickToElement(driver, "//span[@class='cart-qty']");
		
		areJQueryAndJSLoadedSuccess(driver);
		clearTextbox(driver, "//input[@class='qty-input']");
		sendkeyToElement(driver, "//input[@class='qty-input']", productQuantity);
		clickToElement(driver, "//button[@id='updatecart']");
		
		waitForElementClickable(driver, "//a[@id='open-estimate-shipping-popup']").click();
		waitForElementVisible(driver, "//div[@id='estimate-shipping-popup']");
		selectItemInDefaultDropdown(driver, "//select[@id='CountryId']", country);
		selectItemInDefaultDropdown(driver, "//select[@id='StateProvinceId']", "Other");
		clearTextbox(driver, "//input[@id='ZipPostalCode']");
		sendkeyToElement(driver, "//input[@id='ZipPostalCode']", zipCode);
		waitForElementClickable(driver, "//div[text()='" + shipMethod + "']/preceding-sibling::div/label").click();
		clickToElement(driver, "//button[text()='Apply']");
		
		areJQueryAndJSLoadedSuccess(driver);
		skuNumber = getElementText(driver, "//span[@class='sku-number']");
		unitPrice = getElementText(driver, "//span[@class='product-unit-price']");
		totalPriceBefore = getElementText(driver, "//span[@class='product-subtotal']");
		selectItemInDefaultDropdown(driver, "//select[@id='checkout_attribute_1']", giftWrapping);
		totalPriceAfter = getElementText(driver, "//tr[@class='order-total']//strong");
		
		waitForElementClickable(driver, "//input[@id='termsofservice']").click();
		clickToElement(driver, "//button[@id='checkout']");
		
		if (!orderedBefore) {
			senkeyToBillingAddressForm();
		}
		waitForElementClickable(driver, "//div[@id='billing-buttons-container']/button[text()='Continue']").click();
		
		
		waitForElementClickable(driver, "//div[@id='shipping-method-buttons-container']//button[text()='Continue']").click();
		
		Assert.assertTrue(isElementSelected(driver, "//label[contains(text(),'" + payMethod + "')]/preceding-sibling::input"));
		clickToElement(driver, "//div[@id='payment-method-buttons-container']//button[text()='Continue']");
		
		Assert.assertTrue(isElementDisplayed(driver, "//div[@class='info']"));
		clickToElement(driver, "//div[@id='payment-info-buttons-container']//button[text()='Continue']");
		
		verifyBillingAddress();
		Assert.assertTrue(getElementText(driver, "//li[@class='payment-method']/span[@class='value']").contains(payMethod));
		
		verifyShippingAddress();
		Assert.assertTrue(getElementText(driver, "//li[@class='shipping-method']/span[@class='value']").trim().contains(shipMethod));
		
		verifyProductInfo();
		Assert.assertEquals(getElementText(driver, "//label[text()='Total:']/parent::td/following-sibling::td//strong"), totalPriceAfter);
		
		handleCannotOrderConsecutively();
		
		areJQueryAndJSLoadedSuccess(driver);
		waitForElementVisible(driver, "//div[@class='page-title']/h1[text()='Thank you']");
		Assert.assertEquals(getElementText(driver, "//div[@class='section order-completed']//div[@class='title']/strong"), "Your order has been successfully processed!");
		orderNumber = getElementText(driver, "//div[@class='section order-completed']//div[@class='order-number']/strong");
		clickToElement(driver, "//button[text()='Continue']");
		
		waitForElementClickable(driver, "//div[@class='header']//a[text()='My account']").click();
		waitForElementClickable(driver, "//li[@class='customer-orders inactive']/a").click();
		
		Assert.assertTrue(getElementText(driver, "//div[@class='section order-item']//strong").equalsIgnoreCase(orderNumber));
		Assert.assertEquals(getElementText(driver, "//span[@class='order-total']"), totalPriceAfter);
		
		String[] number = orderNumber.split(" ");
		clickToElement(driver, "//strong[text()='Order Number: " + number[2]+ "']/parent::div/following-sibling::div/button[text()='Details']");
		
		Assert.assertEquals(getElementText(driver, "//li[@class='order-total']//strong"), totalPriceAfter);
		
		verifyBillingAddress();
		Assert.assertTrue(getElementText(driver, "//li[@class='payment-method']/span[@class='value']").contains(payMethod));
		
		verifyShippingAddress();
		Assert.assertTrue(getElementText(driver, "//li[@class='shipping-method']/span[@class='value']").trim().equals(shipMethod));
		
		verifyProductInfo();
		Assert.assertEquals(getElementText(driver, "//label[text()='Order Total:']/parent::td/following-sibling::td//strong"), totalPriceAfter);
	}
	
	@Test
	public void TC_08_Re_Order() {
		productQuantity = "10";
		shipMethod = "Next Day Air";
		payMethod = "Money";
		giftWrapping = "Yes [+$10.00]";
		
		
		clickToElement(driver, "//div[@class='header']//a[text()='My account']");
		waitForElementClickable(driver, "//li[@class='customer-orders inactive']/a").click();
		
		String[] number = orderNumber.split(" ");
		clickToElement(driver, "//strong[text()='Order Number: " + number[2]+ "']/parent::div/following-sibling::div/button[text()='Details']");
		
		clickToElement(driver, "//button[text()='Re-order']");
		areJQueryAndJSLoadedSuccess(driver);
		clearTextbox(driver, "//input[@class='qty-input']");
		sendkeyToElement(driver, "//input[@class='qty-input']", productQuantity);
		clickToElement(driver, "//button[text()='Update shopping cart']");
		
		waitForElementClickable(driver, "//a[@id='open-estimate-shipping-popup']").click();
		waitForElementVisible(driver, "//div[@id='estimate-shipping-popup']");
		selectItemInDefaultDropdown(driver, "//select[@id='CountryId']", country);
		selectItemInDefaultDropdown(driver, "//select[@id='StateProvinceId']", "Other");
		clearTextbox(driver, "//input[@id='ZipPostalCode']");
		sendkeyToElement(driver, "//input[@id='ZipPostalCode']", zipCode);
		waitForElementClickable(driver, "//div[text()='" + shipMethod + "']/preceding-sibling::div/label").click();
		clickToElement(driver, "//button[text()='Apply']");
		
		areJQueryAndJSLoadedSuccess(driver);
		unitPrice = getElementText(driver, "//span[@class='product-unit-price']");
		totalPriceBefore = getElementText(driver, "//span[@class='product-subtotal']");
		selectItemInDefaultDropdown(driver, "//select[@id='checkout_attribute_1']", giftWrapping);
		sleepInSecond(1);
		totalPriceAfter = getElementText(driver, "//tr[@class='order-total']//strong");
		
		waitForElementClickable(driver, "//input[@id='termsofservice']").click();
		clickToElement(driver, "//button[@id='checkout']");
		
		areJQueryAndJSLoadedSuccess(driver);
		selectItemInDefaultDropdown(driver, "//select[@id='billing-address-select']", "New Address");
		
		senkeyToBillingAddressForm();
		
		clickToElement(driver, "//div[@id='billing-buttons-container']/button[text()='Continue']");
		
		waitForElementClickable(driver, "//label[contains(text(),'" + shipMethod + "')]/preceding-sibling::input").click();
		waitForElementClickable(driver, "//div[@id='shipping-method-buttons-container']//button[text()='Continue']").click();
		
		if (!isElementSelected(driver, "//label[contains(text(),'" + payMethod + "')]/preceding-sibling::input")) {
			clickToElement(driver, "//label[contains(text(),'" + payMethod + "')]/preceding-sibling::input");
		}
		
		waitForElementClickable(driver, "//div[@id='payment-method-buttons-container']//button[text()='Continue']").click();
		waitForElementClickable(driver, "//div[@id='payment-info-buttons-container']//button[text()='Continue']").click();
		
		waitForElementVisible(driver, "//div[@class='order-review-data']");
		
		verifyBillingAddress();
		Assert.assertTrue(getElementText(driver, "//li[@class='payment-method']/span[@class='value']").contains(payMethod));
		
		verifyShippingAddress();
		Assert.assertTrue(getElementText(driver, "//li[@class='shipping-method']/span[@class='value']").trim().equals(shipMethod));
		
		verifyProductInfo();
		Assert.assertEquals(getElementText(driver, "//label[text()='Total:']/parent::td/following-sibling::td//strong"), totalPriceAfter);
		
		handleCannotOrderConsecutively();
		
		areJQueryAndJSLoadedSuccess(driver);
		waitForElementVisible(driver, "//div[@class='page-title']/h1[text()='Thank you']");
		Assert.assertEquals(getElementText(driver, "//div[@class='section order-completed']//div[@class='title']/strong"), "Your order has been successfully processed!");
		orderNumber = getElementText(driver, "//div[@class='section order-completed']//div[@class='order-number']/strong");
		clickToElement(driver, "//button[text()='Continue']");
		
		waitForElementClickable(driver, "//div[@class='header']//a[text()='My account']").click();
		waitForElementClickable(driver, "//li[@class='customer-orders inactive']/a").click();
		
		Assert.assertTrue(getElementText(driver, "//div[@class='section order-item']//strong").equalsIgnoreCase(orderNumber));
		Assert.assertEquals(getElementText(driver, "//span[@class='order-total']"), totalPriceAfter);
		
		clickToElement(driver, "//button[text()='Details']");
		
		Assert.assertEquals(getElementText(driver, "//li[@class='order-total']//strong"), totalPriceAfter);
		
		verifyBillingAddress();
		Assert.assertTrue(getElementText(driver, "//li[@class='payment-method']/span[@class='value']").contains(payMethod));
		
		verifyShippingAddress();
		Assert.assertTrue(getElementText(driver, "//li[@class='shipping-method']/span[@class='value']").trim().equals(shipMethod));
		
		verifyProductInfo();
		Assert.assertEquals(getElementText(driver, "//label[text()='Order Total:']/parent::td/following-sibling::td//strong"), totalPriceAfter);
	}

	public void verifyBillingAddress() {
		Assert.assertTrue(getElementText(driver, "//strong[text()='Billing Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='name']").contains(firstName + " " + lastName));
		Assert.assertTrue(getElementText(driver, "//strong[text()='Billing Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='email']").contains(randomEmail));
		Assert.assertTrue(getElementText(driver, "//strong[text()='Billing Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='phone']").contains(phoneNumber));
		Assert.assertTrue(getElementText(driver, "//strong[text()='Billing Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='fax']").contains(faxNumber));
		Assert.assertTrue(getElementText(driver, "//strong[text()='Billing Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='company']").contains(companyName));
		Assert.assertTrue(getElementText(driver, "//strong[text()='Billing Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='address1']").contains(address1));
		Assert.assertTrue(getElementText(driver, "//strong[text()='Billing Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='address2']").contains(address2));
		Assert.assertTrue(getElementText(driver, "//strong[text()='Billing Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='city-state-zip']").contains(city));
		Assert.assertTrue(getElementText(driver, "//strong[text()='Billing Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='city-state-zip']").contains(zipCode));
		Assert.assertTrue(getElementText(driver, "//strong[text()='Billing Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='country']").contains(country));
	}
	
	public void verifyShippingAddress() {
		Assert.assertTrue(getElementText(driver, "//strong[text()='Shipping Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='name']").contains(firstName + " " + lastName));
		Assert.assertTrue(getElementText(driver, "//strong[text()='Shipping Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='email']").contains(randomEmail));
		Assert.assertTrue(getElementText(driver, "//strong[text()='Shipping Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='phone']").contains(phoneNumber));
		Assert.assertTrue(getElementText(driver, "//strong[text()='Shipping Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='fax']").contains(faxNumber));
		Assert.assertTrue(getElementText(driver, "//strong[text()='Shipping Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='company']").contains(companyName));
		Assert.assertTrue(getElementText(driver, "//strong[text()='Shipping Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='address1']").contains(address1));
		Assert.assertTrue(getElementText(driver, "//strong[text()='Shipping Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='address2']").contains(address2));
		Assert.assertTrue(getElementText(driver, "//strong[text()='Shipping Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='city-state-zip']").contains(city));
		Assert.assertTrue(getElementText(driver, "//strong[text()='Shipping Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='city-state-zip']").contains(zipCode));
		Assert.assertTrue(getElementText(driver, "//strong[text()='Shipping Address']/parent::div/following-sibling::ul[@class='info-list']/li[@class='country']").contains(country));
	}
	
	public void verifyProductInfo() {
		Assert.assertEquals(getElementText(driver, "//td[@class='product']//a"), productName);
		Assert.assertEquals(getElementText(driver, "//span[@class='sku-number']"), skuNumber);
		Assert.assertEquals(getElementText(driver, "//span[@class='product-unit-price']"), unitPrice);
		Assert.assertEquals(getElementText(driver, "//span[@class='product-quantity']"), productQuantity);
		Assert.assertEquals(getElementText(driver, "//span[@class='product-subtotal']"), totalPriceBefore);
		Assert.assertTrue(getElementText(driver, "//div[@class='selected-checkout-attributes']").contains(giftWrapping));
		Assert.assertEquals(getElementText(driver, "//label[text()='Total:']/following-sibling::span"), totalPriceBefore);
	}
	
	public void senkeyToBillingAddressForm() {
		areJQueryAndJSLoadedSuccess(driver);
		clearTextbox(driver, "//input[@id='BillingNewAddress_FirstName']");
		clearTextbox(driver, "//input[@id='BillingNewAddress_LastName']");
		clearTextbox(driver, "//input[@id='BillingNewAddress_Email']");
		sendkeyToElement(driver, "//input[@id='BillingNewAddress_FirstName']", firstName);
		sendkeyToElement(driver, "//input[@id='BillingNewAddress_LastName']", lastName);
		sendkeyToElement(driver, "//input[@id='BillingNewAddress_Email']", randomEmail);
		sendkeyToElement(driver, "//input[@id='BillingNewAddress_Company']", companyName);
		selectItemInDefaultDropdown(driver, "//select[@id='BillingNewAddress_CountryId']", country);
		sendkeyToElement(driver, "//input[@id='BillingNewAddress_City']", city);
		sendkeyToElement(driver, "//input[@id='BillingNewAddress_Address1']", address1);
		sendkeyToElement(driver, "//input[@id='BillingNewAddress_Address2']", address2);
		sendkeyToElement(driver, "//input[@id='BillingNewAddress_ZipPostalCode']", zipCode);
		sendkeyToElement(driver, "//input[@id='BillingNewAddress_PhoneNumber']", phoneNumber);
		sendkeyToElement(driver, "//input[@id='BillingNewAddress_FaxNumber']", faxNumber);
	}
	
	public void handleCannotOrderConsecutively() {
		if (orderedBefore) {
			longTimeout = 3;
			while (true) {
				if (getPageURL(driver).equals("https://demo.nopcommerce.com/onepagecheckout#opc-confirm_order")) {
					clickToElement(driver, "//button[text()='Confirm']");
					areJQueryAndJSLoadedSuccess(driver);
					if (getPageURL(driver).equals("https://demo.nopcommerce.com/onepagecheckout#opc-confirm_order")) {
						try {
						acceptAlert(driver);
					} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				else {
					break;
				}
			}
		} else {
			clickToElement(driver, "//button[text()='Confirm']");
		}
		longTimeout = 15;
		orderedBefore = true;
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
