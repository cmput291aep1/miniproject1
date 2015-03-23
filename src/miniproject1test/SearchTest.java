package miniproject1test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import jdbc.JDBC;

import org.junit.Test;

import p1.Search;
import p1.Ticket;
import p1.VRecController;

public class SearchTest {
	
	//@Test
	// Use case query general information by name
	public void testSearchGeneralInfoByName() throws SQLException, ClassNotFoundException {
		String username = "edrick";
		String password = "cwielkisbl7";
		JDBC mgr = new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1525:CRS",username,password);
		Search search = new Search(mgr);
		search.queryGeneralInfoByName("Sophia");
		
	}
	
	//@Test
	// Use case query general information by licence number
	public void testSearchGeneralInfoByLicenceNo() throws SQLException, ClassNotFoundException {
		String username = "edrick";
		String password = "cwielkisbl7";
		JDBC mgr = new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1525:CRS",username,password);
		Search search = new Search(mgr);
		search.queryGeneralInfoByLicenceNo("LLL 111");
		
	}
	
	//@Test
	// Use case query violation by SIN
	public void testSearchViolationBySIN() throws SQLException, ClassNotFoundException {
		String username = "edrick";
		String password = "cwielkisbl7";
		JDBC mgr = new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1525:CRS",username,password);
		Search search = new Search(mgr);
		search.queryViolationBySIN("SIN1");
	}
	
	//@Test
	// Use case query violation by Licence number
	public void testSearchViolationByLicenceNo() throws SQLException, ClassNotFoundException {
		String username = "edrick";
		String password = "cwielkisbl7";
		JDBC mgr = new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1525:CRS",username,password);
		Search search = new Search(mgr);
		
		search.queryViolationByLicenceNo("LLL 113");
		
	}
	
	//@Test
	// Use case query vehicle history by vehicle serial number
	public void testSearchVehicleHistBySerialNo() throws SQLException, ClassNotFoundException {
		String username = "edrick";
		String password = "cwielkisbl7";
		JDBC mgr = new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1525:CRS",username,password);
		Search search = new Search(mgr);

		search.queryVehicleHistBySerialNo("S01");
		
	}
	
	//@Test
	public void testGetCount() throws SQLException, ClassNotFoundException {
		String username = "edrick";
		String password = "cwielkisbl7";
		Ticket ticket = new Ticket();
		JDBC mgr = new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1525:CRS",username,password);
		VRecController vc = new VRecController(mgr, ticket);
		System.out.println("Ticket Count: " + vc.getTicketCount());
		Date date = new Date(Calendar.getInstance().getTimeInMillis());
		System.out.println(date);
	}
	
	//@Test
	public void testSendUpdate() throws SQLException, ClassNotFoundException {
		String username = "edrick";
		String password = "cwielkisbl7";
		Ticket ticket = new Ticket();
		JDBC mgr = new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1525:CRS",username,password);
		int ticket_num = 123456;
		String violator_num = "SIN2";
		String vehicle_num= "S02";
		String office_num= "SIN3";
		String vtype= "SPEEDING";
		Date date = null;
		String place = "12345";
		String desc= "12345";
		String update = "INSERT INTO ticket values ("+ticket_num+","+"'"+violator_num+"'"+ "," +"'"+ vehicle_num +"'"+ "," +"'"+ office_num+"'"+ "," +"'"+vtype+"'"+ "," +null + "," +"'"+place+"'"+ "," +"'"+desc+"'"+ ")";
		mgr.sendUpdate(update);
	}
	
	@Test
	public void testVRecSend() throws SQLException, ClassNotFoundException {
		String username = "edrick";
		String password = "cwielkisbl7";
		Ticket ticket = new Ticket();
		JDBC mgr = new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1525:CRS",username,password);
		VRecController vc = new VRecController(mgr, ticket);
		Ticket t = new Ticket();
		int ticket_num = 1234567;
		String violator_num = "SIN1";
		String vehicle_num= "S01";
		String office_num= "SIN2";
		String vtype= "SPEEDING";
		Date date = null;
		String place = "12345";
		String desc= "12345";
		t.setTicket_no(ticket_num);
		t.setViolator_no(violator_num);
		t.setVehicle_id(vehicle_num);
		t.setOffice_no(office_num);
		t.setVType(vtype);
		t.setVDate(date);
		t.setPlace(place);
		t.setDescription(desc);
		vc.sendViolationUpdate();
	}


}
