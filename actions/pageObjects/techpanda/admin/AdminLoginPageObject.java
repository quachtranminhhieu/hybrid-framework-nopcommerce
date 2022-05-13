package pageObjects.techpanda.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObjects.techpanda.user.PageGeneratorManager;
import pageUIs.techpanda.admin.AdminLoginPageUI;

public class AdminLoginPageObject extends BasePage{
	WebDriver driver;
	
	public AdminLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}


	public void inputToUserNameTextbox(String adminUserName) {
		waitForElementVisible(driver, AdminLoginPageUI.USER_NAME_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.USER_NAME_TEXTBOX, adminUserName);
	}


	public void inputToPasswordTextbox(String adminPassword) {
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, adminPassword);
	}


	public AdminCustomerPageObject clickToLoginButton() {
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminCustomerPage(driver);
	}


	public AdminCustomerPageObject loginAsAdmin(String adminUserName, String adminPassword) {
		inputToUserNameTextbox(adminUserName);
		inputToPasswordTextbox(adminPassword);
		return clickToLoginButton();
	}
	
}
