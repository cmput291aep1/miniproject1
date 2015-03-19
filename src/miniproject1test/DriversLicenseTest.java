package miniproject1test;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JDBC;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import p1.AutoTransaction;
import p1.DriversLicense;

public class DriversLicenseTest {
	private DriversLicense dl;
	private JDBC db;
	private Date date;
	public DriversLicenseTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		date=Date.valueOf("2015-10-06");
		dl=new DriversLicense("1", "534 411 780", "driving",null, Date.valueOf("2012-05-06"), Date.valueOf("2015-05-06"));
		db=new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1525:CRS", "bmaroney","mypasswordisc00l");
		setUpTable();
	}

	@After
	public void tearDown() throws SQLException{
		try {
			db.sendUpdate("DROP TABLE people");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.close();
	}

	@Test
	public void testGenerateStatement() {
		String statement=dl.generateStatement();
		System.out.println(statement);
		assertTrue(statement.equals("INSERT INTO auto_sale ("+"TRANSACTION_ID,".toLowerCase()+"SELLER_ID,".toLowerCase()+"BUYER_ID,".toLowerCase()+"VEHICLE_ID,".toLowerCase()+"S_DATE,".toLowerCase()+"PRICE".toLowerCase()+") VALUES (5678,'534 411 780','630 708 949','12345',TO_DATE('"+date.toString()+"','YYYY-MM-DD'),2000)"));
		
	}
	@Test
	public void testSend() throws SQLException {
		db.sendModelinfo(dl);
		ResultSet rs=db.sendQuery("SELECT * FROM auto_sale");
		rs.next();
		
		assertEquals(rs.getInt(rs.findColumn("transaction_id")),5678);
		assertEquals(rs.getString("SELLER_ID".toLowerCase()).trim(),"534 411 780");
		assertEquals(rs.getString("BUYER_ID".toLowerCase()).trim(),"630 708 949");
		assertEquals(rs.getDate("S_DATE".toLowerCase()),date);
		assertEquals(rs.getInt("PRICE".toLowerCase()),2000);
		assertEquals(rs.getString("VEHICLE_ID".toLowerCase()).trim(), "12345");
	}	
	
	private void setUpTable() throws SQLException, ClassNotFoundException{
		db.sendUpdate("CREATE TABLE  people (sin CHAR(15),name VARCHAR(40),height number(5,2),weight number(5,2),eyecolor VARCHAR (10),haircolor VARCHAR(10),addr VARCHAR2(50),gender CHAR,birthday DATE,PRIMARY KEY (sin),CHECK (gender IN ('m', 'f')))");
		populatePeople();
	}
	private void populatePeople() throws SQLException {
		db.sendUpdate("INSERT INTO people(SIN,NAME,HEIGHT,WEIGHT,EYECOLOR,HAIRCOLOR,ADDR,GENDER,BIRTHDAY) VALUES ('534 411 780','James',1.84,94.6,'blue','black','3220 Victoria Park Ave, Toronto, ON M2J 3T7','m','25-AUG-85')");
	}
}
