package com.orangeHRM.employee;

import java.lang.reflect.Method;
import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import pageObjects.orangeHRM.AddEmployeePO;
import pageObjects.orangeHRM.DashboardPO;
import pageObjects.orangeHRM.EmployeeListPO;
import pageObjects.orangeHRM.LoginPO;
import pageObjects.orangeHRM.MyInfoPO;
import reportConfig.ExtentTestManagerV5;
import utilities.DataUtil;
import pageObjects.orangeHRM.HRMPageGeneratorManager;

public class Level_20_Data_Test_I_Into_Class extends BaseTest {

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appURL, Method method) {
		driver = getBrowserDriver(browserName, appURL);
		
		faker = DataUtil.getData();
		
		employeeFirstName = faker.getFirstName();
		employeeLastName = faker.getLastName();
		employeeFullName = employeeFirstName + " " + employeeLastName;
		adminUserName = "Admin";
		adminPassword = "admin123";
		empUserName = faker.getUserName();
		empPassword = faker.getPassword();
		statusValue = "Enabled";
		
		loginPage = HRMPageGeneratorManager.getLoginPage(driver);
		loginPage.closeAllTabWithoutParent(driver, driver.getWindowHandle());
		
		dashboardPage = loginPage.loginToSystem(driver, adminUserName, adminPassword);
	}

	@Test
	public void Employee_01_Add_New_Employee(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_01_Add_New_Employee");	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 01: Open 'Employee List' page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = HRMPageGeneratorManager.getEmployeeListPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 04: Click to 'Add' button");
		employeeListPage.clickToButtonByID(driver, "btnAdd");
		addEmployeePage = HRMPageGeneratorManager.getAddEmployeePage(driver);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 05: Enter to 'First Name' textbox with value is: '" + employeeFirstName + "'");
		addEmployeePage.enterToTextboxByID(driver, employeeFirstName, "firstName");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 06: Enter to 'Last Name' textbox with value is: '" + employeeLastName + "'");
		addEmployeePage.enterToTextboxByID(driver, employeeLastName, "lastName");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 07: Get value of 'Employee ID'");
		employeeID = addEmployeePage.getTextboxValueByID(driver, "employeeId");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 08: Click to 'Create Login Details' checkbox");
		addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 09: Enter to 'User Name' textbox with value is: '" + empUserName + "'");
		addEmployeePage.enterToTextboxByID(driver, empUserName, "user_name");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 10: Enter to 'Password' textbox with value is: '" + empPassword + "'");
		addEmployeePage.enterToTextboxByID(driver, empPassword, "user_password");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 11: Enter to 'Confirm Password' textbox with value is: '" + empPassword + "'");
		addEmployeePage.enterToTextboxByID(driver, empPassword, "re_password");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 12: Select item in 'Status' dropdown with value is: " + statusValue + "'");
		addEmployeePage.selectItemInDropdownByID(driver, statusValue, "status");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 13: Click to 'Save' button");
		addEmployeePage.clickToButtonByID(driver, "btnSave");
		myInfoPage = HRMPageGeneratorManager.getMyInfoPage(driver);
		myInfoPage.areJQueryAndJSLoadedSuccess(driver);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 14: Open 'Employee List' page");
		myInfoPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = HRMPageGeneratorManager.getEmployeeListPage(driver);
		employeeListPage.waitForEmployeeNameTextboxLoadSuccessfully();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 15: Enter to 'Employee Name' textbox with value is: '" + employeeFullName + "'");
		employeeListPage.enterToTextboxByID(driver, employeeFullName, "empsearch_employee_name_empName");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 16: Click to 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		employeeListPage.waitForEmployeeNameTextboxLoadSuccessfully();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 17: Verify Employee Information displayed at 'Result Table'");
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"), employeeID);
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1"), employeeFirstName);
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), employeeLastName);
	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass(String browserName) {
		closeBrowserAndDriver();
	}

	private WebDriver driver;

	String employeeFirstName, employeeLastName, adminUserName, adminPassword, empUserName, empPassword, statusValue, employeeFullName, employeeID;

	AddEmployeePO addEmployeePage;
	DashboardPO dashboardPage;
	EmployeeListPO employeeListPage;
	LoginPO loginPage;
	MyInfoPO myInfoPage;
	DataUtil faker;

}
