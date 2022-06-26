package com.bankguru.user;


import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import environmentConfig.Environment;

public class Level_21_Multiple_Environment extends BaseTest {

	@Parameters({"browser", "environment"})
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
		// Dynamic: Lấy giá trị environmentName từ file xml thế vào biến ${env} ở File Environment.class (ở package environmentConfig)
		ConfigFactory.setProperty("env", environmentName);
		
		environment = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName, environment.appURL());
	}

	@Test
	public void Employee_01_Add_New_Employee() {

	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass(String browserName) {
		closeBrowserAndDriver();
	}

	private WebDriver driver;
	Environment environment;

}
