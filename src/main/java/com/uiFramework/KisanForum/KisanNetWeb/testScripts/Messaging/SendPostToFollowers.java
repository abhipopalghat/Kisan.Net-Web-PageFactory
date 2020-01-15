package com.uiFramework.KisanForum.KisanNetWeb.testScripts.Messaging;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelChatWindow;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LeftDrawer;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class SendPostToFollowers extends TestBase{
	
	@Test(dataProvider = "Send post to channel followers")
	public void sendImagePostToFollowers(String emailId, String password, String channelName, String message, String runMode) throws Exception {
		
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
		channelChatWindow.clickOnAttachmentPin();
		channelChatWindow.clickOnPostOption();
		
		channelChatWindow.enterPostTitle(message);
		channelChatWindow.enterPostDescription(message);
		channelChatWindow.uploadPostMedia("Image For Followers.jpg");
				
		channelChatWindow.clickOnCropPostMediaButton();
		Thread.sleep(1000);
		channelChatWindow.clickOnSendPostButton();
		
		Thread.sleep(5000);
	}
	
	@Test(dataProvider = "Send post to channel followers")
	public void sendVideoPostToFollowers(String emailId, String password, String channelName, String message, String runMode) throws Exception {
		
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
		channelChatWindow.clickOnAttachmentPin();
		channelChatWindow.clickOnPostOption();
		
		channelChatWindow.enterPostTitle(message);
		channelChatWindow.enterPostDescription(message);
		//channelChatWindow.clickOnAttachPostMediaButton();
		channelChatWindow.uploadPostMedia("Video For Followers.mp4");
		
		/*fileUpload.CopyFilePath("Video For Followers.mp4");
		fileUpload.PasteFilePath();
		fileUpload.ClickEnter();*/
		
		channelChatWindow.clickOnSendPostButton();
		
		Thread.sleep(5000);
	}
	
	@DataProvider(name = "Send post to channel followers")
	public Object[][] getPostDetailsForFollowers() throws Exception{
		Object[][] dataSet = getExcelData("Kisan.NetTestData.xlsx", "SendMessage");
		return dataSet;
	}


}
