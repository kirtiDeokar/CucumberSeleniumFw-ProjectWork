

Feature: Search Functionality


  Scenario: User is able to Open the browser, navigate to the URL and Search for Product
    Given User navigated to the home application url
    When User Search for product "Laptop"
    Then Search Result page is displayed

  Scenario: User is click on the Product and check the Product Details
    Given User navigated to the home application url
    And User Search for product "Laptop"
    When User click on any product
    Then Product Description is displayed in new tab

  # Scenario: Search a Product
  # Given User opened browser
  # And User navigated to the home application url
  # When User Search for product "Laptop"
  # Then searched product list should be displayed

  # Scenario: User clicks on the Product and check the Product Details
  # Given User opened browser
  # And User navigated to the home application url
  # And User Search for product "Laptop"
  # When User click on any product
  # Then Product Description should be displayed in new tab  