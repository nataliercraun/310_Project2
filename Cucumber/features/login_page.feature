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

Scenario: If the incorrect username and password are provided, user stays on login page

	When I open loginpage
	And I type "wrong@usc.edu" into "#emailinput"
	And I type "wrong" into "#passinput" 
	And I click on "#acct_button"
	And I sleep for time "5"
	Then I see the current page is "/310_Project2/login.jsp"

Scenario: If a user creates an account, then they can login 

	When I open loginpage
	And I click on "#newUserClick"
	And I type "craun@usc.edu" into "#newemail"
	And I type "dogs" into "#newpw"
	And I type "dogs" into "#confirmpw"
	And I click on "#register_button"
	When I open loginpage
	And I type "craun@usc.edu" into "#emailinput"
	And I type "dogs" into "#passinput"
	And I click on "#acct_button"
	And I sleep for time "3"
	Then I see the current page is "/310_Project2/main.jsp"
	And I click "#logOutBtn"
	And I sleep for time "3"
	
	

