package com.uiFramework.KisanForum.KisanNetWeb.testScripts.Search;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.helper.assertion.AssertionHelper;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.DiscoverPage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class SearchExistingChannelOnDiscoverPage extends TestBase {
	
	@Test(dataProvider="Channel To Be Searched",description = "Search existing channel on discover page")
	public void searchExistingChannelOnDiscoverPage(String emailId, String password, String channelName,String existingChannel, String runMode) throws Exception {
		if(runMode.equalsIgnoreCase("n") || existingChannel.equalsIgnoreCase("n")) {
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
		
		boolean status = discoverPage.isChannelExists(channelName);
		AssertionHelper.updateTestStatus(status);
	}
	
	
	@DataProvider(name = "Channel To Be Searched")
	public Object[][] getChannelToBeSearched() throws Exception{
		Object[][] data = getExcelData("Kisan.NetTestData.xlsx", "Search Channels");
		return data;
	}

}
