package com.page;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserFactory {
	public static WebDriver driver;
	public static String browserName;
	static String path = System.getProperty("user.dir");

	// method to start the browser
	public static WebDriver openBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", path + "/Driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		return driver;
	}

	// method to close the breoser
	public static void closeBrowser() {
		driver.quit();
	}

	// method to switch between browser window
	public static void switchtoCareerPage() {
		Set<String> cwindow = driver.getWindowHandles();
		Iterator<String> i1 = cwindow.iterator();
		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			driver.switchTo().window(ChildWindow);
		}
	}
}