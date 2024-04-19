package com.qa.opencart.pages;
import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	// 1. Private by locators

	private By logoutLink = By.linkText("Logout");
	private By AccountLink = By.linkText("My Account");
	private By headers = By.cssSelector("div#content h2");
	private By searchbox= By.name("search");
	private By searchIcon =By.cssSelector("div#search button");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public String getAccPageTitle() {

		String title = eleutil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, 5);
		System.out.println("Account page title is: " + title);
		return title;

	}

	public String getAccPageURL() {

		String url = eleutil.waitForURLContains(AppConstants.ACC_PAGE_URL_FRACTION, 5);

		System.out.println("Account page url is: " + url);
		return url;

		
	}
	
	public boolean isLogoutLinkExist() {
		
		return eleutil.waitForElementVisible(logoutLink, 10).isDisplayed();
		
	}
	
	public boolean myAccountLinkExist() {
		return eleutil.waitForElementVisible(AccountLink, 10).isDisplayed();
	}
	
	
	public List<String> getAccountPageHeadersList() {
		List<WebElement>headersEleList=  eleutil.getElements(headers);
		
		List<String> headersList= new ArrayList<String>();
		
		for (WebElement e: headersEleList) {
			String header = e.getText();
			headersList.add(header);
		}
		return headersList;
	
	}
	
	
	public SearchResultsPage doSearch(String searchKey) {
		
		System.out.println("Searching key is: "+ searchKey);
		eleutil.doSendKeys(searchbox, searchKey);
		eleutil.doClick(searchIcon);
		return new SearchResultsPage(driver);
		
	}
	

	
	
}

