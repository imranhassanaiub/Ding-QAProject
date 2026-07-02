package Stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class E2ePurchaseStepDefinitions {

	@Given("I am on the Swag Labs login page")
	public void i_am_on_the_swag_labs_login_page() {
	}

	@When("I log in with username {string} and password {string}")
	public void i_log_in_with_username_and_password(String string, String string2) {

	}

	@Then("I should be on the inventory page")
	public void i_should_be_on_the_inventory_page() {
	}

	@When("I add {string} to the cart")
	public void i_add_to_the_cart(String string) {
	}

	@Then("the cart badge should show {string}")
	public void the_cart_badge_should_show(String string) {
	}

	@When("I open the cart")
	public void i_open_the_cart() {
	}

	@Then("I should see {string} items in the cart")
	public void i_should_see_items_in_the_cart(String string) {
	}

	@Then("{string} should be listed in the cart")
	public void should_be_listed_in_the_cart(String string) {
	}

	@When("I proceed to checkout")
	public void i_proceed_to_checkout() {
	}

	@When("I enter first name {string}, last name {string}, and postal code {string}")
	public void i_enter_first_name_last_name_and_postal_code(String string, String string2, String string3) {
	}

	@When("I click continue")
	public void i_click_continue() {
	}

	@Then("I should be on the checkout overview page")
	public void i_should_be_on_the_checkout_overview_page() {
	}

	@Then("{string} should appear in the order summary")
	public void should_appear_in_the_order_summary(String string) {
	}

	@When("I click finish")
	public void i_click_finish() {
	}

	@Then("I should see the confirmation message {string}")
	public void i_should_see_the_confirmation_message(String string) {
	}

}
