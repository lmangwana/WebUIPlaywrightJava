Feature: View cart
  Scenario: Verify items after adding
    Given the user logs in as "standard_user"
    When the user adds "Sauce Labs Bike Light" to the cart
    And navigates to the cart
#    Then the cart shows "Sauce Labs Bike Light"