package p1;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

import ui.PersonView;
import jdbc.JDBC;

public class ATController {
	private AutoTransaction at=new AutoTransaction();
	private People buyer=new People();
	private People seller=new People();
	private JDBC db;
	public ATController(JDBC db) {
		this.db=db;
	}

	public void getPrice(String readLine) throws NumberFormatException {
		at.setPrice(Double.parseDouble(readLine));
	}

	public void setSellDate(String readLine) throws IllegalArgumentException {
		at.setS_date(java.sql.Date.valueOf(readLine));
	}

	public void setVehicleID(String readLine) {
		at.setVehicle_id(readLine);
	}

	public void setBuyerID(String readLine) {
		buyer.setSIN(readLine);
		at.setBuyer_id(readLine);
		
	}

	public void setTransactionID(String readLine) throws NumberFormatException {
		at.setTransaction_id(Integer.parseInt(readLine));
		
	}

	public void submit() {
		if(VehicleNotExists()){
			System.out.println("Vehicle Does not exist");
		}
		else if(SellerNotOwner()){
			System.out.println("Seller does not own vehicle");
		}
		else{
			try {
				db.sendModelinfo(at);
			} catch (FileNotFoundException e) {
			} catch (SQLException e) {
				if(e.getErrorCode()==2291){
					System.out.printf("Person %s does not exist. They  will need to be created\n",buyer.getSIN());
					Resend(buyer);
				}
				else{
					System.out.println(e.getMessage());
					return;
				}
			}
			try {
				db.sendUpdate("delete from owner where vehicle_id='"+at.getVehicle_id()+"'");
				ResultSet rs=db.sendQuery("select owner_id,vehicle_id,is_primary_owner from owner");
				rs.moveToInsertRow();
				rs.updateString("owner_id",buyer.getSIN());
				rs.updateString("vehicle_id",at.getVehicle_id());
				rs.updateString("is_primary_owner","y");
				rs.insertRow();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		
	}

	private void Resend(People buyer2) {
		new PersonView().run(buyer2);
		try {
			db.sendModelinfo(buyer2);
			db.sendModelinfo(at);
		} catch (FileNotFoundException e) {
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private boolean SellerNotOwner() {
		//vehicle( serial_no, maker, model, year, color, type_id )
		//owner(owner_id, vehicle_id, is_primary_owner)
		try {
			ResultSet rs=db.sendQuery("select * from owner where owner_id='"+seller.getSIN()+"' and vehicle_id='"+at.getVehicle_id()+"'");
			boolean failed=!rs.next();
			rs.close();
			return failed;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return true;
		}
	}

	private boolean VehicleNotExists() {
		try {
			ResultSet rs=db.sendQuery("select * from vehicle where serial_no='"+at.getVehicle_id()+"'");
			boolean failed=!rs.next();
			return failed;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return true;
		}
	}

	public void setSellerID(String readLine) {
		seller.setSIN(readLine);
		at.setSeller_id(readLine);
		
	}
}
