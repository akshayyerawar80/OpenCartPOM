package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.ElementUtil;

public class SearchResultsPage {
	
	
	private WebDriver driver;
	private ElementUtil eleutil;

	// 1. Private by locators

	private By searchProducts =By.cssSelector("div.product-thumb");

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	
	public int getSearchProductCount() {
		return eleutil.waitForElementsVisible(searchProducts, 10).size();
	}
	
	public ProductInfoPage selectProduct(String productName) {
		
		System.out.println("searching for product: "+ productName);
		eleutil.waitForElementVisible(By.linkText(productName), 10).click();
	
		return new ProductInfoPage(driver);
	
	
	}
	

}
