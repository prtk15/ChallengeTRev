package com.test;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.page.BrowserFactory;
import com.page.CareerPage;
import com.page.OpenWebsite;

public class VerifyFilter {
	OpenWebsite openweb = PageFactory.initElements(BrowserFactory.driver, OpenWebsite.class);
	CareerPage jobs = PageFactory.initElements(BrowserFactory.driver, CareerPage.class);

	@BeforeMethod
	public void beforeMethod() {
		openweb.openWebsite("https://jobs.lever.co/traderev");
	}

	@Test
	public void verifyCityFilter() {
		jobs.selectFilter("LOCATION");
		jobs.selectDropDownOption("Toronto, Ontario, Canada");

		// verification of all jobs filtered are of Toronto
		for (int i = 0; i < CareerPage.jobtitlelist.size(); i++) {
			assertEquals(CareerPage.location.get(i).getText(), "TORONTO, ONTARIO, CANADA");
		}
		
		// another way to verify
		assertEquals(CareerPage.jobtitlelist.size(), jobs.countofLocation());
	}
	
	@Test
	public void verifyCityandTeamFilter() {
		jobs.selectFilter("LOCATION");
		jobs.selectDropDownOption("Toronto, Ontario, Canada");
		jobs.selectFilter("TEAM");
		jobs.selectDropDownOption("Engineering");
		
		// verification of all jobs filtered are of Toronto
		for (int i = 0; i < CareerPage.jobtitlelist.size(); i++) {
			assertEquals(CareerPage.location.get(i).getText(), "TORONTO, ONTARIO, CANADA");
		}
		
		// another way to verify location Toronto
		assertEquals(CareerPage.jobtitlelist.size(), jobs.countofLocation());
		
		// verifying only jobs related to Engineering is displayed
		assertEquals(CareerPage.maincategory.size(), 1);
		assertEquals(CareerPage.maincategory.get(0).getText(), "ENGINEERING");
		assertEquals(jobs.countofEngineering(), CareerPage.jobtitlelist.size());
		
		// total jobs available
		System.out.println("Total available positions: "+CareerPage.jobtitlelist.size());
		
		// position title
		for(WebElement element : CareerPage.jobtitlelist)
			System.out.println("Position title: "+element.getText());
	}
}