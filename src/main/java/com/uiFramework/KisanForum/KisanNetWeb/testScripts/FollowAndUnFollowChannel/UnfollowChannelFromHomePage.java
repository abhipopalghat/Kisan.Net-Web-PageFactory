package com.uiFramework.KisanForum.KisanNetWeb.testScripts.FollowAndUnFollowChannel;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.helper.assertion.AssertionHelper;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelDashboard;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelProfile;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class UnfollowChannelFromHomePage extends TestBase{
	
	@Test(dataProvider = "Channel To Be Unfollowed",groups = "UnfollowFromHomePage", dependsOnGroups = "UnfollowFromDiscover")
	public void unfollowChannelFromHomePage(String emailId, String password, String channelName, String runMode) throws Exception {
		if(runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run mode for this data is marked N");
		}
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsExhibitor(emailId, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnRightOptionMenu();
		homePage.clickOnSearchButton();
		homePage.enterChannelNameInSearchBox(channelName);
		homePage.clickOnChannelName(channelName);
		
		ChannelDashboard channelDashboard = new ChannelDashboard(driver);
		channelDashboard.clickOnChannelNameInHeader();
		
		ChannelProfile channelProfile = new ChannelProfile(driver);
		channelProfile.clickOnRightOptionMenu();
		channelProfile.clickOnUnfollowChannelOption();
		channelProfile.clickOnYesButtonOnPopup();
		
		boolean status = channelProfile.verifyChannelUnfollowedToast();
		AssertionHelper.updateTestStatus(status);
	}
	
	@DataProvider(name = "Channel To Be Unfollowed")
	public Object[][] getChannelList() throws Exception{
		Object[][] data = getExcelData("Kisan.NetTestData.xlsx", "Follow Channels");
		return data;
	}

}
