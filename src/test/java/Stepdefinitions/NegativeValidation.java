package Stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NegativeValidation {
	@When("I attempt to log in with {string} and {string}")
	public void i_attempt_to_log_in_with_and(String string, String string2) {
	}

	@Then("I should see the login error {string}")
	public void i_should_see_the_login_error(String string) {
	}

	@Given("I am logged in as {string} with password {string}")
	public void i_am_logged_in_as_with_password(String string, String string2) {
	}

	@Given("I have added {string} to the cart")
	public void i_have_added_to_the_cart(String string) {
	}

	@Given("I am on the checkout information page")
	public void i_am_on_the_checkout_information_page() {
	}

	@When("I enter last name {string} and postal code {string} but no first name")
	public void i_enter_last_name_and_postal_code_but_no_first_name(String string, String string2) {
	}

	@Then("I should see the checkout error {string}")
	public void i_should_see_the_checkout_error(String string) {
	}

	@When("I enter first name {string} and postal code {string} but no last name")
	public void i_enter_first_name_and_postal_code_but_no_last_name(String string, String string2) {
	}

	@When("I enter first name {string} and last name {string} but no postal code")
	public void i_enter_first_name_and_last_name_but_no_postal_code(String string, String string2) {
	}

}
