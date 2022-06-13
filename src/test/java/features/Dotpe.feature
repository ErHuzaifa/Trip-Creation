Feature: Dotpe Order Creation
@Dotpe
Scenario Outline: Verify v1.0/vendor/dotpe/order trip creation is working as expected
  Given DotpeOrderCreate API with "<testDataSheet>" "<testCaseName>"
  When Call "/v1.0/vendor/dotpe/order" api with "POST" https request
  Then Status code should be 200
  And "success" in response body should be "true"
  And "message" in response body should be "Well Done! order created"
  And Verify all the keys in the database 
  
  
Examples:
	|testDataSheet			|testCaseName		|
	|Dotpe           		|DotpeTripCreation	|