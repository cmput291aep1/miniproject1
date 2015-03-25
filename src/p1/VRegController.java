package p1;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JDBC;

public class VRegController {
	private Vehicle v1=new Vehicle();
	private People primary=new People();
	private ArrayList<People> secOwners=new ArrayList<People>();
	private JDBC db;
	private boolean multi_owners;
	private ResultSet rs;
	private boolean repeat=false;
	
	
	public VRegController(JDBC DB){
		db=DB;
	}
	public VRegController(){
		dbInit();
	}
	private void dbInit() {
		try {
			db=JDBC.getInstance("","");
		} catch (SQLException e) {
			System.out.println("Lost connection to DB");
			System.exit(0);
		} catch (ClassNotFoundException e) {
		}
	}
	public void addSN(String readLine) {
		v1.setSerial_no(readLine);
	}

	public void submit() {
		try {
			db.sendModelinfo(v1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		submitOwners();
	}

	private void submitOwners() {
		generateOwnerResultSet();
		boolean failed=insertPrimaryOwner();
		if(!failed && multi_owners)
			insertSecondaryOwners();
		try {
			rs.close();
		} catch (SQLException e) {
		}
	}
	private void insertSecondaryOwners() {
		for(People p:secOwners){
			insertOwner(p,"n");
		}
		
	}
	private boolean insertPrimaryOwner() {
		return insertOwner(primary,"y");
		
	}
	private boolean insertOwner(People p,String primary) {
		boolean failure=false;
		try {
			rs.moveToInsertRow();
			rs.updateString("owner_id",p.getSIN());
			rs.updateString("vehicle_id",v1.getSerial_no());
			rs.updateString("is_primary_owner","y");
			rs.insertRow();
		} catch (SQLException e) {
			if(e.getErrorCode()==2291){
				System.out.printf("Person %s does not exist\n",p.getSIN());
				return Resend(p,primary);
			}
			else{
				e.printStackTrace();
				failure=true;
			}
			generateOwnerResultSet();
		}
		return failure;
	}
	private boolean Resend(People p, String primary2) {
		try {
			p.setBday(Date.valueOf("1993-10-06"));
			db.sendModelinfo(p);
			insertOwner(p,primary2);
		} catch (FileNotFoundException e) {
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return true;
		}
		return false;
		
	}
	private ResultSet generateOwnerResultSet() {
		// owner(owner_id, vehicle_id, is_primary_owner)
		try {
			 rs=db.sendQuery("select owner_id,vehicle_id,is_primary_owner from owner");
			 return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void addPrimaryOwner(String readLine) {
		primary.setSIN(readLine);
	}

	public void addSecondaryOwner(String readLine) {
		multi_owners=true;
		People owner=new People();
		owner.setSIN(readLine);
		secOwners.add(owner);
	}
	
	public void setVehicleType(String v_id) throws NumberFormatException {
		v1.setType_id(Integer.parseInt(v_id));
		
	}

	public void setColor(String string) {
		v1.setColor(string);
		
	}

	public void setVModel(String readLine) {
		v1.setModel(readLine);
		
	}
	public void setYear(String readLine) throws NumberFormatException {
		v1.setYear(Integer.parseInt(readLine));
		
	}
}
