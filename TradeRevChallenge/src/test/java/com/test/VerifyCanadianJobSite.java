package com.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.page.BrowserFactory;
import com.page.CareerPage;
import com.page.OpenWebsite;

public class VerifyCanadianJobSite {
	WebDriver driver = BrowserFactory.openBrowser("chrome");
	OpenWebsite openweb = PageFactory.initElements(driver, OpenWebsite.class);
	CareerPage jobs = PageFactory.initElements(driver, CareerPage.class);
	
	@BeforeMethod
	public void beforeMethod() {
		openweb.openWebsite("https://www.traderev.com/en-ca/");
	}
	
	@Test
	public void verifyCanadianJobSiteDisplayedCorrectly() {
		jobs.selectOption("Careers");
		BrowserFactory.switchtoCareerPage();
		jobs.clickCanadianOpportunities();
		BrowserFactory.switchtoCareerPage();
		
		// verifying number of filter options
		assertEquals(CareerPage.filteroptions.size(), 3);
		
		// verifying filter type
		assertEquals(CareerPage.filteroptions.get(0).getText(), "LOCATION");
		assertEquals(CareerPage.filteroptions.get(1).getText(), "TEAM");
		assertEquals(CareerPage.filteroptions.get(2).getText(), "WORK TYPE");
		
		// verifying main category
		assertEquals(CareerPage.maincategory.size(), 2);
		assertEquals(CareerPage.maincategory.get(0).getText(), "ENGINEERING");
		assertEquals(CareerPage.maincategory.get(1).getText(), "SUPPORT");
		
		// verifying category title
		assertEquals(CareerPage.categorytitlelist.size(), 6);
		assertEquals(CareerPage.categorytitlelist.get(0).getText(), "ANALYTICS");
		assertEquals(CareerPage.categorytitlelist.get(3).getText(), "SOFTWARE DEVELOPMENT");
		
		// verifying jobs
		assertEquals(CareerPage.jobtitlelist.size(), 34);
		assertEquals(CareerPage.jobtitlelist.get(0).getText(), "Product Growth Analyst (Remote)");
		assertEquals(CareerPage.jobtitlelist.get(2).getText(), "Software Developer in Test (Remote)");
		assertEquals(CareerPage.jobtitlelist.get(29).getText(), "Bilingual Arbitration Coordinator");
		assertEquals(CareerPage.jobtitlelist.get(33).getText(), "Customer Support Advisor (Dealer Success)");
		
		// verifying apply button for all the jobs
		assertEquals(CareerPage.applybutton.size(), CareerPage.jobtitlelist.size());
		
		// verifying lever link is displayed on the page
		assertTrue(CareerPage.leverlink.isDisplayed());
	}

	@AfterSuite
	public void afterMethod() {
		driver.quit();
	}
}