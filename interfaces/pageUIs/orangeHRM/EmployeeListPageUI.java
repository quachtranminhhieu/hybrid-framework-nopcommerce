package pageUIs.orangeHRM;

public class EmployeeListPageUI {
	public static final String LOADING_EMPLOYEE_NAME_TEXTBOX = "//input[@id='empsearch_employee_name_empName' and @class='ac_loading']";
	public static final String DYNAMIC_TAB_AT_SIDE_BAR_BY_NAME = "//div[@id='sidebar']//a[text()='%s']";
	public static final String DYNAMIC_ROW_VALUES_IN_DEPOSIT_DATA_TABLE = "//table[@id='tblSalary']//table/tbody//td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']/following-sibling::td[text()='%s']";
	public static final String DYNAMIC_HEADER_IN_DATA_TABLE_NAME_BY_NAME = "//h1[text()='%s']/parent::div/following-sibling::div//table/thead//th[text()='%s']/preceding-sibling::th";
	public static final String DYNAMIC_CELL_IN_DATA_TABLE_NAME_AT_ROW_INDEX_AND_COLUMN_INDEX = "//h1[text()='%s']/parent::div/following-sibling::div//table/tbody/tr[%s]/td[%s]";
}
