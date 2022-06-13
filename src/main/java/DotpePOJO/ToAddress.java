package DotpePOJO;

public class ToAddress {
	
	private String address_line1;
	private String address_line2;
    private String landmark;
    private String instructions_to_reach;
    private String google_maps_address;
    private ExactLocation exact_location;
    private String state;
    private int pincode;
	
	public String getAddress_line1() {
		return address_line1;
	}
	public void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}
	public String getAddress_line2() {
		return address_line2;
	}
	public void setAddress_line2(String address_line2) {
		this.address_line2 = address_line2;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getInstructions_to_reach() {
		return instructions_to_reach;
	}
	public void setInstructions_to_reach(String instructions_to_reach) {
		this.instructions_to_reach = instructions_to_reach;
	}
	public String getGoogle_maps_address() {
		return google_maps_address;
	}
	public void setGoogle_maps_address(String google_maps_address) {
		this.google_maps_address = google_maps_address;
	}
	public ExactLocation getExact_location() {
		return exact_location;
	}
	public void setExact_location(ExactLocation exact_location) {
		this.exact_location = exact_location;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

}



