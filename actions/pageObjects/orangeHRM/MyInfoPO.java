package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.orangeHRM.MyInfoPageUI;

public class MyInfoPO extends BasePage{
	private WebDriver driver;

	public MyInfoPO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToChangePhotoImage() {
		waitForElementClickable(driver, MyInfoPageUI.AVATAR_IMAGE);
		clickToElement(driver, MyInfoPageUI.AVATAR_IMAGE);
	}

	public boolean isNewAvatarImageDisplayed() {
		waitForAllElementVisible(driver, MyInfoPageUI.AVATAR_IMAGE);
		int imageWith = Integer.parseInt(getElementAttribute(driver, MyInfoPageUI.AVATAR_IMAGE, "width"));
		int imageHeight = Integer.parseInt(getElementAttribute(driver, MyInfoPageUI.AVATAR_IMAGE, "height"));
		return (imageWith != 200) || (imageHeight != 200);
	}

	public void openTabAtSideBarByName(String tabName) {
		clickToElement(driver, MyInfoPageUI.DYNAMIC_TAB_AT_SIDE_BAR_BY_NAME, tabName);
	}
	
}
