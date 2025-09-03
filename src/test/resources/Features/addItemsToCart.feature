Feature: Add items

  Background:
    Given the user logs in as "standard_user"

  Scenario: Add a specific item to the cart
    Given the user is on the "inventory" page
    When the user adds "Sauce Labs Backpack" to the cart
    And the user adds "Sauce Labs Bike Light" to the cart
    And the user clicks on "the Cart"