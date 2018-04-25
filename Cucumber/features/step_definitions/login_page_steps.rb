When(/^I open loginpage$/) do
  	visit 'http://localhost:8080/310_Project2/login.jsp'
end

When(/^I click on "([^"]*)"$/) do |arg1|
  	find(arg1).click
end

Then(/^I see "([^"]*)" on attribute "([^"]*)" of "([^"]*)"$/) do |arg1, arg2, arg3|
	puts(find(:css, arg3).native.css_value(arg2))
  	expect(find(:css, arg3).native.css_value(arg2) == arg1).to be true 
end

Then(/^I see the current page is "([^"]*)"$/) do |arg1|
	puts(page.current_path) 
  	expect(page.current_path == arg1).to be true
end

When(/^I sleep for time "([^"]*)"$/) do |arg1|
  	sleep(inspection_time=arg1.to_i)
end

Then(/^I click "([^"]*)"$/) do |arg1|
  	find(arg1).click
end






