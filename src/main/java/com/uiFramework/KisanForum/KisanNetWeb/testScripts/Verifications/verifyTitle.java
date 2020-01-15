package com.uiFramework.KisanForum.KisanNetWeb.testScripts.Verifications;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.helper.assertion.AssertionHelper;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class verifyTitle extends TestBase{

	@Test(dataProvider="get login data", description = "Verify the title of home page")
	public void verifyTitleOfPage(String emailId, String password, String runMode) throws Exception {
		if(runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run Mode for this test case is marked as N");
		}
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsExhibitor(emailId, password);
		
		HomePage homePage = new HomePage(driver);
		String title = homePage.getTitle();
		AssertionHelper.verifyText(title, "KISAN.NET");
	}
	
	@DataProvider(name = "get login data")
	public Object[][] getLoginData() throws Exception {
		Object[][] data= getExcelData("Kisan.NetTestData.xlsx", "LoginData" );
		return data;
	}
	
}
