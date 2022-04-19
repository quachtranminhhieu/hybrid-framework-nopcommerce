package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageUIs.nopCommerce.user.UserLoginPageUI;

public class LoginPageObject extends BasePageFactory {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@CacheLookup
	@FindBy(css = "button.login-button")
	private WebElement loginButton;
	
	@CacheLookup
	@FindBy(id = "Email")
	private WebElement emailTextbox;
	
	@CacheLookup
	@FindBy(id = "Password")
	private WebElement passwordTextbox;
	
	@CacheLookup
	@FindBy(css = "div.validation-summary-errors")
	private WebElement unsucessfulErrorMessage;
	
	@CacheLookup
	@FindBy(id = "Email-error")
	private WebElement emailErrorMessage;
	
	public void clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}

	public void inputToEmailTextbox(String invalidEmail) {
		waitForElementVisible(driver, emailTextbox);
		sendkeyToElement(driver, emailTextbox, invalidEmail);
	}

	public String getErrorMessageUnsuccessful() {
		waitForElementVisible(driver, unsucessfulErrorMessage);
		return getElementText(driver, unsucessfulErrorMessage);
	}

	public void inputToPasswordText(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox, password);
	}
	
}
