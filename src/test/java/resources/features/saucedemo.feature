Feature: Saucedemo Automation test

  Scenario: Login with valid username and password
    Given I am on the Sauce Demo login page
    When I enter "standard_user" in the username field
    And I enter "secret_sauce" in the password field
    And I click the Login button
    Then I should be logged in

  Scenario: Login with invalid username
    Given I am on the Sauce Demo login page
    When I enter "trian_basofi_rohman" in the username field
    And I enter "secret_sauce" in the password field
    And I click the Login button
    Then I should see an error message

  Scenario: Login with invalid password
    Given I am on the Sauce Demo login page
    When I enter "standard_user" in the username field
    And I enter "trian_basofi_rohman" in the password field
    And I click the Login button
    Then I should see an error message

  Scenario: Add product to shopping cart
    Given I am logged in to Sauce Demo with user "standard_user" and password "secret_sauce"
    When I click the Add to Cart button for the "Sauce Labs Backpack" product
    Then the product should be added to my shopping cart

  Scenario: Logout from dashboard
    Given I am logged in to Sauce Demo with user "standard_user" and password "secret_sauce"
    When I click logout button
    Then I should be logged out
