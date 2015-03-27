package p1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JDBC;


public class Search
{

	private JDBC mgr;
	private String query1;
	private String query2;
	private String query3;
	private String query4;
	private String query5;
	private String query6;
	private String[] headers;

	public Search(JDBC mgr) {
		this.mgr = mgr;
	}

	public boolean queryGeneralInfoByName(String searchName) throws SQLException {
		query1 = "select p.name, d.licence_no, p.addr, p.birthday, d.class, dc.description, d.expiring_date " +
				"from people p, drive_licence d, driving_condition dc, restriction r " +
				"where p.sin=d.sin and d.licence_no=r.licence_no and dc.c_id=r.r_id and lower(p.name)="+ "'" + searchName.toLowerCase() + "'";
		ResultSet rs = mgr.sendQuery(query1);
		ResultSet rsCheck = mgr.sendQuery("select * from people where lower(name)="+"'"+searchName.toLowerCase()+"'");
		boolean exists = rsCheck.next();
		boolean error = false;
		if (exists != true) {
			System.out.println("No match");
			error = true;
			return error;
		} else {
			printGeneralInfo(rs);
			rsCheck.close();
			return error;
		}
		
	}

	public boolean queryGeneralInfoByLicenceNo(String searchLicenceNo) throws SQLException {
		query2 = "select p.name, d.licence_no, p.addr, p.birthday, d.class, dc.description, d.expiring_date " +
				"from people p, drive_licence d, driving_condition dc, restriction r " +
				"where p.sin=d.sin and d.licence_no=r.licence_no and dc.c_id=r.r_id and lower(d.licence_no)=" + "'" + searchLicenceNo.toLowerCase() + "'";
		ResultSet rs = mgr.sendQuery(query2);
		ResultSet rsCheck = mgr.sendQuery("select * from drive_licence where lower(licence_no)="+"'"+searchLicenceNo.toLowerCase()+"'");
		boolean exists = rsCheck.next();
		boolean error = false;
		if (exists != true) {
			error = true;
			return error;
		} else {
			printGeneralInfo(rs);
			rsCheck.close();
			return error;
		}
	}

	public boolean queryViolationBySIN(String searchSIN) throws SQLException {
		query3 = "select distinct ticket_no, violator_no, vehicle_id, office_no, vtype, vdate, place, descriptions " +
				"from ticket, people p " +
				"where violator_no=p.sin AND lower(p.sin)=" + "'" + searchSIN.toLowerCase() + "'";
		ResultSet rs = mgr.sendQuery(query3);
		ResultSet rsCheck = mgr.sendQuery("select * from people where lower(sin)="+"'"+searchSIN.toLowerCase()+"'");
		boolean exists = rsCheck.next();
		boolean error = false;
		if (exists != true) {
			error = true;
			return error;
		} else {
			printViolations(rs);
			rsCheck.close();
			return error;
		}
	}

	public boolean queryViolationByLicenceNo(String searchLicenceNo) throws SQLException {
		query4 = "select distinct ticket_no, violator_no, vehicle_id, office_no, vtype, vdate, place, descriptions " +
				"from ticket, people p, drive_licence d " +
				"where violator_no=p.sin AND p.sin=d.sin AND lower(d.licence_no)=" + "'" + searchLicenceNo.toLowerCase() + "'";
		ResultSet rs = mgr.sendQuery(query4);
		ResultSet rsCheck = mgr.sendQuery("select * from drive_licence where lower(licence_no)="+"'"+searchLicenceNo.toLowerCase()+"'");
		boolean exists = rsCheck.next();
		boolean error = false;
		if (exists != true) {
			error = true;
			return error;
		} else {
			printViolations(rs);
			rsCheck.close();
			return error;
		}
	}
	
	public boolean queryVehicleHistBySerialNo(String searchVehicleID) throws SQLException {
		query5 = "select vehicle_id, COUNT(*) as TotalChangedHand, AVG(Price) as AveragePrice " +
				"from auto_sale " +
				"group by vehicle_id " +
				"having lower(vehicle_id)=" + "'" + searchVehicleID.toLowerCase() + "'";
		query6 = "select vehicle_id, COUNT(*) as TotalViolations " +
				"from ticket " +
				"group by vehicle_id " +
				"having lower(vehicle_id)=" + "'" + searchVehicleID.toLowerCase() + "'";
		ResultSet rs1 = mgr.sendQuery(query5);
		ResultSet rs2 = mgr.sendQuery(query6);
		ResultSet rsCheck = mgr.sendQuery("select * from vehicle where lower(serial_no)="+"'"+searchVehicleID.toLowerCase()+"'");
		boolean exists = rsCheck.next();
		boolean error = false;
		if (exists != true) {
			error = true;
			return error;
		} else {
			printVehicleHistory(rs1,rs2);
			rsCheck.close();
			return error;
		}
	}
	
	
	private void printGeneralInfo(ResultSet rs) throws SQLException {
		System.out.printf("%-40s%-15s%-50s%-22s%-10s%-22s%-1024s\n","Name", "Licence_No", "Addr", "Birthday", "Class", "Expiring_Date", "Description");
		while(rs.next()){
			System.out.printf("%-40s%-15s%-50s%-22s%-10s%-22s%-1024s\n", rs.getString("name"),rs.getString("Licence_No"),rs.getString("Addr"),rs.getString("Birthday"),rs.getString("Class"),rs.getString("Expiring_Date"),rs.getString("Description"));
		}
		rs.close();
	}
	
	private void printViolations(ResultSet rs) throws SQLException {
		if (rs.next() == false) {
			System.out.println("No Violation Records Found.");
			System.out.println();
			rs.close();
			return;
		}
		System.out.printf("%-15s%-15s%-15s%-15s%-15s%-22s%-20s%-1024s\n","Ticket_No","Violator_No","Vehicle_ID","Office_No","vType","vDate","Place","Descriptions");
		while(rs.next()){
			System.out.printf("%-15s%-12s%-3s%-10s%-15s%-22s%-20s%-1024s\n", rs.getString("Ticket_No"),rs.getString("Violator_No"),rs.getString("Vehicle_ID"),rs.getString("Office_No"),rs.getString("vType"),rs.getString("vDate"),rs.getString("Place"),rs.getString("Descriptions"));
		}
		rs.close();
	}
	
	private void printVehicleHistory(ResultSet rs1, ResultSet rs2) throws SQLException {
		System.out.printf("%-21s%-17s%-10s\n", "# of Changed Hand", "Average Price", "# of Violations");
		while(rs1.next()&rs2.next()) {
			System.out.printf("%-21s%-17s%-10s\n", rs1.getInt("TotalChangedHand"), rs1.getFloat("AveragePrice"), rs2.getInt("TotalViolations"));
		}
		rs1.close();
		rs2.close();
	}

	public int getHeadersSize() {
		return headers.length;
	}
}
