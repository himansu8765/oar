package com.tyss_practice.genericUtils;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {

	/**
	 * This method is used to provide wait for all element to load in DOM document
	 * 
	 * @param driver
	 */
	public void implicitWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	/**
	 * This method is used to provide wait for visibility of specific element in GUI
	 * 
	 * @param driver
	 * @param expElement
	 */
	public void waitForElementVisibility(WebDriver driver, WebElement expElement) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(expElement));
	}

	/**
	 * This method is used to provide wait for page title to be available
	 * 
	 * @param driver
	 * @param pageTitle
	 */
	public void waitForPageTitleVisibility(WebDriver driver, String pageTitle) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleContains(pageTitle));
	}

	/**
	 * This method is used to provide wait for any element
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */
	public boolean waitForAnElement(WebDriver driver, WebElement element) throws InterruptedException {
		boolean flag = false;
		int count = 0;
		while (count < 30) {
			try {
				element.isDisplayed();
				flag = true;
				break;
			} catch (Throwable t) {
				count++;
				Thread.sleep(500);
			}
		}
		return flag;
	}

	/**
	 * This method is used to provide wait and click an element
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */
	public boolean waitAndClickElement(WebDriver driver, WebElement element) throws InterruptedException {
		boolean flag = false;
		int count = 0;
		while (count < 30) {
			try {
				element.click();
				flag = true;
				break;
			} catch (Throwable t) {
				count++;
				Thread.sleep(1000);
			}
		}
		return flag;
	}

	/**
	 * This method is used to select the value from the drop down based on
	 * visibleText
	 * 
	 * @param dropDwonElemnet
	 * @param text
	 */
	public void select(WebElement dropDownElement, String text) {
		Select sel = new Select(dropDownElement);
		sel.selectByVisibleText(text);

	}

	/**
	 * This method is used to select the value from the drop down based on index
	 * 
	 * @param dropDwonElemnet
	 * @param text
	 */
	public void select(WebElement dropDownElement, int index) {
		Select sel = new Select(dropDownElement);
		sel.selectByIndex(index);

	}

	/**
	 * This method is used to switch to new Window
	 * 
	 * @param driver
	 * @param pageTitle
	 */

	public void switchToNewTab(WebDriver driver, String pageTitle) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String winID = it.next();
			driver.switchTo().window(winID);
			String currentPageTitle = driver.getTitle();
			if (currentPageTitle.contains(pageTitle)) {
				break;
			}
		}
	}

	public void alertOk(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void alertCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();

	}

	public void moveMouseToElemnet(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.moveToElement(element).perform();

	}

	public void doubleClick(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.doubleClick(element).perform();

	}

	public void rightClick(WebDriver driver, WebElement element) {
		Actions act = new Actions(driver);
		act.contextClick(element).perform();

	}

	public int getRandomNumber() {
		Random ranObj = new Random();
		int randata = ranObj.nextInt(1000);
		return randata;
	}

	public void switchToFrame(WebDriver drver, String idOrNameAttrib) {
		drver.switchTo().frame(idOrNameAttrib);
	}

	public void switchToFrame(WebDriver drver, int index) {
		drver.switchTo().frame(index);
	}

	public void switchToFrame(WebDriver drver, WebElement element) {
		drver.switchTo().frame(element);
	}

	public void executeJavaScript(WebDriver driver, String javaScript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(javaScript);
	}
}
