package qaproject.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import qaproject.pages.ItemsSelectionPage;
import qaproject.utils.DriverManager;

public class SelectItemsForCheckoutTest {

	private WebDriver driver = null;

	
	@Test(dependsOnMethods = "logintest", description = "Select items for checkout")
	public void checkItemsSelectionPageLanding() {
		driver = DriverManager.driver;
		driver.findElement(By.xpath(ItemsSelectionPage.ItemsModule.item1)).click();
		driver.findElement(By.xpath(ItemsSelectionPage.ItemsModule.item2)).click();
		driver.findElement(By.xpath(ItemsSelectionPage.ItemsModule.cart)).click();

	}
}
