package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logger.Log;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");

		Log.info("Browse name is: " + browserName);

		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("chrome");
			} else {
				// driver = new ChromeDriver(optionsManager.getChromeOptions());
				tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

			}

			break;

		case "firefox":

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("firefox");
			} else {
				// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
				tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

			}
			break;

		case "edge":

			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver("edge");
			} else {
				// driver = new EdgeDriver(optionsManager.getEdgeOptions());
				tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));

			}

			break;

		case "safari":
			// driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;

		default:

			Log.error("please pass the correct browser" + browserName);
			// System.out.println("Please pass the correct browser " + browserName);
			throw new BrowserException("No browser found " + browserName);

		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}

	/**
	 * run tests in selenium grid
	 * 
	 * @param browserName
	 */

	private void init_remoteDriver(String browserName) {
		System.out.println("Runnig tests on Remote Grid on browser" + browserName);

		URL url = null;
		URI uri;
		try {
			uri = new URI(prop.getProperty("huburl"));
			url = uri.toURL();
		} catch (URISyntaxException | MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			switch (browserName.toLowerCase().trim()) {
			case "chrome":

				tlDriver.set(new RemoteWebDriver(url, optionsManager.getChromeOptions()));

				break;

			case "firefox":

				tlDriver.set(new RemoteWebDriver(url, optionsManager.getFirefoxOptions()));

				break;

			case "edge":

				tlDriver.set(new RemoteWebDriver(url, optionsManager.getEdgeOptions()));

				break;

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {

		// envName= qa, stage, prod, uat, dev
		// mvn clean install -Denv="qa"

		FileInputStream ip = null;
		prop = new Properties();
		String envName = System.getProperty("env");
		System.out.println("Running test on Env: " + envName);

		try {
			if (envName == null) {

				System.out.println("No environment is given...Hence running on QA Environment");
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");

			}

			else {

				switch (envName.toLowerCase().trim()) {
				case "qa":

					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");

					break;

				case "dev":

					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");

					break;

				case "stage":

					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");

					break;

				case "uat":

					ip = new FileInputStream("./src/test/resources/config/config.uat.properties");

					break;

				default:
					Log.error("please pass correct environment" + envName);
					// System.out.println("please pass correct environment" + envName);
					throw new FrameworkException(AppError.ENV_NAME_NOT_FOUND + " : " + envName);

				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		}
		try {
			prop.load(ip);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return prop;
	}

	public static String getScreenshot(String methodName) {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp directory
		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis()
				+ ".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}
