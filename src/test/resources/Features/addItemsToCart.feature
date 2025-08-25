Feature: Add items
  Scenario: Add a specific item to the cart
    Given the user logs in as "standard_user"
    When the user adds "Sauce Labs Backpack" to the cart
    And navigates to the cart
#    Then the cart shows "Sauce Labs Backpack"