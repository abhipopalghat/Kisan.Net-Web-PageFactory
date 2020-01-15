package com.uiFramework.KisanForum.KisanNetWeb.testScripts.Messaging;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelChatWindow;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LeftDrawer;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class SendDocumentToFollowers extends TestBase{
	
	@Test(dataProvider = "Send document to channel followers")
	public void sendDocumentToFollowers(String emailId, String password, String channelName, String message, String runMode) throws Exception {
		
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
		//channelChatWindow.clickOnDocumentOption();
		channelChatWindow.uploadDocument("Document For Followers.pdf");
		
		/*fileUpload.CopyFilePath("Document For Followers.docx");
		fileUpload.PasteFilePath();
		fileUpload.ClickEnter();*/
		
		channelChatWindow.clickOnSendImageOrVideoButton();
		Thread.sleep(5000);
	}
	
	@DataProvider(name = "Send document to channel followers")
	public Object[][] getDocForFollowers() throws Exception{
		Object[][] dataSet = getExcelData("Kisan.NetTestData.xlsx", "SendMessage");
		return dataSet;
	}

}
