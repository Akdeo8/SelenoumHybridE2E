package Utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterTest {

	public static ExtentReports getReportObject() {
		
		String reportPath = System.getProperty("user.dir") + "/reports/index.html";
		System.out.println(reportPath);
		ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
		reporter.config().setReportName("Automation Results");
		reporter.config().setDocumentTitle("Test-Results");
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Automation Team");
		System.out.println("Inside getReportObject");
		return extent;

	}

}
