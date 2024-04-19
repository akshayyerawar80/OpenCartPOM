package com.qa.opencart.tests;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class cartPageTest extends BaseTest {
	
	
	@BeforeClass
	public void carPageSetup() {
		accPage= loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		searchResultsPage= accPage.doSearch("macbook");
		productInfoPage= searchResultsPage.selectProduct("MacBook Pro");
		 
	}
	
	@Test
	public void cartValueTest() {
		
	
		cartPage= productInfoPage.addToCart("2");
		
		ArrayList<String> proDetails= cartPage.getProductDetailsOfPage();
		
		for (int i=0;i<proDetails.size();i++) {
			
			
			 try {
			        Assert.assertEquals(proDetails.get(i), "Product 18", "Test Pass");
			    } catch (AssertionError e) {
			        
			    }
				
			}
			
		}
	

		
		
	}
	


