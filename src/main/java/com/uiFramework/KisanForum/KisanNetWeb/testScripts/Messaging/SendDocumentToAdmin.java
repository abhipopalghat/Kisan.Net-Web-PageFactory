package com.uiFramework.KisanForum.KisanNetWeb.testScripts.Messaging;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelChatWindow;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelDashboard;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class SendDocumentToAdmin extends TestBase{
	
	@Test(dataProvider = "Send document to channel admin")
	public void sendDocumentToAdmin(String emailId, String password, String channelName, String message, String runMode) throws Exception {
		
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
		//channelChatWindow.clickOnDocumentOption();
		channelChatWindow.uploadDocument("Document For Followers.docx");
		/*fileUpload.CopyFilePath("Document For Followers.docx");
		fileUpload.PasteFilePath();
		fileUpload.ClickEnter();*/
		
		channelChatWindow.clickOnSendImageOrVideoButton();
		Thread.sleep(5000);
		
	}
	
	
	
	
	@DataProvider(name = "Send document to channel admin")
	public Object[][] getDocForAdmin() throws Exception{
		Object[][] dataSet = getExcelData("Kisan.NetTestData.xlsx", "SendMessageOneToOne");
		return dataSet;
	}

}
