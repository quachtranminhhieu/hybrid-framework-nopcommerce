package pageUIs.jQuery.dataTable;

public class HomePageUI {
	public static final String PAGINATION_PAGE_BY_NUMBER = "//li[@class='qgrd-pagination-page']/a[text()='%s']";
	public static final String PAGINATION_PAGE_ACTIVE_BY_NUMBER = "//li[@class='qgrd-pagination-page']/a[@class='qgrd-pagination-page-link active' and text()='%s']";
	public static final String HEADER_TEXTBOX_BY_LABLE = "//div[text()='%s']/parent::div/following-sibling::input";
	public static final String TOTAL_PAGINATION = "//ul[@class='qgrd-pagination-ul']/li[@class='qgrd-pagination-page']";
	public static final String ALL_ROW_EACH_PAGE = "//tbody/tr";
	
	public static final String TEXTBOX_AT_ROW_INDEX_AND_COLUMN_INDEX = "//tbody/tr[%s]/td[%s]/input";
	public static final String COLUMN_INDEX_BY_NAME = "//td[text()='%s']/preceding-sibling::td";
	public static final String LOAD_DATA_BUTTON = "//button[@id='btnLoad']";
	public static final String DROPDOWN_AT_ROW_INDEX_AND_COLUMN_INDEX = "//tbody/tr[%s]/td[%s]/select";
	public static final String CHECBOX_AT_ROW_INDEX_AND_COLUMN_INDEX = "//tbody/tr[%s]/td[%s]/input[@type='checkbox']";
	public static final String BUTTON_AT_ROW_INDEX_AND_TITLE_NAME = "//tbody/tr[%s]/td[last()]/button[@title='%s']";
}
