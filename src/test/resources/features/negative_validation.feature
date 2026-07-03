@negative
Feature: Negative Scenarios and Input Validation
  As a QA engineer
  I want to verify that meaningful error messages are shown
  When users submit invalid or incomplete inputs

  # ── Login Validation ───────────────────────────────────────────────────────
  Scenario Outline: Login fails with invalid or missing credential	s
    Given I am on the Swag Labs login page
    When I attempt to log in with "<username>" and "<password>"
    Then I should see the login error "<error_message>"

  # ── Checkout Validation ───────────────────────────────────────────────────
  Scenario: Checkout validation fails when first name is missing
    Given I am logged in as "standard_user" with password "secret_sauce"
    And I have added "Sauce Labs Backpack" to the cart
    And I am on the checkout information page
    When I enter last name "Hassan" and postal code "1207" but no first name
    Then I should see the checkout error "Error: First Name is required"

  Scenario: Checkout validation fails when last name is missing
    Given I am logged in as "standard_user" with password "secret_sauce"
    And I have added "Sauce Labs Backpack" to the cart
    And I am on the checkout information page
    When I enter first name "John" and postal code "10001" but no last name
    And I click continue
    Then I should see the checkout error "Error: Last Name is required"

  Scenario: Checkout validation fails when postal code is missing
    Given I am logged in as "standard_user" with password "secret_sauce"
    And I have added "Sauce Labs Backpack" to the cart
    And I am on the checkout information page
    When I enter first name "John" and last name "Doe" but no postal code
    And I click continue
    Then I should see the checkout error "Error: Postal Code is required"
