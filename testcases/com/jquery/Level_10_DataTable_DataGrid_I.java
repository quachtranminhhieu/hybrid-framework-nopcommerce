package com.jquery;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.dataTable.HomePageObject;
import pageObjects.jQuery.dataTable.PageGeneratorManager;

public class Level_10_DataTable_DataGrid_I extends BaseTest {
	private WebDriver driver;
	HomePageObject homePage;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appURL) {

		driver = getBrowserDriver(browserName, appURL);
		homePage = PageGeneratorManager.getHomePage(driver);
	}
	
	//@Test
	public void Table_01_Paging() {
		homePage.openPagingByNumber("1");
		Assert.assertTrue(homePage.isPageNumberActived("1"));
		homePage.openPagingByNumber("2");
		Assert.assertTrue(homePage.isPageNumberActived("2"));
		homePage.openPagingByNumber("3");
		Assert.assertTrue(homePage.isPageNumberActived("3"));
		homePage.openPagingByNumber("4");
		Assert.assertTrue(homePage.isPageNumberActived("4"));
		homePage.openPagingByNumber("5");
		Assert.assertTrue(homePage.isPageNumberActived("5"));
		homePage.openPagingByNumber("6");
		Assert.assertTrue(homePage.isPageNumberActived("6"));
		homePage.openPagingByNumber("7");
		Assert.assertTrue(homePage.isPageNumberActived("7"));
		homePage.openPagingByNumber("8");
		Assert.assertTrue(homePage.isPageNumberActived("8"));
		homePage.openPagingByNumber("9");
		Assert.assertTrue(homePage.isPageNumberActived("9"));
		homePage.openPagingByNumber("10");
		Assert.assertTrue(homePage.isPageNumberActived("10"));
	}

	//@Test
	public void Table_02_Enter_To_Textbox_By_Header() {
		homePage.enterToTextboxByHeader("Females", "384187");
		homePage.enterToTextboxByHeader("Country", "Afghanistan");
		homePage.enterToTextboxByHeader("Males", "407124");
		homePage.enterToTextboxByHeader("Total", "791312");
		
		homePage.sleepInSecond(1);
		homePage.enterToTextboxByHeader("Females", "338282");
		homePage.enterToTextboxByHeader("Country", "Argentina");
		homePage.enterToTextboxByHeader("Males", "349238");
		homePage.enterToTextboxByHeader("Total", "687522");
		homePage.sleepInSecond(1);
	}
	
	@Test
	public void Table_03_Get_Value_Each_Row_At_All_Page() {
		homePage.getValueEachRowAtAllPage();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
