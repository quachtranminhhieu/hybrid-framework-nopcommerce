package com.orangeHRM.employee;

import java.util.Random;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObjects.orangeHRM.AddEmployeePO;
import pageObjects.orangeHRM.DashboardPO;
import pageObjects.orangeHRM.EmployeeListPO;
import pageObjects.orangeHRM.LoginPO;
import pageObjects.orangeHRM.MyInfoPO;
import pageUIs.orangeHRM.HRMBasePageUI;
import pageObjects.orangeHRM.HRMPageGeneratorManager;

public class Level_16_Live_Coding_Report_NG extends BaseTest {

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appURL) {
		log.info("Pre-Condition - Step 01: Open Browser '" + browserName + "' and navigate to '" + appURL + "'");
		driver = getBrowserDriver(browserName, appURL);
		
		employeeFirstName = "Automation" + getRandomNumber();
		employeeLastName = "FC";
		adminUserName = "Admin";
		adminPassword = "admin123";
		empUserName = "automationfc" + getRandomNumber();
		empPassword = "123456789";
		statusValue = "Enabled";
		employeeFullName = employeeFirstName + " " + employeeLastName;
		
		fileName = "cat.jpg";
		editEmpFirstName = "John" + getRandomNumber();
		editEmpLastName = "Wick";
		editEmpFullName = editEmpFirstName + " " + editEmpLastName;
		editEmpGender = "Male";
		editEmpMaritalStatus = "Married";
		editEmpNationality = "Vietnamese";
		
		empFirstAddress = "235 Ly Thuong Kiet"; 
		empSecondAddress = "56 Hoang Dieu"; 
		empCity = "Ho Chi Minh City"; 
		empState = "Thu Duc City"; 
		empZipCode = "550000"; 
		empCountry = "Viet Nam";
		empMobile = "0123456789"; 
		empWorkEmail = "autofc" + getRandomNumber() + "@gmail.com";
		
		empEmergName = "Johny Dang"; 
		empEmergRelp = "Brother"; 
		empEmergHomePhone = "0987654321"; 
		empEmergMobile = "0192837465"; 
		empEmergWorkPhone = "0987612345";
		
		empDepName = "Wanda Maximoff";
		empDepRelp = "Child"; 
		empDepDOB = "1975-04-30";
		
		empJobTitle = "Software Architect"; 
		empJobStatus = "Full-Time Contract"; 
		empJobCategory = "Professionals"; 
		empJobJoined = "2022-01-01"; 
		empJobUnit = "  Quality Assurance"; 
		empJobLocation = "Texas R&D"; 
		empJobStartDate = "2022-01-01"; 
		empJobEndDate = "2030-01-01";
		
		empSalaryGrade = "Grade 2"; 
		empSalaryComponent = "ABCXYZ"; 
		empSalaryFrequency = "Monthly on first pay of month."; 
		empSalaryCurrency = "United States Dollar"; 
		empSalaryAmount = "45000";
		empSalaryAccNumb = "0123456789"; 
		empSalaryAccType = "Savings"; 
		empSalaryRoutNum = "987654321"; 
		empSalaryAccAmount = "20000000";
		
		empTaxFedStatus = "Married"; 
		empTaxFedExemption = "12"; 
		empTaxState = "Ohio"; 
		empTaxStateStatus = "Non Resident Alien"; 
		empTaxStateExemption = "34"; 
		empTaxStateUnemp = "Florida"; 
		empTaxStateWork = "Washington";
		
		empQualExpCompany = "Automation Company"; 
		empQualExpJobTitle = "QC Engineer"; 
		empQualExpFrom = "2018-01-01"; 
		empQualExpTo = "2022-12-31"; 
		empQualEduLevel = "Master's Degree"; 
		empQualEduInst = "Banking Institute"; 
		empQualEduMajor = "Management Information System"; 
		empQualEduYear = "4"; 
		empQualEduGPA = "9.75"; 
		empQualEduStart = "2014-01-01"; 
		empQualEduEnd = "2017-12-31";
		empQualSkill = "Java"; 
		empQualSkillYear = "3";
		empQualLang = "Russian"; 
		empQualLangFluency = "Speaking"; 
		empQualLangCompetency = "Good";
		empQualLicType = "Cisco Certified Network Professional (CCNP)"; 
		empQualLicNumber = "123456789"; 
		empQualLicIssued = "2021-04-14"; 
		empQualLicExpiry = "2025-12-31";
		
		empRepSuperMethod = "Other";
		empRepSuborMethod = "Other";
		empRepSuperSpecify = "Email " + getRandomNumber();
		empRepSuborSpecify = "Skype" + getRandomNumber();
		
		loginPage = HRMPageGeneratorManager.getLoginPage(driver);
		
		log.info("Pre-Condition - Step 02: Login with Admin Role");
		dashboardPage = loginPage.loginToSystem(driver, adminUserName, adminPassword);
	}

	@Test
	public void Employee_01_Add_New_Employee() {
		log.info("Add New 01 - Step 01: Open 'Employee List' page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = HRMPageGeneratorManager.getEmployeeListPage(driver);
		
		log.info("Add New 01 - Step 02: Get the name of Supervisor");
		empRepSuperFirstName = employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "5");
		empRepSuperLastName = employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "5");
		empRepSuperFullName = empRepSuperFirstName + " " + empRepSuperLastName;
		
		log.info("Add New 01 - Step 03: Get the name of Subordinate");
		empRepSuborFirstName = employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "6");
		empRepSuborLastName = employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "6");
		empRepSuborFullName = empRepSuborFirstName + " " + empRepSuborLastName;
		
		log.info("Add New 01 - Step 04: Click to 'Add' button");
		employeeListPage.clickToButtonByID(driver, "btnAdd");
		addEmployeePage = HRMPageGeneratorManager.getAddEmployeePage(driver);

		log.info("Add New 01 - Step 05: Enter to 'First Name' textbox with value is: '" + employeeFirstName + "'");
		addEmployeePage.enterToTextboxByID(driver, employeeFirstName, "firstName");

		log.info("Add New 01 - Step 06: Enter to 'Last Name' textbox with value is: '" + employeeLastName + "'");
		addEmployeePage.enterToTextboxByID(driver, employeeLastName, "lastName");

		log.info("Add New 01 - Step 07: Get value of 'Employee ID'");
		employeeID = addEmployeePage.getTextboxValueByID(driver, "employeeId");

		log.info("Add New 01 - Step 08: Click to 'Create Login Details' checkbox");
		addEmployeePage.clickToCheckboxByLabel(driver, "Create Login Details");

		log.info("Add New 01 - Step 09: Enter to 'User Name' textbox with value is: '" + empUserName + "'");
		addEmployeePage.enterToTextboxByID(driver, empUserName, "user_name");

		log.info("Add New 01 - Step 10: Enter to 'Password' textbox with value is: '" + empPassword + "'");
		addEmployeePage.enterToTextboxByID(driver, empPassword, "user_password");

		log.info("Add New 01 - Step 11: Enter to 'Confirm Password' textbox with value is: '" + empPassword + "'");
		addEmployeePage.enterToTextboxByID(driver, empPassword, "re_password");

		log.info("Add New 01 - Step 12: Select item in 'Status' dropdown with value is: '" + statusValue + "'");
		addEmployeePage.selectItemInDropdownByID(driver, statusValue, "status");

		log.info("Add New 01 - Step 13: Click to 'Save' button");
		addEmployeePage.clickToButtonByID(driver, "btnSave");
		myInfoPage = HRMPageGeneratorManager.getMyInfoPage(driver);
		myInfoPage.areJQueryAndJSLoadedSuccess(driver);

		log.info("Add New 01 - Step 14: Open 'Employee List' page");
		myInfoPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = HRMPageGeneratorManager.getEmployeeListPage(driver);
		employeeListPage.waitForEmployeeNameTextboxLoadSuccessfully();

		log.info("Add New 01 - Step 15: Enter to 'Employee Name' textbox with value is: '" + employeeFullName + "'");
		employeeListPage.enterToTextboxByID(driver, employeeFullName, "empsearch_employee_name_empName");

		log.info("Add New 01 - Step 16: Click to 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		employeeListPage.waitForEmployeeNameTextboxLoadSuccessfully();

		log.info("Add New 01 - Step 17: Verify Employee Information displayed at 'Result Table'");
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"), employeeID);
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1"), employeeFirstName);
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), employeeLastName);
	}

	@Test
	public void Employee_02_Upload_Avatar() {
		log.info("Upload Avatar 02 - Step 01: Login with Employee Role");
		loginPage = employeeListPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, empUserName, empPassword);

		log.info("Upload Avatar 02 - Step 02: Click to 'My Info' Menu");
		dashboardPage.openMenuPage(driver, "My Info");
		myInfoPage = HRMPageGeneratorManager.getMyInfoPage(driver);

		log.info("Upload Avatar 02 - Step 03: Click to Change Photo image");
		myInfoPage.clickToChangePhotoImage();

		log.info("Upload Avatar 02 - Step 04: Upload new avatar");
		myInfoPage.uploadMultipleFile(driver, fileName);

		log.info("Upload Avatar 02 - Step 05: Click to Upload button");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Upload Avatar 02 - Step 06: Verify Success Message is displayed");
//		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Uploaded"));

		log.info("Upload Avatar 02 - Step 07: Verify new Avatar is displayed");
		verifyTrue(myInfoPage.isNewAvatarImageDisplayed());
	}

	@Test
	public void Employee_03_Personal_Details() {
		log.info("Personal Details 03 - Step 01: Open 'Personal Details' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Personal Details");

		log.info("Personal Details 03 - Step 02: Verify all fields at 'Personal Details' form are disabled");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtEmpFirstName"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtEmpLastName"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtEmployeeId"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtLicenNo"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtNICNo"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtSINNo"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_optGender_1"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_cmbMarital"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_DOB"));

		log.info("Personal Details 03 - Step 03: Click To 'Edit' button at 'Personal Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Personal Details 03 - Step 04: Verify 'Employee ID' textbox is still disabled");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtEmployeeId"));

		log.info("Personal Details 03 - Step 05: Verify 'Driver's License Number' textbox is still disabled");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtLicenNo"));

		log.info("Personal Details 03 - Step 06: Verify 'SSN Number' textbox is still disabled");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtNICNo"));

		log.info("Personal Details 03 - Step 07: Verify 'SIN Number' textbox is still disabled");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtSINNo"));

		log.info("Personal Details 03 - Step 08: Verify 'Date of Birth' textbox is still disabled");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_DOB"));

		log.info("Personal Details 03 - Step 09: Enter new value to 'First Name' textbox with values is " + editEmpFirstName + "'");
		myInfoPage.enterToTextboxByID(driver, editEmpFirstName, "personal_txtEmpFirstName");

		log.info("Personal Details 03 - Step 10: Enter new value to 'Last Name' textbox with value is: '" + editEmpLastName + "'");
		myInfoPage.enterToTextboxByID(driver, editEmpLastName, "personal_txtEmpLastName");

		log.info("Personal Details 03 - Step 11: Select new value to 'Gender' radio button with value is: '" + editEmpGender + "'");
		myInfoPage.clickToRadioButtonByLabel(driver, editEmpGender);

		log.info("Personal Details 03 - Step 12: Select new value to 'Marital Status' dropdown with value is: '" + editEmpMaritalStatus + "'");
		myInfoPage.selectItemInDropdownByID(driver, editEmpMaritalStatus, "personal_cmbMarital");

		log.info("Personal Details 03 - Step 13: Select new value to 'Nationality' dropdown with value is: '" + editEmpNationality + "'");
		myInfoPage.selectItemInDropdownByID(driver, editEmpNationality, "personal_cmbNation");

		log.info("Personal Details 03 - Step 14: Click to 'Save' button at 'Personal Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Personal Details 03 - Step 15: Verify Success Message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Personal Details 03 - Step 16: Verify 'First Name' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpFirstName"), editEmpFirstName);

		log.info("Personal Details 03 - Step 17: Verify 'Last Name' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpLastName"), editEmpLastName);

		log.info("Personal Details 03 - Step 18: Verify 'Gender' radio button is updated successfully");
		verifyTrue(myInfoPage.isRadioButtonIsSelectedByLabel(driver, editEmpGender));

		log.info("Personal Details 03 - Step 19: Verify 'Marital Status' dropdown is updated successfully");
		verifyEquals(myInfoPage.getSelectedItemInDropdownByID(driver, "personal_cmbMarital"), editEmpMaritalStatus);

		log.info("Personal Details 03 - Step 20: Verify 'Nationality' dropdown is updated successfully");
		verifyEquals(myInfoPage.getSelectedItemInDropdownByID(driver, "personal_cmbNation"), editEmpNationality);

		log.info("Personal Details 03 - Step 21: Verify 'Employee ID' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmployeeId"), employeeID);

	}

	@Test
	public void Employee_04_Contact_Details() {
		log.info("Contact Details 04 - Step 01: Open 'Contact Details' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Contact Details");

		log.info("Contact Details 04 - Step 02: Verify all fields at 'Contact Details' form are disabled");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_street1"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_street2"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_city"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_province"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_emp_zipcode"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_country"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_emp_hm_telephone"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_emp_mobile"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_emp_work_telephone"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_emp_work_email"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "contact_emp_oth_email"));

		log.info("Contact Details 04 - Step 03: Click to 'Edit' button at 'Contact Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Contact Details 04 - Step 04: Enter to 'Address Street 1' textbox with value is: '" + empFirstAddress + "'");
		myInfoPage.enterToTextboxByID(driver, empFirstAddress, "contact_street1");

		log.info("Contact Details 04 - Step 05: Enter to 'Address Street 2' textbox with value is: '" + empSecondAddress + "'");
		myInfoPage.enterToTextboxByID(driver, empSecondAddress, "contact_street2");

		log.info("Contact Details 04 - Step 06: Enter to 'City' textbox with value is: '" + empCity + "'");
		myInfoPage.enterToTextboxByID(driver, empCity, "contact_city");

		log.info("Contact Details 04 - Step 07: Enter to 'State/Province' textbox with value is: '" + empState + "'");
		myInfoPage.enterToTextboxByID(driver, empState, "contact_province");

		log.info("Contact Details 04 - Step 08: Enter to 'Zip/Postal Code' textbox with value is: '" + empZipCode + "'");
		myInfoPage.enterToTextboxByID(driver, empZipCode, "contact_emp_zipcode");

		log.info("Contact Details 04 - Step 09: Select item in 'State/Province' dropdown with value is: '" + empCountry + "'");
		myInfoPage.selectItemInDropdownByID(driver, empCountry, "contact_country");

		log.info("Contact Details 04 - Step 10: Enter to 'Mobile' textbox with value is: '" + empMobile + "'");
		myInfoPage.enterToTextboxByID(driver, empMobile, "contact_emp_mobile");

		log.info("Contact Details 04 - Step 11: Enter to 'Work Email' textbox with value is: '" + empWorkEmail + "'");
		myInfoPage.enterToTextboxByID(driver, empWorkEmail, "contact_emp_work_email");

		log.info("Contact Details 04 - Step 12: Click to 'Save' button at 'Contact Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		log.info("Contact Details 04 - Step 13: Verify Success Message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		log.info("Contact Details 04 - Step 14: Verify 'Address Street 1' texbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_street1"), empFirstAddress);

		log.info("Contact Details 04 - Step 15: Verify 'Address Street 2' texbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_street2"), empSecondAddress);

		log.info("Contact Details 04 - Step 16: Verify 'City' texbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_city"), empCity);

		log.info("Contact Details 04 - Step 17: Verify 'State/Province' texbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_province"), empState);

		log.info("Contact Details 04 - Step 18: Verify 'Zip/Postal Code' texbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_zipcode"), empZipCode);

		log.info("Contact Details 04 - Step 19: Verify 'Country' dropdown is updated successfully");
		verifyEquals(myInfoPage.getSelectedItemInDropdownByID(driver, "contact_country"), empCountry);

		log.info("Contact Details 04 - Step 20: Verify 'Mobile' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_mobile"), empMobile);

		log.info("Contact Details 04 - Step 21: Verify 'Work Email' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_work_email"), empWorkEmail);
	}

	@Test
	public void Employee_05_Emergency_Contacts() {
		log.info("Emergency Contacts 05 - Step 01: Open 'Emergency Contacts' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Emergency Contacts");
		
		log.info("Emergency Contacts 05 - Step 02: Click to 'Add' button at 'Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnAddContact");
		
		log.info("Emergency Contacts 05 - Step 03: Enter to 'Name' textbox with value is: '" + empEmergName + "'");
		myInfoPage.enterToTextboxByID(driver, empEmergName, "emgcontacts_name");
		
		log.info("Emergency Contacts 05 - Step 04: Enter to 'Relationship' textbox with value is: '" + empEmergRelp + "'");
		myInfoPage.enterToTextboxByID(driver, empEmergRelp, "emgcontacts_relationship");
		
		log.info("Emergency Contacts 05 - Step 05: Enter to 'Home Telephone' textbox with value is: '" + empEmergHomePhone + "'");
		myInfoPage.enterToTextboxByID(driver, empEmergHomePhone, "emgcontacts_homePhone");
		
		log.info("Emergency Contacts 05 - Step 06: Enter to 'Mobile' textbox with value is: '" + empEmergMobile + "'");
		myInfoPage.enterToTextboxByID(driver, empEmergMobile, "emgcontacts_mobilePhone");
		
		log.info("Emergency Contacts 05 - Step 07: Enter to 'Work Telephone' textbox with value is: '" + empEmergWorkPhone + "'");
		myInfoPage.enterToTextboxByID(driver, empEmergWorkPhone, "emgcontacts_workPhone");
		
		log.info("Emergency Contacts 05 - Step 08: Click to 'Save' button at 'Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveEContact");
		
		log.info("Emergency Contacts 05 - Step 09: Verify Success Message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Emergency Contacts 05 - Step 10: Verify 'Name' value is updated successfully in data table");
		verifyEquals(myInfoPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Name", "1"), empEmergName);
		
		log.info("Emergency Contacts 05 - Step 11: Verify 'Relationship' value is updated successfully in data table");
		verifyEquals(myInfoPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Relationship", "1"), empEmergRelp);
		
		log.info("Emergency Contacts 05 - Step 12: Verify 'Home Telephone' value is updated successfully in data table");
		verifyEquals(myInfoPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Home Telephone", "1"), empEmergHomePhone);
		
		log.info("Emergency Contacts 05 - Step 13: Verify 'Mobile' value is updated successfully in data table");
		verifyEquals(myInfoPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Mobile", "1"), empEmergMobile);
		
		log.info("Emergency Contacts 05 - Step 14: Verify 'Work Telephone' value is updated successfully in data table");
		verifyEquals(myInfoPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Work Telephone", "1"), empEmergWorkPhone);
	}

	@Test
	public void Employee_06_Dependents() {
		log.info("Dependents 06 - Step 01: Open 'Dependents' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Dependents");
		
		log.info("Dependents 06 - Step 02: Click to 'Add' button at 'Dependent' form");
		myInfoPage.clickToButtonByID(driver, "btnAddDependent");
		
		log.info("Dependents 06 - Step 03: Enter to 'Name' textbox with value is: '" + empDepName + "'");
		myInfoPage.enterToTextboxByID(driver, empDepName, "dependent_name");
		
		log.info("Dependents 06 - Step 04: Select item in 'Relationship' dropdown with value is: '" + empDepRelp + "'");
		myInfoPage.selectItemInDropdownByID(driver, empDepRelp, "dependent_relationshipType");
		
		log.info("Dependents 06 - Step 05: Enter to 'Date of Birth' datepicker with value is: '" + empDepDOB + "'");
		myInfoPage.enterToTextboxByID(driver, empDepDOB, "dependent_dateOfBirth");
		
		log.info("Dependents 06 - Step 06: Click to 'Save' button at 'Dependent' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveDependent");
		
		log.info("Dependents 06 - Step 07: Verify Success Message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Dependents 06 - Step 08: Verify 'Name' value is updated successfully in data table");
		verifyEquals(myInfoPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Name", "1"), empDepName);
		
		log.info("Dependents 06 - Step 09: Verify 'Relationship' value is updated successfully in data table");
		verifyEquals(myInfoPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Relationship", "1"), empDepRelp.toLowerCase());
		
		log.info("Dependents 06 - Step 10: Verify 'Date of Birth' value is updated successfully in data table");
		verifyEquals(myInfoPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Date of Birth", "1"), empDepDOB);
	}

	@Test
	public void Employee_07_Edit_View_Job() {
		log.info("Job 07 - Step 01: Login with Admin role");
		loginPage= myInfoPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, adminUserName, adminPassword);
		
		log.info("Job 07 - Step 02: Open 'Employee List' page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = HRMPageGeneratorManager.getEmployeeListPage(driver);
		
		log.info("Job 07 - Step 03: Enter to 'Employee Name' textbox with value is: '" + editEmpFullName + "'");
		employeeListPage.sleepInSecond(3);
		employeeListPage.enterToTextboxByID(driver, editEmpFullName, "empsearch_employee_name_empName");

		log.info("Job 07 - Step 04: Click to 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		employeeListPage.waitForEmployeeNameTextboxLoadSuccessfully();
		
		log.info("Job 07 - Step 05: Verify Employee Information displayed at 'Result Table'");
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"), employeeID);
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1"), editEmpFirstName);
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), editEmpLastName);
		
		log.info("Job 07 - Step 06: Click to 'ID' Link in data table");
		employeeListPage.clickToLinkInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable" , "Id", "1");
		
		log.info("Job 07 - Step 07: Open 'Job' tab at Side bar");
		employeeListPage.openTabAtSideBarByName("Job");
		
		log.info("Job 07 - Step 08: Verify all fields at 'Job' form are disabled");
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "job_job_title"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "job_emp_status"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "job_eeo_category"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "job_joined_date"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "job_sub_unit"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "job_location"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "job_contract_start_date"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "job_contract_end_date"));
		
		log.info("Job 07 - Step 09: Click to 'Edit' button at 'Job' form");
		employeeListPage.clickToButtonByID(driver, "btnSave");
		
		log.info("Job 07 - Step 10: Select item in 'Job Title' dropdown with value is: '" + empJobTitle + "'");
		employeeListPage.selectItemInDropdownByID(driver, empJobTitle, "job_job_title");
		
		log.info("Job 07 - Step 11: Select item in 'Employment Status' dropdown with value is: '" + empJobStatus + "'");
		employeeListPage.selectItemInDropdownByID(driver, empJobStatus, "job_emp_status");
		
		log.info("Job 07 - Step 12: Enter to 'Joined Date' textbox with value is: '" + empJobJoined + "'");
		employeeListPage.enterToTextboxByID(driver, empJobJoined, "job_joined_date");
		
		log.info("Job 07 - Step 13: Select item in 'Job Category' dropdown with value is: '" + empJobCategory + "'");
		employeeListPage.selectItemInDropdownByID(driver, empJobCategory, "job_eeo_category");
		
		log.info("Job 07 - Step 14: Select item in 'Sub Unit' dropdown with value is: '" + empJobUnit.trim() + "'");
		employeeListPage.selectItemInDropdownByID(driver, empJobUnit, "job_sub_unit");
		
		log.info("Job 07 - Step 15: Select item in 'Location' dropdown with value is: '" + empJobLocation + "'");
		employeeListPage.selectItemInDropdownByID(driver, empJobLocation, "job_location");
		
		log.info("Job 07 - Step 16: Enter to 'Start Date' textbox with value is: '" + empJobStartDate + "'");
		employeeListPage.enterToTextboxByID(driver, empJobStartDate, "job_contract_start_date");
		
		log.info("Job 07 - Step 17: Enter to 'End Date' textbox with value is: '" + empJobEndDate + "'");
		employeeListPage.enterToTextboxByID(driver, empJobEndDate, "job_contract_end_date");
		
		log.info("Job 07 - Step 18: Upload a file to 'Contract Details' textbox with file name is " + fileName + "'");
		employeeListPage.uploadMultipleFile(driver, fileName);
		
		log.info("Job 07 - Step 19: Click to 'Save' button at 'Job' form");
		employeeListPage.clickToButtonByID(driver, "btnSave");
		
		log.info("Job 07 - Step 21: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Updated"));
		
		log.info("Job 07 - Step 22: Verify 'Job Title' dropdown is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "job_job_title"), empJobTitle);
		
		log.info("Job 07 - Step 23: Verify 'Employee Status' dropdown is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "job_emp_status"), empJobStatus);
		
		log.info("Job 07 - Step 24: Verify 'Job Category' dropdown is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "job_eeo_category"), empJobCategory);
		
		log.info("Job 07 - Step 25: Verify 'Joined Date' datepicker is updated successfully");
		verifyEquals(employeeListPage.getTextboxValueByID(driver, "job_joined_date"), empJobJoined);
		
		log.info("Job 07 - Step 26: Verify 'Sub Unit' dropdown is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "job_sub_unit"), empJobUnit);
		
		log.info("Job 07 - Step 27: Verify 'Location' dropdown is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "job_location"), empJobLocation);
		
		log.info("Job 07 - Step 28: Verify 'Start Date' datepicker is updated successfully");
		verifyEquals(employeeListPage.getTextboxValueByID(driver, "job_contract_start_date"), empJobStartDate);
		
		log.info("Job 07 - Step 29: Verify 'End Date' datepicker is updated successfully");
		verifyEquals(employeeListPage.getTextboxValueByID(driver, "job_contract_end_date"), empJobEndDate);
	}

	@Test
	public void Employee_08_Edit_View_Salary() {
		log.info("Salary 08 - Step 01: Open 'Salary' tab at Side bar");
		employeeListPage.openTabAtSideBarByName("Salary");
		
		log.info("Salary 08 - Step 02: Click to 'Add' button at 'Salary' form");
		employeeListPage.clickToButtonByID(driver, "addSalary");
		
		log.info("Salary 08 - Step 03: Select item in 'Pay Grade' dropdown with value is: '" + empSalaryGrade + "'");
		employeeListPage.selectItemInDropdownByID(driver, empSalaryGrade, "salary_sal_grd_code");
		
		log.info("Salary 08 - Step 04: Enter to 'Salary Component' textbox with value is: '" + empSalaryComponent + "'");
		employeeListPage.enterToTextboxByID(driver, empSalaryComponent, "salary_salary_component");
		
		log.info("Salary 08 - Step 05: Select item in 'Pay Frequency' dropdown with value is: '" + empSalaryFrequency + "'");
		employeeListPage.selectItemInDropdownByID(driver, empSalaryFrequency, "salary_payperiod_code");
		
		log.info("Salary 08 - Step 06: Select item in 'Currency' dropdown with value is: '" + empSalaryCurrency + "'");
		employeeListPage.selectItemInDropdownByID(driver, empSalaryCurrency, "salary_currency_id");
		
		log.info("Salary 08 - Step 07: Enter to 'Amount' textbox with value is: '" + empSalaryAmount + "'");
		employeeListPage.enterToTextboxByID(driver, empSalaryAmount, "salary_basic_salary");
		
		log.info("Salary 08 - Step 08: Check to 'Add Direct Deposit Details' checkbox");
		employeeListPage.clickToCheckboxByLabel(driver, "Add Direct Deposit Details");
		
		log.info("Salary 08 - Step 09: Enter to 'Account Number' textbox with value is: '" + empSalaryAccNumb + "'");
		employeeListPage.enterToTextboxByID(driver, empSalaryAccNumb, "directdeposit_account");
		
		log.info("Salary 08 - Step 10: Select item in 'Account Type' dropdown with value is: '" + empSalaryAccType + "'");
		employeeListPage.selectItemInDropdownByID(driver, empSalaryAccType, "directdeposit_account_type");
		
		log.info("Salary 08 - Step 11: Enter to 'Routing Number' textbox with value is: '" + empSalaryRoutNum + "'");
		employeeListPage.enterToTextboxByID(driver, empSalaryRoutNum, "directdeposit_routing_num");
		
		log.info("Salary 08 - Step 12: Enter to 'Amount' textbox with value is: '" + empSalaryAccAmount + "'");
		employeeListPage.enterToTextboxByID(driver, empSalaryAccAmount, "directdeposit_amount");
		
		log.info("Salary 08 - Step 13: Click to 'Save' button at 'Salary' form");
		employeeListPage.clickToButtonByID(driver, "btnSalarySave");
		
		log.info("Salary 08 - Step 14: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Salary 08 - Step 15: Verify 'Salary Component' value is updated successfully in data table");
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "tblSalary", "Salary Component", "1"), empSalaryComponent);
		
		log.info("Salary 08 - Step 16: Verify 'Pay Frequency' value is updated successfully in data table");
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "tblSalary", "Pay Frequency", "1"), empSalaryFrequency);
		
		log.info("Salary 08 - Step 17: Verify 'Currency' value is updated successfully in data table");
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "tblSalary", "Currency", "1"), empSalaryCurrency);
		
		log.info("Salary 08 - Step 18: Verify 'Amount' value is updated successfully in data table");
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "tblSalary", "Amount", "1"), empSalaryAmount);
		
		log.info("Salary 08 - Step 18: Click to 'Show Direct Deposit Details' checkbox in data table");
		employeeListPage.checkToCheckBoxInDataTableIDAtColumnNameAndRowIndex(driver, "tblSalary", "Show Direct Deposit Details", "1");
		
		log.info("Salary 08 - Step 19: Verify 'Account Number', 'Account Type', 'Routing Number', 'Amount' value is updated successfully in data table");
		verifyTrue(employeeListPage.areValuesInDepositDataTableUpdatedSuccessfully(empSalaryAccNumb, empSalaryAccType,empSalaryRoutNum, empSalaryAccAmount + ".00"));

	}

	@Test
	public void Employee_09_Edit_View_Tax() {
		log.info("Tax 09 - Step 01: Open 'Tax Exemptions' tab at Side bar");
		employeeListPage.openTabAtSideBarByName("Tax Exemptions");
		
		log.info("Tax 09 - Step 02: Verify all fields at 'Tax' form are disabled");
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "tax_federalStatus"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "tax_federalExemptions"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "tax_state"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "tax_stateStatus"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "tax_stateExemptions"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "tax_unempState"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "tax_workState"));
		
		log.info("Tax 09 - Step 03: Click to 'Edit' button at 'Tax' form");
		employeeListPage.clickToButtonByID(driver, "btnSave");
		
		log.info("Tax 09 - Step 04: Select item in 'Federal Income Tax - Status' dropdown with values is " + empTaxFedStatus + "'");
		employeeListPage.selectItemInDropdownByID(driver, empTaxFedStatus, "tax_federalStatus");
		
		log.info("Tax 09 - Step 05: Enter to 'Federal Income Tax - Exemptions' textbox with values is " + empTaxFedExemption + "'");
		employeeListPage.enterToTextboxByID(driver, empTaxFedExemption, "tax_federalExemptions");
		
		log.info("Tax 09 - Step 06: Select item in 'State Income Tax - State' dropdown with values is " + empTaxState + "'");
		employeeListPage.selectItemInDropdownByID(driver, empTaxState, "tax_state");
		
		log.info("Tax 09 - Step 07: Select item in 'State Income Tax - Status' dropdown with values is " + empTaxStateStatus + "'");
		employeeListPage.selectItemInDropdownByID(driver, empTaxStateStatus, "tax_stateStatus");
		
		log.info("Tax 09 - Step 08: Enter to 'State Income Tax - Exemptions' textbox with values is " + empTaxStateExemption + "'");
		employeeListPage.enterToTextboxByID(driver, empTaxStateExemption, "tax_stateExemptions");
		
		log.info("Tax 09 - Step 09: Select item in 'State Income Tax - Unemployment State' dropdown with values is " + empTaxStateUnemp + "'");
		employeeListPage.selectItemInDropdownByID(driver, empTaxStateUnemp, "tax_unempState");
		
		log.info("Tax 09 - Step 10: Select item in 'State Income Tax - Work State' dropdown with values is " + empTaxStateWork + "'");
		employeeListPage.selectItemInDropdownByID(driver, empTaxStateWork, "tax_workState");
		
		log.info("Tax 09 - Step 11: Click to 'Save' button at 'Tax' form");
		employeeListPage.clickToButtonByID(driver, "btnSave");
		
		log.info("Tax 09 - Step 12: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Tax 09 - Step 13: Verify 'Federal Income Tax - Status' value is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "tax_federalStatus"), empTaxFedStatus);
		
		log.info("Tax 09 - Step 14: Verify 'Federal Income Tax - Exemptions' value is updated successfully");
		verifyEquals(employeeListPage.getTextboxValueByID(driver, "tax_federalExemptions"), empTaxFedExemption);
		
		log.info("Tax 09 - Step 15: Verify 'State Income Tax - State' value is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "tax_state"), empTaxState);
		
		log.info("Tax 09 - Step 16: Verify 'State Income Tax - Status' value is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "tax_stateStatus"), empTaxStateStatus);
		
		log.info("Tax 09 - Step 17: Verify 'State Income Tax - Exemptions' value is updated successfully");
		verifyEquals(employeeListPage.getTextboxValueByID(driver, "tax_stateExemptions"), empTaxStateExemption);
		
		log.info("Tax 09 - Step 18: Verify 'State Income Tax - Unemployment State' value is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "tax_unempState"), empTaxStateUnemp);
		
		log.info("Tax 09 - Step 19: Verify 'State Income Tax - Work State' value is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "tax_workState"), empTaxStateWork);
	}
	@Test
	public void Employee_10_Qualifications() {
		log.info("Qualifications 10 - Step 01: Open 'Qualifications' tab at Side bar");
		employeeListPage.openTabAtSideBarByName("Qualifications");
		
		log.info("Qualifications 10 - Step 02: Click to 'Add' button at 'Work Experience' form");
		employeeListPage.clickToButtonByID(driver, "addWorkExperience");
		
		log.info("Qualifications 10 - Step 03: Enter to 'Company' textbox with value is: '" + empQualExpCompany + "'");
		employeeListPage.enterToTextboxByID(driver, empQualExpCompany, "experience_employer");
		
		log.info("Qualifications 10 - Step 04: Enter to 'Job Title' textbox with value is: '" + empQualExpJobTitle + "'");
		employeeListPage.enterToTextboxByID(driver, empQualExpJobTitle, "experience_jobtitle");
		
		log.info("Qualifications 10 - Step 05: Enter to 'From' datepicker with value is: '" + empQualExpFrom + "'");
		employeeListPage.enterToTextboxByID(driver, empQualExpFrom, "experience_from_date");
		
		log.info("Qualifications 10 - Step 06: Enter to 'To' datepicker with value is: '" + empQualExpTo + "'");
		employeeListPage.enterToTextboxByID(driver, empQualExpTo, "experience_to_date");
		
		log.info("Qualifications 10 - Step 07: Click to 'Save' button at 'Work Experience' form");
		employeeListPage.clickToButtonByID(driver, "btnWorkExpSave");
		
		log.info("Qualifications 10 - Step 08: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Qualifications 10 - Step 09: Verify 'Company' value is updated successfully in 'Work Expericence' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Work Experience", "Company", "1"), empQualExpCompany);
		
		log.info("Qualifications 10 - Step 10: Verify 'Job Title' value is updated successfully in 'Work Expericence' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Work Experience", "Job Title", "1"), empQualExpJobTitle);
		
		log.info("Qualifications 10 - Step 11: Verify 'From' value is updated successfully in 'Work Expericence' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Work Experience", "From", "1"), empQualExpFrom);
		
		log.info("Qualifications 10 - Step 12: Verify 'To' value is updated successfully in 'Work Expericence' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Work Experience", "To", "1"), empQualExpTo);
		
		log.info("Qualifications 10 - Step 13: Click to 'Add' button at 'Education' form");
		employeeListPage.clickToButtonByID(driver, "addEducation");
		
		log.info("Qualifications 10 - Step 14: Select item in 'Level' dropdown with value is: '" + empQualEduLevel + "'");
		employeeListPage.selectItemInDropdownByID(driver, empQualEduLevel, "education_code");
		
		log.info("Qualifications 10 - Step 15: Enter to 'Institute' textbox with value is: '" + empQualEduInst + "'");
		employeeListPage.enterToTextboxByID(driver, empQualEduInst, "education_institute");
		
		log.info("Qualifications 10 - Step 16: Enter to 'Major/Specialization' textbox with value is: '" + empQualEduMajor + "'");
		employeeListPage.enterToTextboxByID(driver, empQualEduMajor, "education_major");
		
		log.info("Qualifications 10 - Step 17: Enter to 'Year' textbox with value is: '" + empQualEduYear + "'");
		employeeListPage.enterToTextboxByID(driver, empQualEduYear, "education_year");
		
		log.info("Qualifications 10 - Step 18: Enter to 'GPA/Score' textbox with value is: '" + empQualEduGPA + "'");
		employeeListPage.enterToTextboxByID(driver, empQualEduGPA, "education_gpa");
		
		log.info("Qualifications 10 - Step 19: Enter to 'Start Date' datepicker with value is: '" + empQualEduStart + "'");
		employeeListPage.enterToTextboxByID(driver, empQualEduStart, "education_start_date");
		
		log.info("Qualifications 10 - Step 20: Enter to 'End Date' datepicker with value is: '" + empQualEduEnd + "'");
		employeeListPage.enterToTextboxByID(driver, empQualEduEnd, "education_end_date");
		
		log.info("Qualifications 10 - Step 21: Click to 'Save' button at 'Education' form");
		employeeListPage.clickToButtonByID(driver, "btnEducationSave");
		
		log.info("Qualifications 10 - Step 22: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Qualifications 10 - Step 23: Verify 'Level' value is updated successfully in 'Education' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Education", "Level", "1"), empQualEduLevel);
		
		log.info("Qualifications 10 - Step 24: Verify 'Year' value is updated successfully in 'Education' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Education", "Year", "1"), empQualEduYear);
		
		log.info("Qualifications 10 - Step 25: Verify 'GPA/Score' value is updated successfully in 'Education' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Education", "GPA/Score", "1"), empQualEduGPA);
		
		log.info("Qualifications 10 - Step 26: Click to 'Add' button at 'Skills' form");
		employeeListPage.clickToButtonByID(driver, "addSkill");
		
		log.info("Qualifications 10 - Step 27: Select item in 'Skill' dropdown with value is: '" + empQualSkill + "'");
		employeeListPage.selectItemInDropdownByID(driver, empQualSkill, "skill_code");
		
		log.info("Qualifications 10 - Step 28: Enter to 'Year of Experience' textbox with value is: '" + empQualSkillYear + "'");
		employeeListPage.enterToTextboxByID(driver, empQualSkillYear, "skill_years_of_exp");
		
		log.info("Qualifications 10 - Step 29: Click to 'Save' button at 'Skills' form");
		employeeListPage.clickToButtonByID(driver, "btnSkillSave");
		
		log.info("Qualifications 10 - Step 30: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Qualifications 10 - Step 31: Verify 'Skill' value is updated successfully in 'Skills' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Skills", "Skill", "1"), empQualSkill);
		
		log.info("Qualifications 10 - Step 32: Verify 'Years of Experience' value is updated successfully in 'Skills' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Skills", "Years of Experience", "1"), empQualSkillYear);
		
		log.info("Qualifications 10 - Step 33: Click to 'Add' button at 'Languages' form");
		employeeListPage.clickToButtonByID(driver, "addLanguage");
		
		log.info("Qualifications 10 - Step 34: Select item in 'Language' dropdown with value is: '" + empQualLang + "'");
		employeeListPage.selectItemInDropdownByID(driver, empQualLang, "language_code");
		
		log.info("Qualifications 10 - Step 35: Select item in 'Fluency' dropdown with value is: '" + empQualLangFluency + "'");
		employeeListPage.selectItemInDropdownByID(driver, empQualLangFluency, "language_lang_type");
		
		log.info("Qualifications 10 - Step 36: Select item in 'Competency' dropdown with value is: '" + empQualLangCompetency + "'");
		employeeListPage.selectItemInDropdownByID(driver, empQualLangCompetency, "language_competency");
		
		log.info("Qualifications 10 - Step 37: Click to 'Save' button at 'Languages' form");
		employeeListPage.clickToButtonByID(driver, "btnLanguageSave");
		
		log.info("Qualifications 10 - Step 38: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Qualifications 10 - Step 39: Verify 'Language' value is updated successfully in 'Languages' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Languages", "Language", "1"), empQualLang);
		
		log.info("Qualifications 10 - Step 40: Verify 'Fluency' value is updated successfully in 'Languages' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Languages", "Fluency", "1"), empQualLangFluency);
		
		log.info("Qualifications 10 - Step 41: Verify 'Competency' value is updated successfully in 'Languages' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Languages", "Competency", "1"), empQualLangCompetency);
		
		log.info("Qualifications 10 - Step 42: Click to 'Add' button at 'License' form");
		employeeListPage.clickToButtonByID(driver, "addLicense");
		
		log.info("Qualifications 10 - Step 43: Select item in 'License Type' dropdown with value is: '" + empQualLicType + "'");
		employeeListPage.selectItemInDropdownByID(driver, empQualLicType, "license_code");
		
		log.info("Qualifications 10 - Step 44: Enter to 'License Number' textbox with value is: '" + empQualLicNumber + "'");
		employeeListPage.enterToTextboxByID(driver, empQualLicNumber, "license_license_no");
		
		log.info("Qualifications 10 - Step 45: Enter to 'Issued Date' datepicker with value is: '" + empQualLicIssued + "'");
		employeeListPage.enterToTextboxByID(driver, empQualLicIssued, "license_date");
		
		log.info("Qualifications 10 - Step 46: Enter to 'Expiry Date' datepicker with value is: '" + empQualLicExpiry + "'");
		employeeListPage.enterToTextboxByID(driver, empQualLicExpiry, "license_renewal_date");
		
		log.info("Qualifications 10 - Step 47: Click to 'Save' button at 'License' form");
		employeeListPage.clickToButtonByID(driver, "btnLicenseSave");
		
		log.info("Qualifications 10 - Step 48: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Qualifications 10 - Step 49: Verify 'License Type' value is updated successfully in 'License' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("License", "License Type", "1"), empQualLicType);
		
		log.info("Qualifications 10 - Step 50: Verify 'Issued Date' value is updated successfully in 'License' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("License", "Issued Date", "1"), empQualLicIssued);
		
		log.info("Qualifications 10 - Step 51: Verify 'Expiry Date' value is updated successfully in 'License' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("License", "Expiry Date", "1"), empQualLicExpiry);
	}

	@Test
	public void Employee_11_Report_To() {
		log.info("Report-to 11 - Step 01: Open 'Report-to' tab at Side bar");
		employeeListPage.openTabAtSideBarByName("Report-to");
		
		log.info("Report-to 11 - Step 02: Click to 'Add' button at 'Supervisors' form");
		employeeListPage.clickToButtonByID(driver, "btnAddSupervisorDetail");
		
		log.info("Report-to 11 - Step 03: Enter to 'Name' textbox with value is: '" + empRepSuperFirstName + "'");
		employeeListPage.enterToTextboxByID(driver, empRepSuperFullName, "reportto_supervisorName_empName");
		
		log.info("Report-to 11 - Step 04: Select item in 'Reporting Method' dropdown with value is: '" + empRepSuperMethod + "'");
		employeeListPage.selectItemInDropdownByID(driver, empRepSuperMethod, "reportto_reportingMethodType");
		
		log.info("Report-to 11 - Step 05: Enter to 'Please Specify' textbox with value is: '" + empRepSuperSpecify + "'");
		employeeListPage.enterToTextboxByID(driver, empRepSuperSpecify, "reportto_reportingMethod");
		
		log.info("Report-to 11 - Step 06: Click to 'Save' button at 'Supervisors' form");
		employeeListPage.clickToButtonByID(driver, "btnSaveReportTo");
		
		log.info("Report-to 11 - Step 07: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Report-to 11 - Step 08: Verify 'Name' value is updated successfully in 'Supervisors' data table");
		verifyTrue(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Assigned Supervisors", "Name", "1").contains(empRepSuperLastName));
		
		log.info("Report-to 11 - Step 09: Verify 'Reporting Method' value is updated successfully in 'Supervisors' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Assigned Supervisors", "Reporting Method", "1"), empRepSuperSpecify);
		
		log.info("Report-to 11 - Step 10: Click to 'Add' button at 'Subordinates' form");
		employeeListPage.clickToButtonByID(driver, "btnAddSubordinateDetail");
		
		log.info("Report-to 11 - Step 11: Enter to 'Name' textbox with value is: '" + empRepSuborFirstName + "'");
		employeeListPage.enterToTextboxByID(driver, empRepSuborFullName, "reportto_subordinateName_empName");
		
		log.info("Report-to 11 - Step 12: Select item in 'Reporting Method' dropdown with value is: '" + empRepSuborMethod + "'");
		employeeListPage.selectItemInDropdownByID(driver, empRepSuborMethod, "reportto_reportingMethodType");
		
		log.info("Report-to 11 - Step 13: Enter to 'Please Specify' textbox with value is: '" + empRepSuborSpecify + "'");
		employeeListPage.enterToTextboxByID(driver, empRepSuborSpecify, "reportto_reportingMethod");
		
		log.info("Report-to 11 - Step 14: Click to 'Save' button at 'Subordinates' form");
		employeeListPage.clickToButtonByID(driver, "btnSaveReportTo");
		
		log.info("Report-to 11 - Step 15: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		log.info("Report-to 11 - Step 16: Verify 'Name' value is updated successfully in 'Subordinates' data table");
		verifyTrue(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Assigned Subordinates", "Name", "1").contains(empRepSuborLastName));
		
		log.info("Report-to 11 - Step 17: Verify 'Reporting Method' value is updated successfully in 'Subordinates' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Assigned Subordinates", "Reporting Method", "1"), empRepSuborSpecify);
	}
	
	@Test
	public void Employee_12_Verify_Employee() {
		log.info("Verification 12 - Step 01: Open 'Employee List' page");
		employeeListPage.openSubMenuPage(driver, "PIM", "Employee List");
		
		log.info("Verification 12 - Step 02: Enter to 'Employee Name' textbox with value is: '" + editEmpFullName + "'");
		employeeListPage.sleepInSecond(3);
		employeeListPage.enterToTextboxByID(driver, editEmpFullName, "empsearch_employee_name_empName");

		log.info("Verification 12 - Step 03: Click to 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		employeeListPage.waitForEmployeeNameTextboxLoadSuccessfully();
		
		log.info("Verification 12 - Step 04: Verify Employee Information displayed at 'Result Table'");
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"), employeeID);
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1"), editEmpFirstName);
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), editEmpLastName);
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Job Title", "1"), empJobTitle);
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Employment Status", "1"), empJobStatus);
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Sub Unit", "1"), empJobUnit.trim());
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Supervisor", "1"), empRepSuperFullName);
	}

	@Parameters("browser")
	@AfterClass(alwaysRun = true)
	public void afterClass(String browserName) {
		log.info("Post-Condition: Close Browser '" + browserName + "'");
		closeBrowserAndDriver();
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999);
	}

	private WebDriver driver;

	String employeeFirstName, employeeLastName, adminUserName, adminPassword, empUserName, empPassword, statusValue, employeeFullName, employeeID, 
			
			fileName, 
			
			editEmpFirstName, editEmpLastName, editEmpFullName, editEmpGender, editEmpMaritalStatus, editEmpNationality, 
			
			empFirstAddress, empSecondAddress, empCity, empState, empZipCode, empCountry, empMobile, empWorkEmail, 
			
			empEmergName, empEmergRelp, empEmergHomePhone, empEmergMobile, empEmergWorkPhone, 
			
			empDepName, empDepRelp, empDepDOB, 
			
			empJobTitle, empJobStatus, empJobCategory, empJobJoined, empJobUnit, empJobLocation, empJobStartDate, empJobEndDate,
			
			empSalaryGrade, empSalaryComponent, empSalaryFrequency, empSalaryCurrency, empSalaryAmount, 
			empSalaryAccNumb, empSalaryAccType, empSalaryRoutNum, empSalaryAccAmount,
			
			empTaxFedStatus, empTaxFedExemption, empTaxState, empTaxStateStatus, empTaxStateExemption, empTaxStateUnemp, empTaxStateWork,
			
			empQualExpCompany, empQualExpJobTitle, empQualExpFrom, empQualExpTo, 
			empQualEduLevel, empQualEduInst, empQualEduMajor, empQualEduYear, empQualEduGPA, empQualEduStart, empQualEduEnd,
			empQualSkill, empQualSkillYear,
			empQualLang, empQualLangFluency, empQualLangCompetency,
			empQualLicType, empQualLicNumber, empQualLicIssued, empQualLicExpiry,
			
			empRepSuperFirstName, empRepSuperLastName, empRepSuperFullName, empRepSuperMethod, empRepSuperSpecify, empRepSuborFirstName, empRepSuborLastName, empRepSuborFullName, empRepSuborMethod, empRepSuborSpecify;

	AddEmployeePO addEmployeePage;
	DashboardPO dashboardPage;
	EmployeeListPO employeeListPage;
	LoginPO loginPage;
	MyInfoPO myInfoPage;

}
