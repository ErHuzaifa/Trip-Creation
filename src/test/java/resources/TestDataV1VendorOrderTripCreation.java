package resources;

import java.io.IOException;
import java.util.List;

public class TestDataV1VendorOrderTripCreation {
	
	public v1VendorOrderPOJO.v1VendorOrder testDatav1VendorOrderTripCreation(String testDataSheet, String testCaseName)
			throws IOException {

		v1VendorOrderPOJO.v1VendorOrder data = new v1VendorOrderPOJO.v1VendorOrder();
		Utils u = new Utils();

		ExcelDataDriven ed = new ExcelDataDriven();
		List<String> a = ed.getData(testDataSheet, testCaseName);

		//data.setVendor_order_id(a.get(0));
		data.setReference_id(a.get(0));
		data.setVolume(Integer.parseInt(a.get(1)));
		data.setCash_to_be_collected(Integer.parseInt(a.get(2)));
		data.setDelivery_date(u.tomorrowsDate());
		data.setDelivery_slot(a.get(4));
        
		v1VendorOrderPOJO.OriginatorDetails od = new v1VendorOrderPOJO.OriginatorDetails();
		od.setFirst_name(a.get(5));
		od.setLast_name(a.get(6));
		od.setMobile(a.get(7));

		data.setOriginator_details(od);

		v1VendorOrderPOJO.SenderDetails sd = new v1VendorOrderPOJO.SenderDetails ();
		sd.setName(a.get(8));
		sd.setMobile(a.get(9));

		data.setSender_details(sd);

		v1VendorOrderPOJO.ReceiverDetails rd = new v1VendorOrderPOJO.ReceiverDetails();
		rd.setName(a.get(10));
		rd.setMobile(a.get(11));
		data.setReceiver_details(rd);
		
		v1VendorOrderPOJO.FromAddress fa = new v1VendorOrderPOJO.FromAddress();
		fa.setAddress_line1(a.get(12));
		fa.setAddress_line2(a.get(13));
		fa.setLandmark(a.get(14));
		fa.setInstructions_to_reach(a.get(15));
		fa.setGoogle_maps_address(a.get(16));
		
		v1VendorOrderPOJO.FromAddressExactLocation el = new v1VendorOrderPOJO.FromAddressExactLocation();
        el.setLatitude(Double.parseDouble(a.get(17)));
        el.setLongitude(Double.parseDouble(a.get(18)));
        fa.setExact_location(el);
        fa.setState(a.get(19));
        fa.setPincode(a.get(20));
        data.setFrom_address(fa);
        
        v1VendorOrderPOJO.ToAddress ta = new v1VendorOrderPOJO.ToAddress();
		ta.setAddress_line1(a.get(21));
		ta.setAddress_line2(a.get(22));
		ta.setLandmark(a.get(23));
		ta.setInstructions_to_reach(a.get(24));
		ta.setGoogle_maps_address(a.get(25));
		
		v1VendorOrderPOJO.ToAddressExactLocation el2 = new v1VendorOrderPOJO.ToAddressExactLocation();
        el2.setLatitude(Double.parseDouble(a.get(26)));
        el2.setLongitude(Double.parseDouble(a.get(27)));
        ta.setExact_location(el2);
        ta.setState(a.get(28));
        ta.setPincode(a.get(29));
        data.setTo_address(ta);

		return data;
	}

}
