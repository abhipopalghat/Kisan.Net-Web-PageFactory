package com.uiFramework.KisanForum.KisanNetWeb.testScripts.Invitations;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.helper.assertion.AssertionHelper;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.InvitePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class AppDownloadInviteToValidNumber extends TestBase{

	
	@Test(dataProvider = "getInviteList",description = "Manualy invite to download app with valid mobile number")
	public void inviteManuallyToValidNumber(String emailId, String password, String mobileNumber, String runMode) throws Exception {
		if(runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run mode for this data is marked N");
		}
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsExhibitor(emailId, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnRightOptionMenu();
		homePage.clickOnInviteToAppButton();
		
		InvitePage invitePage = new InvitePage(driver);
		invitePage.clickOnInviteUsingMobile();
		invitePage.enterMobileNumber(mobileNumber);
		invitePage.clickOnSendInviteButton();
		boolean status = invitePage.verifyInviteSuccessToast();
		AssertionHelper.updateTestStatus(status);
	}
	

	@DataProvider(name = "getInviteList")
	public Object[][] getInviteList() throws Exception {
		Object[][] data = getExcelData("Kisan.NetTestData.xlsx", "Invite To Download App");
		return data;
	}
}
