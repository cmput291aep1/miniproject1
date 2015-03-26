package ui;

import jdbc.JDBC;
import p1.ATController;

public class AutoTransView implements View {
/*	CREATE TABLE auto_sale (
			  transaction_id  int,
			  seller_id   CHAR(15),
			  buyer_id    CHAR(15),
			  vehicle_id  CHAR(15),
			  s_date      date,
			  price       numeric(9,2),
			  PRIMARY KEY (transaction_id),
			  FOREIGN KEY (seller_id) REFERENCES people,
			  FOREIGN KEY (buyer_id) REFERENCES people,
			  FOREIGN KEY (vehicle_id) REFERENCES vehicle
			);*/
	private ATController at;
	public AutoTransView(JDBC db) {
		at=new ATController(db);
	}
	@Override
	public void run() {
		getTransactionID();
		getSellerID();
		getBuyerID();
		getVehicleID();
		getSellDate();
		getPrice();
		at.submit();
	}
	private void getPrice() {
		boolean success=false;
		while(!success){
			try {
				at.getPrice(System.console().readLine("Price: "));
				success=true;
			} catch (NumberFormatException e) {
				success=false;
				String response=System.console().readLine("Not a Number.Make a correction(y) or abandon (n)?(y\n)");
				if(!response.equalsIgnoreCase("y")){
					success=true;
				}
			}
		}
		
	}
	private void getSellDate() {
		boolean success=false;
		while(!success){
			try {
				at.setSellDate(System.console().readLine("Sell Date (YYYY-MM-DD): "));
				success=true;
			} catch (IllegalArgumentException e) {
				success=false;
				String response=System.console().readLine("Invalid Date.Make a correction(y) or abandon (n)?(y\n)");
				if(!response.equalsIgnoreCase("y")){
					success=true;
				}
			}
		}
		
	}
	private void getVehicleID() {
		at.setVehicleID(System.console().readLine("Vehicle ID"));
		
	}
	private void getBuyerID() {
		at.setBuyerID(System.console().readLine("Buyer ID"));
		
	}
	private void getSellerID() {
		at.setSellerID(System.console().readLine("Seller ID"));
		
	}
	private void getTransactionID() {
		boolean success=false;
		while(!success){
			try {
				at.setTransactionID(System.console().readLine("Transaction ID"));
				success=true;
			} catch (NumberFormatException e) {
				success=false;
				String response=System.console().readLine("Not a Number.Try again");
			}
		}
		
	}

}
