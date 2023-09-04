package railway.login;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import pageObject.HomePageObject;
import pageObject.RegisterPageObject;

public class Login extends BasePage {
	private WebDriver driver;
//	BasePage basePage;
	private String projectPath = System.getProperty("user.dir");
	private String emailAddress;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private String firstName;
	private String lastName;
	private String password;
	@BeforeClass
public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		registerPage = new LoginPageObject(driver);
//		basePage = BasePage.getBasePageObject();
		firstName = "w_firstName";
		lastName = "w_lastName";
		password = "123456";
		emailAddress = "afc" + generateFakeNumber() + "@gmail.vn";

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://demo.nopcommerce.com");
	  }
  @Test
  public void TC_01_Login_Valid_Username_Password() {
	  
  }
  @Test
  public void TC_02() {
  }
	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
  @AfterClass
  public void afterClass() {
  }

}
