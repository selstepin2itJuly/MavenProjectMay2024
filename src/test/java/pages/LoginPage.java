package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private static final Logger logger = (Logger) LogManager.getLogger(LoginPage.class);
	private WebDriver driver;
	
	public LoginPage(WebDriver dr)
	{
		this.driver=dr;
		PageFactory.initElements(driver, this);
	}
	
 @FindBy(name="username")
 private WebElement username;
 
 @FindBy(name="password")
 private WebElement password;
 
 @FindBy(css="[type='submit']")
 private WebElement loginButton;
 
 @FindBy(className="orangehrm-login-forgot")
 private WebElement fyp;
 
 public void enterUsername(String user)
 {
	 username.sendKeys(user);
 }
 
 public void enterPassword(String pass)
 {
	 password.sendKeys(pass);
 }
 
 public void clickOnLoginButton()
 {
	 loginButton.click();
 }
 
 public void loginToApp(String user, String pass)
 {
	 enterUsername(user);
	 enterPassword(pass);
	 clickOnLoginButton();
 }
 
 public void clickOnForgotYourPassword()
 {
	 fyp.click();
 }
}
