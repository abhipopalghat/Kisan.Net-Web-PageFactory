package com.uiFramework.KisanForum.KisanNetWeb.helper.browserConfiguration.config;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.uiFramework.KisanForum.KisanNetWeb.helper.browserConfiguration.BrowserType;
import com.uiFramework.KisanForum.KisanNetWeb.helper.resource.ResourceHelper;

public class PropertyReader implements ConfigReader {

	private static FileInputStream file;
	public static Properties OR;

	public PropertyReader() {
		try {
			String filePath = ResourceHelper.getResourcePath("src/main/resources/configfile/config.properties");
			file = new FileInputStream(new File(filePath));
			OR = new Properties();
			OR.load(file);
			
			/*String filePath1 = ResourceHelper.getResourcePath("src/main/resources/configfile/config1.properties");
			file = new FileInputStream(new File(filePath1));
			OR = new Properties();
			OR.load(file);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int getImpliciteWait() {
		return Integer.parseInt(OR.getProperty("implicitwait"));
	}

	public int getExplicitWait() {
		return Integer.parseInt(OR.getProperty("explicitwait"));
	}

	public int getPageLoadTime() {
		return Integer.parseInt(OR.getProperty("pageloadtime"));
	}

	public BrowserType getBrowserType() {
		return BrowserType.valueOf(OR.getProperty("browserType"));
	}

	public String getExhibitorLoginUrl() {
		if(System.getProperty("url")!=null){
			return System.getProperty("url");
		}
		return OR.getProperty("applicationUrlExhibitor");
	}

	public String getLoginUrl() {
		if(System.getProperty("applicationUrl")!=null){
			return System.getProperty("applicationUrl");
		}
		return OR.getProperty("applicationUrl");
	}
	
	public String getUserName() {
		if(System.getProperty("userName")!=null){
			return System.getProperty("userName");
		}
		return OR.getProperty("userName");
	}

	public String getPassword() {
		if(System.getProperty("password")!=null){
			return System.getProperty("password");
		}
		return OR.getProperty("password");
	}
	
	public String getProductAndServices() {
		if(System.getProperty("productAndServices")!=null){
			return System.getProperty("productAndServices");
		}
		return OR.getProperty("productAndServices");
	}
	
	public String getGSTNumber() {
		if(System.getProperty("GSTnumber")!=null){
			return System.getProperty("GSTnumber");
		}
		return OR.getProperty("GSTnumber");
	}
	
	public String getRegisteredGSTAddress() {
		if(System.getProperty("registeredGSTAddress")!=null){
			return System.getProperty("registeredGSTAddress");
		}
		return OR.getProperty("registeredGSTAddress");
	}
	
	public String getPincode() {
		if(System.getProperty("pincode")!=null){
			return System.getProperty("pincode");
		}
		return OR.getProperty("pincode");
	}
	
	public String getCountry() {
		if(System.getProperty("country")!=null){
			return System.getProperty("country");
		}
		return OR.getProperty("country");
	}
	
	public String getstate() {
		if(System.getProperty("state")!=null){
			return System.getProperty("state");
		}
		return OR.getProperty("state");
	}
	
	public String getCity() {
		if(System.getProperty("city")!=null){
			return System.getProperty("city");
		}
		return OR.getProperty("city");
	}
	
	public String getCompanyname() {
		if(System.getProperty("companyname")!=null){
			return System.getProperty("companyname");
		}
		return OR.getProperty("companyname");
	}
	
	public String getLastname() {
		if(System.getProperty("lastname")!=null){
			return System.getProperty("lastname");
		}
		return OR.getProperty("lastname");
	}
	
	public String getFirstname() {
		if(System.getProperty("firstname")!=null){
			return System.getProperty("firstname");
		}
		return OR.getProperty("firstname");
	}
	
	public String getPromocode() {
		if(System.getProperty("promocode")!=null){
			return System.getProperty("promocode");
		}
		return OR.getProperty("promocode");
	}

	public String getSignupMobileNumber() {
		if(System.getProperty("signupMobile")!=null){
			return System.getProperty("signupMobile");
		}
		return OR.getProperty("Signup mobile is not specified, Please check config file");
	}

}
