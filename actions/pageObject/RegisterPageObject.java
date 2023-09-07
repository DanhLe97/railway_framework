package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.RegisterPageUI;

public class RegisterPageObject extends BasePage{
	private WebDriver driver;
	public RegisterPageObject (WebDriver driver) {
		this.driver = driver;}
	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	public void inputToConfirmPasswordTextbox(String confirmationPassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRMPASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.CONFIRMPASSWORD_TEXTBOX, confirmationPassword);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
		
	}

	public void inputToPassportNumber(String pidNumber) {
		waitForElementVisible(driver, RegisterPageUI.PID_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.PID_TEXTBOX, pidNumber);		
	}

}
