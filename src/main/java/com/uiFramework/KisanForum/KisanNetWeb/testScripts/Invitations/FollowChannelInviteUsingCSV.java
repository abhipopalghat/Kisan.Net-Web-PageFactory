package com.uiFramework.KisanForum.KisanNetWeb.testScripts.Invitations;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.helper.assertion.AssertionHelper;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelDashboard;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelProfile;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.InvitePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class FollowChannelInviteUsingCSV extends TestBase{
	
	@Test(dataProvider = "Get Channel Name", description = "Invite to follow channel using csv")
	public void inviteToFollowChannelByCSV(String emailId, String password, String channelName, String mobileNumber, String runMode) throws Exception {
		if(runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run mode for this data is marked N ");
		}
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsExhibitor(emailId, password);
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnRightOptionMenu();
		homePage.clickOnSearchButton();
		homePage.enterChannelNameInSearchBox(channelName);
		homePage.clickOnChannelName(channelName);
		
		ChannelDashboard channelDashboard = new ChannelDashboard(driver);
		channelDashboard.clickOnChannelNameInHeader();
		
		ChannelProfile channelProfile = new ChannelProfile(driver);
		channelProfile.clickOnRightOptionMenu();
		channelProfile.clickOnInviteToChannelOption();
		
		InvitePage invitePage = new InvitePage(driver);
		invitePage.clickOnUploadCSVFile();
		invitePage.uploadCSV("sampleCSVfileForAppDownload.csv");
		/*fileUpload.CopyFilePath("sampleCSVfileForAppDownload.csv");
		fileUpload.PasteFilePath();
		fileUpload.ClickEnter();*/
		
		invitePage.clickOnSendCSVInviteButton();
		
		boolean status = invitePage.verifyInviteSuccessToast();
		AssertionHelper.updateTestStatus(status);
	}
	
	@DataProvider(name = "Get Channel Name")
	public Object[][] getChannelName() throws Exception{
		Object[][] data = getExcelData("Kisan.NetTestData.xlsx", "Invite To Follow Channel");
		return data;
	}

}
