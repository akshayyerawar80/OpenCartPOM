package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.util.ExcelUtil;

public class ProductPageInfoTest extends BaseTest {

	@BeforeClass
	public void infoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getSearchData() {

		return new Object[][] { { "macbook", "MacBook Pro" }, { "imac", "iMac" },
				{ "samsung", "Samsung SyncMaster 941BW" }, { "samsung", "Samsung Galaxy Tab 10.1" } };

	}

	@Test(dataProvider = "getSearchData")
	public void productHeaderTest(String searchKey, String selectProductKey) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(selectProductKey);

		Assert.assertEquals(productInfoPage.getProductHeader(), selectProductKey);

	}

	@DataProvider
	public Object[][] getImagesDataFromExcel(){
		return ExcelUtil.getTestData(AppConstants.IMAGES_COUNT_SHEET);
	}

	@DataProvider
	public Object[][] getImagesData() {

		return new Object[][] { { "macbook", "MacBook Pro", "4" }, { "imac", "iMac", "3" },
				{ "samsung", "Samsung SyncMaster 941BW", "1" }, { "samsung", "Samsung Galaxy Tab 10.1", "8" } };

	}

	@Test(dataProvider = "getImagesData")
	public void productImagesCountTest(String searchKey, String productName, String count) {
		searchResultsPage = accPage.doSearch(searchKey);
		productInfoPage = searchResultsPage.selectProduct(productName);

		Assert.assertEquals(productInfoPage.getProductImagesCount(), Integer.parseInt(count));

	}

	@Test
	public void productInfoTest() {

		searchResultsPage = accPage.doSearch("macbook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");

		Map<String, String> productActDetailsMap = productInfoPage.getPrductDetailsMap();

		softAssert.assertEquals(productActDetailsMap.get("Brand"), "Apple");

		softAssert.assertEquals(productActDetailsMap.get("Product Code"), "Product 18");

		softAssert.assertEquals(productActDetailsMap.get("Availability"), "In Stock");

		softAssert.assertEquals(productActDetailsMap.get("productprice"), "$2,000.00");

		softAssert.assertEquals(productActDetailsMap.get("extaxprice"), "$2,000.00");

		softAssert.assertAll();
	}

}
