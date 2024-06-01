package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.TestBase;

public class DashboardPage {
	private static final Logger logger = (Logger) LogManager.getLogger(DashboardPage.class);
	private WebDriver driver;
	public DashboardPage(WebDriver dr)
	{
		this.driver=dr;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//h6[text()='Dashboard']")
	private WebElement header;
	
	@FindBy(className="oxd-userdropdown-name")
	private WebElement userDropdown;
	
	@FindBy(linkText="Logout")
	private WebElement logout;
	
	@FindBy(xpath="//*[@class='oxd-main-menu-item']/child::span[text()='PIM']")
	private WebElement pimMenu;
	
	public boolean isDashboardDisplayed()
	{
		boolean b = false;
		try {
			b = header.isDisplayed();
		}catch(Exception e) {
			e.getMessage();
		}
		
		return b;
	}
	
	public void logout()
	{
		userDropdown.click();
		logout.click();
	}
	
	public void clickOnPIM()
	{
		pimMenu.click();
	}
	
}
