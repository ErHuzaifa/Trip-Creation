package v2VendorOrderSinglePOJO;

import java.util.List;

public class VendorSingleTripCreation {
    private String reference_id;
    private String delivery_date;
    private String delivery_slot;
    private int cod;
    private int bill_amount;
    private boolean not_sending_illegal_items;
	private SupportDetail support_detail;
    private CustomerDetail customer_detail;
    private DispatcherDetail dispatcher_detail;
    private int pickup_address_id;
    private int drop_address_id;
    private List<Packages> packages;
    
    
    public String getReference_id() {
		return reference_id;
	}
	public void setReference_id(String reference_id) {
		this.reference_id = reference_id;
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
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public int getBill_amount() {
		return bill_amount;
	}
	public void setBill_amount(int bill_amount) {
		this.bill_amount = bill_amount;
	}
	public boolean isNot_sending_illegal_items() {
		return not_sending_illegal_items;
	}
	public void setNot_sending_illegal_items(boolean not_sending_illegal_items) {
		this.not_sending_illegal_items = not_sending_illegal_items;
	}
	public List<Packages> getPackages() {
		return packages;
	}
	public void setPackages(List<Packages> packages) {
		this.packages = packages;
	}
	public SupportDetail getSupport_detail() {
		return support_detail;
	}
	public void setSupport_detail(SupportDetail support_detail) {
		this.support_detail = support_detail;
	}
	public CustomerDetail getCustomer_detail() {
		return customer_detail;
	}
	public void setCustomer_detail(CustomerDetail customer_detail) {
		this.customer_detail = customer_detail;
	}
	public DispatcherDetail getDispatcher_detail() {
		return dispatcher_detail;
	}
	public void setDispatcher_detail(DispatcherDetail dispatcher_detail) {
		this.dispatcher_detail = dispatcher_detail;
	}
	public int getPickup_address_id() {
		return pickup_address_id;
	}
	public void setPickup_address_id(int pickup_address_id) {
		this.pickup_address_id = pickup_address_id;
	}
	public int getDrop_address_id() {
		return drop_address_id;
	}
	public void setDrop_address_id(int drop_address_id) {
		this.drop_address_id = drop_address_id;
	}


}
