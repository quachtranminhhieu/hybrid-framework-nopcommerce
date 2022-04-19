package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopCommerce.user.UserAddressesPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserLoginPageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRegisterPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;

public class Level_09_Dynamic_Locator extends BaseTest {
	private WebDriver driver;
	private String emailAddress, firstName, lastName, password;

	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInforPageObject customerInforPage;
	private UserAddressesPageObject addressesPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointPageObject rewardPointPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {

		driver = getBrowserDriver(browserName);

		homePage = PageGeneratorManager.getUserHomePage(driver);

		emailAddress = "autoclass" + getRandomNumber() + "@gmail.com";
		firstName = "Automation";
		lastName = "FC";
		password = "123456";
	}

	@Test
	public void User_01_Register_Login() {
		registerPage = homePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextbox(firstName);
		registerPage.inputToLastNameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfimPasswordTextbox(password);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		homePage = registerPage.clickLogoutLink();
		
		loginPage = homePage.clickToLoginLink();

		loginPage.inputToEmailTextbox(emailAddress);
		loginPage.inputToPasswordTextbox(password);

		homePage = loginPage.clickToLoginButton();

		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		customerInforPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());
	}
	
	@Test
	public void User_02_Switch_Page() {
		// Customer Infor -> Addresses
		addressesPage = customerInforPage.openAddressesPage(driver);
		
		// Addresses -> My Product Review
		myProductReviewPage = addressesPage.openMyProductReviewPage(driver);
		
		// My Product Review -> Reward Point
		rewardPointPage = myProductReviewPage.openRewardPointPage(driver);
		
		// Reward Point -> Addresses
		addressesPage = rewardPointPage.openAddressesPage(driver);
		
		// Addresses -> Reward Point
		rewardPointPage = addressesPage.openRewardPointPage(driver);
		
		// Reward Point -> My Product Review
		myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
		
		// My Product Review -> Addresses
		addressesPage = myProductReviewPage.openAddressesPage(driver);
		
		// Addresses -> Customer Information
		customerInforPage = addressesPage.openCustomerInforPage(driver);
	}
	
	@Test
	public void User_03_Dynamic_Page_01() {
		// Customer Infor -> Addresses
		addressesPage = (UserAddressesPageObject) customerInforPage.openPagesAtMyAccountByName(driver,"Addresses");
		
		// Addresses -> My Product Review
		myProductReviewPage = (UserMyProductReviewPageObject) addressesPage.openPagesAtMyAccountByName(driver, "My product reviews");
		
		// My Product Review -> Reward Point
		rewardPointPage = (UserRewardPointPageObject) myProductReviewPage.openPagesAtMyAccountByName(driver, "Reward points");
		
		// Reward Point -> Addresses
		addressesPage = (UserAddressesPageObject) rewardPointPage.openPagesAtMyAccountByName(driver, "Addresses");
		
		// Addresses -> Reward Point
		rewardPointPage = (UserRewardPointPageObject) addressesPage.openPagesAtMyAccountByName(driver, "Reward points");
		
		// Reward Point -> My Product Review
		myProductReviewPage = (UserMyProductReviewPageObject) rewardPointPage.openPagesAtMyAccountByName(driver, "My product reviews");
		
		// My Product Review -> Addresses
		addressesPage = (UserAddressesPageObject) myProductReviewPage.openPagesAtMyAccountByName(driver, "Addresses");
		
		// Addresses -> Customer Information
		customerInforPage = (UserCustomerInforPageObject) addressesPage.openPagesAtMyAccountByName(driver, "Customer info");
	}

	@Test
	public void User_03_Dynamic_Page_02() {
		// Sử dụng khi hàm openPagesAtMyAccountByName không có switch case
		// Customer Infor -> Addresses
		customerInforPage.openPagesAtMyAccountByName(driver,"Addresses");
		addressesPage = PageGeneratorManager.getUserAddressesPage(driver);
		
		// Addresses -> My Product Review
		addressesPage.openPagesAtMyAccountByName(driver, "My product reviews");
		myProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);
		
		// My Product Review -> Reward Point
		myProductReviewPage.openPagesAtMyAccountByName(driver, "Reward points");
		rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
		
		// Reward Point -> Addresses
		rewardPointPage.openPagesAtMyAccountByName(driver, "Addresses");
		addressesPage = PageGeneratorManager.getUserAddressesPage(driver);
		
		// Addresses -> Reward Point
		addressesPage.openPagesAtMyAccountByName(driver, "Reward points");
		rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);
		
		// Reward Point -> My Product Review
		rewardPointPage.openPagesAtMyAccountByName(driver, "My product reviews");
		myProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);
		
		// My Product Review -> Addresses
		myProductReviewPage.openPagesAtMyAccountByName(driver, "Addresses");
		addressesPage = PageGeneratorManager.getUserAddressesPage(driver);
		
		// Addresses -> Customer Information
		addressesPage.openPagesAtMyAccountByName(driver, "Customer info");
		customerInforPage = PageGeneratorManager.getUserCustomerInforPage(driver);
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
