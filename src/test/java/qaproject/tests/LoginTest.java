package qaproject.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import qaproject.pages.LoginPage;
import qaproject.utils.DriverManager;
import qaproject.utils.UrlTextUtils;

public class LoginTest {

	private WebDriver driver = null;
	
	@Test
	public void checkLoginPageTitle() {
		driver = DriverManager.driver;
		driver.get(UrlTextUtils.URL.Base_URL);
		Assert.assertEquals(driver.getTitle(), UrlTextUtils.TEXT.HomePageTitle);
	}

	@Test(dependsOnMethods = "checkLoginPageTitle")
	public void logintest() {
		driver.findElement(By.xpath(LoginPage.LoginModule.user_name)).sendKeys(LoginPage.LoginModule.loginusername);
        driver.findElement(By.xpath(LoginPage.LoginModule.password)).sendKeys(LoginPage.LoginModule.loginpassword);
        driver.findElement(By.xpath(LoginPage.LoginModule.signIN_BTN)).click();
	}

}
