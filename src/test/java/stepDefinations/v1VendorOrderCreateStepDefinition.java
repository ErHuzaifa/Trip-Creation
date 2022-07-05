package stepDefinations;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import DotpePOJO.Dotpe;
import DotpePOJO.ExactLocation;
import DotpePOJO.FromAddress;
import DotpePOJO.OriginatorDetails;
import DotpePOJO.ReceiverDetails;
import DotpePOJO.SenderDetails;
import DotpePOJO.ToAddress;
import DotpePOJO.ToAddressExactLocation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestDataDotpeTripCreation;
import resources.TestDataV1VendorOrderTripCreation;
import resources.Utils;
import v1VendorOrderPOJO.FromAddressExactLocation;
import v1VendorOrderPOJO.v1VendorOrder;

public class v1VendorOrderCreateStepDefinition extends Utils {
	
	 RequestSpecification requestSpec;
	 ResponseSpecification responseSpec;
	 Response response;
	 static v1VendorOrder requestPayLoad;
	 public static String jsonAsString;
	
	 @Given("v1VendorOrderCreate API with {string} {string}")
	 public void v1VendorOrder_API_with(String string, String string2) throws IOException {
	      
		          String authToken = getGlobalValue("vendorAuthToken");
		          responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		          
		          TestDataV1VendorOrderTripCreation data = new TestDataV1VendorOrderTripCreation();
				  requestPayLoad = data.testDatav1VendorOrderTripCreation(string, string2);
				  requestSpec=given().spec(requestSpecification(authToken))
				  .body(requestPayLoad);
				  
  }


	@When("Call v1VendorOrder {string} api with {string} https request")
	public void calls_V1VendorOrder_API_with_http_request(String endPoint, String method) {
	   
		 response =requestSpec.when().post("v1.0/vendor/order").
				then().spec(responseSpec).extract().response();
		 
	}
	
	@Then("Get status code from v1VendorOrder it should be {int}")
	public void status_code(int int1) {
		
		assertEquals(int1, response.getStatusCode());
	}
	
	@Then("{string} in v1VendorOrder response body should be {string}")
	public void response_body(String keyvalue, String expectedValue) {
	   
		assertEquals(getJsonPath(response, keyvalue), expectedValue);
		
	}
	
	@Then("Verify the data created from v1VendorOrder api from database")
	public void verify_v1VendorOrder_creation_in_the_data_base() throws SQLException, IOException {
	
		ResultSet rs = null;
		
		//Get ReferenceId from Payload
		   String requestReferenceId = requestPayLoad.getReference_id();
		   
		//Get Volume from Payload
		   int requestVolume = requestPayLoad.getVolume();
		   
		//Get COD from Payload
		   int COD = requestPayLoad.getCash_to_be_collected();
		   String requestCOD = Integer.toString(COD);
		   
		//Get delivery_date from Payload
		   String requestDeliveryDate = requestPayLoad.getDelivery_date();
		   
		//Get delivery_slot from Payload
		   String requestDeliverySlot = requestPayLoad.getDelivery_slot();
		
		 //Get originator_details from Payload
		    v1VendorOrderPOJO.OriginatorDetails od = requestPayLoad.getOriginator_details();
		    String requestOriginatorFirstName = od.getFirst_name();
		    String requestOriginatorLastName = od.getLast_name();
		    String  requestOriginatorMobile = od.getMobile();
		    String requestOriginatorMobileQuote = addQuotes(requestOriginatorMobile);
		    
		 //Get sender_details from Payload
		    v1VendorOrderPOJO.SenderDetails sd = requestPayLoad.getSender_details();
			String requestSenderName = sd.getName();
			String requestSenderMobile = sd.getMobile();
			
		 //Get receiver_details from Payload
			v1VendorOrderPOJO.ReceiverDetails rd = requestPayLoad.getReceiver_details();
			String requestReceiverName = rd.getName();
			String requestReceiverMobile = rd.getMobile();
			
		 //Get from_address details from Payload
			v1VendorOrderPOJO.FromAddress fromAddress = requestPayLoad.getFrom_address();
			String addressLine1 = fromAddress.getAddress_line1();
			String addressLine2 = fromAddress.getAddress_line2();
			String landmark = fromAddress.getLandmark();
			String InstructionToReach = fromAddress.getInstructions_to_reach();
			String googleMapsAddress = fromAddress.getGoogle_maps_address();
			FromAddressExactLocation getExactLocation = fromAddress.getExact_location();
			
		 //Converting from_address Latitude, Longitude from double to String	
			double doubleLatitude = getExactLocation.getLatitude();
			String stringLatitude = Double.toString(doubleLatitude);
			double doubleLongitude = getExactLocation.getLongitude();
			String stringLongitude = Double.toString(doubleLongitude);
			
			String fromPincode = fromAddress.getPincode();
			String fromState = fromAddress.getState();
			
		 //Get to_address details from Payload
			v1VendorOrderPOJO.ToAddress toAddress = requestPayLoad.getTo_address();
			String toAddressLine1 = toAddress.getAddress_line1();
			String toAddress_addressLine2 = toAddress.getAddress_line2();
			String toAddress_landmark = toAddress.getLandmark();
			String toAddress_InstructionToReach = toAddress.getInstructions_to_reach();
			String toAddress_googleMapsAddress = toAddress.getGoogle_maps_address();
			v1VendorOrderPOJO.ToAddressExactLocation getToAddressExactLocation = toAddress.getExact_location();
			
		 //Converting to_address Latitude, Longitude from double to String	
		    double doubleLatitude2 = getToAddressExactLocation.getLatitude();
			String stringLatitude2 = Double.toString(doubleLatitude2);
			double doubleLongitude2 = getToAddressExactLocation.getLongitude();
			String stringLongitude2 = Double.toString(doubleLongitude2);
				
			String toPincode = toAddress.getPincode();
			String toState = toAddress.getState();
			
		 //Extracting trip_id from Response
		    String getTripID = getJsonPath(response, "data.trip_id");
		    
		 //Extracting PBID from Response
		    String getPBID = getJsonPath(response, "data.PBID");
		    
		 //Get 1) from_address 2) to_address id from "trips table" by passing trip_id from response to below query 
		    rs = getDBConnectionAndExecuteQuery("select * from trips where id="+getTripID);
		    rs.next();
		    int getFromAddress = rs.getInt("from_address");
		    int getToAddress = rs.getInt("to_address");
		    
		 //In "Trips" Table verify 1) sender_details  
		    String dbTripsSenderDetails = rs.getString("sender_details");
		    String dbTripsSenderDetailsName = getDBJson(dbTripsSenderDetails, "name");
		    String dbTripsSenderDetailsMobile = getDBJson(dbTripsSenderDetails, "mobile");
		    assertEquals("Verify Sender Name is correctly saved in database", requestSenderName, dbTripsSenderDetailsName);
		    assertEquals("Verify Sender Mobile is correctly saved in database", requestSenderMobile, dbTripsSenderDetailsMobile);
		   
		 //In "Trips" Table verify 2) receiver_details 
		    String dbTripsReceiverDetails = rs.getString("receiver_details");
		    String dbTripsReceiverDetailsName = getDBJson(dbTripsReceiverDetails, "name");
		    String dbTripsReceiverDetailsMobile = getDBJson(dbTripsReceiverDetails, "mobile");
		    assertEquals("Verify Receiver Name is correctly saved in database", requestReceiverName, dbTripsReceiverDetailsName);
		    assertEquals("Verify Receiver Mobile is correctly saved in database", requestReceiverMobile, dbTripsReceiverDetailsMobile);
		    
		 //In "addresses" Table verify 1) from_address
		    rs = getDBConnectionAndExecuteQuery("select * from addresses where id="+getFromAddress);
		    rs.next();
		    assertEquals("Verify addressLine1 is correctly saved in database", addressLine1, rs.getString("address_line1"));
		    assertEquals("Verify addressLine2 is correctly saved in database", addressLine2, rs.getString("address_line2"));
		    assertEquals("Verify landmark is correctly saved in database", landmark, rs.getString("landmark"));
		    assertEquals("Verify InstructionToReach is correctly saved in database", InstructionToReach, rs.getString("instructions_to_reach"));
		    assertEquals("Verify googleMapsAddress is correctly saved in database", googleMapsAddress, rs.getString("google_maps_address"));
		    
		    String dbFromAddressExactLocation = rs.getString("exact_location");
		    String  dbFromAddressLatitude = getDBJson(dbFromAddressExactLocation, "latitude");
		    String dbFromAddressLongitude = getDBJson(dbFromAddressExactLocation, "longitude");
		    assertEquals("Verify Latitude is correctly saved in database", dbFromAddressLatitude, stringLatitude);
		    assertEquals("Verify Longitude is correctly saved in database", dbFromAddressLongitude, stringLongitude);
		    
		    assertEquals("Verify From Address Pincode is correctly saved in database", fromPincode, rs.getString("pincode"));
		    assertEquals("Verify fromAddress State is correctly saved in database", fromState, rs.getString("state").trim());
		   
		 //In "addresses" Table verify 2) to_address
		    rs = getDBConnectionAndExecuteQuery("select * from addresses where id="+getToAddress);
		    rs.next();
		    assertEquals("Verify toAddress addressLine1 is correctly saved in database", toAddressLine1, rs.getString("address_line1"));
		    assertEquals("Verify toAddress addressLine2 is correctly saved in database", toAddress_addressLine2, rs.getString("address_line2"));
		    assertEquals("Verify toAddress landmark is correctly saved in database", toAddress_landmark, rs.getString("landmark"));
		    assertEquals("Verify toAddress InstructionToReach is correctly saved in database", toAddress_InstructionToReach, rs.getString("instructions_to_reach"));
		    assertEquals("Verify toAddress googleMapsAddress is correctly saved in database", toAddress_googleMapsAddress, rs.getString("google_maps_address"));
		    
		    String dbToAddressExactLocation = rs.getString("exact_location");
		    String  dbToAddressLatitude = getDBJson(dbToAddressExactLocation, "latitude");
		    String dbToAddressLongitude = getDBJson(dbToAddressExactLocation, "longitude");
		    assertEquals("Verify toAddress Latitude is correctly saved in database", dbToAddressLatitude, stringLatitude2);
		    assertEquals("Verify toAddress Longitude is correctly saved in database", dbToAddressLongitude, stringLongitude2);
		    
		    assertEquals("Verify toAddress Pincode is correctly saved in database", toPincode, rs.getString("pincode"));
		    assertEquals("Verify toAddress State is correctly saved in database", toState, rs.getString("state").trim());
		    
		 //In "multi_cash_payments" Table verify 1) ReferenceId
			rs = getDBConnectionAndExecuteQuery("select * from multi_cash_payments where id="+getPBID);
		    rs.next();
		    
		    assertEquals("Verify ReferenceId is correctly saved in database", requestReferenceId, rs.getString("reference_id"));
		    
		 //In "multi_cash_payments" Table verify 2) COD
		    assertEquals("Verify COD is correctly saved in database", requestCOD, rs.getString("amount"));
		  
		 //In "multi_cash_payments" Table verify 3) sender_details
		    String dbMCPSenderDetails = rs.getString("sender_details");
		    String dbMCPSenderDetailsName = getDBJson(dbMCPSenderDetails, "name");
		    String dbMCPSenderDetailsMobile = getDBJson(dbMCPSenderDetails, "mobile");
		    assertEquals("Verify Sender Name is correctly saved in database", requestSenderName, dbMCPSenderDetailsName);
		    assertEquals("Verify Sender Mobile is correctly saved in database", requestSenderMobile, dbMCPSenderDetailsMobile);
		   
		 //In "multi_cash_payments" Table verify 3) receiver_details
		    String dbMCPReceiverDetails = rs.getString("receiver_details");
		    String dbMCPReceiverDetailsName = getDBJson(dbMCPReceiverDetails, "name");
		    String dbMCPReceiverDetailsMobile = getDBJson(dbMCPReceiverDetails, "mobile");
		    assertEquals("Verify Receiver Name is correctly saved in database", requestReceiverName, dbMCPReceiverDetailsName);
		    assertEquals("Verify Receiver Mobile is correctly saved in database", requestReceiverMobile, dbMCPReceiverDetailsMobile);
		    
		 //In "Users" Table verify 1) Originator first_name 2) Originator last_name 3) Originator mobile
		    rs = getDBConnectionAndExecuteQuery("select * from users where mobile="+requestOriginatorMobileQuote);
			rs.next();
		    assertEquals("Verify OriginatorFirstName is correctly saved in database", requestOriginatorFirstName, rs.getString("first_name"));
		    assertEquals("Verify OriginatorLastName is correctly saved in database", requestOriginatorLastName, rs.getString("last_name"));
		    assertEquals("Verify OriginatorMobile is correctly saved in database", requestOriginatorMobile, rs.getString("mobile"));
		    
		 //In "vendor_credential_logs" Table verify 1) business_id with vendor's token
		    String getAuth = getGlobalValue("vendorAuthToken");
	        String Token = getAuth.replace("Bearer ", "\'")+"\'";
		    rs = getDBConnectionAndExecuteQuery("select * from vendor_credential_logs where api_key ="+Token);
		    rs.next();
		    assertEquals("Verify business_id 139 saved in database", 139, rs.getInt("business_id"));

}
}
