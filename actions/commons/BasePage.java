package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.nopCommerce.admin.AdminLoginPageObject;
import pageObjects.nopCommerce.user.UserAddressesPageObject;
import pageObjects.nopCommerce.user.UserCustomerInforPageObject;
import pageObjects.nopCommerce.user.UserHomePageObject;
import pageObjects.nopCommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopCommerce.user.UserRewardPointPageObject;
import pageObjects.orangeHRM.DashboardPO;
import pageObjects.orangeHRM.HRMPageGeneratorManager;
import pageObjects.orangeHRM.LoginPO;
import pageUIs.nopCommerce.user.BasePageUI;
import pageUIs.orangeHRM.HRMBasePageUI;
import pageUIs.orangeHRM.MyInfoPageUI;

public class BasePage {
	protected long longTimeout = GlobalConstans.LONG_TIMEOUT;
	protected long shortTimeout = GlobalConstans.SHORT_TIMEOUT;
	
	protected void setTimeoutImplicit(WebDriver driver, long longTimeoutImplicit) {
		driver.manage().timeouts().implicitlyWait(longTimeoutImplicit, TimeUnit.SECONDS);
	}

	public static BasePage getBasePageObject() {
		return new BasePage();
	}
	
	public void openPageURL(WebDriver driver, String pageURL) {
		driver.get(pageURL);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshCurentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(2);
	}
	
	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		return setTimeoutExplicit(driver).until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	public void cancleAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();;
	}
	
	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	public void sendkeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);;
	}
	
	public void switchToWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
			}
		}
	}
	
	public void swithToWindowByTitle(WebDriver driver, String tabTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (driver.switchTo().window(id).getTitle().equals(tabTitle)) {
				break;
			}
		}
	}
	
	public void closeAllTabWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	private By getByXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}
	
	private String getDynamicLocator(String dynamicXpath, String... dynamicValues) {
		return String.format(dynamicXpath, (Object[]) dynamicValues);
	}
	
	public WebElement getWebElement(WebDriver driver, String xpathLocator) {
		return driver.findElement(getByXpath(xpathLocator));
	}
	
	public List<WebElement> getListWebElemnt(WebDriver driver, String xpathLocator){
		return driver.findElements(getByXpath(xpathLocator));
	}
	
	public List<WebElement> getListWebElemnt(WebDriver driver, String xpathLocator, String... dynamicValues){
		waitForAllElementVisible(driver, xpathLocator, dynamicValues);
		return driver.findElements(getByXpath(getDynamicLocator(xpathLocator, dynamicValues)));
	}
	
	public void clickToElement(WebDriver driver, String xpathLocator) {
		getWebElement(driver, xpathLocator).click();
	}
	
	public void clickToElement(WebDriver driver, String xpathLocator, String... dynamicValues) {
		waitForElementClickable(driver, getDynamicLocator(xpathLocator, dynamicValues)).click();
	}
	
	public void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue) {
		WebElement element = getWebElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(textValue);
	}

	public void sendkeyToElement(WebDriver driver, String xpathLocator, String textValue, String... dynamicValues) {
		WebElement element = waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}
	
	public String getElementText(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).getText();
	}
	
	public String getElementText(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)).getText();
	}
	
	public void clearTextbox(WebDriver driver, String xpathLocator) {
		getWebElement(driver, xpathLocator).clear();
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		select.selectByVisibleText(textItem);
	}
	
	public void selectItemInDefaultDropdown(WebDriver driver, String xpathLocator, String textItem, String...dynamicValues) {
		Select select = new Select(waitForElementClickable(driver, getDynamicLocator(xpathLocator, dynamicValues)));
		select.selectByVisibleText(textItem);
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}
	
	public String getSelectedItemDefaultDropdown(WebDriver driver, String xpathLocator, String...dynamicValues) {
		Select select = new Select(waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
		WebDriverWait explicitWait = setTimeoutExplicit(driver);
		getWebElement(driver, parentLocator).click();
		
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(childItemLocator)));
		for (WebElement item : allItems) {	
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
		
		
	}
	
	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String xpathLocator, String attributeName, String...dynamicValues) {
		return waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)).getAttribute(attributeName);
	}
	
	public String getElementCssValue(WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	}
	
	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}
	
	public int getElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElemnt(driver, xpathLocator).size();
	}
	
	public int getElementSize(WebDriver driver, String xpathLocator, String...dynamicValues) {
		waitForAllElementVisible(driver, xpathLocator, dynamicValues);
		return getListWebElemnt(driver, getDynamicLocator(xpathLocator, dynamicValues)).size();
	}
	
	public void checkToCheckboxRadio(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void unCheckToCheckbox(WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public void checkToCheckboxRadio(WebDriver driver, String xpathLocator, String...dynamicValues) {
		WebElement element = waitForElementClickable(driver, getDynamicLocator(xpathLocator, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}
	
	public void unCheckToCheckbox(WebDriver driver, String xpathLocator, String...dynamicValues) {
		WebElement element = waitForElementClickable(driver, getDynamicLocator(xpathLocator, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementDisplayed(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isDisplayed();
	}
	
	public boolean isElementDisplayed(WebDriver driver, String xpathLocator, String...dynamicValues) {
		return waitForElementVisible(driver, xpathLocator, dynamicValues).isDisplayed();
	}

	public boolean isElementUndisplayed(WebDriver driver, String xpathLocator) {
		setTimeoutImplicit(driver, shortTimeout);
		List<WebElement> elements = getListWebElemnt(driver, xpathLocator);
		setTimeoutImplicit(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isElementUndisplayed(WebDriver driver, String xpathLocator, String...dynamicValues) {
		setTimeoutImplicit(driver, shortTimeout);
		List<WebElement> elements = getListWebElemnt(driver, xpathLocator, dynamicValues);
		setTimeoutImplicit(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isElementEnable(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}
	
	public boolean isElementEnable(WebDriver driver, String xpathLocator, String...dynamicValues) {
		return waitForElementVisible(driver, xpathLocator, dynamicValues).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();
	}
	
	public boolean isElementSelected(WebDriver driver, String xpathLocator, String...dynamicValues) {
		return waitForElementVisible(driver, xpathLocator, dynamicValues).isSelected();
	}
	
	public void switchToFrameIframe(WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}
	
	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	
	public void hoverMouseToElement(WebDriver driver, String xpathLocator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocator)).perform();
	}
	
	public void hoverMouseToElement(WebDriver driver, String xpathLocator, String...dynamicValues) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicLocator(xpathLocator, dynamicValues))).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key, String...dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)), key).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String xpathLocator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, xpathLocator), key).perform();
	}
	
	public void uploadMultipleFile(WebDriver driver, String... fileNames) {
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName += GlobalConstans.UPLOAD_FILE_PATH + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, BasePageUI.UPLOAD_FILE).sendKeys(fullFileName);
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void highlightElement(WebDriver driver, String xpathLocator, String...dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}
	
	public void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	public void clickToElementByJS(WebDriver driver, String xpathLocator, String...dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)));
	}

	public void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}

	public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
	}

	public Boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = setTimeoutExplicit(driver);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
	}
	
	public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isImageLoaded(WebDriver driver, String xpathLocator, String...dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", waitForElementVisible(driver, getDynamicLocator(xpathLocator, dynamicValues)));
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	
	public WebDriverWait setTimeoutExplicit(WebDriver driver) {
		return new WebDriverWait(driver, longTimeout);
	}
	
	public WebElement waitForElementVisible(WebDriver driver, String xpathLocator) {
		return setTimeoutExplicit(driver).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(xpathLocator)));
	}
	
	public WebElement waitForElementVisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return setTimeoutExplicit(driver).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, dynamicValues))));
	}
	
	public List<WebElement> waitForAllElementVisible(WebDriver driver, String xpathLocator) {
		return setTimeoutExplicit(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(xpathLocator)));
	}
	
	public List<WebElement> waitForAllElementVisible(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return setTimeoutExplicit(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(getDynamicLocator(xpathLocator, dynamicValues))));
	}
	
	public boolean waitForElementInvisible(WebDriver driver, String xpathLocator) {
		setTimeoutImplicit(driver, shortTimeout);
		boolean status = setTimeoutExplicit(driver).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(xpathLocator)));
		setTimeoutImplicit(driver, longTimeout);
		return status;
	}
	
	public boolean waitForElementInvisible(WebDriver driver, String xpathLocator, String...dynamicValues) {
		setTimeoutImplicit(driver, shortTimeout);
		boolean status = setTimeoutExplicit(driver).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(getDynamicLocator(xpathLocator, dynamicValues))));
		setTimeoutImplicit(driver, longTimeout);
		return status;	
	}
	
	public boolean waitForAllElementInvisible(WebDriver driver, String xpathLocator) {
		return setTimeoutExplicit(driver).until(ExpectedConditions.invisibilityOfAllElements(getListWebElemnt(driver, xpathLocator)));
	}
	
	public boolean waitForAllElementInvisible(WebDriver driver, String xpathLocator, String...dynamicValues) {
		return setTimeoutExplicit(driver).until(ExpectedConditions.invisibilityOfAllElements(getListWebElemnt(driver, getDynamicLocator(xpathLocator, dynamicValues))));
	}
	
	public WebElement waitForElementClickable(WebDriver driver, String xpathLocator) {
		return setTimeoutExplicit(driver).until(ExpectedConditions.elementToBeClickable(getByXpath(xpathLocator)));
	}
	
	public WebElement waitForElementClickable(WebDriver driver, String xpathLocator, String...dynamicValues) {
		return setTimeoutExplicit(driver).until(ExpectedConditions.elementToBeClickable(getByXpath(getDynamicLocator(xpathLocator, dynamicValues))));
	}
	
	// Tối ưu ở Level_07_Switch_Page
	public UserAddressesPageObject openAddressesPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ADDRESSES_LINK);
		clickToElement(driver, BasePageUI.ADDRESSES_LINK);
		return PageGeneratorManager.getUserAddressesPage(driver);
	}
	
	public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
		clickToElement(driver, BasePageUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getUserMyProductReviewPage(driver);
	}
	
	public UserRewardPointPageObject openRewardPointPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.REWARD_POINT_LINK);
		clickToElement(driver, BasePageUI.REWARD_POINT_LINK);
		return PageGeneratorManager.getUserRewardPointPage(driver);
	}
	
	public UserCustomerInforPageObject openCustomerInforPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CUSTOMER_INFOR_LINK);
		clickToElement(driver, BasePageUI.CUSTOMER_INFOR_LINK);
		return PageGeneratorManager.getUserCustomerInforPage(driver);
	}
	
	public UserCustomerInforPageObject openMyAccountPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, BasePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserCustomerInforPage(driver);
	}
	
	// Tối ưu ở Level_08_Switch_Role
	public AdminLoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_ADMIN);
		return PageGeneratorManager.getAdminLoginPageObject(driver);
	}
	
	public UserHomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK_AT_USER);
		clickToElement(driver, BasePageUI.LOGOUT_LINK_AT_USER);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	// Tối ưu ở Level_09_Dynamic_Locator
	public BasePage openPagesAtMyAccountByName(WebDriver driver, String pageName) {
		clickToElement(driver, BasePageUI.DYNAMIC_MY_ACCOUNT_PAGE, pageName);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInforPage(driver);
		case "Addresses":
			return PageGeneratorManager.getUserAddressesPage(driver);
		case "Reward points":
			return PageGeneratorManager.getUserRewardPointPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getUserMyProductReviewPage(driver);
		default:
			throw new RuntimeException("Invalid page name at My Account Listbox");
		}
	}
	
	// Tối ưu ở Level_18_Pattern_Object
//	/**
//	 * Enter to Dynamic Textbox by ID
//	 * 
//	 * @author Hieu
//	 * @param driver
//	 * @param textboxID
//	 * @param textValue
//	 */
//	public void inputToTextboxByID(WebDriver driver,String textboxID, String textValue) {
//		sendkeyToElement(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, textValue, textboxID);
//	}
//	
//	/**
//	 * 
//	 * Click to Dynamic Button by Text
//	 * 
//	 * @author Hieu
//	 * @param driver
//	 * @param buttonText
//	 */
//	public void clickToButtonByText(WebDriver driver, String buttonText) {
//		clickToElement(driver, BasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
//	}
//
//	/**
//	 * 
//	 * Select item in Dynamic Dropdown by Name
//	 * 
//	 * @param driver
//	 * @param dropdownName
//	 * @param itemValue
//	 */
//	public void selectToDropdownByName(WebDriver driver, String dropdownName, String itemValue) {
//		selectItemInDefaultDropdown(driver, BasePageUI.DYNAMIC_DROPDOWN_BY_NAME, itemValue, dropdownName);
//	}
//
//	/**
//	 * 
//	 * Click to Dynamic Radio Button by Label
//	 * 
//	 * @param driver
//	 * @param radioButtonLabel
//	 */
//	public void clickToRadioButtonByLabel(WebDriver driver, String radioButtonLabel) {
//		checkToCheckboxRadio(driver, BasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioButtonLabel);
//	}
//
//	/**
//	 * 
//	 * Click to Dynamic Checkbox by Label
//	 * 
//	 * @param driver
//	 * @param checkboxLabel
//	 */
//	public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabel) {
//		checkToCheckboxRadio(driver, BasePageUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabel);
//	}
//
//	public String getTextboxValueByID(WebDriver driver, String textboxID) {
//		return getElementAttribute(driver, BasePageUI.DYNAMIC_TEXTBOX_BY_ID, "value", textboxID);
//	}

	// Live - Coding
	public LoginPO logoutToSystem(WebDriver driver) {
		waitForElementClickable(driver, HRMBasePageUI.WELCOME_USER_LINK);
		clickToElement(driver, HRMBasePageUI.WELCOME_USER_LINK);
		
		waitForElementClickable(driver, HRMBasePageUI.LOGOUT_LINK);
		clickToElement(driver, HRMBasePageUI.LOGOUT_LINK);
		
		return HRMPageGeneratorManager.getLoginPage(driver);
	}
	
	public DashboardPO loginToSystem(WebDriver driver, String userName, String password) {
		sendkeyToElement(driver, HRMBasePageUI.USERNAME_TEXTBOX, userName);
		sendkeyToElement(driver, HRMBasePageUI.PASSWORD_TEXTBOX, password);
		clickToElement(driver, HRMBasePageUI.LOGIN_BUTTON);
		return HRMPageGeneratorManager.getDashboardPage(null);
	}
	
	public void openMenuPage(WebDriver driver, String menuPageName) {
		clickToElement(driver, HRMBasePageUI.DYNAMIC_MENU_PAGE, menuPageName);
		
		areJQueryAndJSLoadedSuccess(driver);
	}
	
	public void openSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName) {
		hoverMouseToElement(driver, HRMBasePageUI.DYNAMIC_MENU_PAGE, menuPageName);
		
		clickToElement(driver, HRMBasePageUI.DYNAMIC_MENU_PAGE, subMenuPageName);
		
		areJQueryAndJSLoadedSuccess(driver);
	}
	
	public void openChildSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName, String childSubMenuPageName) {
		hoverMouseToElement(driver, HRMBasePageUI.DYNAMIC_MENU_PAGE, menuPageName);
		
		hoverMouseToElement(driver, HRMBasePageUI.DYNAMIC_MENU_PAGE, subMenuPageName);
		
		clickToElement(driver, HRMBasePageUI.DYNAMIC_MENU_PAGE, childSubMenuPageName);
		
		areJQueryAndJSLoadedSuccess(driver);
	}
	
	public void clickToButtonByID(WebDriver driver, String buttonID) {
		clickToElement(driver, HRMBasePageUI.DYNAMIC_BUTTON_BY_ID, buttonID);
		areJQueryAndJSLoadedSuccess(driver);
	}

	public void clickToRadioButtonByLabel(WebDriver driver, String radioLabel) {
		clickToElement(driver, HRMBasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioLabel);
	}
	
	public void enterToTextboxByID(WebDriver driver, String textValue, String textboxID) {
		sendkeyToElement(driver, HRMBasePageUI.DYNAMIC_TEXTBOX_BY_ID, textValue, textboxID);
	}
	
	public String getTextboxValueByID(WebDriver driver, String textboxID) {
		return getElementAttribute(driver, HRMBasePageUI.DYNAMIC_TEXTBOX_BY_ID, "value", textboxID);
	}
	
	public boolean isRadioButtonIsSelectedByLabel(WebDriver driver, String radioLabel) {
		return isElementSelected(driver, HRMBasePageUI.DYNAMIC_RADIO_BUTTON_BY_LABEL, radioLabel);
	}
	
	public void clickToCheckboxByLabel(WebDriver driver, String checkboxLabel) {
		checkToCheckboxRadio(driver, HRMBasePageUI.DYNAMIC_CHECKBOX_BY_LABEL, checkboxLabel);
	}

	public void selectItemInDropdownByID(WebDriver driver, String statusValue, String dropdownID) {
		selectItemInDefaultDropdown(driver, HRMBasePageUI.DYNAMIC_DROPDOWN_BY_ID, statusValue, dropdownID);
	}
	
	public String getValueInDataTableIDAtColumnNameAndRowIndex(WebDriver driver, String tableID, String headerName, String rowIndex) {
		int columnIndex = getElementSize(driver, HRMBasePageUI.DYNAMIC_HEADER_IN_DATA_TABLE_ID_BY_NAME, tableID, headerName) + 1;
		return getElementText(driver, HRMBasePageUI.DYNAMIC_CELL_IN_DATA_TABLE_ID_AT_ROW_INDEX_AND_COLUMN_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
	}

	public void clickToLinkInDataTableIDAtColumnNameAndRowIndex(WebDriver driver, String tableID, String headerName, String rowIndex) {
		int columnIndex = getElementSize(driver, HRMBasePageUI.DYNAMIC_HEADER_IN_DATA_TABLE_ID_BY_NAME, tableID, headerName) + 1;
		clickToElement(driver, HRMBasePageUI.DYNAMIC_LINK_IN_DATA_TABLE_ID_AT_ROW_INDEX_AND_COLUMN_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
	}
	
	public void checkToCheckBoxInDataTableIDAtColumnNameAndRowIndex(WebDriver driver, String tableID, String headerName,
			String rowIndex) {
		int columnIndex = getElementSize(driver, HRMBasePageUI.DYNAMIC_HEADER_IN_DATA_TABLE_ID_BY_NAME, tableID, headerName) + 1;
		checkToCheckboxRadio(driver,HRMBasePageUI.DYNAMIC_CHECKBOX_IN_DATA_TABLE_ID_AT_ROW_INDEX_AND_COLUMN_INDEX, tableID, rowIndex, String.valueOf(columnIndex));
	}
	
	public String getSelectedItemInDropdownByID(WebDriver driver, String dropdownID) {
		return getSelectedItemDefaultDropdown(driver, HRMBasePageUI.DYNAMIC_DROPDOWN_BY_ID, dropdownID);
	}
	
	public boolean isSuccessMessageDisplayed(WebDriver driver, String messageValue) {
		waitForAllElementVisible(driver, HRMBasePageUI.DYNAMIC_SUCCESS_MESSAGE_BY_TEXT, messageValue);
		return isElementDisplayed(driver, HRMBasePageUI.DYNAMIC_SUCCESS_MESSAGE_BY_TEXT, messageValue);
	}
	
	public boolean isFieldEnableByID(WebDriver driver, String fieldID) {
		return isElementEnable(driver, HRMBasePageUI.DYNAMIC_ANY_FIELD_BY_ID, fieldID);
	}
}
