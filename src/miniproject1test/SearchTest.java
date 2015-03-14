package miniproject1test;

import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JDBC;

import org.junit.Test;

import p1.Search;

public class SearchTest {
	
	@Test
	// Use case query general information by name
	public void testSearchGeneralInfoByName() throws SQLException, ClassNotFoundException {
		String username = "edrick";
		String password = "cwielkisbl7";
		JDBC mgr = new JDBC(username, password);
		Search search = new Search(mgr);
		
		search.queryGeneralInfoByName("George");
		
	}
	
	@Test
	// Use case query general information by licence number
	public void testSearchGeneralInfoByLicenceNo() {
		//Search search = new Search();
		
		//search.queryGeneralInfoByLicenceNo("LLL 111");
		
	}
	
	@Test
	// Use case query violation by SIN
	public void testSearchViolationBySIN() {
		//Search search = new Search();
		
		//search.queryViolationBySIN("AB12CD");

		
	}
	
	@Test
	// Use case query violation by Licence number
	public void testSearchViolationByLicenceNo() {
		//Search search = new Search();
		
		//search.queryViolationByLicenceNo("LLL 111");
		
	}
	
	@Test
	// Use case query vehicle history by vehicle serial number
	public void testSearchVehicleHistBySerialNo() {
		//Search search = new Search();

		//search.queryVehicleHistBySerialNo("123ASD456");
		
	}
	
	@Test
	public void testSearchPrintResult() throws SQLException, ClassNotFoundException {
		String username = "edrick";
		String password = "cwielkisbl7";
		JDBC mgr = new JDBC(username,password);
		Search search = new Search(mgr);
		ResultSet rs = mgr.sendQuery("select * from people");
		search.printResult(rs, "Name", "SIN", "Addr");
	}
}
