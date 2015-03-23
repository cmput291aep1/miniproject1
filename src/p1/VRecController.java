package p1;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

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
	// TODO error checking
	public void setTicketNo() throws SQLException {
		ticket.setTicket_no(getTicketCount());
	}
	public void setViolatorNo(String violator_no) {
		ticket.setViolator_no(violator_no);
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
