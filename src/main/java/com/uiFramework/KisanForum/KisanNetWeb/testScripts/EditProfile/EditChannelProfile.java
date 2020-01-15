package com.uiFramework.KisanForum.KisanNetWeb.testScripts.EditProfile;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.helper.fileUpload.FileUploadHelper;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.Categories;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelChatWindow;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.ChannelProfile;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LeftDrawer;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class EditChannelProfile extends TestBase{
	
	//public FileUploadHelper fileUpload = new FileUploadHelper();
	
	@Test(dataProvider = "Edit channel profile")
	public void editChannelProfile(String emailId, String password, String channelName, String description,String hexCode, String runMode) throws Exception{
		if(runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run mode for this data is marked N");
		}
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsExhibitor(emailId, password);
		//loginPage.loginToApp("exh_0008@mailinator.com", "kisankisan");

		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnLeftDrawerIcon();
		
		LeftDrawer leftDrawer = new LeftDrawer(driver);
		leftDrawer.clickOnMyChannelOption();
		
		ChannelChatWindow channelChatWindow = new ChannelChatWindow(driver);
		channelChatWindow.clickOnBroadcastTextInHeader();
		
		/*ChannelDashboard channelDashboard = new ChannelDashboard(driver);
		channelDashboard.clickOnChannelNameInHeaderForAdmin();*/
		
		ChannelProfile channelProfile = new ChannelProfile(driver);
		channelProfile.clickOnRightOptionMenuForAdmin();
		channelProfile.clickOnEditProfileOption();
		
		//channelProfile.clickOnChannelProfilImage();
		channelProfile.uploadImage("Channel Profile Image.jpg");
		/*fileUpload.CopyFilePath("Channel Profile Image.jpg");
		fileUpload.PasteFilePath();
		fileUpload.ClickEnter();*/
		channelProfile.clickOnCropProfileImageButton();
		
		channelProfile.enterChannelName(channelName);
		//channelProfile.enterChannelName("Neel Kamal Bio");

		
		channelProfile.clickOnColorBucket();
		channelProfile.setBackgroundColorCode(hexCode);
		channelProfile.clickOnOkButtonOnSetBackground();
		
		channelProfile.enterChannelDescription(description);
		
		channelProfile.clickOnAddMoreCategoriesButton();	
		Categories categories = new Categories(driver);
		categories.updateCategories();
		categories.clickOnNextButton();
		
		channelProfile.clickOnDoneButton();
		
		Thread.sleep(5000);
	}
	
	@DataProvider(name = "Edit channel profile")
	public Object[][] getEditChannelDetails() throws Exception {
		Object[][] dataSet = getExcelData("Kisan.NetTestData.xlsx", "Edit Channel");
		return dataSet;
	}
}
