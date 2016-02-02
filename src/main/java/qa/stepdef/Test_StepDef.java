package qa.stepdef;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import junit.framework.Assert;
import qa.SeleniumUtils.InstanceHelper;
import qa.config.Config;
import qa.pages.PageObject;
import qa.config.*;
import qa.SeleniumUtils.*;

public class Test_StepDef {
	InstanceHelper helper;
	
	public Test_StepDef(InstanceHelper helper) {
		this.helper = helper;
	}
	
	@Given("^User can log into the application as expected$")
	public void user_can_log_into_the_application_as_expected(){
		WebDriver driver = helper.getTheDriver();
		driver.get(Config.url);
	}

	@Then("^User can see his name in the top right corner$")
	public void user_can_see_his_name_in_the_top_right_corner(){
		WebDriver driver = helper.getTheDriver();
		PageObject pageObj = helper.getPageObj(driver);
		
		String name = SeleniumCommon.waitUntilVisibleThenGetText(driver, pageObj.userName);
		Assert.assertEquals(name, "Mike");
		
	}

}