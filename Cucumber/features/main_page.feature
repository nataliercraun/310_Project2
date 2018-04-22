Feature: Main Page 

Scenario: Main Web Page has a collage topic input box

	When I open loginpage
	And I type "stanleyjkim@gmail.com" into "#emailinput"
	And I type "j00nhakim" into "#passinput" 
	And I click on "#acct_button"
	And I sleep for time "3"
	Then I see "topicBox" on attribute "id" on id "#topicBox"

Scenario: Main Web Page has a collage shape input box

	When I open mainpage
	Then I see "shapeBox" on attribute "id" on id "#shapeBox"

Scenario: Main Web Page has a build collage button

	When I open mainpage
	Then I see "buildCollageBtn" on attribute "id" on id "#buildCollageBtn"

Scenario: Main Web Page has a filter option selector 

	When I open mainpage
	Then I see "filterValue" on attribute "id" on id "#filterValue"

Scenario: Main Web Page has a border option input 

	When I open mainpage
	Then I see "borderBox" on attribute "id" on id "#borderBox"

Scenario: Main Web Page has a rotate option input 

	When I open mainpage
	Then I see "rotateBox" on attribute "id" on id "#rotateBox"

Scenario: Main Web Page has an image height option input 

	When I open mainpage
	Then I see "heightBox" on attribute "id" on id "#heightBox"

Scenario: Main Web Page has an image width option input 

	When I open mainpage
	Then I see "widthBox" on attribute "id" on id "#widthBox"

Scenario: Main Web Page has a collage display area 

	When I open mainpage
	Then I see "collageDisplay" on attribute "id" on id "#collageDisplay"

Scenario: Main Web Page has a save to history button 

	When I open mainpage
	Then I see "saveToHistoryBtn" on attribute "id" on id "#saveToHistoryBtn"

Scenario: Main Web Page has an export collage button 

	When I open mainpage
	Then I see "exportBtn" on attribute "id" on id "#exportBtn"

Scenario: Height input box changes the collage display height 

	When I open mainpage
	And I type "200" into "#heightBox"
	And I hit enter on "#heightBox"
	Then I see "200px" on attribute "height" on id "#collageDisplay" 

Scenario: Width input box changes the collage display width

	When I open mainpage
	And I type "200" into "#widthBox"
	And I hit enter on "#widthBox"
	Then I see "200px" on attribute "width" on id "#collageDisplay" 

Scenario: Filter option selector changes the collage display filter 

	When I open mainpage
	Then I see the attribute "value" of id "#filterValue" is seen on attribute "filter" on id "#collageDisplay"

Scenario: If the user clicks the logout button, they are directed back to the login page  

	When I open mainpage
	And I click "#logOutBtn"
	And I sleep for time "3"
	Then I see an alert that says "You were logged out."
	And I close the alert 
	Then I see the current page is "/310_Project2/login.jsp"


Scenario: If the user clicks the Collage Options button, an options menu appears
	When I open mainpage
	And I click "#collageOptionsBtn"
	Then I see "block" on attribute "display" on class "optionsMenu"


Scenario: If the user clicks the logout button, and tries to access main.jsp with no user logged in, they are directed back to the login page  
	When I open mainpage
	And I click "#logOutBtn"
	And I sleep for time "3"
	Then I see an alert that says "You were logged out."
	And I close the alert 
	Then I see the current page is "/310_Project2/login.jsp"
	When I open mainpage
	Then I see an alert that says "You were logged out."
	And I close the alert 
	Then I see the current page is "/310_Project2/login.jsp"

	When I open mainpage
	And I click "#logOutBtn"
	And I sleep for time "3"
	Then I see an alert that says "You were logged out."
	And I close the alert 
	Then I see the current page is "/310_Project2/login.jsp"
	And I open mainpage
	Then I see an alert that says "You were logged out."
	And I close the alert 
	Then I see the current page is "/310_Project2/login.jsp"

Scenario: If no collage is shown, user should not be able to click "#saveToHistoryBtn"
	When I open mainpage
	Then I see no collage displayed
	And the "#saveToHistoryBtn" should be disabled


Scenario: If collage is shown and user clicks "#saveToHistoryBtn", the collage should be shown in collage history gallery
	When I open mainpage
	And I type "banana" into "#topicBox"
	And I type "o" into "#shapeBox"
	And I click "#buildCollageBtn"
	Then I see a collage displayed
	When I click "#saveToHistoryBtn"
	Then I should see the collage saved in the collage history gallery
	
Scenario: If delete button is pressed on a collage, that collage should be removed from the collage history gallery
	
	
	




	
