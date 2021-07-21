package Globals;

import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.*;

import org.testng.annotations.AfterClass;

import java.awt.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class WebGlobals {
	public static String directoryPath = System.getProperty("user.dir");
	
	
	public static WebDriver browser = null;
	
	public static DesiredCapabilities capabilities;
	
	public static RemoteWebDriver driver;
	public static String currentDateTime = "";
	public static boolean debug = false;
	public static boolean netExport = false;
	protected static ExtentTest test;
	private static RemoteWebDriver remote = null;

	// Browser setup
	static {
		// Optional, if not specified, WebDriver will search your path for
		// chromedriver.
		System.setProperty("webdriver.chrome.driver", directoryPath
				+ "\\lib\\chromedriver.exe");
		System.setProperty("webdriver.ie.driver", directoryPath
				+ "\\lib\\DriverRelated\\IE\\IEDriverServer2.53_Win32.exe");
		System.setProperty("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.Jdk14Logger");
		System.setProperty("browser", "Chrome");
		System.setProperty("browserVersion", "60.0.3112.78");
        System.setProperty("webdriver.remote", "false");

		File file = new File(directoryPath + "\\lib\\geckodriver.exe");
		System.out.println(file.getAbsolutePath());
		System.setProperty("webdriver.gecko.driver",
				file.getAbsolutePath());

	}

	// Close the browser instance
	public static void browserClose() throws Exception {
		// Make sure we shut everything down to avoid memory leaks
		browser.close();
		Thread.sleep(3000);
		browser.quit();
	}

	// Maximize the browser screen
	public static void browserMax() {
		browser.manage().window().maximize();
	}

	// Browser navigate
	public static void browserNav(String url) {
		//log("Navigating to " + url);
		//clearCache();
		browser.get(url);
	}

	// Select browser to use
	public static void browserSelect() throws Exception {
		System.setProperty("webdriver.chrome.driver", directoryPath
				+ "\\lib\\chromedriver.exe");
		browser = new ChromeDriver();
	}



	
	public static void getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/dd/MM HH:mm:ss.SSS");
		Date date = new Date();
		String dateNew = dateFormat.format(date).toString().replace("/", "_")
				.replace(" ", "_").replace(":", "_").replace(".", "_");
		currentDateTime = dateNew;
	}


	// Log a message to the console
	public static void log(String msg) {
		System.out.println(msg);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		browserClose();
		// Close any browser instances left open
		if (browser != null && debug == false) {
			log("Closing [" + System.getProperty("browser") + "] browser");
			browserClose();
		}

		// Close any Sauce Labs connections
		if (driver != null) {
			log("Closing Sauce Labs connection.");
			driver.quit();
		}

	}

	public static boolean VerifyPageReady(int timeout)
			throws HeadlessException, IOException, AWTException {
		log("Waiting for Page to Get Ready.. ");
		try {
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) browser)
							.executeScript("return document.readyState")
							.equals("complete");
				}
			};
			WebDriverWait wait = new WebDriverWait(browser, timeout);
			wait.until(pageLoadCondition);
		} catch (Exception e) {
			String ErrorMsg = "Failed wait for Page to load within timeout of : "
					+ timeout + " seconds  at url:" + browser.getCurrentUrl();
			log(ErrorMsg);
			StackTraceElement ste = new Exception().getStackTrace()[1];
			log(ste.getClassName() + "/" + ste.getMethodName() + ":"
					+ ste.getLineNumber());
			assertTrue(false);
			return false;
		}
		log("Wait for Page Load : Passed ");
		assertTrue(true);
		return true;
	}

	public static void wait(int numOfSeconds) throws InterruptedException {
		Thread.sleep(numOfSeconds * 1000);
	}

	public static void Wait(WebElement element, int timeoutInSeconds)
			throws HeadlessException, IOException, AWTException {
		try {
			WebDriverWait wait = new WebDriverWait(browser, timeoutInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			// Thread.sleep(2000);
			log("Waiting for " + element + " for " + timeoutInSeconds
					+ " seconds.");
		} catch (Exception e) {
			log(e.getMessage());
			// Fail the test
			assertTrue(false);
		}
	}

	public boolean verifyElementDisplayed(WebElement element, long seconds) {

		log("Check if element: " + element.toString()
				+ " is displayed waiting for " + seconds);

		// Get previous Implicit Wait value
		long previousImplicitWait = getImplicitWait();
		boolean isDisp = false;

		// Set new Implicit Wait value to seconds
		setImplicitWait(seconds);

		try {
			isDisp = element.isDisplayed();

			if (isDisp) {
				log("Element: " + element.toString() + " displayed waiting for "
						+ seconds);
			} else {
				log("Element: " + element.toString()
						+ " NOT displayed waiting for " + seconds);
			}
		} catch (NoSuchElementException e) {
			//log("Element: " + element.toString() + " NOT found");
			return isDisp;
		}

		// Restore the previous Implicit Wait
		setImplicitWait(previousImplicitWait);

		return isDisp;
	}
	/**
	 * Get Current Implicit Wait value in Seconds
	 *
	 * @return currentImplicitWait in seconds
	 */
	public long getImplicitWait() {
		long currentImplicitWait = 10;

		try {
			currentImplicitWait = Long
					.valueOf(30);
		} catch (Exception e) {
			log("Failed to get Implicit Wait Configuration: " + e.toString());
		}

		return currentImplicitWait;
	}

	/**
	 * Set new Implicit Wait value in seconds
	 *
	 * @param newImplicitWait in seconds
	 */
	public void setImplicitWait(long newImplicitWait) {
		// Set new Implicit Wait value to 0
		browser.manage().timeouts().implicitlyWait(newImplicitWait,
				TimeUnit.SECONDS);
	}
	// Click on a button
	public void click(WebElement element) {
		element.click();
	}



}
