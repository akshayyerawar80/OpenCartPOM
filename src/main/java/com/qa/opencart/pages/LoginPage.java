package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logger.Log;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	
	private WebDriver driver;
	private ElementUtil eleutil;
	
	
	//1. Private by locators
	
	
	private By emailId= By.id("input-email");
	private By password= By.id("input-password");
	private By loginButton= By.xpath("//input[@value= 'Login']");
	private By forgotPwd= By.linkText("Forgotten Password");
	private By registerLink= By.linkText("Register");
	
	
	
	//2. Public page class constructor.
	
	public LoginPage (WebDriver driver) {
		this.driver=driver;
		eleutil= new ElementUtil(driver);
	}
	
	
	
	//3. Public page Action/ Method
	
	@Step("getting login page title")
	
	public String getLoginPageTitle() {

		String title = eleutil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, 5);
		Log.info("Login page title is: " + title);
		return title;

	}
	
	@Step("getting login page URL")
	public String getLoginPageURL() {
		
		String url= eleutil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, 5);
		 
		System.out.println("Login page url is: " +url);
		return url;
	
	}
	
	
	@Step("getting state of Forgot pwd link")
	public boolean isForgotPwdLinkExist() {
		
		
	return eleutil.isElementDisplayed(forgotPwd);
			
	
	}
	
	@Step("login with username {0} and password {1}")
	public AccountsPage doLogin (String username, String pwd) {
		
		System.out.println("user credentials are:"+"username: " + username + " password: "+pwd);
		
		eleutil.waitForElementVisible(emailId, 10).sendKeys(username);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(loginButton);
		
		return new AccountsPage (driver);
		
		
	}
	
	@Step("navigating to registration page")
	public RegisterationPage navigateToRegisterPage() {
		eleutil.waitForElementVisible(registerLink, 10).click();
		return new RegisterationPage (driver);
	}
	
	
	
	
	
	
	
	

}
