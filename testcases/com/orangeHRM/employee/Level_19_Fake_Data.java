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

public class Level_19_Fake_Data extends BaseTest {

	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appURL, Method method) {
		driver = getBrowserDriver(browserName, appURL);
//		ExtentTestManagerV5.startTest(method.getName(), "Pre-Condition");
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Pre-Condition - Step 01: Open Browser '" + browserName + "' and navigate to '" + appURL + "'");

		faker = DataUtil.getData();
		
		employeeFirstName = faker.getFirstName();
		employeeLastName = faker.getLastName();
		employeeFullName = employeeFirstName + " " + employeeLastName;
		adminUserName = "Admin";
		adminPassword = "admin123";
		empUserName = faker.getUserName();
		empPassword = faker.getPassword();
		statusValue = "Enabled";
		
		fileName = "cat.jpg";
		editEmpFirstName = faker.getFirstName();
		editEmpLastName = faker.getLastName();
		editEmpFullName = editEmpFirstName + " " + editEmpLastName;
		editEmpGender = faker.getGender();
		editEmpMaritalStatus = "Married";
		editEmpNationality = "Vietnamese";
		
		empFirstAddress = faker.getAddress();
		empSecondAddress = faker.getAddress();
		empCity = faker.getCity(); 
		empState = faker.getState(); 
		empZipCode = faker.getPostalCode();
		empCountry = "Viet Nam";
		empMobile = faker.getRandomNumber(100000000,999999999); 
		empWorkEmail = faker.getEmailAddress();
		
		empEmergName = faker.getFullName(); 
		empEmergRelp = "Brother"; 
		empEmergHomePhone = faker.getRandomNumber(100000000,999999999);  
		empEmergMobile = faker.getRandomNumber(100000000,999999999);  
		empEmergWorkPhone = faker.getRandomNumber(100000000,999999999); 
		
		empDepName = faker.getFullName();
		empDepRelp = "Child"; 
		empDepDOB = faker.getBirthDate();
		
		empJobTitle = "Software Architect"; 
		empJobStatus = "Full-Time Contract"; 
		empJobCategory = "Professionals"; 
		empJobJoined = faker.getPastDate();
		empJobUnit = "  Quality Assurance";
		empJobLocation = "Texas R&D"; 
		empJobStartDate = faker.getPastDate(); 
		empJobEndDate = faker.getFutureDate();
		
		empSalaryGrade = "Grade 2"; 
		empSalaryComponent = "Basic"; 
		empSalaryFrequency = "Monthly on first pay of month."; 
		empSalaryCurrency = "United States Dollar"; 
		empSalaryAmount = faker.getRandomNumber(40000,50000); 
		empSalaryAccNumb = faker.getRandomNumber(100000000,999999999); 
		empSalaryAccType = "Savings"; 
		empSalaryRoutNum = faker.getRandomNumber(100000000,999999999);  
		empSalaryAccAmount = faker.getRandomNumber(100000000,999999999); 
		
		empTaxFedStatus = "Married"; 
		empTaxFedExemption = faker.getRandomNumber(10,99);  
		empTaxState = "Ohio"; 
		empTaxStateStatus = "Non Resident Alien"; 
		empTaxStateExemption = faker.getRandomNumber(10,99); 
		empTaxStateUnemp = "Florida"; 
		empTaxStateWork = "Washington";
		
		empQualExpCompany = "Automation Company"; 
		empQualExpJobTitle = "QC Engineer"; 
		empQualExpFrom = faker.getPastDate();
		empQualExpTo = faker.getFutureDate(); 
		empQualEduLevel = "Master's Degree"; 
		empQualEduInst = "Banking Institute"; 
		empQualEduMajor = "Management Information System"; 
		empQualEduYear = faker.getRandomDigit();
		empQualEduGPA = faker.getRandomDouble(1,1,9); 
		empQualEduStart = faker.getPastDate();
		empQualEduEnd = faker.getFutureDate();
		empQualSkill = "Java"; 
		empQualSkillYear = faker.getRandomDigit();
		empQualLang = "Russian"; 
		empQualLangFluency = "Speaking"; 
		empQualLangCompetency = "Good";
		empQualLicType = "Cisco Certified Network Professional (CCNP)"; 
		empQualLicNumber = faker.getRandomNumber(100000000,999999999); 
		empQualLicIssued = faker.getPastDate(); 
		empQualLicExpiry = faker.getFutureDate();
		
		empRepSuperMethod = "Other";
		empRepSuborMethod = "Other";
		empRepSuperSpecify = "Email " + faker.getRandomNumber(1,999);
		empRepSuborSpecify = "Skype " + faker.getRandomNumber(1,999);
		
		loginPage = HRMPageGeneratorManager.getLoginPage(driver);
		
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Pre-Condition - Step 02: Login with Admin Role");
		dashboardPage = loginPage.loginToSystem(driver, adminUserName, adminPassword);
	}

	@Test
	public void Employee_01_Add_New_Employee(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_01_Add_New_Employee");	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 01: Open 'Employee List' page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = HRMPageGeneratorManager.getEmployeeListPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 02: Get the name of Supervisor");
		empRepSuperFirstName = employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "5");
		empRepSuperLastName = employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "5");
		empRepSuperFullName = empRepSuperFirstName + " " + empRepSuperLastName;
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Add New 01 - Step 03: Get the name of Subordinate");
		empRepSuborFirstName = employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "6");
		empRepSuborLastName = employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "6");
		empRepSuborFullName = empRepSuborFirstName + " " + empRepSuborLastName;
		
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

	@Test
	public void Employee_02_Upload_Avatar(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_02_Upload_Avatar");	
		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload Avatar 02 - Step 01: Login with Employee Role");
		loginPage = employeeListPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, empUserName, empPassword);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload Avatar 02 - Step 02: Click to 'My Info' Menu");
		dashboardPage.openMenuPage(driver, "My Info");
		myInfoPage = HRMPageGeneratorManager.getMyInfoPage(driver);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload Avatar 02 - Step 03: Click to Change Photo image");
		myInfoPage.clickToChangePhotoImage();

		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload Avatar 02 - Step 04: Upload new avatar");
		myInfoPage.uploadMultipleFile(driver, fileName);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload Avatar 02 - Step 05: Click to Upload button");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload Avatar 02 - Step 06: Verify Success Message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Uploaded"));

		ExtentTestManagerV5.getTest().log(Status.INFO, "Upload Avatar 02 - Step 07: Verify new Avatar is displayed");
		verifyTrue(myInfoPage.isNewAvatarImageDisplayed());
	}

	@Test
	public void Employee_03_Personal_Details(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_03_Personal_Details");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 01: Open 'Personal Details' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Personal Details");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 02: Verify all fields at 'Personal Details' form are disabled");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtEmpFirstName"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtEmpLastName"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtEmployeeId"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtLicenNo"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtNICNo"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtSINNo"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_optGender_1"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_cmbMarital"));
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_DOB"));

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 03: Click To 'Edit' button at 'Personal Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 04: Verify 'Employee ID' textbox is still disabled");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtEmployeeId"));

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 05: Verify 'Driver's License Number' textbox is still disabled");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtLicenNo"));

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 06: Verify 'SSN Number' textbox is still disabled");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtNICNo"));

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 07: Verify 'SIN Number' textbox is still disabled");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_txtSINNo"));

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 08: Verify 'Date of Birth' textbox is still disabled");
		verifyFalse(myInfoPage.isFieldEnableByID(driver, "personal_DOB"));

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 09: Enter new value to 'First Name' textbox with values is: '" + editEmpFirstName + "'");
		myInfoPage.enterToTextboxByID(driver, editEmpFirstName, "personal_txtEmpFirstName");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 10: Enter new value to 'Last Name' textbox with value is: '" + editEmpLastName + "'");
		myInfoPage.enterToTextboxByID(driver, editEmpLastName, "personal_txtEmpLastName");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 11: Select new value to 'Gender' radio button with value is: '" + editEmpGender + "'");
		myInfoPage.clickToRadioButtonByLabel(driver, editEmpGender);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 12: Select new value to 'Marital Status' dropdown with value is: '" + editEmpMaritalStatus + "'");
		myInfoPage.selectItemInDropdownByID(driver, editEmpMaritalStatus, "personal_cmbMarital");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 13: Select new value to 'Nationality' dropdown with value is: '" + editEmpNationality + "'");
		myInfoPage.selectItemInDropdownByID(driver, editEmpNationality, "personal_cmbNation");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 14: Click to 'Save' button at 'Personal Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 15: Verify Success Message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 16: Verify 'First Name' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpFirstName"), editEmpFirstName);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 17: Verify 'Last Name' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmpLastName"), editEmpLastName);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 18: Verify 'Gender' radio button is updated successfully");
		verifyTrue(myInfoPage.isRadioButtonIsSelectedByLabel(driver, editEmpGender));

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 19: Verify 'Marital Status' dropdown is updated successfully");
		verifyEquals(myInfoPage.getSelectedItemInDropdownByID(driver, "personal_cmbMarital"), editEmpMaritalStatus);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 20: Verify 'Nationality' dropdown is updated successfully");
		verifyEquals(myInfoPage.getSelectedItemInDropdownByID(driver, "personal_cmbNation"), editEmpNationality);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Personal Details 03 - Step 21: Verify 'Employee ID' textbox value is correct");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "personal_txtEmployeeId"), employeeID);

	}

	@Test
	public void Employee_04_Contact_Details(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_04_Contact_Details");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 01: Open 'Contact Details' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Contact Details");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 02: Verify all fields at 'Contact Details' form are disabled");
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

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 03: Click to 'Edit' button at 'Contact Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 04: Enter to 'Address Street 1' textbox with value is: '" + empFirstAddress + "'");
		myInfoPage.enterToTextboxByID(driver, empFirstAddress, "contact_street1");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 05: Enter to 'Address Street 2' textbox with value is: '" + empSecondAddress + "'");
		myInfoPage.enterToTextboxByID(driver, empSecondAddress, "contact_street2");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 06: Enter to 'City' textbox with value is: '" + empCity + "'");
		myInfoPage.enterToTextboxByID(driver, empCity, "contact_city");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 07: Enter to 'State/Province' textbox with value is: '" + empState + "'");
		myInfoPage.enterToTextboxByID(driver, empState, "contact_province");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 08: Enter to 'Zip/Postal Code' textbox with value is: '" + empZipCode + "'");
		myInfoPage.enterToTextboxByID(driver, empZipCode, "contact_emp_zipcode");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 09: Select item in 'Country' dropdown with value is: '" + empCountry + "'");
		myInfoPage.selectItemInDropdownByID(driver, empCountry, "contact_country");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 10: Enter to 'Mobile' textbox with value is: '" + empMobile + "'");
		myInfoPage.enterToTextboxByID(driver, empMobile, "contact_emp_mobile");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 11: Enter to 'Work Email' textbox with value is: '" + empWorkEmail + "'");
		myInfoPage.enterToTextboxByID(driver, empWorkEmail, "contact_emp_work_email");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 12: Click to 'Save' button at 'Contact Details' form");
		myInfoPage.clickToButtonByID(driver, "btnSave");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 13: Verify Success Message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 14: Verify 'Address Street 1' texbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_street1"), empFirstAddress);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 15: Verify 'Address Street 2' texbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_street2"), empSecondAddress);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 16: Verify 'City' texbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_city"), empCity);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 17: Verify 'State/Province' texbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_province"), empState);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 18: Verify 'Zip/Postal Code' texbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_zipcode"), empZipCode);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 19: Verify 'Country' dropdown is updated successfully");
		verifyEquals(myInfoPage.getSelectedItemInDropdownByID(driver, "contact_country"), empCountry);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 20: Verify 'Mobile' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_mobile"), empMobile);

		ExtentTestManagerV5.getTest().log(Status.INFO, "Contact Details 04 - Step 21: Verify 'Work Email' textbox is updated successfully");
		verifyEquals(myInfoPage.getTextboxValueByID(driver, "contact_emp_work_email"), empWorkEmail);
	}

	@Test
	public void Employee_05_Emergency_Contacts(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_05_Emergency_Contacts");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step 01: Open 'Emergency Contacts' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Emergency Contacts");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step 02: Click to 'Add' button at 'Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnAddContact");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step 03: Enter to 'Name' textbox with value is: '" + empEmergName + "'");
		myInfoPage.enterToTextboxByID(driver, empEmergName, "emgcontacts_name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step 04: Enter to 'Relationship' textbox with value is: '" + empEmergRelp + "'");
		myInfoPage.enterToTextboxByID(driver, empEmergRelp, "emgcontacts_relationship");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step 05: Enter to 'Home Telephone' textbox with value is: '" + empEmergHomePhone + "'");
		myInfoPage.enterToTextboxByID(driver, empEmergHomePhone, "emgcontacts_homePhone");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step 06: Enter to 'Mobile' textbox with value is: '" + empEmergMobile + "'");
		myInfoPage.enterToTextboxByID(driver, empEmergMobile, "emgcontacts_mobilePhone");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step 07: Enter to 'Work Telephone' textbox with value is: '" + empEmergWorkPhone + "'");
		myInfoPage.enterToTextboxByID(driver, empEmergWorkPhone, "emgcontacts_workPhone");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step 08: Click to 'Save' button at 'Emergency Contacts' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveEContact");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step 09: Verify Success Message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step 10: Verify 'Name' value is updated successfully in data table");
		verifyEquals(myInfoPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Name", "1"), empEmergName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step 11: Verify 'Relationship' value is updated successfully in data table");
		verifyEquals(myInfoPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Relationship", "1"), empEmergRelp);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step 12: Verify 'Home Telephone' value is updated successfully in data table");
		verifyEquals(myInfoPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Home Telephone", "1"), empEmergHomePhone);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step 13: Verify 'Mobile' value is updated successfully in data table");
		verifyEquals(myInfoPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Mobile", "1"), empEmergMobile);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Emergency Contacts 05 - Step 14: Verify 'Work Telephone' value is updated successfully in data table");
		verifyEquals(myInfoPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "emgcontact_list", "Work Telephone", "1"), empEmergWorkPhone);
	}

	@Test
	public void Employee_06_Dependents(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_06_Dependents");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step 01: Open 'Dependents' tab at Side bar");
		myInfoPage.openTabAtSideBarByName("Dependents");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step 02: Click to 'Add' button at 'Dependent' form");
		myInfoPage.clickToButtonByID(driver, "btnAddDependent");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step 03: Enter to 'Name' textbox with value is: '" + empDepName + "'");
		myInfoPage.enterToTextboxByID(driver, empDepName, "dependent_name");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step 04: Select item in 'Relationship' dropdown with value is: '" + empDepRelp + "'");
		myInfoPage.selectItemInDropdownByID(driver, empDepRelp, "dependent_relationshipType");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step 05: Enter to 'Date of Birth' datepicker with value is: '" + empDepDOB + "'");
		myInfoPage.enterToTextboxByID(driver, empDepDOB, "dependent_dateOfBirth");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step 06: Click to 'Save' button at 'Dependent' form");
		myInfoPage.clickToButtonByID(driver, "btnSaveDependent");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step 07: Verify Success Message is displayed");
		verifyTrue(myInfoPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step 08: Verify 'Name' value is updated successfully in data table");
		verifyEquals(myInfoPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Name", "1"), empDepName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step 09: Verify 'Relationship' value is updated successfully in data table");
		verifyEquals(myInfoPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Relationship", "1"), empDepRelp.toLowerCase());
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Dependents 06 - Step 10: Verify 'Date of Birth' value is updated successfully in data table");
		verifyEquals(myInfoPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "dependent_list", "Date of Birth", "1"), empDepDOB);
	}

	@Test
	public void Employee_07_Edit_View_Job(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_07_Edit_View_Job");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 01: Login with Admin role");
		loginPage= myInfoPage.logoutToSystem(driver);
		dashboardPage = loginPage.loginToSystem(driver, adminUserName, adminPassword);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 02: Open 'Employee List' page");
		dashboardPage.openSubMenuPage(driver, "PIM", "Employee List");
		employeeListPage = HRMPageGeneratorManager.getEmployeeListPage(driver);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 03: Enter to 'Employee Name' textbox with value is: '" + editEmpFullName + "'");
		employeeListPage.sleepInSecond(3);
		employeeListPage.enterToTextboxByID(driver, editEmpFullName, "empsearch_employee_name_empName");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 04: Click to 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		employeeListPage.waitForEmployeeNameTextboxLoadSuccessfully();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 05: Verify Employee Information displayed at 'Result Table'");
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Id", "1"), employeeID);
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "First (& Middle) Name", "1"), editEmpFirstName);
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable", "Last Name", "1"), editEmpLastName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 06: Click to 'ID' Link in data table");
		employeeListPage.clickToLinkInDataTableIDAtColumnNameAndRowIndex(driver, "resultTable" , "Id", "1");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 07: Open 'Job' tab at Side bar");
		employeeListPage.openTabAtSideBarByName("Job");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 08: Verify all fields at 'Job' form are disabled");
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "job_job_title"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "job_emp_status"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "job_eeo_category"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "job_joined_date"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "job_sub_unit"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "job_location"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "job_contract_start_date"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "job_contract_end_date"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 09: Click to 'Edit' button at 'Job' form");
		employeeListPage.clickToButtonByID(driver, "btnSave");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 10: Select item in 'Job Title' dropdown with value is: '" + empJobTitle + "'");
		employeeListPage.selectItemInDropdownByID(driver, empJobTitle, "job_job_title");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 11: Select item in 'Employment Status' dropdown with value is: '" + empJobStatus + "'");
		employeeListPage.selectItemInDropdownByID(driver, empJobStatus, "job_emp_status");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 12: Enter to 'Joined Date' textbox with value is: '" + empJobJoined + "'");
		employeeListPage.enterToTextboxByID(driver, empJobJoined, "job_joined_date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 13: Select item in 'Job Category' dropdown with value is: '" + empJobCategory + "'");
		employeeListPage.selectItemInDropdownByID(driver, empJobCategory, "job_eeo_category");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 14: Select item in 'Sub Unit' dropdown with value is: '" + empJobUnit.trim() + "'");
		employeeListPage.selectItemInDropdownByID(driver, empJobUnit, "job_sub_unit");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 15: Select item in 'Location' dropdown with value is: '" + empJobLocation + "'");
		employeeListPage.selectItemInDropdownByID(driver, empJobLocation, "job_location");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 16: Enter to 'Start Date' textbox with value is: '" + empJobStartDate + "'");
		employeeListPage.enterToTextboxByID(driver, empJobStartDate, "job_contract_start_date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 17: Enter to 'End Date' textbox with value is: '" + empJobEndDate + "'");
		employeeListPage.enterToTextboxByID(driver, empJobEndDate, "job_contract_end_date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 18: Upload a file to 'Contract Details' textbox with file name is: '" + fileName + "'");
		employeeListPage.uploadMultipleFile(driver, fileName);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 19: Click to 'Save' button at 'Job' form");
		employeeListPage.clickToButtonByID(driver, "btnSave");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 21: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Updated"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 22: Verify 'Job Title' dropdown is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "job_job_title"), empJobTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 23: Verify 'Employee Status' dropdown is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "job_emp_status"), empJobStatus);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 24: Verify 'Job Category' dropdown is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "job_eeo_category"), empJobCategory);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 25: Verify 'Joined Date' datepicker is updated successfully");
		verifyEquals(employeeListPage.getTextboxValueByID(driver, "job_joined_date"), empJobJoined);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 26: Verify 'Sub Unit' dropdown is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "job_sub_unit"), empJobUnit);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 27: Verify 'Location' dropdown is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "job_location"), empJobLocation);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 28: Verify 'Start Date' datepicker is updated successfully");
		verifyEquals(employeeListPage.getTextboxValueByID(driver, "job_contract_start_date"), empJobStartDate);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Job 07 - Step 29: Verify 'End Date' datepicker is updated successfully");
		verifyEquals(employeeListPage.getTextboxValueByID(driver, "job_contract_end_date"), empJobEndDate);
	}

	@Test
	public void Employee_08_Edit_View_Salary(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_08_Edit_View_Salary");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 01: Open 'Salary' tab at Side bar");
		employeeListPage.openTabAtSideBarByName("Salary");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 02: Click to 'Add' button at 'Salary' form");
		employeeListPage.clickToButtonByID(driver, "addSalary");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 03: Select item in 'Pay Grade' dropdown with value is: '" + empSalaryGrade + "'");
		employeeListPage.selectItemInDropdownByID(driver, empSalaryGrade, "salary_sal_grd_code");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 04: Enter to 'Salary Component' textbox with value is: '" + empSalaryComponent + "'");
		employeeListPage.enterToTextboxByID(driver, empSalaryComponent, "salary_salary_component");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 05: Select item in 'Pay Frequency' dropdown with value is: '" + empSalaryFrequency + "'");
		employeeListPage.selectItemInDropdownByID(driver, empSalaryFrequency, "salary_payperiod_code");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 06: Select item in 'Currency' dropdown with value is: '" + empSalaryCurrency + "'");
		employeeListPage.selectItemInDropdownByID(driver, empSalaryCurrency, "salary_currency_id");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 07: Enter to 'Amount' textbox with value is: '" + empSalaryAmount + "'");
		employeeListPage.enterToTextboxByID(driver, empSalaryAmount, "salary_basic_salary");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 08: Check to 'Add Direct Deposit Details' checkbox");
		employeeListPage.clickToCheckboxByLabel(driver, "Add Direct Deposit Details");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 09: Enter to 'Account Number' textbox with value is: '" + empSalaryAccNumb + "'");
		employeeListPage.enterToTextboxByID(driver, empSalaryAccNumb, "directdeposit_account");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 10: Select item in 'Account Type' dropdown with value is: '" + empSalaryAccType + "'");
		employeeListPage.selectItemInDropdownByID(driver, empSalaryAccType, "directdeposit_account_type");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 11: Enter to 'Routing Number' textbox with value is: '" + empSalaryRoutNum + "'");
		employeeListPage.enterToTextboxByID(driver, empSalaryRoutNum, "directdeposit_routing_num");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 12: Enter to 'Amount' textbox with value is: '" + empSalaryAccAmount + "'");
		employeeListPage.enterToTextboxByID(driver, empSalaryAccAmount, "directdeposit_amount");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 13: Click to 'Save' button at 'Salary' form");
		employeeListPage.clickToButtonByID(driver, "btnSalarySave");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 14: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 15: Verify 'Salary Component' value is updated successfully in data table");
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "tblSalary", "Salary Component", "1"), empSalaryComponent);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 16: Verify 'Pay Frequency' value is updated successfully in data table");
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "tblSalary", "Pay Frequency", "1"), empSalaryFrequency);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 17: Verify 'Currency' value is updated successfully in data table");
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "tblSalary", "Currency", "1"), empSalaryCurrency);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 18: Verify 'Amount' value is updated successfully in data table");
		verifyEquals(employeeListPage.getValueInDataTableIDAtColumnNameAndRowIndex(driver, "tblSalary", "Amount", "1"), empSalaryAmount);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 18: Click to 'Show Direct Deposit Details' checkbox in data table");
		employeeListPage.checkToCheckBoxInDataTableIDAtColumnNameAndRowIndex(driver, "tblSalary", "Show Direct Deposit Details", "1");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Salary 08 - Step 19: Verify 'Account Number', 'Account Type', 'Routing Number', 'Amount' value is updated successfully in data table");
		verifyTrue(employeeListPage.areValuesInDepositDataTableUpdatedSuccessfully(empSalaryAccNumb, empSalaryAccType,empSalaryRoutNum, empSalaryAccAmount + ".00"));

	}

	@Test
	public void Employee_09_Edit_View_Tax(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_09_Edit_View_Tax");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 01: Open 'Tax Exemptions' tab at Side bar");
		employeeListPage.openTabAtSideBarByName("Tax Exemptions");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 02: Verify all fields at 'Tax' form are disabled");
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "tax_federalStatus"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "tax_federalExemptions"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "tax_state"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "tax_stateStatus"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "tax_stateExemptions"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "tax_unempState"));
		verifyFalse(employeeListPage.isFieldEnableByID(driver, "tax_workState"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 03: Click to 'Edit' button at 'Tax' form");
		employeeListPage.clickToButtonByID(driver, "btnSave");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 04: Select item in 'Federal Income Tax - Status' dropdown with values is: '" + empTaxFedStatus + "'");
		employeeListPage.selectItemInDropdownByID(driver, empTaxFedStatus, "tax_federalStatus");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 05: Enter to 'Federal Income Tax - Exemptions' textbox with values is: '" + empTaxFedExemption + "'");
		employeeListPage.enterToTextboxByID(driver, empTaxFedExemption, "tax_federalExemptions");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 06: Select item in 'State Income Tax - State' dropdown with values is: '" + empTaxState + "'");
		employeeListPage.selectItemInDropdownByID(driver, empTaxState, "tax_state");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 07: Select item in 'State Income Tax - Status' dropdown with values is: '" + empTaxStateStatus + "'");
		employeeListPage.selectItemInDropdownByID(driver, empTaxStateStatus, "tax_stateStatus");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 08: Enter to 'State Income Tax - Exemptions' textbox with values is: '" + empTaxStateExemption + "'");
		employeeListPage.enterToTextboxByID(driver, empTaxStateExemption, "tax_stateExemptions");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 09: Select item in 'State Income Tax - Unemployment State' dropdown with values is: '" + empTaxStateUnemp + "'");
		employeeListPage.selectItemInDropdownByID(driver, empTaxStateUnemp, "tax_unempState");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 10: Select item in 'State Income Tax - Work State' dropdown with values is: '" + empTaxStateWork + "'");
		employeeListPage.selectItemInDropdownByID(driver, empTaxStateWork, "tax_workState");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 11: Click to 'Save' button at 'Tax' form");
		employeeListPage.clickToButtonByID(driver, "btnSave");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 12: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 13: Verify 'Federal Income Tax - Status' value is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "tax_federalStatus"), empTaxFedStatus);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 14: Verify 'Federal Income Tax - Exemptions' value is updated successfully");
		verifyEquals(employeeListPage.getTextboxValueByID(driver, "tax_federalExemptions"), empTaxFedExemption);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 15: Verify 'State Income Tax - State' value is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "tax_state"), empTaxState);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 16: Verify 'State Income Tax - Status' value is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "tax_stateStatus"), empTaxStateStatus);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 17: Verify 'State Income Tax - Exemptions' value is updated successfully");
		verifyEquals(employeeListPage.getTextboxValueByID(driver, "tax_stateExemptions"), empTaxStateExemption);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 18: Verify 'State Income Tax - Unemployment State' value is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "tax_unempState"), empTaxStateUnemp);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Tax 09 - Step 19: Verify 'State Income Tax - Work State' value is updated successfully");
		verifyEquals(employeeListPage.getSelectedItemInDropdownByID(driver, "tax_workState"), empTaxStateWork);
	}
	
	@Test
	public void Employee_10_Qualifications(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_10_Qualifications");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 01: Open 'Qualifications' tab at Side bar");
		employeeListPage.openTabAtSideBarByName("Qualifications");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 02: Click to 'Add' button at 'Work Experience' form");
		employeeListPage.clickToButtonByID(driver, "addWorkExperience");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 03: Enter to 'Company' textbox with value is: '" + empQualExpCompany + "'");
		employeeListPage.enterToTextboxByID(driver, empQualExpCompany, "experience_employer");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 04: Enter to 'Job Title' textbox with value is: '" + empQualExpJobTitle + "'");
		employeeListPage.enterToTextboxByID(driver, empQualExpJobTitle, "experience_jobtitle");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 05: Enter to 'From' datepicker with value is: '" + empQualExpFrom + "'");
		employeeListPage.enterToTextboxByID(driver, empQualExpFrom, "experience_from_date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 06: Enter to 'To' datepicker with value is: '" + empQualExpTo + "'");
		employeeListPage.enterToTextboxByID(driver, empQualExpTo, "experience_to_date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 07: Click to 'Save' button at 'Work Experience' form");
		employeeListPage.clickToButtonByID(driver, "btnWorkExpSave");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 08: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 09: Verify 'Company' value is updated successfully in 'Work Expericence' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Work Experience", "Company", "1"), empQualExpCompany);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 10: Verify 'Job Title' value is updated successfully in 'Work Expericence' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Work Experience", "Job Title", "1"), empQualExpJobTitle);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 11: Verify 'From' value is updated successfully in 'Work Expericence' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Work Experience", "From", "1"), empQualExpFrom);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 12: Verify 'To' value is updated successfully in 'Work Expericence' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Work Experience", "To", "1"), empQualExpTo);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 13: Click to 'Add' button at 'Education' form");
		employeeListPage.clickToButtonByID(driver, "addEducation");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 14: Select item in 'Level' dropdown with value is: '" + empQualEduLevel + "'");
		employeeListPage.selectItemInDropdownByID(driver, empQualEduLevel, "education_code");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 15: Enter to 'Institute' textbox with value is: '" + empQualEduInst + "'");
		employeeListPage.enterToTextboxByID(driver, empQualEduInst, "education_institute");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 16: Enter to 'Major/Specialization' textbox with value is: '" + empQualEduMajor + "'");
		employeeListPage.enterToTextboxByID(driver, empQualEduMajor, "education_major");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 17: Enter to 'Year' textbox with value is: '" + empQualEduYear + "'");
		employeeListPage.enterToTextboxByID(driver, empQualEduYear, "education_year");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 18: Enter to 'GPA/Score' textbox with value is: '" + empQualEduGPA + "'");
		employeeListPage.enterToTextboxByID(driver, empQualEduGPA, "education_gpa");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 19: Enter to 'Start Date' datepicker with value is: '" + empQualEduStart + "'");
		employeeListPage.enterToTextboxByID(driver, empQualEduStart, "education_start_date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 20: Enter to 'End Date' datepicker with value is: '" + empQualEduEnd + "'");
		employeeListPage.enterToTextboxByID(driver, empQualEduEnd, "education_end_date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 21: Click to 'Save' button at 'Education' form");
		employeeListPage.clickToButtonByID(driver, "btnEducationSave");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 22: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 23: Verify 'Level' value is updated successfully in 'Education' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Education", "Level", "1"), empQualEduLevel);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 24: Verify 'Year' value is updated successfully in 'Education' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Education", "Year", "1"), empQualEduYear);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 25: Verify 'GPA/Score' value is updated successfully in 'Education' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Education", "GPA/Score", "1"), empQualEduGPA);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 26: Click to 'Add' button at 'Skills' form");
		employeeListPage.clickToButtonByID(driver, "addSkill");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 27: Select item in 'Skill' dropdown with value is: '" + empQualSkill + "'");
		employeeListPage.selectItemInDropdownByID(driver, empQualSkill, "skill_code");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 28: Enter to 'Year of Experience' textbox with value is: '" + empQualSkillYear + "'");
		employeeListPage.enterToTextboxByID(driver, empQualSkillYear, "skill_years_of_exp");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 29: Click to 'Save' button at 'Skills' form");
		employeeListPage.clickToButtonByID(driver, "btnSkillSave");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 30: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 31: Verify 'Skill' value is updated successfully in 'Skills' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Skills", "Skill", "1"), empQualSkill);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 32: Verify 'Years of Experience' value is updated successfully in 'Skills' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Skills", "Years of Experience", "1"), empQualSkillYear);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 33: Click to 'Add' button at 'Languages' form");
		employeeListPage.clickToButtonByID(driver, "addLanguage");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 34: Select item in 'Language' dropdown with value is: '" + empQualLang + "'");
		employeeListPage.selectItemInDropdownByID(driver, empQualLang, "language_code");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 35: Select item in 'Fluency' dropdown with value is: '" + empQualLangFluency + "'");
		employeeListPage.selectItemInDropdownByID(driver, empQualLangFluency, "language_lang_type");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 36: Select item in 'Competency' dropdown with value is: '" + empQualLangCompetency + "'");
		employeeListPage.selectItemInDropdownByID(driver, empQualLangCompetency, "language_competency");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 37: Click to 'Save' button at 'Languages' form");
		employeeListPage.clickToButtonByID(driver, "btnLanguageSave");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 38: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 39: Verify 'Language' value is updated successfully in 'Languages' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Languages", "Language", "1"), empQualLang);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 40: Verify 'Fluency' value is updated successfully in 'Languages' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Languages", "Fluency", "1"), empQualLangFluency);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 41: Verify 'Competency' value is updated successfully in 'Languages' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Languages", "Competency", "1"), empQualLangCompetency);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 42: Click to 'Add' button at 'License' form");
		employeeListPage.clickToButtonByID(driver, "addLicense");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 43: Select item in 'License Type' dropdown with value is: '" + empQualLicType + "'");
		employeeListPage.selectItemInDropdownByID(driver, empQualLicType, "license_code");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 44: Enter to 'License Number' textbox with value is: '" + empQualLicNumber + "'");
		employeeListPage.enterToTextboxByID(driver, empQualLicNumber, "license_license_no");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 45: Enter to 'Issued Date' datepicker with value is: '" + empQualLicIssued + "'");
		employeeListPage.enterToTextboxByID(driver, empQualLicIssued, "license_date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 46: Enter to 'Expiry Date' datepicker with value is: '" + empQualLicExpiry + "'");
		employeeListPage.enterToTextboxByID(driver, empQualLicExpiry, "license_renewal_date");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 47: Click to 'Save' button at 'License' form");
		employeeListPage.clickToButtonByID(driver, "btnLicenseSave");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 48: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 49: Verify 'License Type' value is updated successfully in 'License' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("License", "License Type", "1"), empQualLicType);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 50: Verify 'Issued Date' value is updated successfully in 'License' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("License", "Issued Date", "1"), empQualLicIssued);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Qualifications 10 - Step 51: Verify 'Expiry Date' value is updated successfully in 'License' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("License", "Expiry Date", "1"), empQualLicExpiry);
	}

	@Test
	public void Employee_11_Report_To(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_11_Report_To");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step 01: Open 'Report-to' tab at Side bar");
		employeeListPage.openTabAtSideBarByName("Report-to");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step 02: Click to 'Add' button at 'Supervisors' form");
		employeeListPage.clickToButtonByID(driver, "btnAddSupervisorDetail");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step 03: Enter to 'Name' textbox with value is: '" + empRepSuperFirstName + "'");
		employeeListPage.enterToTextboxByID(driver, empRepSuperFullName, "reportto_supervisorName_empName");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step 04: Select item in 'Reporting Method' dropdown with value is: '" + empRepSuperMethod + "'");
		employeeListPage.selectItemInDropdownByID(driver, empRepSuperMethod, "reportto_reportingMethodType");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step 05: Enter to 'Please Specify' textbox with value is: '" + empRepSuperSpecify + "'");
		employeeListPage.enterToTextboxByID(driver, empRepSuperSpecify, "reportto_reportingMethod");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step 06: Click to 'Save' button at 'Supervisors' form");
		employeeListPage.clickToButtonByID(driver, "btnSaveReportTo");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step 07: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step 08: Verify 'Name' value is updated successfully in 'Supervisors' data table");
		verifyTrue(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Assigned Supervisors", "Name", "1").contains(empRepSuperLastName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step 09: Verify 'Reporting Method' value is updated successfully in 'Supervisors' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Assigned Supervisors", "Reporting Method", "1"), empRepSuperSpecify);
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step 10: Click to 'Add' button at 'Subordinates' form");
		employeeListPage.clickToButtonByID(driver, "btnAddSubordinateDetail");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step 11: Enter to 'Name' textbox with value is: '" + empRepSuborFirstName + "'");
		employeeListPage.enterToTextboxByID(driver, empRepSuborFullName, "reportto_subordinateName_empName");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step 12: Select item in 'Reporting Method' dropdown with value is: '" + empRepSuborMethod + "'");
		employeeListPage.selectItemInDropdownByID(driver, empRepSuborMethod, "reportto_reportingMethodType");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step 13: Enter to 'Please Specify' textbox with value is: '" + empRepSuborSpecify + "'");
		employeeListPage.enterToTextboxByID(driver, empRepSuborSpecify, "reportto_reportingMethod");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step 14: Click to 'Save' button at 'Subordinates' form");
		employeeListPage.clickToButtonByID(driver, "btnSaveReportTo");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step 15: Verify Success Message is displayed");
		verifyTrue(employeeListPage.isSuccessMessageDisplayed(driver, "Successfully Saved"));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step 16: Verify 'Name' value is updated successfully in 'Subordinates' data table");
		verifyTrue(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Assigned Subordinates", "Name", "1").contains(empRepSuborLastName));
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Report-to 11 - Step 17: Verify 'Reporting Method' value is updated successfully in 'Subordinates' data table");
		verifyEquals(employeeListPage.getValueInDataTableNameAtColumnNameAndRowIndex("Assigned Subordinates", "Reporting Method", "1"), empRepSuborSpecify);
	}
	
	@Test
	public void Employee_12_Verify_Employee(Method method) {
		ExtentTestManagerV5.startTest(method.getName(), "Employee_12_Verify_Employee");
		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 12 - Step 01: Open 'Employee List' page");
		employeeListPage.openSubMenuPage(driver, "PIM", "Employee List");
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 12 - Step 02: Enter to 'Employee Name' textbox with value is: '" + editEmpFullName + "'");
		employeeListPage.sleepInSecond(3);
		employeeListPage.enterToTextboxByID(driver, editEmpFullName, "empsearch_employee_name_empName");

		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 12 - Step 03: Click to 'Search' button");
		employeeListPage.clickToButtonByID(driver, "searchBtn");
		employeeListPage.waitForEmployeeNameTextboxLoadSuccessfully();
		
		ExtentTestManagerV5.getTest().log(Status.INFO, "Verification 12 - Step 04: Verify Employee Information displayed at 'Result Table'");
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
//		ExtentTestManagerV5.getTest().log(Status.INFO, "Post-Condition: Close Browser '" + browserName + "'");
		closeBrowserAndDriver();
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
	DataUtil faker;

}
