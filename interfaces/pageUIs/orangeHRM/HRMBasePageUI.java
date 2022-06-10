package pageUIs.orangeHRM;

public class HRMBasePageUI {
	public static final String DYNAMIC_MENU_PAGE = "//div[@id='mainMenu']//a[string()='%s']";
	public static final String DYNAMIC_BUTTON_BY_ID = "//input[@id='%s']";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "//input[@id='%s']";
	public static final String DYNAMIC_CHECKBOX_BY_LABEL = "//label[text()='%s']/following-sibling::input";
	public static final String DYNAMIC_RADIO_BUTTON_BY_LABEL = "//label[text()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_DROPDOWN_BY_ID = "//select[@id='%s']";
	public static final String DYNAMIC_ANY_FIELD_BY_ID = "//*[@id='%s']";
	public static final String DYNAMIC_HEADER_IN_DATA_TABLE_ID_BY_NAME = "//table[@id='%s']/thead//th[string()='%s']/preceding-sibling::th";
	public static final String DYNAMIC_CELL_IN_DATA_TABLE_ID_AT_ROW_INDEX_AND_COLUMN_INDEX = "//table[@id='%s']/tbody/tr[%s]/td[%s]";
	public static final String DYNAMIC_CHECKBOX_IN_DATA_TABLE_ID_AT_ROW_INDEX_AND_COLUMN_INDEX = "//table[@id='%s']/tbody/tr[%s]/td[%s]/input[@type='checkbox']";
	
	public static final String DYNAMIC_LINK_IN_DATA_TABLE_ID_AT_ROW_INDEX_AND_COLUMN_INDEX = "//table[@id='%s']/tbody/tr[%s]/td[%s]/a";
	public static final String DYNAMIC_SUCCESS_MESSAGE_BY_TEXT = "//div[@class='inner']/div[contains(text(),'%s')]";
	public static final String WELCOME_USER_LINK = "//a[@id='welcome']";
	public static final String LOGOUT_LINK = "//div[@id='welcome-menu']//a[text()='Logout']";
	public static final String UPLOAD_FILE = "//input[@type='file']";
	
	public static final String USERNAME_TEXTBOX = "//input[@id='txtUsername']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='txtPassword']";
	public static final String LOGIN_BUTTON = "//input[@id='btnLogin']";
}
