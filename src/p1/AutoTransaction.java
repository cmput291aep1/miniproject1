package p1;

import java.sql.Date;

public class AutoTransaction extends Model {
	private Integer transaction_id;
	private String seller_id;
	private String buyer_id; 
	private String vehicle_id;
	private Date s_date;
	private Integer price;

	public AutoTransaction(Integer transaction_id,String seller_id, String buyer_id, String vehicle_id, Date s_date, Integer price){
		this.transaction_id=transaction_id;
		this.seller_id=seller_id;
		this.buyer_id=buyer_id;
		this.vehicle_id=vehicle_id;
		this.s_date=s_date;
		this.price=price;
	}

	@Override
	public String generateStatement() {
		return generateInsert("auto_sale","seller_id","buyer_id","vehicle_id","s_date","price")+encapsulate("'"+seller_id+"','"+buyer_id+"','"+vehicle_id+"','"+s_date.toString()+"',"+price);
	}
}
