package com.uiFramework.KisanForum.KisanNetWeb.testScripts.Search;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.helper.assertion.AssertionHelper;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.DiscoverPage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class SearchNonExistingChannelOnDiscoverPage extends TestBase {
	
	@Test(dataProvider = "Channel To Be Searched", description = "Search non existing channel on discover page")
	public void searchNonExistingChannelOnDiscoverPage(String emailId, String password, String channelName, String existingChannel, String runMode) throws Exception {
		
			if(runMode.equalsIgnoreCase("n") || existingChannel.equalsIgnoreCase("y")) {
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
			
			//homePage.clickOnDiscoverIcon();// To be removed later added to test retry mechanism
			boolean status = discoverPage.verifyNoChannelsFoundToast();
			AssertionHelper.updateTestStatus(status);
		}
	
	
	@DataProvider(name = "Channel To Be Searched")
	public Object[][] getChannelToBeFollowed() throws Exception{
		Object[][] data = getExcelData("Kisan.NetTestData.xlsx", "Search Channels");
		return data;
	}

}
