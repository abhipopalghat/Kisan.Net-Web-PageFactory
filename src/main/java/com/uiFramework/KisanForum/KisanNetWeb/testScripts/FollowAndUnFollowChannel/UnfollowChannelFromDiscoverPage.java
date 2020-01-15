package com.uiFramework.KisanForum.KisanNetWeb.testScripts.FollowAndUnFollowChannel;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.helper.assertion.AssertionHelper;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelProfile;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.DiscoverPage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class UnfollowChannelFromDiscoverPage extends TestBase {

	//@Test(dataProvider="Channel To Be Unfollowed",description = "Search existing channel on discover page",groups = "UnfollowFromDiscover",dependsOnGroups="FollowFromDiscover")
	@Test(dataProvider="Channel To Be Unfollowed",description = "Search existing channel on discover page")

	public void unfollowChannelFromDiscoverPage(String emailId, String password, String channelName, String runMode) throws Exception {
		if(runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run mode for this data is marked N");
		}
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsExhibitor(emailId, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnDiscoverIcon();
		
		DiscoverPage discoverPage = new DiscoverPage(driver);
		discoverPage.clickOnGotItButton();
		discoverPage.clickOnSearchButton();
		discoverPage.enterChannelNameInSearchbox(channelName);
		discoverPage.clickOnChannelName(channelName);
		
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
