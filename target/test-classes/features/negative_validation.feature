@negative
Feature: Negative Scenarios and Input Validation
  As a QA engineer
  I want to verify that meaningful error messages are shown
  When users submit invalid or incomplete inputs

  # ── Login Validation ───────────────────────────────────────────────────────
  #
  # Covers four error paths in a single Scenario Outline:
  #   1. Wrong credentials (unknown user)
  #   2. Locked-out account
  #   3. Completely empty form
  #   4. Missing password only
  #
  Scenario Outline: Login fails with invalid or missing credentials
    Given I am on the Swag Labs login page
    When I attempt to log in with "<username>" and "<password>"
    Then I should see the login error "<error_message>"

    Examples:
      | username        | password     | error_message                                                             |
      | invalid_user    | wrong_pass   | Epic sadface: Username and password do not match any user in this service |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
      |                 |              | Epic sadface: Username is required                                        |
      | standard_user   |              | Epic sadface: Password is required                                        |

  # ── Checkout Step-One Validation ───────────────────────────────────────────
  #
  # Each scenario omits exactly one required field and asserts the
  # corresponding inline validation error.
  #
  Scenario: Checkout validation fails when first name is missing
    Given I am logged in as "standard_user" with password "secret_sauce"
    And I have added "Sauce Labs Backpack" to the cart
    And I am on the checkout information page
    When I enter last name "Doe" and postal code "10001" but no first name
    And I click continue
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
