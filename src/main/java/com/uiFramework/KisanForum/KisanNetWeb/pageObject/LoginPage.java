package com.uiFramework.KisanForum.KisanNetWeb.pageObject;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.uiFramework.KisanForum.KisanNetWeb.helper.assertion.VerificationHelper;
import com.uiFramework.KisanForum.KisanNetWeb.helper.database.ApplicationDBQuery;
import com.uiFramework.KisanForum.KisanNetWeb.helper.logger.LoggerHelper;
import com.uiFramework.KisanForum.KisanNetWeb.helper.wait.WaitHelper;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;


public class LoginPage {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(LoginPage.class);
	WaitHelper waitHelper;
	VerificationHelper verificationHelper;
	
	@FindBy(xpath ="//input[@type='tel']")
	WebElement txtboxMobileNumber;
		
	@FindBy(xpath = "//span[contains(text(),'next')]")
	WebElement btnNext;
	
	@FindBy(xpath = "//div[contains(text(),'Verify your phone number')]/parent::div/following-sibling::div[1]")
	WebElement btnClose;
	
	@FindBy(xpath = "//input[@type='tel']")
	WebElement enterYourCode;
	
	@FindBy(xpath = "//button[contains(text(),'Continue')]")
	WebElement continueButton;
	
	//@FindBy(xpath = "//*[@id=\"greenColorBody\"]/div[3]/iframe")
	@FindBy(tagName = "iframe")
	WebElement facebookLoginWindow;

	@FindBy(xpath = "//a[contains(text(),'Exhibitor Login')]")
	WebElement btnExhibitorLogin;
	
	@FindBy(id = "username")
	WebElement txtBoxEmail;
	
	@FindBy(id = "password")
	WebElement txtBoxPassword;
	
	@FindBy(xpath = "//input[@type='submit']")
	WebElement btnLogin;
	
	@FindBy(xpath = "//h4[contains(text(),' UNAUTHORIZED')]")
	WebElement unauthorizedMessage;
	
	@FindBy(xpath = "//strong[contains(text(),'Member')]")
	WebElement btnloginAsMember;
	
	@FindBy(xpath = "//strong[contains(text(),'Organization')]")
	WebElement btnloginAsOrganization;
	
	@FindBy(xpath = "//input[@placeholder='Your Number']")
	WebElement txtBoxYourNumber;
	
	@FindAll(@FindBy(xpath = "//div[@class='wrapper otp-verification-input-container ng-star-inserted']/input"))
	List<WebElement> boxesEnterOTP;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		new TestBase().getNavigationScreen("LoginPage",driver);
		TestBase.logExtentReport("Kisan.Net Login Page Object Created");
	}
	
	public void logExtentReport(String s1){
		TestBase.test.log(Status.INFO, s1);
	}
	
	public void switchToFacebookFrame() {
		log.info("Switching to facebook login window");
		logExtentReport("Switching to facebook login window");
		/*boolean yourNumber = verificationHelper.isDisplayed(yourNumberTextBox);
		if(yourNumber==true) {
			yourNumberTextBox.click();
		}*/
		waitHelper.waitForframeToBeAvailableAndSwitchToIt(facebookLoginWindow, 10);
		System.out.println("Switched to facebook window");
	}
		
	public void clickOnNextButton() {
		waitHelper.WaitForElementClickable(btnNext, 10);
		log.info("clicked on next button...");
		logExtentReport("clicked on next button...");
		btnNext.click();
	}
	
	public void clickOnContinueButton() {
		log.info("clicked on continue button...");
		logExtentReport("clicked on continue button...");
		waitHelper.WaitForElementClickable(continueButton, 40);
		continueButton.click();
	}
	
	public void clickOnCloseButton() {
		log.info("Closing visitor login popup");
		logExtentReport("Closing the visitor login popup");
		waitHelper.WaitForElementClickable(btnClose, 5);
		btnClose.click();
		driver.switchTo().defaultContent();
	}
	
	public void clickOnExhibitorLoginButton() {
		log.info("clicking on exhibitor login button...");
		logExtentReport("clicking on exhibitor login button...");
		waitHelper.WaitForElementClickable(btnExhibitorLogin, 40);
		btnExhibitorLogin.click();
	}
	
	public void enterEmailId(String emailId) {
		log.info("entering email id...."+emailId);
		logExtentReport("entering email id...."+emailId);
		waitHelper.waitForElementVisible(txtBoxEmail, 10);
		this.txtBoxEmail.click();
		this.txtBoxEmail.clear();
		this.txtBoxEmail.sendKeys(emailId);
	}
	
	public void enterPassword(String password) {
		log.info("entering password...."+password);
		logExtentReport("entering password...."+password);
		waitHelper.waitForElementVisible(txtBoxPassword, 10);
		this.txtBoxPassword.click();
		this.txtBoxPassword.clear();
		this.txtBoxPassword.sendKeys(password);
	}
	
	public void clickOnLoginButton() throws Exception {
		log.info("clicked on login button...");
		logExtentReport("clicked on login button...");
		waitHelper.WaitForElementClickable(btnLogin, 40);
		btnLogin.click();
	}
	
	public void loginAsExhibitor(String emailId, String password) throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		/*loginPage.switchToFacebookFrame();
		loginPage.clickOnCloseButton();
		loginPage.clickOnExhibitorLoginButton();*/
		
		loginPage.clickOnLoginAsOrganizationButton();
		loginPage.enterEmailId(emailId);
		loginPage.enterPassword(password);
		loginPage.clickOnLoginButton();
		if(isUnautorizedAccess()) {
			driver.navigate().back();
			loginAsExhibitor(emailId, password);
		}
	}
	
	public void loginAsVisitor(String mobile) throws Exception {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.clickOnLoginAsMemberButton();
		loginPage.enterMobileNumber(mobile);
		loginPage.clickOnNextButton();
		ApplicationDBQuery applicationDBQuery = new ApplicationDBQuery();
		String otp = applicationDBQuery.getOTP(mobile);
		System.out.println("Verification code is : " + otp);
		enterOTP(otp);
		loginPage.clickOnNextButton();
		Thread.sleep(5000);
		/*if(isUnautorizedAccess()) {
			driver.navigate().back();
			loginAsExhibitor(emailId, password);
		}*/
	}
	
	public void enterOTP(String otp) {
		String code[] = otp.split("");
		int i = 0;
		waitHelper.WaitForElementClickable(boxesEnterOTP.get(i), 10);
		for(String s : code) {
			waitHelper.WaitForElementClickable(boxesEnterOTP.get(i), 10);
			boxesEnterOTP.get(i).sendKeys(s);
			i= i+1;
		}
		
	}
	
	public boolean isUnautorizedAccess() {
		log.info("Checking for unautorized access");
		logExtentReport("Checking for unauthorized access");
		try {
			unauthorizedMessage.isDisplayed();
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public void clickOnLoginAsMemberButton() {
		waitHelper.WaitForElementClickable(btnloginAsMember, 10);
		log.info("clicked on login as member button...");
		logExtentReport("clicked on login as member button...");
		btnloginAsMember.click();
	}
	
	public void clickOnLoginAsOrganizationButton() {
		waitHelper.WaitForElementClickable(btnloginAsOrganization, 10);
		log.info("clicking on login as organization button...");
		logExtentReport("clicking on login as organization button...");
		btnloginAsOrganization.click();
	}
	
	public void enterMobileNumber(String mobileNumber) {
		waitHelper.waitForElementVisible(txtBoxYourNumber, 10);
		log.info("entering mobile number...."+mobileNumber);
		logExtentReport("entering mobile number...."+mobileNumber);
		txtBoxYourNumber.click();
		txtBoxYourNumber.sendKeys(mobileNumber);
		//this.txtboxMobileNumber.sendKeys(mobileNumber);
	}
}
