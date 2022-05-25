package resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import v2VendorOrderSinglePOJO.CustomerDetail;
import v2VendorOrderSinglePOJO.Dimensions;
import v2VendorOrderSinglePOJO.DispatcherDetail;
import v2VendorOrderSinglePOJO.Packages;
import v2VendorOrderSinglePOJO.SupportDetail;
import v2VendorOrderSinglePOJO.VendorSingleTripCreation;

public class TestDataSingleTripCreation {

	public v2VendorOrderSinglePOJO.VendorSingleTripCreation testDataVendorSingleTripCreation(String testDataSheet, String testCaseName)
			throws IOException {

		v2VendorOrderSinglePOJO.VendorSingleTripCreation data = new v2VendorOrderSinglePOJO.VendorSingleTripCreation();
		Utils u = new Utils();

		ExcelDataDriven ed = new ExcelDataDriven();
		List<String> a = ed.getData(testDataSheet, testCaseName);

		data.setReference_id(a.get(0));
		data.setDelivery_date(u.tomorrowsDate());
		data.setDelivery_slot(a.get(1));
		data.setCod(Integer.parseInt(a.get(2)));
		data.setBill_amount(u.stringToInt(a, 3));
		data.setNot_sending_illegal_items(true);
		data.setPickup_address_id(u.stringToInt(a, 4));
		data.setDrop_address_id(u.stringToInt(a, 5));

		v2VendorOrderSinglePOJO.DispatcherDetail dd = new v2VendorOrderSinglePOJO.DispatcherDetail();
		dd.setName(a.get(6));
		dd.setMobile(a.get(7));

		data.setDispatcher_detail(dd);

		v2VendorOrderSinglePOJO.CustomerDetail cd = new v2VendorOrderSinglePOJO.CustomerDetail();
		cd.setName(a.get(8));
		cd.setMobile(a.get(9));

		data.setCustomer_detail(cd);

		v2VendorOrderSinglePOJO.SupportDetail sd = new v2VendorOrderSinglePOJO.SupportDetail();
		sd.setName(a.get(10));
		sd.setMobile(a.get(11));
		data.setSupport_detail(sd);
		
		v2VendorOrderSinglePOJO.Packages p1 = new v2VendorOrderSinglePOJO.Packages();
		p1.setQuantity(u.stringToInt(a, 12));
		p1.setLabel(a.get(13));
		v2VendorOrderSinglePOJO.Dimensions d = new v2VendorOrderSinglePOJO.Dimensions();
		d.setVolume(u.stringToInt(a, 14));
		d.setWeight(u.stringToInt(a, 15));
		d.setHeight(u.stringToInt(a, 16));
		d.setLength(u.stringToInt(a, 17));
		d.setWidth(u.stringToInt(a, 18));
		p1.setDimension(d);

		v2VendorOrderSinglePOJO.Packages p2 = new v2VendorOrderSinglePOJO.Packages();
		p2.setQuantity(u.stringToInt(a, 19));
		p2.setLabel(a.get(20));
		v2VendorOrderSinglePOJO.Dimensions d2 = new v2VendorOrderSinglePOJO.Dimensions();
		d2.setVolume(u.stringToInt(a, 21));
		d2.setWeight(u.stringToInt(a, 22));
		d2.setHeight(u.stringToInt(a, 23));
		d2.setLength(u.stringToInt(a, 24));
		d2.setWidth(u.stringToInt(a, 25));
		p2.setDimension(d2);
		
		List<v2VendorOrderSinglePOJO.Packages> packages = new ArrayList<v2VendorOrderSinglePOJO.Packages>();
		packages.add(p1);
		packages.add(p2);
		
		data.setPackages(packages);

		return data;
	}

}

