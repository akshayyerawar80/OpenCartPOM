package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleutil;

	private Map<String, String> productMap = new HashMap<String, String>();

	// 1. Private by locators

	private By productHeader = By.tagName("h1");
	private By images = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][1]/li");
	private By productPriceData = By.xpath("//div[@id='content']//ul[@class='list-unstyled'][2]/li");
	private By quantityValue= By.xpath("//input[@name=\"quantity\"]");
	private By addCartButton = By.xpath("//button[@id=\"button-cart\"]");
	private By successMessage= By.xpath("//a[@href=\"https://naveenautomationlabs.com/opencart/index.php?route=checkout/cart\"and @title=\"Shopping Cart\"]");
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}

	public String getProductHeader() {
		String header = eleutil.doGetElementText(productHeader);
		System.out.println(header);
		return header;
	}

	public int getProductImagesCount() {

		int countOfImages = eleutil.waitForElementsVisible(images, 10).size();
		System.out.println("count of images are: " + countOfImages);
		return countOfImages;
	}

	private void getProductMetaData() {

		List<WebElement> metalist = eleutil.getElements(productMetaData);

	;	for (WebElement e : metalist) {

			String text = e.getText();
			String metakey = text.split(":")[0].trim();
			String metavalue = text.split(":")[1].trim();

			productMap.put(metakey, metavalue);

		}

	}

	private void getProductPriceData() {

		List<WebElement> priceList = eleutil.getElements(productPriceData);

		String price = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText().split(":")[1].trim();

		productMap.put("productprice", price);
		productMap.put("extaxprice", exTaxPrice);

	}
	
	public Map<String, String> getPrductDetailsMap() {
		
		productMap.put("header", getProductHeader());
		
		productMap.put("productimages", String.valueOf(getProductImagesCount()));
		getProductMetaData();
		getProductPriceData();
		
		return productMap;
		
	}
	
	public CartPage addToCart(String cartvalue) {
		eleutil.doSendKeys(quantityValue, cartvalue);
		String s= eleutil.doElementGetAttribute(quantityValue, "value");
		System.out.println("Entered quantity is" +s);
		eleutil.doClick(addCartButton);
		eleutil.doClick(successMessage);
	
		return new CartPage(driver);
	}


}
