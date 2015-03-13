package miniproject1test;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JDBC;
import junit.framework.Assert;

import org.junit.Test;

import p1.Search;

public class JDBCTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
//	Search Engine
//
//	This component is used to perform the following searches.
//
	
/*	List the name, licence_no, addr, birthday, driving class, driving_condition, and the expiring_data of a driver 
 *	by entering either a licence_no or a given name. It shall display all the entries if a duplicate name is given.
 *	"select p.name, d.licence_no, p.addr, p.birthday, d.class, dc.description, d.expiring_date
 *   from people p, drive_licence d, driving_condition dc
 *   where p.sin=d.sin and  
 */
//	List all violation records received by a person if  the drive licence_no or sin of a person  is entered.
	
/*	Print out the vehicle_history, including the number of times that a vehicle has been changed hand, the average price, 
 * and the number of violations it has been involved by entering the vehicle's serial number.
 */

	@Test(expected = Exception.class)
	// Use Case: Database User Connects to the Database
	public void testConnection() throws SQLException, ClassNotFoundException {
		String username = "edrick";
		String password = "cwielkisbl7";
		String driver = "123123";
		String url = "123123";
		JDBC jdbcManager = new JDBC(driver, url, username, password);
		jdbcManager.connect();
	}
	
	@Test
	// Use Case: Database User queries the database
	public void testSendQuery() throws SQLException, ClassNotFoundException {
		String m_driverName = "oracle.jdbc.driver.OracleDriver";
		String m_url = "jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";
		String username = "edrick";
		String password = "cwielkisbl7";
		JDBC jdbcMgr = new JDBC(username, password);
		
		// ResultSet
		ResultSet rs = 	jdbcMgr.sendQuery("select name from people");
		// Expected Result
		ArrayList<String> re = new ArrayList<String>();
		re.add(0, "Nathaniel");
		re.add(1, "Sophia");
		re.add(2, "Lucas");
		re.add(3, "George");
		re.add(4, "Rose");
		re.add(5, "Dante");
		re.add(6, "Hieu");
		re.add(7, "Monica");
		
		int flag = 0;
		for (int i = 0; i < 8; i++) {
			
			if (rs.next()==true) {
				if (!rs.getString("Name").equals(re.get(i))) {
					flag = 1;
					System.out.println("RS: " + rs.getString("Name") + " RE: " + re.get(i));
				} else {
					flag = 0;
				}
				
			}
		}
		rs.close();
		assertEquals(flag, 0);	
	}
	
	@Test
	public void testSendUpdate() throws SQLException, ClassNotFoundException {
		String m_driverName = "oracle.jdbc.driver.OracleDriver";
		String m_url = "jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";
		String username = "edrick";
		String password = "cwielkisbl7";
		JDBC jdbcMgr = new JDBC(username, password);
		
		// ResultSet
		jdbcMgr.sendUpdate("insert into vehicle_type values(5, 'Motocycle')");
	}
	
	

}
