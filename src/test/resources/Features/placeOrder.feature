Feature: Place order
  Scenario: Complete checkout
    Given the user is on "step-two" of the check out flow
    And the Price total is correct
    When the user clicks on "Finish"
    Then they see an order confirmation