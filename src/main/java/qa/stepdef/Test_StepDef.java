package qa.stepdef;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import qa.SeleniumUtils.InstanceHelper;
import qa.config.Config;
import qa.pages.PageObject;
import qa.config.*;
import qa.CucumberUtils.Reporter;
import qa.SeleniumUtils.*;

public class Test_StepDef {

	PageObject pageObj;
	DriverUtils driveUtils;
	
	public Test_StepDef(DriverUtils driveUtils, PageObject pageObj) {
		this.pageObj = pageObj;
		this.driveUtils = driveUtils;
	}
	
	@Given("^User can open github site and do a search$")
	public void user_can_log_into_the_application_as_expected(){
		WebDriver driver = driveUtils.getDriver();
		driver.get(Config.appUrl);
		Reporter.writeTextToReport("Getting to site: "+ Config.appUrl);
	}

	@Then("^search result can be returned$")
	public void user_can_see_his_name_in_the_top_right_corner(){
		WebDriver driver = driveUtils.getDriver();
	
		WebElement e=SeleniumCommon.waitUntilClickableThenClick(driver, pageObj.searchBox);
		e.sendKeys("cucumber");
		e.sendKeys(Keys.ENTER);
		SeleniumCommon.sleepInHalfSec(4);
		Assert.assertTrue(pageObj.getResultInfo().startsWith("We’ve found"));
		Reporter.takeScreenShot();
	}
}

//public class Test_StepDef {
//	InstanceHelper helper;
//
//	public Test_StepDef(InstanceHelper helper) {
//		this.helper = helper;
//	}
//
//	@Given("^User can open github site and do a search$")
//	public void user_can_log_into_the_application_as_expected() {
//		WebDriver driver = helper.getTheDriver();
//		driver.get(Config.appUrl);
//
//		Reporter.writeTextToReport("Getting to site: " + Config.appUrl);
//
//	}
//
//	@Then("^search result can be returned$")
//	public void user_can_see_his_name_in_the_top_right_corner() {
//		WebDriver driver = helper.getTheDriver();
//		PageObject pageObj = helper.getPageObj(driver);
//		WebElement e = SeleniumCommon.waitUntilClickableThenClick(driver, pageObj.searchBox);
//		e.sendKeys("cucumber");
//		e.sendKeys(Keys.ENTER);
//		SeleniumCommon.sleepInHalfSec(4);
//		Assert.assertTrue(pageObj.getResultInfo().startsWith("We’ve found"));
//		Reporter.takeScreenShot();
//	}
//}
