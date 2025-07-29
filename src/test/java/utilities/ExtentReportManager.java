package utilities;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
    static ExtentReports extent;
    static ExtentTest scenario;

    public static ExtentReports getReporter() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("target/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

    public static void startScenario(String name) {
        scenario = getReporter().createTest(name);
    }

    public static void logInfo(String msg) {
        scenario.info(msg);
    }

    public static void flushReports() {
        getReporter().flush();
    }
}
