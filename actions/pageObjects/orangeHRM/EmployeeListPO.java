package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.orangeHRM.EmployeeListPageUI;
import pageUIs.orangeHRM.MyInfoPageUI;

public class EmployeeListPO extends BasePage{
	private WebDriver driver;

	public EmployeeListPO(WebDriver driver) {
		this.driver = driver;
	}

	public void waitForEmployeeNameTextboxLoadSuccessfully() {
		waitForElementInvisible(driver, EmployeeListPageUI.LOADING_EMPLOYEE_NAME_TEXTBOX);
	}
	
	public void openTabAtSideBarByName(String tabName) {
		clickToElement(driver, EmployeeListPageUI.DYNAMIC_TAB_AT_SIDE_BAR_BY_NAME, tabName);
	}

	public boolean areValuesInDepositDataTableUpdatedSuccessfully(String empSalaryAccNumb, String empSalaryAccType,
			String empSalaryRoutNum, String empSalaryAccAmount) {
		return isElementDisplayed(driver, EmployeeListPageUI.DYNAMIC_ROW_VALUES_IN_DEPOSIT_DATA_TABLE, empSalaryAccNumb, empSalaryAccType, empSalaryRoutNum, empSalaryAccAmount);
	}

	public String getValueInDataTableNameAtColumnNameAndRowIndex(String dataTableName, String headerName, String rowIndex) {
		int columnIndex = getElementSize(driver, EmployeeListPageUI.DYNAMIC_HEADER_IN_DATA_TABLE_NAME_BY_NAME, dataTableName, headerName) + 1;
		return getElementText(driver, EmployeeListPageUI.DYNAMIC_CELL_IN_DATA_TABLE_NAME_AT_ROW_INDEX_AND_COLUMN_INDEX, dataTableName, rowIndex, String.valueOf(columnIndex));
	}
}
