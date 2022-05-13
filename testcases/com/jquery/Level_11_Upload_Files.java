package com.jquery;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.uploadFiles.HomePageObject;
import pageObjects.jQuery.uploadFiles.PageGeneratorManager;

public class Level_11_Upload_Files extends BaseTest {
	private WebDriver driver;
	HomePageObject homePage;
	String catFileName = "cat.jpg";
	String dogFileName = "dog.jpg";
	String mouseFileName = "mouse.jpg";
	String[] multipleFileNames = {catFileName , dogFileName , mouseFileName};

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appURL) {

		driver = getBrowserDriver(browserName, appURL);
		homePage = PageGeneratorManager.getHomePage(driver);
	}
	
	@Test
	public void TC_01_Upload_One_File() {
		homePage.uploadMultipleFile(driver, catFileName);
		
		Assert.assertTrue(homePage.isFileLoadedByName(catFileName));
		
		homePage.clickStartUploadButton();
		
		Assert.assertTrue(homePage.isFileLinkUploadedByName(catFileName));
		
		Assert.assertTrue(homePage.isFileImageUploadedByName(catFileName));
	}
	
	@Test
	public void TC_02_Upload_Multiple_File() {
		homePage.refreshCurentPage(driver);
		
		homePage.uploadMultipleFile(driver, multipleFileNames);
		
		Assert.assertTrue(homePage.isFileLoadedByName(catFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(dogFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(mouseFileName));
		
		homePage.clickStartUploadButton();
		
		Assert.assertTrue(homePage.isFileLinkUploadedByName(catFileName));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(dogFileName));
		Assert.assertTrue(homePage.isFileLinkUploadedByName(mouseFileName));
		
		Assert.assertTrue(homePage.isFileImageUploadedByName(catFileName));
		Assert.assertTrue(homePage.isFileImageUploadedByName(dogFileName));
		Assert.assertTrue(homePage.isFileImageUploadedByName(mouseFileName));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
