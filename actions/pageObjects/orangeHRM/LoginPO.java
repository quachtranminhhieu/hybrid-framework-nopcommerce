package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.orangeHRM.LoginPageUI;
import pageUIs.orangeHRM.HRMBasePageUI;

public class LoginPO extends BasePage{
	private WebDriver driver;

	public LoginPO(WebDriver driver) {
		this.driver = driver;
	}

}
