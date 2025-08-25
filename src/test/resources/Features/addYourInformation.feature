Feature: Checkout information
  Scenario: Fill Your Information
    Given the user logs in as "standard_user"
    When the user adds "Sauce Labs Backpack" to the cart
    And navigates to the cart
#    And the user proceeds to checkout
#    And enters shipping details "Lutho" "Mangwana" "2196"