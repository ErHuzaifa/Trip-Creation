package stepDefinations;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import v2VendorOrderSinglePOJO.CustomerDetail;
import v2VendorOrderSinglePOJO.DispatcherDetail;
import v2VendorOrderSinglePOJO.Packages;
import v2VendorOrderSinglePOJO.SupportDetail;
import v2VendorOrderSinglePOJO.VendorSingleTripCreation;
import resources.TestDataSingleTripCreation;
import resources.Utils;

public class vendorSIngleOrderCreateStepDefinition extends Utils {
	
	 RequestSpecification requestSpec;
	 ResponseSpecification responseSpec;
	 Response response;
	 static VendorSingleTripCreation requestPayLoad;
	 public static String jsonAsString;
	
	 @Given("VendorSingleOrderCreate API with {string} {string}")
	 public void vendorsingleordercreate_API_with(String string, String string2) throws IOException {
	      
		          String authToken = getGlobalValue("vendorAuthToken");
		          responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		          
				  TestDataSingleTripCreation data = new TestDataSingleTripCreation();
				  requestPayLoad = data.testDataVendorSingleTripCreation(string, string2);
				  requestSpec=given().spec(requestSpecification(authToken))
				  .body(requestPayLoad);
				  
    }


	@When("User calls {string} api with {string} https request")
	public void user_calls_VendorSingleOrderCreate_API_with_http_request(String endPoint, String method) {
	   
		 response =requestSpec.when().post("v2.0/vendor/order/single").
				then().spec(responseSpec).extract().response();
		 
	}
	
	@Then("The api call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(int int1) {
		
		assertEquals(int1, response.getStatusCode());
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyvalue, String expectedValue) {
	   
		assertEquals(getJsonPath(response, keyvalue), expectedValue);
		
	}
	
	@Then("Verify each request key in the database")
	public void verify_rider_reservation_created_in_the_data_base() throws SQLException, IOException {
		ResultSet rs = null;
		
	 //Extracting JSON Data from Request
		int cod = requestPayLoad.getCod();
		String RefID = requestPayLoad.getReference_id();
		int ToAddress = requestPayLoad.getDrop_address_id();
		int FromAddress = requestPayLoad.getPickup_address_id();
		DispatcherDetail dispatcherDetail = requestPayLoad.getDispatcher_detail();
		String DisName = dispatcherDetail.getName();
		String DisMobile = dispatcherDetail.getMobile();
		CustomerDetail customerDetail = requestPayLoad.getCustomer_detail();
		String CusName = customerDetail.getName();
		String CusMobile = customerDetail.getMobile();
		SupportDetail supportDetail = requestPayLoad.getSupport_detail();
		String supportName = supportDetail.getName();
		String supportMobile = supportDetail.getMobile();
		
	 //Extracting JSON Data from Response
	    String getTripID = getJsonPath(response, "data.trip_id");
	    String getPBID = getJsonPath(response, "data.PBID");
	    
	 //Extracting quantity from packages array and adding total quantity in a variable
	    List<Packages> p = requestPayLoad.getPackages();
        int totalPackageQuantity = 0;
		for(int i=0; i<p.size(); i++) {
			
			int quantity = p.get(i).getQuantity();
			totalPackageQuantity = totalPackageQuantity + quantity;
		}
		
	 //In "map_trip_packages" Table verify 1) Total Quantity from Multiple Packages
		rs = getDBConnectionAndExecuteQuery("select * from map_trip_packages where trip_id ="+getTripID);
	    rs.next();
	    assertEquals("Verify Total Quantity from Multiple Packages is correctly saved in database",totalPackageQuantity, rs.getInt("quantity"));
	    
	 //In "packages" Table verify 1) Volume
	    int packageID = rs.getInt("package_id");
	    rs = getDBConnectionAndExecuteQuery("select * from packages where id ="+packageID);
	    rs.next();
	    assertEquals("Verify volume is correctly saved in database",1800, rs.getInt("volume"));
	    
	 //In "multi_cash_payments" Table verify 1) COD 2) reference_id 3) to_address 4) from_address 5) Dispatcher Details 6) Customer Details
		rs = getDBConnectionAndExecuteQuery("select * from multi_cash_payments where id="+getPBID);
	    rs.next();
	    assertEquals("Verify cod is correctly saved in database",cod, rs.getInt("amount"));
	    assertEquals("Verify reference_id is correctly saved in database",RefID, rs.getString("reference_id"));
	    
	    assertEquals("Verify ToAddress is correctly saved in database", ToAddress, rs.getInt("to_address"));
	    assertEquals("Verify FromAddress is correctly saved in database", FromAddress, rs.getInt("from_address"));
	    
	    String dbMCPSenderDetails = rs.getString("sender_details");
	    String dbMCPSenderDetailsName = getDBJson(dbMCPSenderDetails, "name");
	    String dbMCPSenderDetailsMobile = getDBJson(dbMCPSenderDetails, "mobile");
	    assertEquals("Verify Dispather Name is correctly saved in database", DisName, dbMCPSenderDetailsName);
	    assertEquals("Verify Dispather Mobile is correctly saved in database", DisMobile, dbMCPSenderDetailsMobile);
	    
	    String dbMCPReceiverDetails = rs.getString("receiver_details");
	    String dbMCPReceiverDetailsName = getDBJson(dbMCPReceiverDetails, "name");
	    String dbMCPReceiverDetailsMobile = getDBJson(dbMCPReceiverDetails, "mobile");
	    assertEquals("Verify Customer Name is correctly saved in database", CusName, dbMCPReceiverDetailsName);
	    assertEquals("Verify Customer Mobile is correctly saved in database", CusMobile, dbMCPReceiverDetailsMobile);
	    	    
	 //In "Trips" Table verify 1) to_address 2) from_address 3) Dispatcher Details 4) Customer Details
	    rs = getDBConnectionAndExecuteQuery("select * from trips where id ="+getTripID);
	    rs.next();
	    assertEquals("Verify ToAddress is correctly saved in database", ToAddress, rs.getInt("to_address"));
	    assertEquals("Verify FromAddress is correctly saved in database", FromAddress, rs.getInt("from_address"));
	    
	    String dbTripsSenderDetails = rs.getString("sender_details");
	    String dbTripsSenderDetailsName = getDBJson(dbTripsSenderDetails, "name");
	    String dbTripsSenderDetailsMobile = getDBJson(dbTripsSenderDetails, "mobile");
	    assertEquals("Verify Dispather Name is correctly saved in database", DisName, dbTripsSenderDetailsName);
	    assertEquals("Verify Dispather Mobile is correctly saved in database", DisMobile, dbTripsSenderDetailsMobile);
	    
	    String dbTripsReceiverDetails = rs.getString("receiver_details");
	    String dbTripsReceiverDetailsName = getDBJson(dbTripsReceiverDetails, "name");
	    String dbTripsReceiverDetailsMobile = getDBJson(dbTripsReceiverDetails, "mobile");
	    assertEquals("Verify Customer Name is correctly saved in database", CusName, dbTripsReceiverDetailsName);
	    assertEquals("Verify Customer Mobile is correctly saved in database", CusMobile, dbTripsReceiverDetailsMobile);
	    
	 //In "Vendor_Orders" Table verify 1) Delivery_date 2) Delivery_slot
	    rs = getDBConnectionAndExecuteQuery("select * from vendor_orders where trip_id ="+getTripID);
	    rs.next();
	    String deliveryDate = requestPayLoad.getDelivery_date();
	    String deliverySlot = requestPayLoad.getDelivery_slot();
	    
	    assertEquals("Verify deliveryDate is correctly saved in database", deliveryDate, rs.getString("delivery_date"));
	    assertEquals("Verify deliverySlot is correctly saved in database", deliverySlot, rs.getString("delivery_slot"));
	    
     //In "vendor_credential_logs" Table verify 1) business_id with vendor's token
	    String getAuth = getGlobalValue("vendorAuthToken");
        String Token = getAuth.replace("Bearer ", "\'")+"\'";
	    rs = getDBConnectionAndExecuteQuery("select * from vendor_credential_logs where api_key ="+Token);
	    rs.next();
	    assertEquals("Verify business_id 139 saved in database", 139, rs.getInt("business_id"));
	    
	 //In "Users" Table verify 1) User's Name 2) User's Mobile
	    String suppMobile = addQuotes(supportMobile);
	    rs = getDBConnectionAndExecuteQuery("select * from users where mobile ="+suppMobile);
	    rs.next();
	    assertEquals("Verify User's Name is correctly saved in database", supportName, rs.getString("last_name"));
	    assertEquals("Verify User's Mobile is correctly saved in database", supportMobile, rs.getString("mobile"));
	    
	}
}