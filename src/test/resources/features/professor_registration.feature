Feature: Professor Registration Form

  Scenario Outline: Validate State and City dropdowns for a selected country
    Given the user is on the professor registration page
    When the user selects "<Country>" from the country dropdown
    Then the state dropdown should be populated
    And the city dropdown should be populated

    Examples:
      | Country |
      | India   |
