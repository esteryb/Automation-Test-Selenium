@All
Feature: Para bank Registration

  @Positive @Register
  Scenario: Success Register
    Given User is on Para Bank page
    When User click register
    Then User is on Register Page
    When User fill data
    And User fill valid username and password
    And User input password confirmation
    And User click register button
    Then User register successfully

  @Negative @Register
  Scenario: Failed Register - Mismatch Password
    Given User is on Para Bank page
    When User click register
    Then User is on Register Page
    When User fill data
    And User fill valid username and password
    And User input invalid password confirmation
    And User click register button
    Then User get error password did not match