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
	// TODO error checking, also check whether elements are already in the database
	public void setTicketNo() throws SQLException {
		ticket.setTicket_no(getTicketCount());
	}
	public boolean setViolatorNo(String violator_no) {
		boolean violator_exist = false;
		boolean error = false;
		ResultSet rs;
		try {
			rs = db.sendQuery("select * from people where sin="+"'"+violator_no+"'");
			rs.next();
		} catch (SQLException e) {
			// If query returns no violator no then user can proceed entering 
			System.out.println(e.getMessage());
			System.out.println("Violator does not exist!");
			violator_exist = false;
		}
		if (violator_no.length() > 15) {
			error = true;
			return error;
		} else if (violator_exist == true) {
			error = true;
			return error;
		} else {
			ticket.setViolator_no(violator_no);
			return true;
		}			
	}
	public void setVehicleID(String vehicle_id) {
		ticket.setVehicle_id(vehicle_id);
	}
	public void setOfficeNo(String office_no) {
		ticket.setOffice_no(office_no);
	}
	public void setVType(String vtype) {
		ticket.setVType(vtype);
	}
	public void setPlace(String place) {
		ticket.setPlace(place);
	}
	public void setDesc(String desc) {
		ticket.setDescription(desc);
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
