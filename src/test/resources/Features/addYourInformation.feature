Feature: Checkout information
  Scenario: Fill Your Information
    Given the user is on "step-one" of the check out flow
    And they enter their details:
    |Name | Last Name | Zip or Postal Code|
    | Test | User     | 2031              |

    And the user clicks on "Continue"