package pageUIs.techpanda.admin;

public class AdminCustomerPageUI {
	public static final String CLOSE_MESSAGE_POPUP_LINK = "//div[@class='message-popup-head']/a";
	public static final String COLUMN_INDEX_BY_TEXT= "//span[text()='%s']/ancestor::th/preceding-sibling::th";
	public static final String TEXTBOX_AT_COLUMN_INDEX= "//tr[@class='filter']/th[%s]//input";
	public static final String BUTTON_BY_TITLE_NAME = "//button[@title='%s']";
	public static final String LOADING_POPUP = "//p[@id='loading_mask_loader']";
	public static final String RESULT_ROW = "//td[contains(text(),'%s')]/following-sibling::td[contains(text(),'%s')]";
	public static final String CHECKBOX_AT_ROW = "//table[@id='customerGrid_table']//td[%s]";
	public static final String HEADER_ACTION_DROPDOWN = "//label[text()='Actions']/parent::span/select";
	public static final String NO_RECORD_FOUND_MESSAGE = "//td[text()='No records found.']";
	public static final String LOGOUT_LINK = "//a[text()='Log Out']";
	
}
