Feature: Login Page 

Scenario: Login Page has a username input box

	When I open loginpage
	Then I see "email" on attribute "name" on id "#emailinput"

Scenario: Login Page has a password input box

	When I open loginpage
	Then I see "pw" on attribute "name" on id "#passinput"

Scenario: If the wrong username is provided, an error message appears 

	When I open loginpage
	And I type "fake@gmail.com" into "#emailinput"
	And I hit enter on "#acct_button"
	Then I see 
