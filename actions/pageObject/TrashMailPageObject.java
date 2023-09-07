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
public void verifyRegistedMail() {
	// TODO Auto-generated method stub
	
}

public void getEmail(String generateFakeNumber) {
	sendkeyToElement(driver, TrashMailPageUI , generateFakeNumber);
}


public int generateFakeNumber() {
	Random rand = new Random();
	return rand.nextInt(9999);
}

}
