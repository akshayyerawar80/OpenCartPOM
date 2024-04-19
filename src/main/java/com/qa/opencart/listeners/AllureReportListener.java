package com.qa.opencart.listeners;

import java.io.ByteArrayInputStream;
import java.util.Calendar;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qa.opencart.factory.DriverFactory;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.model.Status;

public class AllureReportListener implements ITestListener {

	private static final String OUTPUT_FOLDER = "./allure-results/";

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test Suite started!");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println(("Test Suite is ending!"));
	}

	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + " started!");
		Allure.step(methodName, Status.PASSED);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println((methodName + " passed!"));
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println((methodName + " failed!"));
		takeScreenshotAndAttachToAllure(methodName);
		Allure.step(result.getThrowable().toString(), Status.FAILED);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println((methodName + " skipped!"));
		Allure.step(methodName, Status.SKIPPED);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	@Attachment(value = "Screenshot", type = "image/png")
	private byte[] takeScreenshot(WebDriver driver) {
		byte[] screenshot = null;
		try {
			// Convert WebDriver object to TakesScreenshot
			TakesScreenshot ts = (TakesScreenshot) driver;
			// Capture screenshot as byte array
			screenshot = ts.getScreenshotAs(OutputType.BYTES);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return screenshot;
	}

	private void takeScreenshotAndAttachToAllure(String methodName) {

		// Attach screenshot to Allure report
		byte[] screenshot = takeScreenshot(DriverFactory.getDriver());
		if (screenshot != null) {
			Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}
