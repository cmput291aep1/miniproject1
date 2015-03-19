package p1;

import java.sql.Date;

public class AutoTransaction extends Model {
	private Integer transaction_id;
	private String seller_id;
	private String buyer_id; 
	private String vehicle_id;
	private Date s_date;
	private Integer price;
	
	public AutoTransaction(){
		
	}
	public AutoTransaction(Integer transaction_id,String seller_id, String buyer_id, String vehicle_id, Date s_date, Integer price){
		this.transaction_id=transaction_id;
		this.seller_id=seller_id.toLowerCase();
		this.buyer_id=buyer_id.toLowerCase();
		this.vehicle_id=vehicle_id.toLowerCase();
		this.s_date=s_date;
		this.price=price;
	}
	
	public Integer getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(Integer transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id.toLowerCase();
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id.toLowerCase();
	}

	public String getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id.toLowerCase();
	}

	public Date getS_date() {
		return s_date;
	}

	public void setS_date(Date s_date) {
		this.s_date = s_date;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}


	@Override
	public String generateStatement() {
		return generateInsert("auto_sale","transaction_id","seller_id","buyer_id","vehicle_id","s_date","price")+" "+encapsulate(transaction_id+",'"+seller_id+"','"+buyer_id+"','"+vehicle_id+"',TO_DATE('"+s_date+"','YYYY-MM-DD'),"+price);
	}
}
