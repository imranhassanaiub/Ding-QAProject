package qaproject.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import qaproject.utils.DriverManager;
import qaproject.utils.UrlTextUtils;

public class CartCheckoutTest {
	private WebDriver driver = null;
	
	@Test(dependsOnMethods = "checkItemsSelectionPageLanding", description = "Check Cart Page Landing")
	public void checkCartPageLanding() {
		driver = DriverManager.driver;
		driver.getCurrentUrl().equals(UrlTextUtils.URL.Cart_URL);
		System.out.println("Cart Page Landing Checked");
	}
	
	@Test(dependsOnMethods = "checkCartPageLanding", description = "Click Checkout Button")
	public void cartcheckoutTest() {
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutbtn)).click();
		System.out.println("Checkout Button Clicked");
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutFname)).sendKeys("Imran");
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutLname)).sendKeys("Hassan");
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutPostalCode)).sendKeys("1207");
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutContinueBtn)).click();
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.Finalbtn)).click();
		driver.getCurrentUrl().equals(UrlTextUtils.URL.Checkout_URL);
		System.out.println("Checkout Completed");

	}
	
}
