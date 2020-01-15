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

public class Categories {

	WebDriver driver;
	WaitHelper waitHelper;
	VerificationHelper verificationHelper;
	private final Logger log = LoggerHelper.getLogger(Categories.class);
	
	@FindAll(@FindBy(xpath = "//h4[@class='font-bold-four mrgn-b-none']"))
	List<WebElement> channelCategories;
	
	@FindBy(xpath = "//mat-list-item[@class='apply-filter width-auto mat-list-item']/div/a")
	WebElement btnNext;
	
	public void logExtentReport(String s1) {
		TestBase.test.log(Status.INFO, s1);
	}
	
	public Categories(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		verificationHelper = new VerificationHelper(driver);
		TestBase.logExtentReport("Categories Page Object Created");
	}
	
	public void updateCategories() throws Exception {
		log.info("Updating channel categories");
		logExtentReport("Updating channel categories");
		Thread.sleep(2000);
		waitHelper.waitForElementVisible(channelCategories.get(0), ObjectReader.reader.getExplicitWait());
		for(int i = 0; i<5; i++) {
			channelCategories.get(i).click();
		}
		Thread.sleep(2000);
	}
	
	public void clickOnNextButton() throws Exception {
		log.info("Clicking on next button");
		logExtentReport("Clicking on next button");
		waitHelper.WaitForElementClickable(btnNext, ObjectReader.reader.getExplicitWait());
		Thread.sleep(1000);
		btnNext.click();
	}
	
}
