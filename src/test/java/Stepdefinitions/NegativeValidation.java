package Stepdefinitions;

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

public class NegativeValidation {

	private WebDriver driver = null;

	@When("I attempt to log in with {string} and {string}")
	public void i_attempt_to_log_in_with_and(String string, String string2) {
		driver = DriverManager.driver;
		driver.get(UrlTextUtils.URL.Base_URL);
		driver.findElement(By.xpath(LoginPage.LoginModule.user_name))
				.sendKeys(LoginPage.LoginModule.loginwrongusername);
		driver.findElement(By.xpath(LoginPage.LoginModule.password)).sendKeys(LoginPage.LoginModule.loginpassword);
		driver.findElement(By.xpath(LoginPage.LoginModule.signIN_BTN)).click();

	}

	@Then("I should see the login error {string}")
	public void i_should_see_the_login_error(String string) {
		driver = DriverManager.driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement errorElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));

		String actualErrorMessage = errorElement.getText();
		System.out.println("Captured Error Message: " + actualErrorMessage);
	}

	@Given("I am logged in as {string} with password {string}")
	public void i_am_logged_in_as_with_password(String string, String string2) {
		driver = DriverManager.driver;
		driver.get(UrlTextUtils.URL.Base_URL);
		driver.findElement(By.xpath(LoginPage.LoginModule.user_name)).sendKeys(LoginPage.LoginModule.loginusername);
		driver.findElement(By.xpath(LoginPage.LoginModule.password)).sendKeys(LoginPage.LoginModule.loginpassword);
		driver.findElement(By.xpath(LoginPage.LoginModule.signIN_BTN)).click();
	}

	@Given("I have added {string} to the cart")
	public void i_have_added_to_the_cart(String string) {

		driver.findElement(By.id(ItemsSelectionPage.ItemsModule.item1)).click();
		driver.findElement(By.xpath(ItemsSelectionPage.ItemsModule.cart)).click();

	}

	@Given("I am on the checkout information page")
	public void i_am_on_the_checkout_information_page() {

		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutbtn)).click();
		System.out.println("Checkout Button Clicked");
		driver.getCurrentUrl().equals(UrlTextUtils.URL.CheckoutInformation_URL);
	}

	@When("I enter last name {string} and postal code {string} but no first name")
	public void i_enter_last_name_and_postal_code_but_no_first_name(String string, String string2) {
		driver = DriverManager.driver;
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutFname)).sendKeys("");
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutLname)).sendKeys("Hassan");
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutPostalCode)).sendKeys("1207");
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutContinueBtn)).click();

	}

	@Then("I should see the checkout error {string}")
	public void i_should_see_the_checkout_error(String string) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement errorElement = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));

		String actualErrorMessage = errorElement.getText();
		System.out.println("Captured Error Message: " + actualErrorMessage);
	}

	@When("I enter first name {string} and postal code {string} but no last name")
	public void i_enter_first_name_and_postal_code_but_no_last_name(String string, String string2) {
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutFname)).sendKeys("Imran");
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutLname)).sendKeys("");
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutPostalCode)).sendKeys("1207");
	}

	@When("I enter first name {string} and last name {string} but no postal code")
	public void i_enter_first_name_and_last_name_but_no_postal_code(String string, String string2) {
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutFname)).sendKeys("");
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutLname)).sendKeys("Hassan");
		driver.findElement(By.xpath(qaproject.pages.CartPage.CartModules.checkoutPostalCode)).sendKeys("");
	}

}
