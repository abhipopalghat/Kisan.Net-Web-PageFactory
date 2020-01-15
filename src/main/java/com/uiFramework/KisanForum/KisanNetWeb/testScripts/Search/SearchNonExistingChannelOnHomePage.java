package com.uiFramework.KisanForum.KisanNetWeb.testScripts.Search;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.helper.assertion.AssertionHelper;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class SearchNonExistingChannelOnHomePage extends TestBase {

@Test(dataProvider = "ChannelToBeSearched",description = "Search non-existing channel on home page")
	public void searchNonExistingChannelOnHomePage(String emailId, String password, String channelName, String existingChannel, String runMode) throws Exception {
		if(runMode.equalsIgnoreCase("n") || existingChannel.equalsIgnoreCase("y")) {
			if(runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run mode for this data is marked N");
			}
			else {
				throw new SkipException("This channel is already existing");
			}
		}	
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsExhibitor(emailId, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnRightOptionMenu();
		homePage.clickOnSearchButton();
		homePage.enterChannelNameInSearchBox(channelName);
	
		boolean status = homePage.verifyNoChannelsFoundToast();
		AssertionHelper.updateTestStatus(status);
	}
	
	@DataProvider(name = "ChannelToBeSearched")
	public Object[][] getChannelToBeSearch() throws Exception {
		Object[][] data = getExcelData("Kisan.NetTestData.xlsx", "Search Channels");
		return data;
	}
}
