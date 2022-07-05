package v1VendorOrderPOJO;

public class v1VendorOrder {
	
	//private String vendor_order_id;
	private String reference_id;
	private int volume;
	private int cash_to_be_collected;
	private String delivery_date;
	private String delivery_slot;
	private OriginatorDetails originator_details;
	private SenderDetails sender_details;
	private ReceiverDetails receiver_details;
	private FromAddress from_address;
	private ToAddress to_address;
	
//	public String getVendor_order_id() {
//		return vendor_order_id;
//	}
//	public void setVendor_order_id(String vendor_order_id) {
//		this.vendor_order_id = vendor_order_id;
//	}
	public String getReference_id() {
		return reference_id;
	}
	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}
	public int getCash_to_be_collected() {
		return cash_to_be_collected;
	}
	public void setCash_to_be_collected(int cash_to_be_collected) {
		this.cash_to_be_collected = cash_to_be_collected;
	}
	public String getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}
	public String getDelivery_slot() {
		return delivery_slot;
	}
	public void setDelivery_slot(String delivery_slot) {
		this.delivery_slot = delivery_slot;
	}
	public OriginatorDetails getOriginator_details() {
		return originator_details;
	}
	public void setOriginator_details(OriginatorDetails originator_details) {
		this.originator_details = originator_details;
	}
	public SenderDetails getSender_details() {
		return sender_details;
	}
	public void setSender_details(SenderDetails sender_details) {
		this.sender_details = sender_details;
	}
	public ReceiverDetails getReceiver_details() {
		return receiver_details;
	}
	public void setReceiver_details(ReceiverDetails receiver_details) {
		this.receiver_details = receiver_details;
	}
	public FromAddress getFrom_address() {
		return from_address;
	}
	public void setFrom_address(FromAddress from_address) {
		this.from_address = from_address;
	}
	public ToAddress getTo_address() {
		return to_address;
	}
	public void setTo_address(ToAddress to_address) {
		this.to_address = to_address;
	}


}
