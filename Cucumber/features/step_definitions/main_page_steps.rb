When(/^I open mainpage$/) do
	visit 'http://localhost:8081/310_Project2/main.jsp'
end

Then(/^I see "([^"]*)" on attribute "([^"]*)" on id "([^"]*)"$/) do |arg1, arg2, arg3|
  	puts(find(arg3)['id'])
end

When(/^I type "([^"]*)" into "([^"]*)"$/) do |arg1, arg2|
	find(:css, arg2).set(arg1)
end

When(/^I hit enter on "([^"]*)"$/) do |arg1|
	find(:css, arg1).set("\n")
end

Then(/^I see the attribute "([^"]*)" of id "([^"]*)" is seen on attribute "([^"]*)" on id "([^"]*)"$/) do |arg1, arg2, arg3, arg4|
	expect(find(:css, arg4).native.css_value(arg3) == find(arg2)['value']).to be true
end

Then(/^I see an alert that says "([^"]*)"$/) do |arg1|
  	alert = page.driver.browser.switch_to.alert
	puts(alert.text)
	alert.text.should eq(arg1)
end

Then(/^I close the alert$/) do
  	alert = page.driver.browser.switch_to.alert
	alert.send('accept')
end

