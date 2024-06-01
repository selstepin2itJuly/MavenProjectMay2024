package testbase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;


public class Base {

	private static final Logger logger = (Logger) LogManager.getLogger(Base.class);
	
	public WebDriver driver;
	
	public void scrollToElement(WebElement ele) {
		logger.info("Scroll to {}"+ele);
		Actions actions = new Actions(driver);
		actions.scrollToElement(ele).perform();
		JavascriptExecutor e = (JavascriptExecutor) driver;
		e.executeScript("window.scrollBy(0,400)", "");
	}
	
	public void captureScreen() throws IOException {
		File loc = new File("ScreenshotFolder");
		String path = null;
		if (loc.exists()) {
			path = loc.getAbsolutePath();
		} else {
			loc.mkdirs();
			path = loc.getAbsolutePath();
		}
		logger.info("capture screenshot");
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File(path+"/" + getDateTime() + "_image.png"));
	}
	
	public void attachScreenShot() throws IOException {
		//captureScreen();
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		String src = screenshot.getScreenshotAs(OutputType.BASE64);
		String image="<img src=\"data:image/png;base64,"+src+"\" height=\"600\" width=\"800\" />";
		Reporter.log(image);
	}

	public void waitForElementClickable(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}
	
	public void waitForElementVisible(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	private String getDateTime() {
		logger.info("Get the current Time: "+getDateTime());
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("Y_MMM_d_HH_mm_ss_SSS");
		sdf.format(date);
		return sdf.format(date);
	}
}

