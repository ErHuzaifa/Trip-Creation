Feature: V1 VendorOrder Order Creation
@v1VendorOrder
Scenario Outline: Verify v1.0/vendor/order trip creation is working as expected
  Given v1VendorOrderCreate API with "<testDataSheet>" "<testCaseName>"
  When Call v1VendorOrder "/v1.0/vendor/order" api with "POST" https request
  Then Get status code from v1VendorOrder it should be 200
  And "success" in v1VendorOrder response body should be "true"
  And "message" in v1VendorOrder response body should be "Well Done! order created"
  And Verify the data created from v1VendorOrder api from database 
  
  
Examples:
	|testDataSheet			|testCaseName		        |
	|v1VendorOrder          |v1VendorOrderTripCreation	|