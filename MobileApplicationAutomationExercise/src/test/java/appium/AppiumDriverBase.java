package appium;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppiumDriverBase
{
	protected AndroidDriver driver;
	protected WebDriverWait wait;

	@BeforeTest
	protected void createAppiumDriver() throws MalformedURLException, InterruptedException
	{
		/*
		final File classpathRoot = new File(System.getProperty("user.dir"));
		final File appDir = new File(classpathRoot, "src/test/resources/apps/");
		
		final File app = new File(appDir, "eBay.apk");
		*/
		// setting up desired capability
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("platform", "ANDROID");
		dc.setCapability("platformVersion", "9");
		dc.setCapability("deviceName", "ANDROID");
		dc.setCapability("appPackage", "com.ebay.mobile");
		dc.setCapability("appActivity", "com.ebay.mobile.activities.MainActivity");

		// initializing driver object
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);

		// initializing explicit wait object
		wait = new WebDriverWait(driver, 15);
	}
	@AfterTest
	public void afterTest() throws InterruptedException
	{
		System.out.println("Test done");
		//Thread.sleep(10000);
		
		driver.quit();
		System.out.println("Driver quit");
	}
}