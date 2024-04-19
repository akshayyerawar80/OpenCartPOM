package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.ElementUtil;

public class CartPage {
	
	
	private WebDriver driver;
	private ElementUtil eleutil;

	// 1. Private by locators


	private By infoElements= By.xpath("//td [contains(@class, 'text')]");
	

	public CartPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	public ArrayList<String> getProductDetailsOfPage() {
		
		
		
		List<WebElement> productElements= eleutil.getElements(infoElements);
		ArrayList<String> productDetails= new ArrayList<String>();
		
		for(WebElement e: productElements) {
			
		productDetails.add(e.getText());
		
		
		}
		System.out.println(productDetails);
		return productDetails;
	}

	
	


}
