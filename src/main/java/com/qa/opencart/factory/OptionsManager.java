package com.qa.opencart.factory;

import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import com.qa.opencart.logger.Log;

public class OptionsManager {
	
	private Properties prop;
	
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;
	
	public OptionsManager(Properties prop) {
		
		this.prop = prop;
	}
	
	public ChromeOptions getChromeOptions() {
		co= new ChromeOptions();
		
		if(Boolean.parseBoolean((prop.getProperty("remote")))) {
			co.setCapability("browserName", "chrome");
			
		}
		
		
		
		
		
		if(Boolean.parseBoolean((prop.getProperty("headless").trim()))) {
			Log.info("Running chrome in headless mode");
			co.addArguments("--headless");
			
		}
		
		
		if(Boolean.parseBoolean((prop.getProperty("incognito").trim()))) {
			Log.info("Running chrome in incognito mode");
			co.addArguments("--incognito");
		}
		
		return co;
	}

	
	public FirefoxOptions getFirefoxOptions() {
		fo= new FirefoxOptions();
		
		if(Boolean.parseBoolean((prop.getProperty("remote")))) {
			fo.setCapability("browserName", "firefox");
			
		}
		if(Boolean.parseBoolean((prop.getProperty("headless").trim()))) {
			Log.info("Running firefox in headless mode");
			fo.addArguments("--headless");
		}
		
		
		if(Boolean.parseBoolean((prop.getProperty("incognito").trim()))) {
			Log.info("Running firefox in incognito mode");
			fo.addArguments("--incognito");
		}
		
		return fo;
	}	
	
	
	public EdgeOptions getEdgeOptions() {
		eo= new EdgeOptions();
		if(Boolean.parseBoolean((prop.getProperty("remote")))) {
			eo.setCapability("browserName", "MicrosoftEdge");
	        eo.setCapability("browserVersion", "desired_version");
	        eo.setCapability("platform", Platform.LINUX);
	        eo.setCapability("headless", true);
	        eo.setCapability("screenResolution", "1920x1080");
	        Proxy proxy = new Proxy();
	        proxy.setProxyType(ProxyType.MANUAL);
	        proxy.setHttpProxy("proxy_host:proxy_port");
	        eo.setCapability(CapabilityType.PROXY, proxy);
//	        eo.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	        eo.setCapability("timeouts", "{implicit: 30000}");
//	        eo.setCapability("ms:edgeOptions", "extensions", "");
//	        eo.setCapability("ms:edgeOptions", "args", Arrays.asList("--disable-gpu", "--disable-extensions"));
			
		}
		
		
		if(Boolean.parseBoolean((prop.getProperty("headless").trim()))) {
			eo.addArguments("--headless");
		}
		
		
		if(Boolean.parseBoolean((prop.getProperty("incognito").trim()))) {
			eo.addArguments("--incognito");
		}
		
		return eo;
	}		
	
	
	
}
