package p1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jdbc.JDBC;

public class VRecController {

	private JDBC db;
	private Ticket ticket;

	public VRecController(JDBC db) {
		this.db = db;
		this.ticket = new Ticket();

	}

	public void sendViolationUpdate() throws SQLException {
		String query = "INSERT INTO ticket values ("+getTicketCount()+","+"'"+ticket.getViolator_no()+"'"+ "," +"'"+ ticket.getVehicle_id() +"'"+ "," +"'"+ ticket.getOffice_no()+"'"+ "," +"'"+ticket.getVType()+"'"+","+"to_date(" + "'" +ticket.getVDate()+"'"+",'DD-MON-YYYY')"+"," +"'"+ticket.getPlace()+"'"+ "," +"'"+ticket.getDescription()+"'"+ ")";
		db.sendUpdate(query);
	}

	public int getTicketCount() throws SQLException {
		ResultSet rs = db.sendQuery("select count(*) as count from ticket");
		int count = 0;
		while(rs.next()) {
			count = Integer.parseInt(rs.getString("count")) + 1;
		}
		System.out.println("New Ticket will be Ticket Number: " + count);
		rs.close();
		return count;
	}

	public String getDate() {
		Date cDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		String output = sdf.format(cDate);
		System.out.println("DATE: " + output);
		ticket.setVDate(output);
		return output;
	}
	
	public void setTicketNo() throws SQLException {
		ticket.setTicket_no(getTicketCount());
	}
	public boolean setViolatorNo(String violator_no) throws SQLException {
		boolean violator_exist = true;
		boolean error = false;
		ResultSet rs;

		rs = db.sendQuery("select * from people where sin="+"'"+violator_no+"'");
		violator_exist = rs.next();

		// if length is greater than 15, tell user it is INVALID
		if (violator_no.length() > 15) {
			System.out.println("Violator Number length > 15!!!");
			error = true;
			rs.close();
			return error;
			// if violator DOES NOT exist, tell user to register the Violator first
		} else if (violator_exist == false) {
			System.out.println("Violator Number does not exist");
			error = true;
			rs.close();
			return error;
		} else {
			ticket.setViolator_no(violator_no);
			error = false;
			rs.close();
			return error;
		}			
	}

	// Returns: 0 for No Error, 1 for Vehicle Doesn't Exist, 2 for Primary Owner Retrieved, 3 for WrongDataType
	public int setVehicleID(String vehicle_id) throws SQLException {
		ResultSet rs = db.sendQuery("select * from owner where vehicle_id="+"'"+vehicle_id+"'");
		boolean vehicle_exist = rs.next();
		int rc = 0;
		// Input too long
		if (vehicle_id.length() > 15) {
			System.out.println("Input Too Long > 15!!!");
			rc = 1;
			rs.close();
			return rc;
		// Vehicle Does Not Exist
		} else if (vehicle_exist == false) {
			rc = 1;
			rs.close();
			return rc;
		// Vehicle Exists
		} else if (vehicle_exist == true) {
			boolean try_again = true;
			while(try_again) {
				String response = System.console().readLine("Vehicle Exists, Primary Owner of vehicle identified.\nUse primary owner as violator? (y/n): ");
				if (response.toLowerCase().equals("y")) {
					setViolatorNo(rs.getString("owner_id"));
					rc = 2;
					try_again = false;
				} else if(response.toLowerCase().equals("n")) {
					rc = 0;
					try_again = false;
				} else {
					System.console().printf("Invalid Respnse, try again\n");
					try_again = true;
				}
			}
			ticket.setVehicle_id(vehicle_id);
			rs.close();
			return rc;
		} else {
			ticket.setVehicle_id(vehicle_id);
			rs.close();
			return rc;
		}	
	}
	public boolean setOfficeNo(String office_no) {
		boolean error = false;
		if (office_no.length() > 15) {
			error = true;
			return error;
		} else {
			error = false;
			ticket.setOffice_no(office_no);
			return error;
		}
	}
	public boolean setVType(String vtype) {
		boolean error = false;
		if(vtype.length() > 10) {
			error = true;
			return error;
		} else {
			error = false;
			ticket.setVType(vtype);
			return error;
		}
	}
	public boolean setPlace(String place) {
		boolean error = false;
		if (place.length() > 20) {
			error = true;
			return error;
		} else {
			error = false;
			ticket.setPlace(place);
			return error;
		}	
	}
	public boolean setDesc(String desc) {
		boolean error = false;
		if (desc.length() > 1024) {
			error = true;
			return error;
		} else {
			error = false;
			ticket.setDescription(desc);
			return error;
		}	
	}
	
	public void setDate() {
		ticket.setVDate(getDate());
	}

	public void test() {
		System.out.println(ticket.getVType());
		System.out.println(ticket.getTicket_no());
		System.out.println(ticket.getViolator_no());
		System.out.println(ticket.getOffice_no());
		System.out.println(ticket.getVehicle_id());
		System.out.println(ticket.getVDate());
	}
}
