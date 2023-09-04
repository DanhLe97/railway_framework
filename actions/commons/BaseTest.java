package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");

 protected WebDriver getBrowserDriver (String browserName) {
		System.out.println("Run on: "+browserName);
		if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if(browserName.equals("h_firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920-1080");
			driver = new FirefoxDriver(options);
		}
		
		else if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
//			WebDriverManager.chromedriver().browserVersion("104").setup();
			driver = new ChromeDriver();
		} 

		else if (browserName.equals("h_chrome")) {
			WebDriverManager.chromedriver().setup();
//			WebDriverManager.chromedriver().browserVersion("104").setup();

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			options.addArguments("window-size=1920-1080");
			driver = new ChromeDriver(options);
			
		}
		else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Browser name invalid");
		}
		return driver;
 }
}
