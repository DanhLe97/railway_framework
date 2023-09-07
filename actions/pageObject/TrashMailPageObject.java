package pageObject;

import java.util.Random;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.TrashMailPageUI;

public class TrashMailPageObject extends BasePage{
private WebDriver driver;
public TrashMailPageObject (WebDriver driver) {
	this.driver = driver;
}
public void verifyRegistedMail (String generateFakeNumber) throws InterruptedException {
	clickToElement(driver, TrashMailPageUI.CHANGE_EMAILNAME_BUTTON);
	sendkeyToElement(driver, TrashMailPageUI.EMAILNAME_TEXTBOX, generateFakeNumber);
	clickToElement(driver, TrashMailPageUI.SET_EMAILNAME_BUTTON);
	wait(5000);
}

public String getEmail(String generateFakeNumber) {
	clickToElement(driver, TrashMailPageUI.CHANGE_EMAILNAME_BUTTON);
	sendkeyToElement(driver, TrashMailPageUI.EMAILNAME_TEXTBOX, generateFakeNumber);
	clickToElement(driver, TrashMailPageUI.SET_EMAILNAME_BUTTON);

	return generateFakeNumber + "@" + getElementText(driver, TrashMailPageUI.EMAIL_DOMAIN_TEXTBOX);
 

}


public int generateFakeNumber() {
	Random rand = new Random();
	return rand.nextInt(9999);
}


}
