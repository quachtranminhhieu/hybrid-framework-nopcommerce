package com.orangeHRM.employee;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.orangeHRM.data.Employee_Data_Driven;

import commons.BaseTest;
import pageObjects.orangeHRM.AddEmployeePO;
import pageObjects.orangeHRM.DashboardPO;
import pageObjects.orangeHRM.EmployeeListPO;
import pageObjects.orangeHRM.LoginPO;
import pageObjects.orangeHRM.MyInfoPO;
import reportConfig.ExtentTestManagerV5;
import pageObjects.orangeHRM.HRMPageGeneratorManager;

public class Level_20_Data_Test_IV_Data_Driven extends BaseTest {

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appURL, Method method) {
		driver = getBrowserDriver(browserName, appURL);
		
		loginPage = HRMPageGeneratorManager.getLoginPage(driver);
		
		// New: employeeData = Employee_Data_Driven.getEmployee();
		employeeData = Employee_Data_Driven.getEmployee();
		
		// New: employeeData.getAdminUsername(), employeeData.getAdminPassword()
		dashboardPage = loginPage.loginToSystem(driver, employeeData.getAdminUsername(), employeeData.getAdminPassword());
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

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 05: Enter to 'First Name' textbox with value is: '" + employeeData.getFirstName() + "'");
		addEmployeePage.enterToTextboxByID(driver, employeeData.getFirstName(), "firstName");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 06: Enter to 'Last Name' textbox with value is: '" + employeeData.getLastName() + "'");
		addEmployeePage.enterToTextboxByID(driver, employeeData.getLastName(), "lastName");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 07: Get value of 'Employee ID'");
		employeeID = addEmployeePage.getTextboxValueByID(driver, "employeeId");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 08: Click to 'Create Login Details' checkbox");
		addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 09: Enter to 'User Name' textbox with value is: '" + employeeData.getUserName() + "'");
		addEmployeePage.enterToTextboxByID(driver, employeeData.getUserName(), "user_name");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 10: Enter to 'Password' textbox with value is: '" + employeeData.getPassword() + "'");
		addEmployeePage.enterToTextboxByID(driver, employeeData.getPassword(), "user_password");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 11: Enter to 'Confirm Password' textbox with value is: '" + employeeData.getPassword() + "'");
		addEmployeePage.enterToTextboxByID(driver, employeeData.getPassword(), "re_password");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 12: Select item in 'Status' dropdown with value is: " + employeeData.getStatusValue() + "'");
		addEmployeePage.selectItemInDropdownByID(driver, employeeData.getStatusValue(), "status");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 13: Click to 'Save' button");
		addEmployeePage.clickToButtonByID(driver, "btnSave");
		myInfoPage = HRMPageGeneratorManager.getMyInfoPage(driver);
		myInfoPage.areJQueryAndJSLoadedSuccess(driver);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 14: Open 'Employee List' page");
		myInfoPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = HRMPageGeneratorManager.getEmployeeListPage(driver);
		employeeListPage.waitForEmployeeNameTextboxLoadSuccessfully();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 15: Enter to 'Employee Name' textbox with value is: '" + employeeData.getFullName() + "'");
		employeeListPage.enterToTextboxByID(driver, employeeData.getFullName(), "empsearch_employee_name_empName");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 16: Click to 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		employeeListPage.waitForEmployeeNameTextboxLoadSuccessfully();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 17: Verify Employee Information displayed at 'Result Table'");
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"), employeeID);
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1"), employeeData.getFirstName());
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), employeeData.getLastName());
	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass(String browserName) {
		closeBrowserAndDriver();
	}

	private WebDriver driver;

	String employeeID;

	AddEmployeePO addEmployeePage;
	DashboardPO dashboardPage;
	EmployeeListPO employeeListPage;
	LoginPO loginPage;
	MyInfoPO myInfoPage;
	Employee_Data_Driven employeeData;
}
