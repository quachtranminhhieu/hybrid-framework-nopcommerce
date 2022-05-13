//package reportConfig;
//
//import com.relevantcodes.extentreports.ExtentReports;
//
//public class ExtentManagerV2 {
//
//	private static ExtentReports extent;
//
//	public synchronized static ExtentReports getReporter() {
//		if (extent == null) {
//			extent = new ExtentReports(System.getProperty("user.dir") + "/extentReportV2/ExtentReportV2.html", true);
//		}
//		return extent;
//	}
//}
