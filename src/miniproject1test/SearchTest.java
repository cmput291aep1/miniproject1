package miniproject1test;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
		VRecController vc = new VRecController(mgr);
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
		int ticket_num = 1113;
		String violator_num = "SIN2";
		String vehicle_num= "S02";
		String office_num= "SIN3";
		String vtype= "SPEEDING";
		Date date = null;
		String place = "12345";
		String desc= "12345";
		String update = "INSERT INTO ticket values ("+ticket_num+","+"'"+violator_num+"'"+ "," +"'"+ vehicle_num +"'"+ "," +"'"+ office_num+"'"+ "," +"'"+vtype+"'"+ "," +date + "," +"'"+place+"'"+ "," +"'"+desc+"'"+ ")";
		mgr.sendUpdate(update);
	}
	
	@Test
	public void testVRecSend() throws SQLException, ClassNotFoundException {
		String username = "edrick";
		String password = "cwielkisbl7";
		JDBC mgr = new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1525:CRS",username,password);
		Ticket t = new Ticket();
		String violator_num = "S01";
		String vehicle_num= "S02";
		String office_num= "S01";
		String vtype= "SPEEDING";
		String place = "12345";
		String desc= "12345";
		VRecController vc = new VRecController(mgr);
		vc.setViolatorNo(violator_num);
		vc.setVehicleID(vehicle_num);
		vc.setOfficeNo(office_num);
		vc.setVType(vtype);
		vc.setPlace(place);
		vc.setDesc(desc);
		vc.setTicketNo();
		vc.setDate();
		vc.test();
//		vc.sendViolationUpdate();
//		int currentNumofTickets = vc.getTicketCount()-1;
//		ResultSet rs = mgr.sendQuery("select * from ticket where ticket_no="+currentNumofTickets);
//		rs.next();
//		assertEquals(rs.getInt("ticket_no"),currentNumofTickets);
//		assertEquals(rs.getString("violator_no").trim(),"s01");
//		assertEquals(rs.getString("vehicle_id").trim(),"s02");
//		assertEquals(rs.getString("office_no").trim(),"s01");
//		//assertEquals(rs.getString("vdate"),vc.getDate());
//		assertEquals(rs.getString("vtype").trim(), "speeding");
//		assertEquals(rs.getString("place").trim(),"12345");
//		assertEquals(rs.getString("descriptions").trim(),"12345");
	}
	



}
