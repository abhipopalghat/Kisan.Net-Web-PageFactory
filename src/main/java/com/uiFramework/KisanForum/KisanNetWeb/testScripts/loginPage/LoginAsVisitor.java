package com.uiFramework.KisanForum.KisanNetWeb.testScripts.loginPage;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class LoginAsVisitor extends TestBase{
	
	@Test(dataProvider="testData",description="Login to App")
	public void loginToApp(String emailId, String password, String mobile, String runMode) throws Exception {
		if(runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run mode for this data is marked N");
		}		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsVisitor("9021633629");			// incase we need to login with different number
		//loginPage.loginAsVisitor(mobile);
		Thread.sleep(5000);
	}
	
	@DataProvider(name="testData")
	public Object[][] testData() throws Exception{
		Object[][] data = getExcelData("Kisan.NetTestData.xlsx", "LoginData");
		return data;
	}	

}
