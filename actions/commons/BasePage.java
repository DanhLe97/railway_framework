package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	public static BasePage getBasePageObject () {
		return new BasePage ();
	}
	public void openPageUrl (WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	public String getPageUrl (WebDriver driver) {
		return driver.getCurrentUrl();
	}
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	public void forwardToPage (WebDriver driver) {
		driver.navigate().forward();
	}	
	public void refreshCurrentPage (WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence (WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver,30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}
	public void acceptAlert (WebDriver driver) {
		Alert alert = waitForAlertPresence(driver);
		alert.accept();
	}
	public void cancelAlert (WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	public String getAlerText (WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	public void sendkeyToAlert (WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue); 
	}
	public void switchtoWindowByTitle (WebDriver driver, String tabTitle) {
		Set <String> allTabIDs = driver.getWindowHandles();
		for (String id: allTabIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle().trim();
			if (actualTitle.equals(tabTitle)) {
				System.out.println("-----" + actualTitle+"-----");
				break;
			}
		}
	}
	public void closeAllTabWindowParent (WebDriver driver, String parenID) {
		Set <String> allTabIDs = driver.getWindowHandles();
		for (String id:allTabIDs) {
			if(!id.equals(parenID)) {
				driver.switchTo().window(id);
				driver.close();
			}
				driver.switchTo().window(parenID);
		}
	}
	public void switchtoWindowByID (WebDriver driver, String ID) {
		Set <String>	allTabIDs = driver.getWindowHandles();
		for (String id: allTabIDs) {
			if (!id.equals(allTabIDs)) {
				driver.switchTo().window(ID);
				break;
			}
		}
	}
	public void closeAllTabWindowWithouParent (WebDriver driver, String parentID) {
		Set <String> allTabIDs = driver.getWindowHandles();
		for (String id:allTabIDs) {
			if (!id.equals(allTabIDs)) {
				driver.switchTo().window(id);
				driver.close();
			}
				driver.switchTo().window(parentID);
		}	
	}
	private WebElement getWebElement (WebDriver driver, String xpathLocator) {
		return driver.findElement(getXpath(xpathLocator));
	}
	private List<WebElement> getListWebElements (WebDriver driver, String xpathLocator) {
		return driver.findElements(By.xpath(xpathLocator));
	}
	
	
	public void clickToElement(WebDriver driver, String xpathLocator) {
		getWebElement(driver, xpathLocator).click();
	}
	public void sendkeyToElement(WebDriver driver,String xpathLocator, String textValue) {
		WebElement element = getWebElement(driver, xpathLocator);
		element.clear();
		element.sendKeys(textValue);
	}
	public String getElementText (WebDriver driver, String xpathLocator) {	
		return getWebElement(driver, xpathLocator).getText();
	}
	public void selectItemInDefaultDropdown (WebDriver driver, String xpathLocator, String textItem) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		select.selectByValue(textItem);
	}
	public String getSelectedItemDefaultDropdown (WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.getFirstSelectedOption().getText();
	}
	public boolean isDropdownMultiple (WebDriver driver, String xpathLocator) {
		Select select = new Select(getWebElement(driver, xpathLocator));
		return select.isMultiple();
	}
	public void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void selectItemInCustomDropdown (WebDriver driver, String parentXpath, String childXpath, String expectedTextItem) {
		getWebElement(driver, parentXpath).click();
		sleepInSecond(1);
		
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List <WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getXpath(childXpath)));
		for (WebElement item:allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);",item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}
	
	public void checkToDefaultChecboxRadio (WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (!element.isSelected()) {
			element.click();
		}
	}
	public void uncheckToDefaultCheckboox (WebDriver driver, String xpathLocator) {
		WebElement element = getWebElement(driver, xpathLocator);
		if (element.isSelected()) {
			element.click();
		}
	}
	public void hoverMouseToElement (WebDriver driver, String xpathLocato) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, xpathLocato)).perform();
	}
	public void switchToFrameIFrame (WebDriver driver, String xpathLocator) {
		driver.switchTo().frame(getWebElement(driver, xpathLocator));
	}
	public void switchToDefautlContent (WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	public boolean isElementDisplayed (WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isDisplayed();
	}
	public boolean isElementEnabled (WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isEnabled();
	}
	public boolean isElementSelected (WebDriver driver, String xpathLocator) {
		return getWebElement(driver, xpathLocator).isSelected();
	}
	private By getXpath(String xpathLocator) {
		return By.xpath(xpathLocator);
	}
	public String getElementAttribute (WebDriver driver, String xpathLocator, String attributeName) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}
	public String getElementCssValue (WebDriver driver, String xpathLocator, String propertyName) {
		return getWebElement(driver, xpathLocator).getCssValue(propertyName);
	}
//	public String getHexaCOlorFromRGBA(String rgbaValue) {
//		return Color.fromString(rgbaValue).asHex();
//	}
	public int getElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElements(driver, xpathLocator).size();
	}




	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}


	public void highlightElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, xpathLocator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, xpathLocator));
	}

	public void scrollToElement(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, xpathLocator));
	}


	public void removeAttributeInDOM(WebDriver driver, String xpathLocator, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, xpathLocator));
	}
	public void waitForElementVisible (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getXpath(xpathLocator)));
	}
	public void waitForAllElementsVisible (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getXpath(xpathLocator)));
	}
	public void waitForElementInvisible (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getXpath(xpathLocator)));
	}
	public void waitForAllElementsInvisible (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver,xpathLocator)));
	}
	public void waitForElementClickable (WebDriver driver, String xpathLocator) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getXpath(xpathLocator)));
	}
	private long longTimeout = 30;
	private long shortTimeout = 5;

//	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
//		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
//		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//
//		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
//			@Override
//			public Boolean apply(WebDriver driver) {
//				try {
//					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
//				} catch (Exception e) {
//					return true;
//				}
//			}
//		};

//		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
//			@Override
//			public Boolean apply(WebDriver driver) {
//				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
//			}
//		};
//
//		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
//	}

	public String getElementValidationMessage(WebDriver driver, String xpathLocator) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, xpathLocator));
	}

//	public boolean isImageLoaded(WebDriver driver, String xpathLocator) {
//		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, xpathLocator));
//		if (status) {
//			return true;
//		} else {
//			return false;
//		}
//	}

}
