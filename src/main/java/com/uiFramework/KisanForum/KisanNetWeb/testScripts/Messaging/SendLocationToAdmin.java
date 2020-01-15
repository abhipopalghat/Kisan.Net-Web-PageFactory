package com.uiFramework.KisanForum.KisanNetWeb.testScripts.Messaging;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelChatWindow;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelDashboard;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class SendLocationToAdmin extends TestBase{
	
	@Test(dataProvider = "Send location to channel admin")
	public void sendLocationToAdmin(String emailId, String password, String channelName, String message, String runMode) throws Exception {
		
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
		channelDashboard.clickOnChatWithOwnerIcon();
		
		ChannelChatWindow channelChatWindow = new ChannelChatWindow(driver);
		channelChatWindow.clickOnAttachmentPin();
		channelChatWindow.clickOnLocationOption();
		channelChatWindow.clickOnSelectLocationHeader();
		channelChatWindow.clickOnCurrentLocation();
		channelChatWindow.clickOnSendLocationButton();
		
		Thread.sleep(5000);
	}
	
	@DataProvider(name = "Send location to channel admin")
	public Object[][] getlocationForAdmin() throws Exception{
		Object[][] dataSet = getExcelData("Kisan.NetTestData.xlsx", "SendMessageOneToOne");
		return dataSet;
	}

}
