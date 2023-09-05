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
import pageObject.LoginPageObject;
import pageObject.RegisterPageObject;

public class Login extends BasePage {
	private WebDriver driver;
//	BasePage basePage;
	private String projectPath = System.getProperty("user.dir");
	private String emailAddress;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private String firstName;
	private String lastName;
	private String password;
	@BeforeClass
public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		homePage = new HomePageObject();
//		basePage = BasePage.getBasePageObject();
		firstName = "w_firstName";
		lastName = "w_lastName";
		password = "123456";
		emailAddress = "";

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://saferailway.somee.com/Page/HomePage.cshtml");
		homePage.clickToRegisterPage();
		registerPage = new RegisterPageObject();
		registerPage.inputToEmailTextbox();
		registerPage.inputToPasswordTextbox();
		registerPage.inputToConfirmPasswordTextbox();
		registerPage.inputToPassportNumber();
		registerPage.clickToRegisterButton
		
	  }
  @Test
  public void TC_01_Login_Valid_Username_Password() {
	  	homePage.clickToLoginPage();
	  	loginPage = new LoginPageObject();
	  	loginPage.inputToEmailTextbox(emailAddress);
	  	loginPage.inputToPasswordTextbox(password);
	  	loginPage.clickToLoginButton();
	  	
	  	
  }
  @Test
  public void TC_02_Login_Blank_Username() {
	  homePage.clickToLoginPage();
	  loginPage = new LoginPageObject();
	  	loginPage.inputToEmailTextbox("");
	  	loginPage.inputToPasswordTextbox(password);
	  	loginPage.clickToLoginButton();
	  	
	  	
  }
  @Test
  public void TC_03_Login_Invalid_Password() {
	  homePage.clickToLoginPage();

	  loginPage = new LoginPageObject();
	  	loginPage.inputToEmailTextbox(emailAddress);
	  	loginPage.inputToPasswordTextbox("111111");
	  	loginPage.clickToLoginButton();
	  	
	  	
  }
  @Test
  public void TC_04_Login_Wrong_Password_More_Than_3_Times() {
	  homePage.clickToLoginPage();	  
	  int i=0;
	  
	  do {
		 
		  loginPage = new LoginPageObject();
		  	loginPage.inputToEmailTextbox(emailAddress);
		  	loginPage.inputToPasswordTextbox("");
		  	loginPage.clickToLoginButton();
		  	i++;
	} while (i<4);
	  
	  	
	  	
  }
  @Test
  public void TC_05_Login_Inactive_Account() {
	  homePage.clickToRegisterPage();
		registerPage = new RegisterPageObject();
		
		registerPage.inputToEmailTextbox();
		registerPage.inputToPasswordTextbox();
		registerPage.inputToConfirmPasswordTextbox();
		registerPage.inputToPassportNumber();
		registerPage.clickToRegisterButton();
		homePage.clickToLoginPage();
		

	  loginPage = new LoginPageObject();
	  	loginPage.inputToEmailTextbox(emailAddress);
	  	loginPage.inputToPasswordTextbox("111111");
	  	loginPage.clickToLoginButton();
	  	
	  	
  }
	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
  @AfterClass
  public void afterClass() {
  }

}
