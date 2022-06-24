package commons;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;
	
	private enum ENVIRONMENT {
		DEV, TESTING, STAGING, PRODUCTION;
	}
	
	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}
	
	@BeforeSuite
	public void initBeforeSuite() {
		deleteAllureReport();
	}

	public void deleteAllureReport() {
		try {
			String pathFolderDownload = GlobalConstans.PROJECT_PATH + "/allure-results";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	public WebDriver getDriverInstance() {
		return this.driver;
	}
	
	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		switch (browser) {
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case H_FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions h_f_options = new FirefoxOptions();
			h_f_options.addArguments("--headless");
			h_f_options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(h_f_options);
			break;
		case CHROME:
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case COCCOC:
			WebDriverManager.chromedriver().driverVersion("98.0.4758.48").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Users\\Dell\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case SAFARI:
			driver = new SafariDriver();
			break;
		default:
			throw new RuntimeException("Browser name invalid.");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(GlobalConstans.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(GlobalConstans.USER_PAGE_URL);
		return driver;
	}
	
	protected WebDriver getBrowserDriver(String browserName, String appURL) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		switch (browser) {
		case FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			
			// Add extensions to Firefox
			FirefoxProfile profile = new FirefoxProfile();
			File firefoxExtension = new File(GlobalConstans.PROJECT_PATH + File.separator + "browserExtensions" + File.separator + "adblock_plus-3.14.xpi");
			profile.addExtension(firefoxExtension);
			FirefoxOptions f_options = new FirefoxOptions();
			f_options.setProfile(profile);
			
			// Run in InCognito => No Extensions added
			// f_options.addArguments("-private");
			
			// Disable browser log in Console
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstans.PROJECT_PATH + File.separator + "browserLogs" + File.separator + "Firefox.log");
			
			
			driver = new FirefoxDriver(f_options);
			break;
		case H_FIREFOX:
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions h_f_options = new FirefoxOptions();
			h_f_options.addArguments("--headless");
			h_f_options.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(h_f_options);
			break;
		case CHROME:
			WebDriverManager.chromedriver().setup();
			
			// Add extension to Chrome
			File chromeExtension = new File(GlobalConstans.PROJECT_PATH + File.separator + "browserExtensions" + File.separator + "adblock_plus_3_14_0_0.crx");
			ChromeOptions c_options = new ChromeOptions();
			c_options.addExtensions(chromeExtension);
			
			// Run in InCognito => No Extensions added
			// c_options.addArguments("--incognito");
			
			// Disable infobar in chrome
			// Old: 
			// c_options.addArguments("--disable-infobars");
			// New:
			c_options.setExperimentalOption("useAutomationExtension", "false");
			c_options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			
			// Disable notifications popup
			c_options.addArguments("--disable-notifications");
			
			// Disable location popup
			c_options.addArguments("--disable-geolocation");
			
			// Disable browser log in Console
			System.setProperty("webdriver.chrome.args", "--disable-logging");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			
			// Disable Save Password popup
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			c_options.setExperimentalOption("prefs", prefs);
			
			driver = new ChromeDriver(c_options);
			break;
		case COCCOC:
			WebDriverManager.chromedriver().driverVersion("98.0.4758.48").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("C:\\Users\\Dell\\AppData\\Local\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(options);
			break;
		case EDGE:
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case SAFARI:
			driver = new SafariDriver();
			break;
		default:
			throw new RuntimeException("Browser name invalid.");
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(GlobalConstans.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get(appURL);
		
		// Áp dụng cho Level_21_Multiple_Environment
		// Thêm <parameter> trong file xml:
		// <parameter name = "environment" value = "dev" />
		// driver.get(getEnvironmentValue(appURL));
		return driver;
	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}
	
	protected void showBrowserConsoleLogs(WebDriver driver) {
		if (driver.toString().contains("chrome")) {
			LogEntries logs = driver.manage().logs().get("browser");
			List<LogEntry> logList = logs.getAll();
			for (LogEntry logging : logList) {
				log.info("----------" + logging.getLevel().toString() + "----------\n" + logging.getMessage());
			}
		}
	}
	
	protected void closeBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	// Áp dụng ở phía trên
	private String getEnvironmentValue(String enviromentValue) {
		ENVIRONMENT enviroment = ENVIRONMENT.valueOf(enviromentValue.toUpperCase());
		switch (enviroment) {
		case DEV:
			return "https://demo.guru99.com/v1";
		case TESTING:
			return "https://demo.guru99.com/v2";
		case STAGING:
			return "https://demo.guru99.com/v3";
		case PRODUCTION:
			return "https://demo.guru99.com/v4";
		default:
			return "https://demo.guru99.com/v1";
		}
	}
}
