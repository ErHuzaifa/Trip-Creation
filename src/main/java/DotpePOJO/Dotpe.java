package DotpePOJO;

import DotpePOJO.OriginatorDetails;
import DotpePOJO.SenderDetails;
import DotpePOJO.ReceiverDetails;

public class Dotpe {
	
	private String vendor_order_id;
	private OriginatorDetails originator_details;
    private SenderDetails sender_details;
    private ReceiverDetails receiver_details;
    private FromAddress from_address;
    private ToAddress To_address;
	
	public String getVendor_order_id() {
		return vendor_order_id;
	}
	public void setVendor_order_id(String vendor_order_id) {
		this.vendor_order_id = vendor_order_id;
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
		return To_address;
	}
	public void setTo_address(ToAddress to_address) {
		To_address = to_address;
	}
	
}
