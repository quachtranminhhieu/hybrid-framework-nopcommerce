package pageObjects.orangeHRM;

import org.openqa.selenium.WebDriver;

public class HRMPageGeneratorManager {
	
	public static AddEmployeePO getAddEmployeePage(WebDriver driver) {
		return new AddEmployeePO(driver);
	}
	
	public static DashboardPO getDashboardPage(WebDriver driver) {
		return new DashboardPO(driver);
	}
	
	public static EmployeeListPO getEmployeeListPage(WebDriver driver) {
		return new EmployeeListPO(driver);
	}
	
	public static LoginPO getLoginPage(WebDriver driver) {
		return new LoginPO(driver);
	}
	
	public static MyInfoPO getMyInfoPage(WebDriver driver) {
		return new MyInfoPO(driver);
	}
	
}
