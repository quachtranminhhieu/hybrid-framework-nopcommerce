package reportConfig;
//package reportConfig;
//
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//import com.relevantcodes.extentreports.LogStatus;
//
//import commons.BaseTest;
//
//public class ExtentTestListenerV2 extends BaseTest implements ITestListener {
//	@Override
//	public void onStart(ITestContext context) {
//		context.setAttribute("WebDriver", this.getDriverInstance());
//	}
//
//	@Override
//	public void onFinish(ITestContext context) {
//		ExtentTestManagerV2.endTest();
//		ExtentManagerV2.getReporter().flush();
//	}
//
//	@Override
//	public void onTestStart(ITestResult result) {
//	}
//
//	@Override
//	public void onTestSuccess(ITestResult result) {
//		ExtentTestManagerV2.getTest().log(LogStatus.PASS, "Test passed");
//	}
//
//	@Override
//	public void onTestFailure(ITestResult result) {
//
//		Object testClass = result.getInstance();
//		WebDriver webDriver = ((BaseTest) testClass).getDriverInstance();
//		String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
//		ExtentTestManagerV2.getTest().log(LogStatus.FAIL, "Test Failed", ExtentTestManagerV2.getTest().addBase64ScreenShot(base64Screenshot));
//	}
//
//	@Override
//	public void onTestSkipped(ITestResult result) {
//		ExtentTestManagerV2.getTest().log(LogStatus.SKIP, "Test Skipped");
//	}
//
//	@Override
//	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//	}
//}