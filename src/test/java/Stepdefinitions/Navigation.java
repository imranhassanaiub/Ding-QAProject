package Stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import qaproject.pages.ItemsSelectionPage;
import qaproject.utils.DriverManager;
import qaproject.utils.UrlTextUtils;

public class Navigation {
	private WebDriver driver = null;

	@Given("I am on the cart page")
	public void i_am_on_the_cart_page() {
		driver = DriverManager.driver;
		driver.getCurrentUrl().equals(UrlTextUtils.URL.Cart_URL);
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutbtn)).click();

	}

	@When("I cancel from the checkout information page")
	public void i_cancel_from_the_checkout_information_page() {
		driver = DriverManager.driver;
		driver.getCurrentUrl().equals(UrlTextUtils.URL.CheckoutInformation_URL);
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutLname)).sendKeys("Hassan");
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutPostalCode)).sendKeys("1207");
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutCancelBtn)).click();

	}

	@Then("I should be on the cart page")
	public void i_should_be_on_the_cart_page() {
		driver = DriverManager.driver;
		driver.getCurrentUrl().equals(UrlTextUtils.URL.Cart_URL);
		driver.findElement(By.xpath(ItemsSelectionPage.ItemsModule.item1present)).isDisplayed();

	}

}
