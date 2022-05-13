package pageObjects.techpanda.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.techpanda.user.BasePageUI;
import pageUIs.techpanda.user.UserHomePageUI;
import pageUIs.techpanda.user.UserRegisterPageUI;

public class UserHomePageObject extends BasePage{
	WebDriver driver;
	
	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickToAccountButton() {
		waitForElementClickable(driver, BasePageUI.MY_ACCOUNT_ICON);
		clickToElement(driver, BasePageUI.MY_ACCOUNT_ICON);
	}

	public BasePage clickToLinkByText(String textLink) {
		clickToAccountButton();
		clickToElement(driver, BasePageUI.LINK_AT_HEADER_ACCOUNT_BY_TEXT, textLink);
		
		switch (textLink) {
		case "Register":
			return PageGeneratorManager.getUserRegisterPage(driver);
		case "Log In":
			return PageGeneratorManager.getUserLoginPage(driver);
		case "Log Out":
			return PageGeneratorManager.getUserHomePage(driver);
		default:
			throw new RuntimeException("Please input correct text link");
		}
	}

	public boolean isSucessfullRegisterMessageDisplayed() {
		waitForElementVisible(driver, UserHomePageUI.SUCCESSFUL_REGISTER_MESSAGE);
		return isElementDisplayed(driver, UserHomePageUI.SUCCESSFUL_REGISTER_MESSAGE);
	}

	public boolean isMyDashboardHeaderDisplayed() {
		return isElementDisplayed(driver, UserHomePageUI.MY_DASHBOARD_HEADER);
	}

}
