package p1;

import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JDBC;

public class VRecController {

	private JDBC db;
	private Ticket ticket;
	
	public VRecController(JDBC db, Ticket ticket) {
		this.db = db;
		this.ticket = ticket;
	}
	
	public void sendViolationUpdate() throws SQLException {
		// Date Entry to_date('05-Mar-2010','DD-MON-YYYY')
		String query = "INSERT INTO ticket values ("+getTicketCount()+","+"'"+ticket.getViolator_no()+"'"+ "," +"'"+ ticket.getVehicle_id() +"'"+ "," +"'"+ ticket.getOffice_no()+"'"+ "," +"'"+ticket.getVType()+"'"+ "," +ticket.getVDate() + "," +"'"+ticket.getPlace()+"'"+ "," +"'"+ticket.getDescription()+"'"+ ")";
		db.sendUpdate(query);
	}
	
	public int getTicketCount() throws SQLException {
		ResultSet rs = db.sendQuery("select count(*) as count from ticket");
		int count = 0;
		while(rs.next()) {
			count = Integer.parseInt(rs.getString("count")) + 1;
		}
		return count;
	}
}
