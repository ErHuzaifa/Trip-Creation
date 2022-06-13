Feature: Vendor V2 Single Order Creation
@VendorV2
Scenario Outline: Verify v2.0/vendor/order/single trip creation is working as expected
  Given VendorSingleOrderCreate API with "<testDataSheet>" "<testCaseName>"
  When User calls "/v2.0/vendor/order/single" api with "POST" https request
  Then The api call got success with status code 200
  And "success" in response body is "true"
  And "message" in response body is "OK"
  And Verify each request key in the database 
  
  
Examples:
	|testDataSheet			|testCaseName		|
	|VendorTripCreation		|singleTripCreation	|