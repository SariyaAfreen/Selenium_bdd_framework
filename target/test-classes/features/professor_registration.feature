Feature: Professor Registration Form

  Scenario: Validate State and City dropdowns when India is selected
    Given the user is on the professor registration page
    When the user selects "India" from the country dropdown
    Then the state dropdown should be populated
    And the city dropdown should be populated
