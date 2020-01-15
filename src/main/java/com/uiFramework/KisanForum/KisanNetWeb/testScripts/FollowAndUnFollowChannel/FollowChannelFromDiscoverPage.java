package com.uiFramework.KisanForum.KisanNetWeb.testScripts.FollowAndUnFollowChannel;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.pageObject.DiscoverPage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LeftDrawer;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class FollowChannelFromDiscoverPage extends TestBase{
	
	@Test(dataProvider = "Follow Channel From Discover", description = "Follow channel from discover page",groups = "FollowFromDiscover")
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
	
	@Test(dataProvider = "Follow Channel From Discover", description = "Follow channel from discover page")
	public void followChannelFromDiscoverPage1(String emailId, String password, String channelName, String runMode) throws Exception {
		if(runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run mode for this data is marked N");
		}
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsExhibitor(emailId, password);
		
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
	
	@DataProvider(name = "Follow Channel From Discover")
	public Object[][] getChannelToFollow() throws Exception{
	Object[][] data = getExcelData("Kisan.NetTestData.xlsx", "Follow Channels");
	return data;
	}

}
