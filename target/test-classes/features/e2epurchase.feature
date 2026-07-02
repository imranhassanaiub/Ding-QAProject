@e2e
Feature: End-to-End Purchase Flow
  As a registered Swag Labs user
  I want to browse products, add them to my cart, and complete the checkout
  So that I can successfully purchase items online

  Scenario: Complete a full purchase from login to order confirmation
    Given I am on the Swag Labs login page
    When I log in with username "standard_user" and password "secret_sauce"
    Then I should be on the inventory page

    # Add two products — verifies multi-item cart handling
    When I add "Sauce Labs Backpack" to the cart
    And I add "Sauce Labs Bike Light" to the cart
    Then the cart badge should show "2"

    # Verify cart contents before checkout
    When I open the cart
    Then I should see "2" items in the cart
    And "Sauce Labs Backpack" should be listed in the cart
    And "Sauce Labs Bike Light" should be listed in the cart

    # Complete the two-step checkout
    When I proceed to checkout
    And I enter first name "John", last name "Doe", and postal code "10001"
    And I click continue
    Then I should be on the checkout overview page
    And "Sauce Labs Backpack" should appear in the order summary
    And "Sauce Labs Bike Light" should appear in the order summary

    # Confirm successful purchase
    When I click finish
    Then I should see the confirmation message "Thank you for your order!"
