package miniproject1test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JDBC;

import org.junit.Test;

import p1.Search;

public class SearchTest {
	
	@Test
	// Use case query general information by name
	public void testSearchGeneralInfoByName() throws SQLException, ClassNotFoundException {
		String username = "edrick";
		String password = "cwielkisbl7";
		JDBC mgr = new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1525:CRS",username,password);
		Search search = new Search(mgr);
		search.queryGeneralInfoByName("Sophia");
		
	}
	
	@Test
	// Use case query general information by licence number
	public void testSearchGeneralInfoByLicenceNo() throws SQLException, ClassNotFoundException {
		String username = "edrick";
		String password = "cwielkisbl7";
		JDBC mgr = new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1525:CRS",username,password);
		Search search = new Search(mgr);
		search.queryGeneralInfoByLicenceNo("LLL 111");
		
	}
	
	@Test
	// Use case query violation by SIN
	public void testSearchViolationBySIN() throws SQLException, ClassNotFoundException {
		String username = "edrick";
		String password = "cwielkisbl7";
		JDBC mgr = new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1525:CRS",username,password);
		Search search = new Search(mgr);
		search.queryViolationBySIN("SIN1");
	}
	
	@Test
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

		search.queryVehicleHistBySerialNo("123ASD456");
		
	}
	
//	@Test
//	public void testSearchPrintResult() throws SQLException, ClassNotFoundException {
//		String username = "edrick";
//		String password = "cwielkisbl7";
//		JDBC mgr = new JDBC(username,password);
//		Search search = new Search(mgr);
//		ResultSet rs = mgr.sendQuery("select * from people");
//		search.printResult(rs, "Name", "SIN", "Addr");
//	}

}
