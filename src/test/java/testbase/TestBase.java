package testbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class TestBase extends Base{

	private static final Logger logger = (Logger) LogManager.getLogger(TestBase.class);
	
	Properties prop = new Properties();
	String browser;
	public WebDriver getInstance() throws IOException {
	
		logger.info("Read the config");
		String configLoc ="./src/test/resources/config/config.properties";
		FileInputStream inStream = new FileInputStream(new File(configLoc));
		
		logger.info("Get the properties file");
		prop.load(inStream);
		
		browser = prop.getProperty("browser");
		logger.info("Get the browser: {}" + browser);
		if(browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",prop.getProperty("chromeagent"));
			driver = new ChromeDriver();
			logger.info("Initialized   ... {} "+ browser);
		}else if(browser.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",prop.getProperty("firfoxagent"));
			FirefoxOptions opt = new FirefoxOptions();
			opt.setBinary(prop.getProperty("firefoxBinary"));
			driver = new FirefoxDriver(opt);
			logger.info("Initialized   ... {} "+ browser);
		}else if(browser.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver",prop.getProperty("edgeagent"));
			driver = new EdgeDriver();
			logger.info("Initialized   ... {} "+ browser);
		}else
		{
			Throwable thr = new Throwable();
			thr.initCause(null);
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		logger.info("Open the url {}:"+ prop.getProperty("url") );
		driver.get(prop.getProperty("url"));
		
		return driver;
	}

	

	
}
