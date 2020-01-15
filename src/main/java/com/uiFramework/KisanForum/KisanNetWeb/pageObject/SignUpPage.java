package com.uiFramework.KisanForum.KisanNetWeb.pageObject;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;
import com.uiFramework.KisanForum.KisanNetWeb.helper.assertion.VerificationHelper;
import com.uiFramework.KisanForum.KisanNetWeb.helper.browserConfiguration.config.ObjectReader;
import com.uiFramework.KisanForum.KisanNetWeb.helper.fileUpload.FileUploadHelper;
import com.uiFramework.KisanForum.KisanNetWeb.helper.logger.LoggerHelper;
import com.uiFramework.KisanForum.KisanNetWeb.helper.select.DropDownHelper;
import com.uiFramework.KisanForum.KisanNetWeb.helper.wait.WaitHelper;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class SignUpPage {

	private WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(SignUpPage.class);
	WaitHelper waitHelper;
	VerificationHelper verificationHelper;
	FileUploadHelper fileUploadHelper;
	DropDownHelper dropdownHelper;
	
	
	@FindBy(xpath ="//a[contains(text(),'Next')]")
	WebElement btnNext;
	
	@FindBy(xpath ="//input[@placeholder='First name']")
	WebElement txtboxFirstName;
	
	@FindBy(xpath ="//input[@placeholder='Last name']")
	WebElement txtboxLastName;
	
	@FindBy(xpath ="//input[@placeholder='Pincode']")
	WebElement txtboxPincode;
	
	@FindBy(xpath ="//span[contains(text(),'State')]")
	WebElement dropdownState;
	
	@FindAll(@FindBy(xpath = "//mat-option[@role='option']"))
	List<WebElement> allStates;
	
	@FindBy(xpath ="//span[contains(text(),'District')]")
	WebElement dropdownDistrict;
	
	@FindAll(@FindBy(xpath = "//mat-option[@role='option']"))
	List<WebElement> allDistricts;
	
	@FindBy(xpath ="//video[@id='myImgVideo']")
	WebElement btnPhoto;
	
	@FindBy(xpath ="//div[@class='mat-menu-content']/button[2]")
	WebElement btnUploadPhoto;
	
	@FindBy(xpath ="//button[contains(text(),'Crop')]")
	WebElement btnCropUserImage;
	
	@FindBy(xpath ="//a[contains(text(),'Ignore this error')]")
	WebElement btnIgnoreThisError;
	
	

	public SignUpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		fileUploadHelper = new FileUploadHelper(driver);
		dropdownHelper = new DropDownHelper(driver);
		new TestBase().getNavigationScreen("SignUpPage",driver);
		TestBase.logExtentReport("Kisan.Net Signup Page Object Created");
	}
	
	public void logExtentReport(String s1){
		TestBase.test.log(Status.INFO, s1);
	}

	public void clickOnPhotoIcon() {
		waitHelper.WaitForElementClickable(btnPhoto, 10);
		log.info("clicked on photo button...");
		logExtentReport("clicked on photo button...");
		btnPhoto.click();		
	}

	
	public void uploadPhoto(String fileName) {
		fileUploadHelper.CopyFilePath(fileName);
		fileUploadHelper.PasteFilePath();
		fileUploadHelper.ClickEnter();		
	}

	public void clickOnUploadPhotoOption() throws Exception {
		waitHelper.waitForElementVisible(btnUploadPhoto, 10);
		log.info("clicked on upload photo option...");
		logExtentReport("clicked on uplaod photo option...");
		Thread.sleep(2000);
		btnUploadPhoto.click();
	}

	public void clickOnCropUserImageButton() {
		log.info("Clicking on crop button of user image");
		logExtentReport("Clicking on crop button of user image");
		waitHelper.waitForElementVisible(btnCropUserImage, ObjectReader.reader.getExplicitWait());
		btnCropUserImage.click();
	}

	public void clickOnIgnoreIfErrorButton() {
		try {
		waitHelper.WaitForElementClickable(btnIgnoreThisError, 10);
		log.info("clicked on ignore this error button...");
		logExtentReport("clicked on ignore this error button...");
		btnIgnoreThisError.click();
		}
		catch (Exception e) {
		log.info("Human face identified...");
		logExtentReport("Human face identified...");
			
		}
	}

	public void enterFirstName(String firstName) {
		log.info("Entering first name of user");
		logExtentReport("Entering first name of user");
		waitHelper.waitForElementVisible(txtboxFirstName, ObjectReader.reader.getExplicitWait());
		txtboxFirstName.sendKeys(firstName);
		}

	public void enterLastName(String lastName) {
		log.info("Entering last name of user");
		logExtentReport("Entering last name of user");
		waitHelper.waitForElementVisible(txtboxLastName, ObjectReader.reader.getExplicitWait());
		txtboxLastName.sendKeys(lastName);
		}		
	
	public void selectState(String state) {
		log.info("selecting state of user");
		logExtentReport("selecting state of user");
		waitHelper.waitForElementVisible(dropdownState, ObjectReader.reader.getExplicitWait());
		dropdownHelper.selectByText(dropdownState, allStates, state);
		
	}

	public void selectDistrict(String district) {
		log.info("selecting district of user");
		logExtentReport("selecting district of user");
		waitHelper.waitForElementVisible(dropdownDistrict, ObjectReader.reader.getExplicitWait());
		dropdownHelper.selectByText(dropdownDistrict, allDistricts, district);
		
	}

	public void enterPincode(String pincode) {
		log.info("Entering pincode of user");
		logExtentReport("Entering pincode of user");
		waitHelper.waitForElementVisible(txtboxPincode, ObjectReader.reader.getExplicitWait());
		txtboxPincode.sendKeys(pincode);		
	}
	
	public void clickOnNextButton() {
		log.info("Clicking on next button to conplete sign up process");
		logExtentReport("Clicking on next button to conplete sign up process");
		waitHelper.waitForElementVisible(btnNext, ObjectReader.reader.getExplicitWait());
		btnNext.click();
	}

		
	}

		
	
	

