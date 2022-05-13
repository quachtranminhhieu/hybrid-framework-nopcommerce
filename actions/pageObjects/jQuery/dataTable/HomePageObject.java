package pageObjects.jQuery.dataTable;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUIs.jQuery.dataTable.HomePageUI;

public class HomePageObject extends BasePage{
	WebDriver driver;
	
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void openPagingByNumber(String pageNumber) {
		clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
	}

	public void enterToTextboxByHeader(String headerLabel, String value) {
		sendkeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABLE, value, headerLabel);
	}

	public boolean isPageNumberActived(String pageNumber) {
		return isElementDisplayed(driver, HomePageUI.PAGINATION_PAGE_ACTIVE_BY_NUMBER, pageNumber);
	}

	public List<String> getValueEachRowAtAllPage() {
		int totalPage = getElementSize(driver, HomePageUI.TOTAL_PAGINATION);
		List<String> allRowValueAtAllPage = new ArrayList<String>();
		
		for (int i = 1; i <= totalPage; i++) {
			clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, String.valueOf(i));
			allRowValueAtAllPage.add("Page " + i);
			List<WebElement> allRowElementAtEachPage = getListWebElemnt(driver, HomePageUI.ALL_ROW_EACH_PAGE);
			for (WebElement eachRow : allRowElementAtEachPage) {
				allRowValueAtAllPage.add(eachRow.getText());
			}
		}
		
		for (String value : allRowValueAtAllPage) {
			System.out.println("---------------------------");
			System.out.println(value);
		}
		
		return allRowValueAtAllPage;
	}

	
	public void inputToTextboxAtRowNumberAndColumnName(String rowNumber, String columnName, String textValue) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		
		sendkeyToElement(driver, HomePageUI.TEXTBOX_AT_ROW_INDEX_AND_COLUMN_INDEX, textValue, rowNumber, String.valueOf(columnIndex));
	}

	public void clickToLoadDataButton() {
		clickToElement(driver, HomePageUI.LOAD_DATA_BUTTON);
	}

	public void selectDropdownAtRowNumberAndColumnName(String rowNumber, String columnName, String textValue) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		
		waitForElementClickable(driver, HomePageUI.DROPDOWN_AT_ROW_INDEX_AND_COLUMN_INDEX, rowNumber, String.valueOf(columnIndex));
		selectItemInDefaultDropdown(driver, HomePageUI.DROPDOWN_AT_ROW_INDEX_AND_COLUMN_INDEX, textValue, rowNumber, String.valueOf(columnIndex));
	}

	public void checkToCheckboxAtRowNumberAndColumnName(String rowNumber, String columnName) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		
		checkToCheckboxRadio(driver, HomePageUI.CHECBOX_AT_ROW_INDEX_AND_COLUMN_INDEX, rowNumber, String.valueOf(columnIndex));
	}

	public void uncheckToCheckboxAtRowNumberAndColumnName(String rowNumber, String columnName) {
		int columnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		
		unCheckToCheckbox(driver, HomePageUI.CHECBOX_AT_ROW_INDEX_AND_COLUMN_INDEX, rowNumber, String.valueOf(columnIndex));
	}

	public void clickToButtonAtRowNumber(String rowNumber, String buttonName) {
		clickToElement(driver, HomePageUI.BUTTON_AT_ROW_INDEX_AND_TITLE_NAME, rowNumber, buttonName);
	}
	
}
