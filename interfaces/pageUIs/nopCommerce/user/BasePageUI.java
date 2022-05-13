package pageUIs.nopCommerce.user;

public class BasePageUI {
	public static final String MY_ACCOUNT_LINK = "//div[@class='header']//a[text()='My account']";
	public static final String ADDRESSES_LINK = "//div[@class='listbox']//a[text()='Addresses']";
	public static final String CUSTOMER_INFOR_LINK = "//div[@class='listbox']//a[text()='Customer info']";
	public static final String MY_PRODUCT_REVIEW_LINK = "//div[@class='listbox']//a[text()='My product reviews']";
	public static final String REWARD_POINT_LINK = "//div[@class='listbox']//a[text()='Reward points']";
	
	public static final String DYNAMIC_MY_ACCOUNT_PAGE = "//div[@class='listbox']//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX_BY_ID = "//input[@id='%s']";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "//button[text()='%s']";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "//select[@name='%s']";
	public static final String DYNAMIC_RADIO_BUTTON_BY_LABEL = "//label[text()='%s']/preceding-sibling::input";
	public static final String DYNAMIC_CHECKBOX_BY_LABEL = "//label[contains(text(),'%s')]/following-sibling::input";
	
	
	public static final String LOGOUT_LINK_AT_ADMIN = "//a[text()='Logout']";
	public static final String LOGOUT_LINK_AT_USER = "//a[@class='ico-logout']";
	public static final String UPLOAD_FILE = "//input[@type='file']";
}
