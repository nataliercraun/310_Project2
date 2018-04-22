Feature: Login Page 

Scenario: Login Page has a username input box

	When I open loginpage
	Then I see "email" on attribute "name" on id "#emailinput"

Scenario: Login Page has a password input box

	When I open loginpage
	Then I see "pw" on attribute "name" on id "#passinput"

Scenario: Login page has an option to create a new user
	
	When I open loginpage
	And I click on "#newUserClick"
	Then I see "block" on attribute "display" of "#newusercontainer" 

Scenario: If the correct username and password are provided, the main page appears

	When I open loginpage
	And I type "stanleyjkim@gmail.com" into "#emailinput"
	And I type "j00nhakim" into "#passinput" 
	And I click on "#acct_button"
	And I sleep for time "3"
	Then I see the current page is "/310_Project2/main.jsp"
	And I click "#logOutBtn"
	And I sleep for time "3"

Scenario: If the incorrect username and password are provided, an alert message appears 

	When I open loginpage
	And I type "wrong@usc.edu" into "#emailinput"
	And I type "wrongpassword" into "#passinput" 
	And I click on "#acct_button"
	And I sleep for time "3"
	Then I see an alert that says "Incorrect username or password"

Scenario: If the user enters just an email, they are asked to provide a password as well 

	When I open loginpage
	And I type "craun@usc.edu" into "#emailinput"
	And I click on "#acct_button"
	Then I see an alert that says "Please enter a password"

Scenario: If the user enters just a password, they are asked to provide an email as well 

	When I open loginpage
	And I type "123abc" into "#passinput"
	And I click on "#acct_button"
	Then I see an alert that says "Please enter an email"

Scenario: If the user enters neither a password nor email, they are asked to provide both

	When I open loginpage
	And I click on "#acct_button"
	Then I see an alert that says "Please enter an email and password"

Scenario: If the user tries to create a password with less than 6 characters, an alert message appears 

	When I open loginpage
	And I click on "#newUserClick"
	And I type "another@gmail.com" into "#newemail" 
	And I type "123" into "#newpw"
	And I type "123" into "#confirmpw"	
	And I click "#register_button"
	And I sleep for time "3"
	Then I see an alert that says "auth/weak-password Password should be at least 6 characters"

Scenario: If the user tries to register with a non-email, an alert message appears 

	When I open loginpage
	And I click on "#newUserClick"
	And I type "notanemail" into "#newemail" 
	And I type "123valid" into "#newpw"
	And I type "123valid" into "#confirmpw"	
	And I click "#register_button"
	And I sleep for time "3"
	Then I see an alert that says "auth/invalid-email The email address is badly formatted."

Scenario: If the user enters two different passwords during registration, an alert appears 

	When I open loginpage
	And I click on "#newUserClick"
	And I type "email@gmail.com" into "#newemail" 
	And I type "123valid" into "#newpw"
	And I type "123different" into "#confirmpw"	
	And I click "#register_button"
	And I sleep for time "3"
	Then I see an alert that says "Passwords do not match!"

Scenario: If the user enters an email that's already in use, an alert message appears 

	When I open loginpage
	And I click on "#newUserClick"
	And I type "craun@usc.edu" into "#newemail" 
	And I type "123valid" into "#newpw"
	And I type "123valid" into "#confirmpw"	
	And I click "#register_button"
	And I sleep for time "3"
	Then I see an alert that says "auth/email-already-in-use The email address is already in use by another account."



	
	

