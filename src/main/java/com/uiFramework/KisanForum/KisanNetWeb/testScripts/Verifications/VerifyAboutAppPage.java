package com.uiFramework.KisanForum.KisanNetWeb.testScripts.Verifications;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.uiFramework.KisanForum.KisanNetWeb.pageObject.AboutApp;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LeftDrawer;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class VerifyAboutAppPage extends TestBase {

@Test(dataProvider = "About page content")
	public void verifyAboutPage(String emailId, String password, String namaskar, String version, String message, String runMode) throws Exception {
		if(runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run mode for this data is marked N");
		}
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsExhibitor(emailId, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnLeftDrawerIcon();
		
		LeftDrawer leftDrawer = new LeftDrawer(driver);
		leftDrawer.clickOnAboutAppOption();
		
		AboutApp aboutApp = new AboutApp(driver);
		SoftAssert softAssertion = new SoftAssert();
		softAssertion.assertTrue(aboutApp.verifyNamaskarText(namaskar), "Namaskar text displayed");
		softAssertion.assertTrue(aboutApp.verifyAppVersion(version), "App version is correct");
		//softAssertion.assertTrue(aboutApp.verifyInfoMessage(message), "Page information is correct");
		
		softAssertion.assertAll();
		
	}

	@DataProvider(name = "About page content")
	public Object[][] getAboutPageContent() throws Exception{
		Object[][] data = getExcelData("Kisan.NetTestData.xlsx", "AboutApp");
		return data;
	}
}
