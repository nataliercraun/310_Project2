When(/^I open localhost:(\d+)$/) do |arg1|
	visit 'localhost:' + arg1
end

Then(/^I see the name "([^"]*)" on id "([^"]*)" on attribute "([^"]*)"$/) do |arg1, arg2, arg3|
	expect(find(arg2)['id'] == arg1).to be true 
end

