package com.uiFramework.KisanForum.KisanNetWeb.testScripts.Messaging;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelChatWindow;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LeftDrawer;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class SendOneToOneAudioToFollower extends TestBase{
	

	
	@Test(dataProvider = "Send one to one audio to follower")
	public void sendOneToOneAudioToFollower(String emailId, String password, String channelName, String message, String runMode) throws Exception {
		if(runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run mode for this data is marked N");
		}
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsExhibitor(emailId, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnLeftDrawerIcon();
		
		LeftDrawer leftDrawer = new LeftDrawer(driver);
		leftDrawer.clickOnMyChannelOption();
		
		ChannelChatWindow channelChatWindow = new ChannelChatWindow(driver);
		channelChatWindow.clickOnFirstFollowerFromOneToOneChatList();
		channelChatWindow.clickOnAttachmentPin();
		channelChatWindow.clickOnAudioOption();
		//channelChatWindow.clickOnChooseAudioOption();
		channelChatWindow.uploadAudio("Audio For Followers.mp3");	
		
		/*fileUpload.CopyFilePath("Audio For Followers.mp3");
		fileUpload.PasteFilePath();
		fileUpload.ClickEnter();*/
		
		channelChatWindow.clickOnSendAudioButton();
		Thread.sleep(5000);
	}
	
	@DataProvider(name = "Send one to one audio to follower")
	public Object[][] getAudioForFollower() throws Exception{
		Object[][] dataSet = getExcelData("Kisan.NetTestData.xlsx", "SendMessageOneToOne");
		return dataSet;
	}

}
