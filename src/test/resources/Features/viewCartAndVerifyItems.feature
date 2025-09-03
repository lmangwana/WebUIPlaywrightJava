Feature: View cart

  Background:
    Given the user logs in as "standard_user"

  Scenario: Verify items after adding
    Given the user is on the "cart" page
    Then the cart should show the following items:

    |Qty | Description           | Price|
    | 1  | Sauce Labs Bike Light | $9.99|
    | 1  | Sauce Labs Backpack   | $29.99|

    Then the user clicks on "Checkout"