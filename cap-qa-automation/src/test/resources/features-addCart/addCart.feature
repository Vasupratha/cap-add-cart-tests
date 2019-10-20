Feature: To Test the deletion and Saving booking form

Scenario: To test create account and sign out
    Given user access the automation practice site
    And user clicks on the sign in button
    And user creates an account by entering email id
    And user enters details to create an account
    And get the login details of the user
    Then user sign outs from the appplication
    
    And user sign in to the application
    And clicks on Dresses top menu
    And user selects the dress with highest price tag
    And checks if the product is added to the cart
    Then user sign outs from the appplication
    And user clicks on the sign in button    
    And user sign in to the application
    And checks if the product is added to the cart
    