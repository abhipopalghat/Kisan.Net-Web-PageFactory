package com.uiFramework.KisanForum.KisanNetWeb.testScripts.Verifications;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LeftDrawer;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.SupportPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class VerifySupportPage extends TestBase{
	
	@Test(dataProvider = "Support page content")
	public void verifySupportPage(String emailId, String password, String namaskar, String email, String phone, String helpText, String runMode) throws Exception {
		if(runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run mode for this data is marked N");
		}
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsExhibitor(emailId, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnLeftDrawerIcon();
		
		LeftDrawer leftDrawer = new LeftDrawer(driver);
		leftDrawer.clickOnSupportOption();
		
		SupportPage supportPage = new SupportPage(driver);
		SoftAssert softAssertion = new SoftAssert();
		softAssertion.assertTrue(supportPage.verifyNamaskarText(namaskar), "Namaskar text is not displayed");
		softAssertion.assertTrue(supportPage.verifyCompanyEmail(email), "Company email is not correct");
		softAssertion.assertTrue(supportPage.verifyCompanyPhoneNumber(phone), "Company phone number is not correct");
		softAssertion.assertTrue(supportPage.verifyhelpTexxt(helpText), "Help text is not correct");
		softAssertion.assertAll();
		
	}

	@DataProvider(name = "Support page content")
	public Object[][] getAboutPageContent() throws Exception{
		Object[][] data = getExcelData("Kisan.NetTestData.xlsx", "SupportPage");
		return data;
	}


}
