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

public class Level_10_DataTable_DataGrid_II extends BaseTest {
	private WebDriver driver;
	HomePageObject homePage;

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appURL) {

		driver = getBrowserDriver(browserName, appURL);
		homePage = PageGeneratorManager.getHomePage(driver);
	}
	
	@Test
	public void Table_01_Paging() {
		homePage.inputToTextboxAtRowNumberAndColumnName("1", "Album", "There's No One At All");
		homePage.inputToTextboxAtRowNumberAndColumnName("1", "Artist", "Son Tung M-TP");
		homePage.inputToTextboxAtRowNumberAndColumnName("1", "Year", "2022");
		homePage.selectDropdownAtRowNumberAndColumnName("1", "Origin", "US");
		homePage.checkToCheckboxAtRowNumberAndColumnName("1", "With Poster?");
		homePage.inputToTextboxAtRowNumberAndColumnName("1", "Price", "60.0");
		homePage.sleepInSecond(2);
		
		homePage.refreshCurentPage(driver);
		homePage.clickToLoadDataButton();
		
		homePage.inputToTextboxAtRowNumberAndColumnName("1", "Album", "There's No One At All");
		homePage.inputToTextboxAtRowNumberAndColumnName("2", "Artist", "Son Tung M-TP");
		homePage.inputToTextboxAtRowNumberAndColumnName("3", "Year", "2022");
		homePage.selectDropdownAtRowNumberAndColumnName("4", "Origin", "US");
		homePage.inputToTextboxAtRowNumberAndColumnName("5", "Price", "60.0");
		homePage.sleepInSecond(2);
		
		homePage.uncheckToCheckboxAtRowNumberAndColumnName("1", "With Poster?");
		homePage.uncheckToCheckboxAtRowNumberAndColumnName("2", "With Poster?");
		homePage.checkToCheckboxAtRowNumberAndColumnName("3", "With Poster?");
		homePage.uncheckToCheckboxAtRowNumberAndColumnName("4", "With Poster?");
		homePage.checkToCheckboxAtRowNumberAndColumnName("5", "With Poster?");
		
		homePage.sleepInSecond(2);
		
		homePage.inputToTextboxAtRowNumberAndColumnName("1", "Album", "5");
		homePage.inputToTextboxAtRowNumberAndColumnName("2", "Album", "1");
		homePage.inputToTextboxAtRowNumberAndColumnName("3", "Album", "2");
		homePage.inputToTextboxAtRowNumberAndColumnName("4", "Album", "3");
		homePage.inputToTextboxAtRowNumberAndColumnName("5", "Album", "4");
		
		homePage.clickToButtonAtRowNumber("1", "Insert Row Above");
		homePage.clickToButtonAtRowNumber("1", "Remove Current Row");
		homePage.clickToButtonAtRowNumber("2", "Move Up");
		homePage.clickToButtonAtRowNumber("3", "Move Up");
		homePage.clickToButtonAtRowNumber("4", "Move Up");
		homePage.clickToButtonAtRowNumber("4", "Move Down");
		homePage.sleepInSecond(2);
		
		homePage.clickToButtonAtRowNumber("1", "Remove Current Row");
		homePage.clickToButtonAtRowNumber("1", "Remove Current Row");
		homePage.clickToButtonAtRowNumber("1", "Remove Current Row");
		homePage.clickToButtonAtRowNumber("1", "Remove Current Row");
		homePage.clickToButtonAtRowNumber("1", "Remove Current Row");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
