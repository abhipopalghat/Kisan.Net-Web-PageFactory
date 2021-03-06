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
import com.uiFramework.KisanForum.KisanNetWeb.helper.browserConfiguration.config.ObjectReader;
import com.uiFramework.KisanForum.KisanNetWeb.helper.logger.LoggerHelper;
import com.uiFramework.KisanForum.KisanNetWeb.helper.wait.WaitHelper;
import com.uiFramework.KisanForum.KisanNetWeb.testbase.TestBase;

public class DiscoverPage {
	
	WebDriver driver;
	WaitHelper waitHelper;
	VerificationHelper verificationHelper;
	private final Logger log = LoggerHelper.getLogger(DiscoverPage.class);
	
	@FindBy(xpath = "//span[contains(text(),'Got It')]")
	WebElement btnGotIt;
	
	@FindBy(xpath = "//div[@class='loadingoverlay']")
	WebElement lodingOverlay;
	
	@FindBy(xpath = "//span[contains(text(),'Discover Channels')]")
	WebElement discoverPageHeaderText;
	
	@FindBy(xpath = "//i[@class='zmdi zmdi-close']")
	WebElement btnClose;
	
	@FindBy(xpath = "//input[@class='search-btn ib-m']")
	WebElement btnSearch;
	
	@FindBy(xpath = "//div[@class='filterBlock text-center']")
	WebElement btnFilter;
	
	@FindBy(xpath = "//input[@type='search']")
	WebElement txtBoxSearch;
	
	@FindAll(@FindBy(xpath = "//h4[@class='mrgn-t-md']"))
	List<WebElement> channelList;
	
	@FindBy(xpath = "//div[@id='toast-container']")
	WebElement noChannelsFoundToast;
	
	@FindAll(@FindBy(xpath = "//mat-card-actions[@class='mrgn-b-none pad-b-md mat-card-actions']"))
	List<WebElement> followButtonList;		
	
	@FindBy(xpath = "//div[@aria-label='Channel Followed successfully.']")
	WebElement channelFollowedToast;
	
	
	public DiscoverPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
		waitHelper = new WaitHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		new TestBase().getNavigationScreen("DiscoverPage",driver);
		TestBase.logExtentReport("Discover page object created");
	}
	
	public void logExtentReport(String s1) {
		TestBase.test.log(Status.INFO, s1);
	}

	
	public void clickOnGotItButton() throws Exception {
		log.info("Clicking on Got It button");
		logExtentReport("Clicking on Got It button");
		boolean isGotItButtonPresent = verifyGotItButton();
		boolean isLoaderPresent = false;
		if(isGotItButtonPresent) {
			isLoaderPresent = verificationHelper.isDisplayed(lodingOverlay);
			if(isLoaderPresent) {
				Thread.sleep(8000);
				btnGotIt.click();
			}
			else {
				btnGotIt.click();
			}
			
		}
		else {
		System.out.println("Got it button is not present");
		}
	}
	
	public boolean verifyGotItButton() {
		log.info("Verifying Got It button");
		logExtentReport("Verifying Got It button");
		boolean gotItButtonPresent = verificationHelper.isDisplayed(btnGotIt);
		return gotItButtonPresent;
	}
	
	public String getHeaderText() {
		log.info("Getting discover page header text");
		logExtentReport("Getting discover page header text");
		String headerText = verificationHelper.getText(discoverPageHeaderText);
		return headerText;
	}
	
	public void clickOnCloseButton() {
		log.info("Clicking on Close button");
		logExtentReport("Clicking on close button");
		waitHelper.WaitForElementClickable(btnClose, ObjectReader.reader.getExplicitWait());
		btnClose.click();
	}
	
	public void clickOnSearchButton() throws Exception {
		log.info("Clicking on search button");
		logExtentReport("Clicking on search button");
		waitHelper.WaitForElementClickable(btnSearch, ObjectReader.reader.getExplicitWait());
		btnSearch.click();
	}
	
	public void clickOnFilterButton() {
		log.info("Clicking on filter button");
		logExtentReport("Clicking on filter button");
		waitHelper.WaitForElementClickable(btnFilter, ObjectReader.reader.getExplicitWait());
		btnFilter.click();
	}
	
	public void enterChannelNameInSearchbox(String channelName) throws Exception {
		log.info("Entering channel name in search box");
		logExtentReport("Entering channel name in search box");
		waitHelper.waitForElementVisible(txtBoxSearch, ObjectReader.reader.getExplicitWait());
		txtBoxSearch.sendKeys(channelName);
		Thread.sleep(4000);
	}
	
	public boolean isChannelExists(String channelName) throws Exception {
		List<WebElement> channelList = getChannelList();
		boolean presenceOfChannel = false;
		for(int i=0; i<channelList.size(); i++) {
			if(channelList.get(i).getText().equals(channelName)) {
				presenceOfChannel = true;
				break;
			}
			else {
				presenceOfChannel = false;
			}
		}
		return presenceOfChannel;
	}

	public List<WebElement> getChannelList() {
		return channelList;
	}
	
	public boolean verifyNoChannelsFoundToast() {
		return new VerificationHelper(driver).isDisplayed(noChannelsFoundToast);
	}
	
	public int getChannelId(String channelName) {
		List<WebElement> channelList = getChannelList();
		boolean presenceOfChannel = false;
		int i;
		for(i=0; i<channelList.size(); i++) {
			if(channelList.get(i).getText().equals(channelName)) {
				presenceOfChannel = true;
				break;
			}
			else {
				presenceOfChannel = false;
			}
		}
		return i;
	}
	
	public void clickOnChannelName(String channelName) throws Exception {
		List<WebElement> channelList = getChannelList();
		int i;
		for(i=0; i<channelList.size(); i++) {
			if(channelList.get(i).getText().equals(channelName)) {
				channelList.get(i).click();
				Thread.sleep(3000);
				break;
			}
			else {
				continue;
			}
		}
	}
	
	public void clickOnFollowButton(String channelName) {
		int i = getChannelId(channelName);
		followButtonList.get(i).click();
	}
	
	public boolean verifyChannelFollowedToast() {
		return new VerificationHelper(driver).isDisplayed(channelFollowedToast);
	}
	
}
