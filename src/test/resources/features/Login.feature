Feature: Login Feature
	Scenario: Successful login
		Given User launches the browser
    		When User opens the login page
    		Then User enters valid credentials and clicks login
    		Then User should be logged in successfully
