package com.uiFramework.KisanForum.KisanNetWeb.testScripts.loginPage;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uiFramework.KisanForum.KisanNetWeb.pageObject.Categories;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.HomePage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.LoginPage;
import com.uiFramework.KisanForum.KisanNetWeb.pageObject.SignUpPage;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class SignUp extends TestBase{
	
	@Test(dataProvider="testData",description="Signup to App")
	public void signUpToApp(String mobile, String firstName,String lastName,String state, String district, String pincode,String runMode) throws Exception {
		if(runMode.equalsIgnoreCase("n")) {
			throw new SkipException("Run mode for this data is marked N");
		}		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginAsVisitor("1234567823"+ "");			// incase we need to login with different number
		//loginPage.loginAsVisitor(mobile);
		SignUpPage signupPage = new SignUpPage(driver);
		signupPage.clickOnPhotoIcon();
		signupPage.clickOnUploadPhotoOption();
		signupPage.uploadPhoto("Image For Followers.jpg");
		signupPage.clickOnCropUserImageButton();
		signupPage.clickOnIgnoreIfErrorButton();
		signupPage.enterFirstName(firstName);
		signupPage.enterLastName(lastName);
		signupPage.selectState(state);
		signupPage.selectDistrict(district);
		signupPage.enterPincode(pincode);
		signupPage.clickOnNextButton();
		
		Categories categories = new Categories(driver);
		categories.updateCategories();
		categories.clickOnNextButton();
		
		/*HomePage homePage = new HomePage(driver);
		Thread.sleep(20000);*/
	}
	
	@DataProvider(name="testData")
	public Object[][] testData() throws Exception{
		Object[][] data = getExcelData("Kisan.NetTestData.xlsx", "SignupData");
		return data;
	}	

}
