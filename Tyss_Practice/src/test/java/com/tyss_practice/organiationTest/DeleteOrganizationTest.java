package com.tyss_practice.organiationTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.tyss_practice.genericUtils.ExcelLib;
import com.tyss_practice.genericUtils.PropertyFileLib;
import com.tyss_practice.genericUtils.WebDriverUtils;
/**
 * 
 * @author Himanshu
 * 
 * This class is used for Delete An Organization from Organization page
 *
 */
public class DeleteOrganizationTest {
	/**
	 * This Method is used for Delete An Organization
	 * @throws Throwable
	 */
	@Test
	public void deleteOrganization() throws Throwable {

		WebDriverUtils wLib = new WebDriverUtils();
		PropertyFileLib pfLib = new PropertyFileLib();
		ExcelLib excelLib = new ExcelLib();

		/* Fetch data from Property File */
		String USERNAME = pfLib.getPropertyKeyValue("username");
		String PASSWORD = pfLib.getPropertyKeyValue("password");
		String URL = pfLib.getPropertyKeyValue("url");
		String BROWSER = pfLib.getPropertyKeyValue("browser");

		/* Fetch Test Script specific data */
		String orgName = excelLib.getExcelData("org", 1, 2) + "_" + wLib.getRandomNumber();
		String org_Type = excelLib.getExcelData("org", 1, 3);
		String org_industry = excelLib.getExcelData("org", 1, 4);

		/* step 1 : Launch the browser */
		WebDriver driver = null;

		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (BROWSER.equals("ie")) {
			driver = new InternetExplorerDriver();
		} else {
			driver = new FirefoxDriver();
		}

		wLib.implicitWait(driver);
		driver.get(URL);

		/* step 2 : Login to the application */
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		/* step 3 : Navigate to Organization page */
		driver.findElement(By.linkText("Organizations")).click();

		/* step 4 : Navigate to create new Organization page */
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		/* step 5 : create Organization */
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		WebElement swb1 = driver.findElement(By.name("accounttype"));
		wLib.select(swb1, org_Type);

		WebElement swb2 = driver.findElement(By.name("industry"));
		wLib.select(swb2, org_industry);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		/* step 6 : Verify the Creation of Organization  */
		String actOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		Assert.assertTrue(actOrgName.contains(orgName));
		System.out.println("Organization created Sucessfully");
		
		//Delete the Organization
		/* step 7 : Navigate to the Organization Page*/
		driver.findElement(By.linkText("Organizations")).click();
		
		/* step 8 : Search the Organization */
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		WebElement optionListBox = driver.findElement(By.id("bas_searchfield"));
		wLib.select(optionListBox, excelLib.getExcelData("org", 1, 6));
		driver.findElement(By.name("submit")).click();	
		
		/* step 9 : Select the Organization */
		driver.findElement(By.xpath("//tr[1]//a[text()='"+orgName+"']/..//preceding-sibling::td[2]")).click();		
		
		/* step 10 : Delete the Organization */
		driver.findElement(By.cssSelector("input[value='Delete']")).click();
		wLib.alertOk(driver);		
		
		/* step 11 : Verify the Deletion of Organization */
		String actDelConformartion = driver.findElement(By.xpath("//span[contains(text(),'No Organization Found')]")).getText();
		String expDelConformation = excelLib.getExcelData("org", 1, 5);
		boolean flag = actDelConformartion.contains(expDelConformation);
		Assert.assertTrue(flag);
		System.out.println("Organization Deleted Sucessfully");
		
		/* step 12 : Logout from application*/
		WebElement wb = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
        wLib.moveMouseToElemnet(driver, wb);
		driver.findElement(By.linkText("Sign Out")).click();
		
		/* step 13 : Close the browser */
		driver.close();
		
		
	}
}
