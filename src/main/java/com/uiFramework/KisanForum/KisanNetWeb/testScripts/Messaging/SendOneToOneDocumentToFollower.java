package com.uiFramework.KisanForum.KisanNetWeb.testScripts.Messaging;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelChatWindow;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LeftDrawer;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class SendOneToOneDocumentToFollower extends TestBase {


	
	@Test(dataProvider = "Send one to one document to follower")
	public void sendOneToOneDocumentToFollower(String emailId, String password, String channelName, String message, String runMode) throws Exception {
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
		//channelChatWindow.clickOnDocumentOption();
		channelChatWindow.uploadDocument("Document For Followers.docx");
		
		/*fileUpload.CopyFilePath("Document For Followers.docx");
		fileUpload.PasteFilePath();
		fileUpload.ClickEnter();*/
		
		channelChatWindow.clickOnSendImageOrVideoButton();
		Thread.sleep(5000);
	}
	
	@DataProvider(name = "Send one to one document to follower")
	public Object[][] getDocumentForFollower() throws Exception{
		Object[][] dataSet = getExcelData("Kisan.NetTestData.xlsx", "SendMessageOneToOne");
		return dataSet;
	}

}
