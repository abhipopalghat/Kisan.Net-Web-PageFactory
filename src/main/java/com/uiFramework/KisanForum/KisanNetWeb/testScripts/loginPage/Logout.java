package com.uiFramework.KisanForum.KisanNetWeb.testScripts.loginPage;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.helper.browserConfiguration.config.ObjectReader;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LeftDrawer;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class Logout extends TestBase{

@Test(dataProvider="testData",description="Login to App")
	public void logoutFromApp(String emailId, String password, String runMode) throws Exception {
		if(runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run mode for this data is marked N");
		}
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsExhibitor(emailId, password);
		Thread.sleep(5000);
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnLeftDrawerIcon();
		
		LeftDrawer leftDrawer = new LeftDrawer(driver);
		leftDrawer.clickOnLogoutOption();
		leftDrawer.clickOnYesButtonOnPopup();
		
		String urlAfterLogout = driver.getCurrentUrl();
		System.out.println(urlAfterLogout);
		Assert.assertEquals(urlAfterLogout, ObjectReader.reader.getLoginUrl());
	}
	
	@DataProvider(name="testData")
	public Object[][] testData() throws Exception{
		Object[][] data = getExcelData("Kisan.NetTestData.xlsx", "LoginData");
		return data;
	}	
	
	
}
