package com.page;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CareerPage {
	String part1;

	@FindBy(xpath = ".//a[@class='nav-site__item']")
	public static List<WebElement> options;

	@FindBy(xpath = ".//a[@title='Canadian Jobs']")
	public static WebElement canadianjobs;

	@FindBy(css = ".filter-button-wrapper>.filter-button")
	public static List<WebElement> filteroptions;

	@FindBy(xpath = ".//div[@class='large-category-header']")
	public static List<WebElement> maincategory;

	@FindBy(xpath = ".//div[contains(@class,'posting-category-title')]")
	public static List<WebElement> categorytitlelist;

	@FindBy(xpath = ".//h5[@data-qa='posting-name']")
	public static List<WebElement> jobtitlelist;

	@FindBy(xpath = ".//div[@class='posting-apply']/a")
	public static List<WebElement> applybutton;

	@FindBy(className = "image-link")
	public static WebElement leverlink;

	@FindBy(xpath = ".//div[@class='filter-popup']/ul/li/a")
	public static List<WebElement> dropdownoption;

	@FindBy(xpath = ".//span[starts-with(@class,'sort-by-location')]")
	public static List<WebElement> location;

	@FindBy(xpath = ".//span[starts-with(@class,'sort-by-team')]")
	public static List<WebElement> team;

	// method to select website options from the header
	public void selectOption(String menuoption) {
		OpenWebsite.wait.until(ExpectedConditions.visibilityOfAllElements(options));
		for (WebElement element : options) {
			if (element.getText().equals(menuoption))
				element.click();
		}
	}

	// method to click on Canadian Opportunities
	public void clickCanadianOpportunities() {
		JavascriptExecutor jse2 = (JavascriptExecutor) BrowserFactory.driver;
		jse2.executeScript("arguments[0].click();", canadianjobs);
	}

	// method to select filter type
	public void selectFilter(String value) {
		OpenWebsite.wait.until(ExpectedConditions.visibilityOfAllElements(filteroptions));
		for (WebElement element : filteroptions) {
			if (element.getText().equals(value))
				element.click();
		}
	}

	// method to select option from the drop down list
	public void selectDropDownOption(String option) {
		OpenWebsite.wait.until(ExpectedConditions.visibilityOfAllElements(filteroptions));
		int attempts = 0;
		while (attempts < 5) {
			try {
				for (WebElement element : dropdownoption) {
					if (element.getText().equals(option))
						element.click();
				}
				break;
			} catch (StaleElementReferenceException e) {
			}
			attempts++;
		}
	}

	// method to count number of Toronto, Ontario, Canada
	public int countofLocation() {
		int count = 0;
		for (int i = 0; i < CareerPage.location.size(); i++) {
			if (CareerPage.location.get(i).getText().equals("TORONTO, ONTARIO, CANADA")) {
				count++;
			}
		}
		return count;
	}

	// method to count number of Engineering label
	public int countofEngineering() {
		int count = 0;
		for (int i = 0; i < CareerPage.team.size(); i++) {
			String label = patternMatcher("([A-z]+)\\s[–]\\s+[A-z]+", CareerPage.team.get(i).getText());
			if (label.equals("ENGINEERING"))
				count++;
		}
		return count;
	}

	// regular expression patter matcher
	public String patternMatcher(String pattern, String matcher) {
		Pattern p = Pattern.compile(pattern);
		Matcher m1 = p.matcher(matcher);
		if (m1.matches()) {
			part1 = m1.group(1);
		}
		return part1;
	}
}