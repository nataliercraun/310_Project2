When(/^I open mainpage$/) do
	visit 'http://localhost:8080/310_Project2/main.jsp'
end

Then(/^I see "([^"]*)" on attribute "([^"]*)" on id "([^"]*)"$/) do |arg1, arg2, arg3|
  	puts(find(arg3)[arg2])
	expect(find(arg3)[arg2] == arg1).to be true 
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

Then (/^I see no collage displayed$/) do
    find("#collageImage").should be_disabled
end

Then (/^the "([^"]*)" should be disabled$/) do |arg1|
    find(arg1).should be_disabled
end

Then(/^I see "([^"]*)" on attribute "([^"]*)" on class "([^"]*)"$/) do |arg1, arg2, arg3|
	puts(find(arg3)['class']) 
  	expect(find(arg3)[arg2] == arg1).to be true 
end

Then(/^I see a png extension on attribute "([^"]*)" of id "([^"]*)"$/) do |arg1, arg2|
	puts(find("#topicBox")['src']+'.png')
	puts(find(arg2)[arg1])
  	expect(find("#topicBox")['src']+'.png' == find(arg2)[arg1]).to be true
end


