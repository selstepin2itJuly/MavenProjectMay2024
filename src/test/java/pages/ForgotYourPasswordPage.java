package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBase;

public class ForgotYourPasswordPage {
	private static final Logger logger = (Logger) LogManager.getLogger(ForgotYourPasswordPage.class);
	private WebDriver driver;

	public ForgotYourPasswordPage(WebDriver dr) {
		this.driver = dr;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[type='submit']")
	private WebElement resetButton;
	
	public boolean isResetButtonDisplayed()
	{
		boolean b = false;
		try {
			b = resetButton.isDisplayed();
		}catch(Exception e) {
			e.getMessage();
		}
		
		return b;
	}
}
