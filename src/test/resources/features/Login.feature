Feature: Login Functionality

  Scenario: Login with valid credentials
    Given the user is on the login page
    When the user enters valid username and password
    And solves the captcha
    And clicks the login button
    Then the user should be redirected to the SmartUniversity dashboard

  Scenario: Login with invalid credentials
    Given the user is on the login page
    When the user enters invalid username and password
    And solves the captcha
    And clicks the login button
    Then an alert with message containing "invalid" should appear

  Scenario: Login without password
    Given the user is on the login page
    When the user enters valid username
    And solves the captcha
    And clicks the login button
    Then a password required error should be displayed

  Scenario: Login without captcha
    Given the user is on the login page
    When the user enters valid username and password
    And clicks the login button without captcha
    Then a captcha required error should be displayed

  Scenario: Login with incorrect captcha
    Given the user is on the login page
    When the user enters valid username and password
    And enters invalid captcha
    And clicks the login button
    Then an error message for invalid captcha should be displayed

  Scenario: Check UI alignment and labels
    Given the user is on the login page
    Then all input field labels should be properly displayed
