package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;
import pageUIs.nopCommerce.user.UserRegisterPageUI;

public class RegisterPageObject extends BasePageFactory {

	private WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@CacheLookup
	@FindBy(id = "register-button")
	private WebElement registerButton;
	
	@CacheLookup
	@FindBy(id = "FirstName-error")
	private WebElement firstNameErrorMessage;
	
	@CacheLookup
	@FindBy(id = "LastName-error")
	private WebElement lastNameErrorMessage;
	
	@CacheLookup
	@FindBy(id = "Email-error")
	private WebElement emailErrorMessage;
	
	@CacheLookup
	@FindBy(id = "Password-error")
	private WebElement passwordErrorMessage;
	
	@CacheLookup
	@FindBy(id = "ConfirmPassword-error")
	private WebElement confirmPasswordErrorMessage;
	
	@CacheLookup
	@FindBy(id = "FirstName")
	private WebElement firstNameTextbox;
	
	@CacheLookup
	@FindBy(id = "LastName")
	private WebElement lastNameTextbox;
	
	@CacheLookup
	@FindBy(id = "Email")
	private WebElement emailTextbox;
	
	@CacheLookup
	@FindBy(id = "Password")
	private WebElement passwordTextbox;
	
	@CacheLookup
	@FindBy(id = "ConfirmPassword")
	private WebElement confirmPasswordTextbox;
	
	@CacheLookup
	@FindBy(css = "div.result")
	private WebElement registerSuccessMessage;
	
	@CacheLookup
	@FindBy(css = "a.ico-logout")
	private WebElement logoutLink;
	
	@CacheLookup
	@FindBy(xpath = "//div[contains(@class,'message-error')]//li")
	private WebElement existingEmailErrorMessage;
	
	public void clickToRegisterButton() {
//		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON).click();
		
		waitForElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);
	}

	public String getErrorMessageAtFirstNameTextbox() {
		waitForElementVisible(driver, firstNameErrorMessage);
		return getElementText(driver, firstNameErrorMessage);
	}

	public String getErrorMessageAtLastNameTextbox() {
		waitForElementVisible(driver, lastNameErrorMessage);
		return getElementText(driver, lastNameErrorMessage);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}

	public String getErrorMessageAtPasswordTextbox() {
		waitForElementVisible(driver, passwordErrorMessage);
		return getElementText(driver, passwordErrorMessage);
	}

	public String getErrorMessageAtConfirmPasswordTextbox() {
		waitForElementVisible(driver, confirmPasswordErrorMessage);
		return getElementText(driver, confirmPasswordErrorMessage);
	}

	public void inputToFirstNameTextbox(String firstName) {
		waitForElementVisible(driver, firstNameTextbox);
		sendkeyToElement(driver, firstNameTextbox, firstName);
	}

	public void inputToLastNameTextbox(String lastName) {
		waitForElementVisible(driver, lastNameTextbox);
		sendkeyToElement(driver, lastNameTextbox, lastName);
	}

	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, emailTextbox);
		sendkeyToElement(driver, emailTextbox, email);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox, password);
	}

	public void inputToConfimPasswordTextbox(String password) {
		waitForElementVisible(driver, confirmPasswordTextbox);
		sendkeyToElement(driver, confirmPasswordTextbox, password);
	}

	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, registerSuccessMessage);
		return getElementText(driver, registerSuccessMessage);
	}

	public void clickLogoutLink() {
		waitForElementVisible(driver, logoutLink);
		clickToElement(driver, logoutLink);
	}

	public String getErrorExistingMessage() {
		waitForElementVisible(driver, existingEmailErrorMessage);
		return getElementText(driver, existingEmailErrorMessage);
	}

}
