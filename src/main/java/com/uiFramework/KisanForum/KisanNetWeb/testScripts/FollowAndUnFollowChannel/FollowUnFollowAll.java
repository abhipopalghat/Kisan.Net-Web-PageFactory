package com.uiFramework.KisanForum.KisanNetWeb.testScripts.FollowAndUnFollowChannel;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.helper.assertion.AssertionHelper;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelDashboard;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelProfile;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.DiscoverPage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LeftDrawer;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class FollowUnFollowAll extends TestBase{

	@Test(dataProvider = "FollowUnFollow Channel", description = "Follow channel from discover page",dependsOnMethods="unfollowChannelFromHomePage", alwaysRun = true)
	public void followChannelFromDiscoverPage(String emailId, String password, String channelName, String runMode) throws Exception {
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
		discoverPage.clickOnFollowButton(channelName);
		
		boolean status = discoverPage.verifyChannelFollowedToast();
		Assert.assertTrue(status, "Channel followed successfully.");
	}
	
	/*@Test(dataProvider = "Follow Channel From Discover", description = "Follow channel from discover page")
	public void followChannelFromDiscoverPage1(String emailId, String password, String channelName, String runMode) throws Exception {
		if(runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run mode for this data is marked N");
		}
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToApp(emailId, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnLeftDrawerIcon();
		
		LeftDrawer leftDrawer = new LeftDrawer(driver);
		leftDrawer.clickOnDiscoverOption();
		
		DiscoverPage discoverPage = new DiscoverPage(driver);
		discoverPage.clickOnGotItButton();
		discoverPage.clickOnSearchButton();
		discoverPage.enterChannelNameInSearchbox(channelName);
		discoverPage.clickOnFollowButton(channelName);
		
		boolean status = discoverPage.verifyChannelFollowedToast();
		Assert.assertTrue(status, "Channel followed successfully.");
	}
	*/
	
	@Test(dataProvider = "FollowUnFollow Channel", description = "Follow channel from channel profile",dependsOnMethods="unfollowChannelFromDiscoverPage", alwaysRun = true)
	public void followChannelFromProfile(String emailId, String password, String channelName, String runMode) throws Exception {
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
		channelProfile.clickOnFollowButton();
		
		boolean status = discoverPage.verifyChannelFollowedToast();
		Assert.assertTrue(status, "Channel followed successfully.");
	}
	
	@Test(dataProvider="FollowUnFollow Channel",description = "unfollowChannelFromDiscoverPage", alwaysRun = true)
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
	
	@Test(dataProvider = "FollowUnFollow Channel",dependsOnMethods ="followChannelFromProfile", alwaysRun = true)
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
	
	@Test(dataProvider = "FollowUnFollow Channel", dependsOnMethods = "followChannelFromDiscoverPage", alwaysRun = true)
	public void unfollowChannelFromUserProfile(String emailId, String password, String channelName, String runMode) throws Exception {
		
		if(runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run mode for this data is marked N");
		}
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsExhibitor(emailId, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnLeftDrawerIcon();
		
		LeftDrawer leftDrawer = new LeftDrawer(driver);
		leftDrawer.clickOnUserProfileImage();
		int totalChannelsFollowed = leftDrawer.getTotalChannlesFollowed();
		leftDrawer.clickOnViewAllButton();
		leftDrawer.clickOnChannelName(channelName,totalChannelsFollowed);
		leftDrawer.clickOnChannelProfileButton();
		
		ChannelProfile channelProfile = new ChannelProfile(driver);
		channelProfile.clickOnRightOptionMenu();
		channelProfile.clickOnUnfollowChannelOption();
		channelProfile.clickOnYesButtonOnPopup();
		
		boolean status = channelProfile.verifyChannelUnfollowedToast();
		AssertionHelper.updateTestStatus(status);
	}
	
	
	
	@DataProvider(name = "FollowUnFollow Channel")
	public Object[][] getChannelToFollow() throws Exception{
	Object[][] data = getExcelData("Kisan.NetTestData.xlsx", "Follow Channels");
	return data;
	}
}
