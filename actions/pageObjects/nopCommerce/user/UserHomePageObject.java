package pageObjects.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import io.qameta.allure.Step;
import pageUIs.nopCommerce.user.UserHomePageUI;

public class UserHomePageObject extends BasePage {
	private WebDriver driver;
	
	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Click to 'Register' link")
	public UserRegisterPageObject clickToRegisterLink() {
		waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK).click();
		return PageGeneratorManager.getUserRegisterPage(driver);
	}

	@Step("Click to 'Login' link")
	public UserLoginPageObject clickToLoginLink() {
		waitForElementClickable(driver, UserHomePageUI.LOGIN_LINK).click();
		return PageGeneratorManager.getUserLoginPage(driver);
	}

	@Step("Verify 'My Account' link is displayed")
	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, UserHomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, UserHomePageUI.MY_ACCOUNT_LINK);
	}

	@Step("Click to 'My Account' link")
	public UserCustomerInforPageObject clickToMyAccountLink() {
		waitForElementClickable(driver, UserHomePageUI.MY_ACCOUNT_LINK).click();
		return PageGeneratorManager.getUserCustomerInforPage(driver);
	}

	@Step("Hover mouse to 'Computer' link")
	public void hoverMouseToComputerLink() {
		waitForElementVisible(driver, UserHomePageUI.COMPUTER_LINK);
		hoverMouseToElement(driver, UserHomePageUI.COMPUTER_LINK);
	}
}
