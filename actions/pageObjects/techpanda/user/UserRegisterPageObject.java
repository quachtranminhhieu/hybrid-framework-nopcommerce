package pageObjects.techpanda.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.techpanda.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage{
	WebDriver driver;
	
	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToRegisterTextboxByTitle(String titleName, String textValue) {
		sendkeyToElement(driver, UserRegisterPageUI.TEXTBOX_AT_REGISTER_FORM, textValue, titleName);
	}

	public UserHomePageObject clickToRegisterButton() {
		waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public void clickToLogoutLinkAtUserPage() {
		// TODO Auto-generated method stub
		
	}
	
}
