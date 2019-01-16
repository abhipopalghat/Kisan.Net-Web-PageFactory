package com.uiFramework.KisanForum.KisanNetWeb.testScripts.Invitations;

import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.helper.assertion.AssertionHelper;
import com.uiFramework.KisanForum.KisanNetWeb.helper.logger.LoggerHelper;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelDashboard;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelProfile;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.InvitePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class FollowChannelInviteToValidNumber extends TestBase{
	
	private final Logger log = LoggerHelper.getLogger(FollowChannelInviteToValidNumber.class);
	
	@Test(dataProvider="get invite list",description="Invite to follow channel with valid number")
	public void inviteToFollowChannelWithValidNumber(String emailId, String password, String channelName, String mobileNumber, String runMode) throws Exception {
		if(runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run mode for this data is marked N ");
		}
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApp(emailId, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnRightOptionMenu();
		homePage.clickOnSearchButton();
		homePage.enterChannelNameInSearchBox(channelName);
		homePage.clickOnChannelName(channelName);
		
		ChannelDashboard channelDashboard = new ChannelDashboard(driver);
		channelDashboard.clickOnChannelNameInHeader();
		
		ChannelProfile channelProfile = new ChannelProfile(driver);
		channelProfile.clickOnRightOptionMenu();
		channelProfile.clickOnInviteToChannelOption();
		
		InvitePage invitePage = new InvitePage(driver);
		invitePage.clickOnInviteUsingMobile();
		invitePage.enterMobileNumber(mobileNumber);
		invitePage.clickOnSendInviteButton();
		
		boolean status = invitePage.verifyInviteSuccessToast();
		AssertionHelper.updateTestStatus(status);
	
	}
	
	@DataProvider(name = "get invite list")
	public Object[][] getInviteList() throws Exception{
		Object[][] data = getExcelData("Kisan.NetTestData.xlsx", "Invite To Follow Channel");
		return data;		
	}
	
}
