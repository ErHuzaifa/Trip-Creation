package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestDataDotpeTripCreation {
	
	public DotpePOJO.Dotpe testDataDotpeTripCreation(String testDataSheet, String testCaseName)
			throws IOException {

		DotpePOJO.Dotpe data = new DotpePOJO.Dotpe();
		Utils u = new Utils();

		ExcelDataDriven ed = new ExcelDataDriven();
		List<String> a = ed.getData(testDataSheet, testCaseName);

		data.setVendor_order_id(a.get(0));
        
		DotpePOJO.OriginatorDetails od = new DotpePOJO.OriginatorDetails();
		od.setFirst_name(a.get(1));
		od.setLast_name(a.get(2));
		od.setMobile(a.get(3));

		data.setOriginator_details(od);

		DotpePOJO.SenderDetails sd = new DotpePOJO.SenderDetails ();
		sd.setName(a.get(4));
		sd.setMobile(a.get(5));

		data.setSender_details(sd);

		DotpePOJO.ReceiverDetails rd = new DotpePOJO.ReceiverDetails();
		rd.setName(a.get(6));
		rd.setMobile(a.get(7));
		data.setReceiver_details(rd);
		
		DotpePOJO.FromAddress fa = new DotpePOJO.FromAddress();
		fa.setAddress_line1(a.get(8));
		fa.setAddress_line2(a.get(9));
		fa.setLandmark(a.get(10));
		fa.setInstructions_to_reach(a.get(11));
		fa.setGoogle_maps_address(a.get(12));
		
		DotpePOJO.ExactLocation el = new DotpePOJO.ExactLocation();
        el.setLatitude(Double.parseDouble(a.get(13)));
        el.setLongitude(Double.parseDouble(a.get(14)));
        fa.setExact_location(el);
        fa.setState(a.get(15));
        fa.setPincode(u.stringToInt(a, 16));
        data.setFrom_address(fa);
        
        DotpePOJO.ToAddress ta = new DotpePOJO.ToAddress();
		ta.setAddress_line1(a.get(17));
		ta.setAddress_line2(a.get(18));
		ta.setLandmark(a.get(19));
		ta.setInstructions_to_reach(a.get(20));
		ta.setGoogle_maps_address(a.get(21));
		
		DotpePOJO.ExactLocation el2 = new DotpePOJO.ExactLocation();
        el2.setLatitude(Double.parseDouble(a.get(22)));
        el2.setLongitude(Double.parseDouble(a.get(23)));
        ta.setExact_location(el);
        ta.setState(a.get(24));
        ta.setPincode(u.stringToInt(a, 25));
        data.setTo_address(ta);

		return data;
	}

}
