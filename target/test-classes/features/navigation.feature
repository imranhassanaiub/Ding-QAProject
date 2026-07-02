@navigation
Feature: Navigation and Cart State Preservation
  As a logged-in Swag Labs user
  I want to navigate backwards through the checkout flow
  So that my product selections are not lost when I return to a previous page

  # ── Path 1: Cart → Inventory ───────────────────────────────────────────────
  #
  # "Continue Shopping" on the cart page must return the user to the inventory
  # without clearing the cart. The badge count is the observable proof.
  #
  Scenario: Returning to inventory from the cart preserves the cart badge count
    Given I am logged in as "standard_user" with password "secret_sauce"
    When I add "Sauce Labs Backpack" to the cart
    And I open the cart
    And I click continue shopping
    Then I should be on the inventory page
    And the cart badge should show "1"

  # ── Path 2: Checkout Step One → Cart ──────────────────────────────────────
  #
  # "Cancel" on the customer-information form must return the user to the cart
  # page with the originally added item still present as a line item.
  #
  Scenario: Cancelling checkout step one returns to the cart with items intact
    Given I am logged in as "standard_user" with password "secret_sauce"
    And I have added "Sauce Labs Backpack" to the cart
    And I am on the cart page
    When I proceed to checkout
    And I cancel from the checkout information page
    Then I should be on the cart page
    And "Sauce Labs Backpack" should be listed in the cart

  # ── Path 3: Checkout Step Two → Inventory ─────────────────────────────────
  #
  # "Cancel" on the order overview must return the user to the inventory page
  # with the cart badge still showing the correct item count — confirming
  # the cart was not cleared during the cancelled checkout attempt.
  #
  Scenario: Cancelling the checkout overview returns to inventory with the cart preserved
    Given I am logged in as "standard_user" with password "secret_sauce"
    And I have added "Sauce Labs Backpack" to the cart
    And I am on the checkout information page
    When I enter first name "John", last name "Doe", and postal code "10001"
    And I click continue
    And I cancel from the checkout overview page
    Then I should be on the inventory page
    And the cart badge should show "1"
