package testcases;

import org.testng.annotations.Test;

import pages.ForgotYourPasswordPage;
import pages.LoginPage;
import testbase.TestBase;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class ForgotYourPasswordTest {
	private static final Logger logger = (Logger) LogManager.getLogger(ForgotYourPasswordTest.class);
	private WebDriver dr;
	TestBase testBase = new TestBase();
	LoginPage loginPage;
	ForgotYourPasswordPage fyp;
	@Test(enabled=true, description="FYP Test", priority=1)
  public void TC_003verifyForgotYourPasswordPage() throws IOException {
	  loginPage.clickOnForgotYourPassword();
	  boolean act = fyp.isResetButtonDisplayed();
	  testBase.attachScreenShot();
	  Assert.assertEquals(act, true);
  }
  @BeforeMethod(alwaysRun=true)
  public void beforeMethod() throws IOException {
	  dr = testBase.getInstance(); 
	  loginPage = new LoginPage(dr);
	  fyp = new ForgotYourPasswordPage(dr);
  }

  @AfterMethod(alwaysRun=true)
  public void afterMethod() {
	  dr.quit();
  }

}
