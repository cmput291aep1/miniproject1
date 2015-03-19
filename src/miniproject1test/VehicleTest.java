package miniproject1test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JDBC;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import p1.Model;
import p1.Vehicle;

public class VehicleTest {
	private Vehicle v1;
	private JDBC db;
	public VehicleTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		v1=new Vehicle("12345","Toyota","Corolla",1994,"blue",2);
		db=new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1525:CRS", "bmaroney","mypasswordisc00l");
		setUpTable();
	}

	@After
	public void tearDown() throws SQLException{
		try {
			db.sendUpdate("DROP TABLE vehicle");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			db.sendUpdate("DROP TABLE vehicle_type");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.close();
	}

	@Test
	public void testGenerateStatement() {
		String statement=v1.generateStatement();
		assertTrue(statement.equals("INSERT INTO vehicle (serial_no,maker,model,year,color,type_id) VALUES ('12345','toyota','corolla',1994,'blue',2)"));
		
	}
	@Test
	public void testSend() throws SQLException {
		db.sendModelinfo(v1);
		ResultSet rs=db.sendQuery("SELECT * FROM vehicle");
		rs.next();
		
		assertEquals(rs.getString(rs.findColumn("serial_no")).trim(),"12345");
		assertEquals(rs.getString("maker").trim(), "Toyota".toLowerCase());
		assertEquals(rs.getString("model").trim(), "Corolla".toLowerCase());
		assertEquals(rs.getInt("year"),1994);
		assertEquals(rs.getInt("type_id"),2);
		assertEquals(rs.getString("color").trim(), "blue".toLowerCase());
	}	
	
	private void setUpTable() throws SQLException, ClassNotFoundException{
		db.sendUpdate("CREATE TABLE vehicle_type (type_id integer,type CHAR(10),PRIMARY KEY (type_id))");
		populateVehicleType();
		db.sendUpdate("CREATE TABLE vehicle (serial_no CHAR(15),maker VARCHAR(20),model VARCHAR(20),year number(4,0),color VARCHAR(10),type_id integer,PRIMARY KEY (serial_no),FOREIGN KEY (type_id) REFERENCES vehicle_type)");
	}

	private void populateVehicleType() throws SQLException {
		ResultSet rs=db.sendQuery("SELECT type_id,type FROM vehicle_type");
		
		rs.moveToInsertRow();
		rs.updateInt("type_id",2);
		rs.updateString("type","Sedan");
		rs.insertRow();
		
	}

}
