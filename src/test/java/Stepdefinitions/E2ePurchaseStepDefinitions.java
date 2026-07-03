package Stepdefinitions;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import qaproject.pages.ItemsSelectionPage;
import qaproject.pages.LoginPage;
import qaproject.utils.DriverManager;
import qaproject.utils.UrlTextUtils;

public class E2ePurchaseStepDefinitions {

	private WebDriver driver = null;

	@Given("I am on the Swag Labs login page")
	public void i_am_on_the_swag_labs_login_page() {
		driver = DriverManager.driver;
		driver.get(UrlTextUtils.URL.Base_URL);
	}

	@When("I log in with username {string} and password {string}")
	public void i_log_in_with_username_and_password(String string, String string2) {
		driver.findElement(By.xpath(LoginPage.LoginModule.user_name)).sendKeys("standard_user");
		driver.findElement(By.xpath(LoginPage.LoginModule.password)).sendKeys("secret_sauce");
		driver.findElement(By.xpath(LoginPage.LoginModule.signIN_BTN)).click();

	}

	@Then("I should be on the inventory page")
	public void i_should_be_on_the_inventory_page() {
		driver.getCurrentUrl().equals(UrlTextUtils.URL.Inventory_URL);
	}

	@When("I add {string} to the cart")
	public void i_add_to_the_cart(String string) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement addToCart1 = wait
				.until(ExpectedConditions.elementToBeClickable(By.id(ItemsSelectionPage.ItemsModule.item1)));
		addToCart1.click();
		WebElement addToCart2 = wait
				.until(ExpectedConditions.elementToBeClickable(By.id(ItemsSelectionPage.ItemsModule.item2)));

		addToCart2.click();

	}

	@Then("the cart badge should show {string}")
	public void the_cart_badge_should_show(String expectedCount) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement badge = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
		assertEquals(expectedCount, badge.getText());

	}

	@When("I open the cart")
	public void i_open_the_cart() {
		driver.getCurrentUrl().equals(UrlTextUtils.URL.Cart_URL);
		driver.findElement(By.xpath(ItemsSelectionPage.ItemsModule.cart)).click();

	}

	@Then("I should see {string} items in the cart")
	public void i_should_see_items_in_the_cart(String string) {

		driver.findElement(By.xpath("//span[@class='shopping_cart_badge']")).getText().equals(string);

	}

	@Then("{string} should be listed in the cart")
	public void should_be_listed_in_the_cart(String string) {
		driver.findElement(By.xpath(ItemsSelectionPage.ItemsModule.item1present)).isDisplayed();
		driver.findElement(By.xpath(ItemsSelectionPage.ItemsModule.item2present)).isDisplayed();
	}

	@When("I proceed to checkout")
	public void i_proceed_to_checkout() {
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutbtn)).click();
		System.out.println("Checkout Button Clicked");
	}

	@When("I enter first name {string}, last name {string}, and postal code {string}")
	public void i_enter_first_name_last_name_and_postal_code(String string, String string2, String string3) {
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutFname)).sendKeys("Imran");
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutLname)).sendKeys("Hassan");
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutPostalCode)).sendKeys("1207");
	}

	@When("I click continue")
	public void i_click_continue() {
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutContinueBtn)).click();

	}

	@Then("I should be on the checkout overview page")
	public void i_should_be_on_the_checkout_overview_page() {

		driver.getCurrentUrl().equals(UrlTextUtils.URL.Checkout_URL);
	}

	@Then("{string} should appear in the order summary")
	public void should_appear_in_the_order_summary(String string) {
		driver.findElement(By.xpath(ItemsSelectionPage.ItemsModule.item1present)).isDisplayed();
		driver.findElement(By.xpath(ItemsSelectionPage.ItemsModule.item2present)).isDisplayed();

	}

	@When("I click finish")
	public void i_click_finish() {
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.Finalbtn)).click();
	}

	@Then("I should see the confirmation message {string}")
	public void i_should_see_the_confirmation_message(String string) {
		driver.getCurrentUrl().equals(UrlTextUtils.URL.Checkout_URL);
		System.out.println("Checkout Completed");
	}

}
