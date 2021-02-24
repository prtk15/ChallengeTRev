package com.page;

import org.openqa.selenium.support.ui.WebDriverWait;

public class OpenWebsite {
	public static WebDriverWait wait;

	// method open website
	public void openWebsite(String url) {
		BrowserFactory.driver.manage().window().maximize();
		BrowserFactory.driver.get(url);
		wait = new WebDriverWait(BrowserFactory.driver, 15);
	}	
}