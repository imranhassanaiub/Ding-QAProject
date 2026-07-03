@navigation
Feature: Navigation and Cart State Preservation
  As a logged-in Swag Labs user
  I want to navigate backwards through the checkout flow
  So that my product selections are not lost when I return to a previous page

  Scenario: Cancelling checkout step one returns to the cart with items intact
    Given I am logged in as "standard_user" with password "secret_sauce"
    And I have added "Sauce Labs Backpack" to the cart
    And I am on the cart page
    And I cancel from the checkout information page
    Then I should be on the cart page
    And "Sauce Labs Backpack" should be listed in the cart
