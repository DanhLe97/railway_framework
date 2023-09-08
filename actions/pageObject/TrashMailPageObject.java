package pageObject;

import java.sql.Date;
import java.util.Random;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.TrashMailPageUI;

public class TrashMailPageObject extends BasePage{
private WebDriver driver;
public TrashMailPageObject (WebDriver driver) {
	this.driver = driver;
}
public void verifyRegistedMail (String generateFakeNumber) {
	driver.get("https://www.guerrillamail.com/inbox");
	clickToElement(driver, TrashMailPageUI.CHANGE_EMAILNAME_BUTTON);
	sendkeyToElement(driver, TrashMailPageUI.EMAILNAME_TEXTBOX, generateFakeNumber);
	clickToElement(driver, TrashMailPageUI.SET_EMAILNAME_BUTTON);
	waitForElementVisible(driver, TrashMailPageUI.RECIEVED_EMAIL);
	clickToElement(driver, TrashMailPageUI.RECIEVED_EMAIL);
	
	waitForElementVisible(driver, TrashMailPageUI.ACTIVE_LINK);
	clickToElement(driver, TrashMailPageUI.ACTIVE_LINK);
	
}
public void setEmail() {
	
}

public String getEmail(String generateFakeNumber) {
	clickToElement(driver, TrashMailPageUI.CHANGE_EMAILNAME_BUTTON);
	sendkeyToElement(driver, TrashMailPageUI.EMAILNAME_TEXTBOX, generateFakeNumber+"a");
	clickToElement(driver, TrashMailPageUI.SET_EMAILNAME_BUTTON);

	return generateFakeNumber + "@" + getElementText(driver, TrashMailPageUI.EMAIL_DOMAIN_TEXTBOX);
 

}


public int generateFakeNumber() {
	Random rand = new Random();
	return rand.nextInt(9999);
}

	

public void activeRegistedMail() {
	 String emailName = generateFakeNumber()+"a";
	clickToElement(driver, TrashMailPageUI.CHANGE_EMAILNAME_BUTTON);
	sendkeyToElement(driver, TrashMailPageUI.EMAILNAME_TEXTBOX, emailName);
	clickToElement(driver, TrashMailPageUI.SET_EMAILNAME_BUTTON);
	waitForElementVisible(driver, TrashMailPageUI.RECIEVED_EMAIL);
	clickToElement(driver, TrashMailPageUI.RECIEVED_EMAIL);
	
	waitForElementVisible(driver, TrashMailPageUI.ACTIVE_LINK);
	clickToElement(driver, TrashMailPageUI.ACTIVE_LINK);
	
}


}
