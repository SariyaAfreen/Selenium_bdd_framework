Feature: Add Professor Form Validation for Invalid Date of Birth
Scenario Outline: User submits the Add Professor form with invalid DOB (past/future date)
  Given the user is on the Add Professor page
  When the user fills the form with details:
    | First Name     | <firstName>   |
    | Last Name      | <lastName>    |
    | Phone          | <phone>       |
    | Email          | <email>       |
    | Department     | <department>  |
    | DOB            | <dob>         |
    | Gender         | <gender>      |
    | Qualification  | <qualification> |
    | Country        | <country>     |
    | State          | <state>       |
    | City           | <city>        |
  And the user submits the form
  Then the form should not be submitted due to invalid DOB

Examples:
  | firstName | lastName | phone      | email             | department | dob         | gender | qualification | country | state      | city     |
  | John      | Doe      | 9999999999 | john@example.com  | CSE        | 01-12-1222  | Male   | M.E           | India   | Tamilnadu  | Chennai  |
  | Alice     | Smith    | 8888888888 | alice@test.com    | CSE        | 01-01-3099  | Female | Ph.D          | India   | Tamilnadu  | Chennai  |
