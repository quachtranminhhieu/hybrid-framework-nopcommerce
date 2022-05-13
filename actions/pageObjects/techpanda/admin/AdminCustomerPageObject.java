package pageObjects.techpanda.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObjects.techpanda.user.PageGeneratorManager;
import pageUIs.techpanda.admin.AdminCustomerPageUI;

public class AdminCustomerPageObject extends BasePage{
	WebDriver driver;
	
	public AdminCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void closeMessagePopup() {
		waitForElementClickable(driver, AdminCustomerPageUI.CLOSE_MESSAGE_POPUP_LINK);
		clickToElement(driver, AdminCustomerPageUI.CLOSE_MESSAGE_POPUP_LINK);
	}

	public void inputToHeaderTextboxByName(String textboxName, String textValue) {
		int columnIndex = getElementSize(driver, AdminCustomerPageUI.COLUMN_INDEX_BY_TEXT, textboxName) + 1;
		
		sendkeyToElement(driver, AdminCustomerPageUI.TEXTBOX_AT_COLUMN_INDEX, textValue, String.valueOf(columnIndex));
	}

	public void clickToButtonByTitle(String titleName) {
		clickToElement(driver, AdminCustomerPageUI.BUTTON_BY_TITLE_NAME, titleName);
		if (titleName.equals("Search")) {
			waitForElementInvisible(driver, AdminCustomerPageUI.LOADING_POPUP);
		}
	}

	public boolean isMyAccountExisted(String fullName, String emailAddress) {
		return isElementDisplayed(driver, AdminCustomerPageUI.RESULT_ROW, fullName, emailAddress);
	}

	public void selectHeaderDropdownActions(String actionName) {
		waitForElementClickable(driver, AdminCustomerPageUI.HEADER_ACTION_DROPDOWN);
		selectItemInDefaultDropdown(driver, AdminCustomerPageUI.HEADER_ACTION_DROPDOWN, actionName);
	}

	public void clickToCheckboxAtRow(String rowNumber) {
		clickToElement(driver, AdminCustomerPageUI.CHECKBOX_AT_ROW, rowNumber);
	}

	public boolean isNoRecordFoundMessageDisplayed() {
		return isElementDisplayed(driver, AdminCustomerPageUI.NO_RECORD_FOUND_MESSAGE);
	}
	
	public AdminLoginPageObject clickToLogoutLinkAtAdminPage() {
		clickToElement(driver, AdminCustomerPageUI.LOGOUT_LINK);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}
	
}
