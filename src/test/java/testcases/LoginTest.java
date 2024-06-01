package testcases;

import org.testng.annotations.Test;

import pages.DashboardPage;
import pages.LoginPage;
import testbase.TestBase;
import utilities.YAMLHelper;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class LoginTest {
	private static final Logger logger = (Logger) LogManager.getLogger(LoginTest.class);
	private WebDriver dr;
	TestBase testBase = new TestBase();
	LoginPage loginPage;
	DashboardPage dashboardPage;
	YAMLHelper yamlHelper = new YAMLHelper();
	@Test(enabled=true, description="Test Valid Cred", priority=-1)
  public void TC_001_verifyLoginSuccessfull() throws IOException {
	  
	  Map<Object, Object> map = yamlHelper.getTestcaseSpecificData("valid credentals");
	  loginPage.loginToApp(map.get("username").toString(), map.get("password").toString());
	  boolean actual = dashboardPage.isDashboardDisplayed();
	  testBase.attachScreenShot();
	  Assert.assertEquals(actual, true);
  }

  @Test(dataProvider = "loginCredentials",enabled=true, description="Test Wrong Cred", priority=2)
  public void TC_002_verifyLoginUnSuccessfull(String user, String pass) throws IOException {
	  
	  loginPage.loginToApp(user, pass);
	  boolean actual = dashboardPage.isDashboardDisplayed();
	  testBase.attachScreenShot();
	  Assert.assertEquals(actual, false);
  }
  @BeforeMethod(alwaysRun=true)
  public void beforeMethod() throws IOException {
	  dr = testBase.getInstance(); 
	  loginPage = new LoginPage(dr);
	  dashboardPage = new DashboardPage(dr);
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
  
  @DataProvider(name = "loginCredentials")
  public Object[][] loginCredentials() {
   return new Object[][] {
     { "Admin1", "admin123"},
     { "Admin", "admin1234"},
     { "Admin1","admin1234"}
   };
  }

}
