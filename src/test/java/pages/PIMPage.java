package pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testbase.Base;

public class PIMPage extends Base{
	private static final Logger logger = (Logger) LogManager.getLogger(PIMPage.class);
	private WebDriver driver1;
	public PIMPage(WebDriver dr)
	{
		this.driver1=dr;
		PageFactory.initElements(driver1, this);
	}

	@FindBy(xpath="//h6[text()='PIM']")
	private WebElement pimHeader;
	
	@FindBy(css="[class='orangehrm-horizontal-padding orangehrm-vertical-padding']>span")
	private WebElement count;
	
	@FindBy(css="[class*='oxd-pagination-page-item oxd-pagination-page-item--page']")
	private List<WebElement> paging;
	
	@FindBy(css="[class*='oxd-table-body']>[class='oxd-table-card']")
	private List<WebElement> records;
	
	@FindBy(xpath="//*[contains(@class,'oxd-table-body')]/*[@class='oxd-table-card']/div/div[5]")
	private List<WebElement> jobtitle;
	
	public boolean isPIMHeaderDisplayed()
	{
		boolean b = false;
		try {
			b = pimHeader.isDisplayed();
		}catch(Exception e) {
			e.getMessage();
		}
		
		return b;
	}
	
	public int getTheCountOfRecords()
	{
		// (128) Records Found
		String text =count.getText();
		logger.info("Records Text-->"+text);
		String[] arr=text.split(" ");
		logger.info("Record-->"+arr[0]+"-"+arr[1]);
		CharSequence ss = arr[0].subSequence(1, arr[0].length()-1);
		logger.info("Record-->"+ss);
		return Integer.parseInt(String.valueOf(ss));
	}
	
	public int getTheTableRecord()
	{
		waitForElementClickable(paging.get(0));
		logger.info("Record-->"+paging.get(0).getText());
		int pages =paging.size();
		logger.info("Paging Size-->"+pages);
		int counter=0;
		for(int i=0;i<pages;i++)
		{
			paging.get(i).click();
			waitForElementVisible(records.get(i));
			counter = counter+records.size();
			logger.info("Record-->"+records.size());
			logger.info("Record-->"+paging.get(i)+"-->"+counter);
		}
		logger.info("total Record-->"+counter);
		return counter;
	}
	
	public String getTheIDOfEmployee(String role)
	{
		waitForElementClickable(paging.get(0));
		logger.info("Record-->"+paging.get(0).getText());
		int pages =paging.size();
		logger.info("Paging Size-->"+pages);
		String idNum=null;
		for(int i=0;i<pages;i++)
		{
			paging.get(i).click();
			waitForElementVisible(jobtitle.get(i));
			logger.info("Role-->"+jobtitle.get(i).getText());
			if(jobtitle.get(i).getText().equals(role))
			{
				WebElement id = driver1.findElement(By.xpath("//*[contains(@class,'oxd-table-body')]/*[@class='oxd-table-card']["+i+"]/div/div[2]/div"));
				idNum = id.getText();
			}
		}
		logger.info("total Record-->"+idNum);
		return idNum;
	}
}
