package miniproject1test;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import jdbc.JDBC;

import org.junit.Test;

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
	public void testSendQuery() {
		String m_driverName = "oracle.jdbc.driver.OracleDriver";
		String m_url = "jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";
		String username = "edrick";
		String password = "cwielkisbl7";
		JDBC jdbcMgr = new JDBC(username, password);
		
		Search search = new Search();
		
		jdbcMgr.sendQuery("select * from people");
		
	}

}
