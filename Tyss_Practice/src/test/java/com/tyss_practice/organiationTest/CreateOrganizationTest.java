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

public class CreateOrganizationTest {

	@Test
	public void createORgtest() throws Throwable {

		WebDriverUtils wLib = new WebDriverUtils();
		PropertyFileLib pfLib = new PropertyFileLib();
		ExcelLib excelLib = new ExcelLib();

		/* read data from property File */
		String USERNAME = pfLib.getPropertyKeyValue("username");
		String PASSWORD = pfLib.getPropertyKeyValue("password");
		String URL = pfLib.getPropertyKeyValue("url");
		String BROWSER = pfLib.getPropertyKeyValue("browser");

		/* read test script specific data */
		String orgName = excelLib.getExcelData("org", 1, 2) + "_" + wLib.getRandomNumber();
		String org_Type = excelLib.getExcelData("org", 1, 3);
		String org_industry = excelLib.getExcelData("org", 1, 4);

		/* step 1 : launch the browser */
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

		/* step 2 : login */
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		/* step 3 : navigate to Org page */
		driver.findElement(By.linkText("Organizations")).click();

		/* step 4 : navigate to create new Org page */
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();

		/* step 5 : create Org */
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		WebElement swb1 = driver.findElement(By.name("accounttype"));
		wLib.select(swb1, org_Type);

		WebElement swb2 = driver.findElement(By.name("industry"));
		wLib.select(swb2, org_industry);

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		/* step 6 : verify the Org */
		String actOrgName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();

		Assert.assertTrue(actOrgName.contains(orgName));

		/* step 1 : logout */
		WebElement wb = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wLib.moveMouseToElemnet(driver, wb);
		driver.findElement(By.linkText("Sign Out")).click();

		/* close browse */
		driver.close();

	}
}
