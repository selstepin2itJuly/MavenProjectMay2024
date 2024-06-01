package testcases;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import pages.PIMPage;
import testbase.TestBase;

import org.testng.annotations.BeforeMethod;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class PIMTest {
	private static final Logger logger = (Logger) LogManager.getLogger(PIMTest.class);
	private WebDriver dr;
	TestBase testBase = new TestBase();
	LoginPage loginPage;
	DashboardPage dashboardPage;
	PIMPage pim;
 
	@Test(enabled=true, description="Get Record Count", priority=1)
  public void TC_009_GetRecordCountItemsCount() throws IOException {
	  loginPage.loginToApp("Admin", "admin123");
	  testBase.attachScreenShot();
	  boolean dlogin = dashboardPage.isDashboardDisplayed();
	  Assert.assertEquals(dlogin, true);
	  dashboardPage.clickOnPIM();
	  boolean pimpage = pim.isPIMHeaderDisplayed();
	  testBase.attachScreenShot();
	  Assert.assertEquals(pimpage, true);
	  int expected = pim.getTheCountOfRecords();
	  int actual = pim.getTheTableRecord();
	  testBase.attachScreenShot();
	  Assert.assertEquals(actual, expected);
  }
  
  @Test(enabled=true, description="Get Employee ID", priority=0)
  public void TC_009_GetIdOfEmployee() throws IOException {
	  loginPage.loginToApp("Admin", "admin123");
	  boolean dlogin = dashboardPage.isDashboardDisplayed();
	  testBase.attachScreenShot();
	  Assert.assertEquals(dlogin, true);
	  dashboardPage.clickOnPIM();
	  boolean pimpage = pim.isPIMHeaderDisplayed();
	  testBase.attachScreenShot();
	  Assert.assertEquals(pimpage, true);
	  String id = pim.getTheIDOfEmployee("Chief Financial Officer");
	  Assert.assertEquals(id, "007");
  }
  @BeforeMethod(alwaysRun=true)
  public void beforeMethod() throws IOException {
	  dr = testBase.getInstance(); 
	  loginPage = new LoginPage(dr);
	  dashboardPage = new DashboardPage(dr);
	  pim=new PIMPage(dr);
  }

  @AfterMethod(alwaysRun=true)
  public void afterMethod() {
	  try {
		  dashboardPage.logout();
	  }catch(Exception e)
	  {
		  e.getMessage();
	  }
	  dr.quit();
  }

}
