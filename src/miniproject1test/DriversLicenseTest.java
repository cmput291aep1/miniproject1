package miniproject1test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JDBC;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import p1.AutoTransaction;
import p1.DLController;
import p1.DriversLicense;

public class DriversLicenseTest {
	private DriversLicense dl;
	private JDBC db;
	public DriversLicenseTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		dl=new DriversLicense("1", "534 411 780", "driving","J6fXu.jpg", Date.valueOf("2012-05-06"), Date.valueOf("2015-05-06"));
		db=new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1525:CRS", "bmaroney","mypasswordisc00l");
		setUpTable();
	}

	@After
	public void tearDown() throws SQLException{
		try {
			db.sendUpdate("DROP TABLE drive_licence");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		assertTrue(statement.equals("INSERT INTO drive_licence (licence_no,sin,class,photo,issuing_date,expiring_date) VALUES ('1','534 411 780','driving',?,TO_DATE('2012-05-06','YYYY-MM-DD'),TO_DATE('2015-05-06','YYYY-MM-DD'))"));
		dl.invalidatePicture();
		statement=dl.generateStatement();
		assertTrue(statement.equals("INSERT INTO drive_licence (licence_no,sin,class,issuing_date,expiring_date) VALUES ('1','534 411 780','driving',TO_DATE('2012-05-06','YYYY-MM-DD'),TO_DATE('2015-05-06','YYYY-MM-DD'))"));
	}
	@Test
	public void testSend() throws SQLException, FileNotFoundException {
		db.sendModelinfo(dl);
		ResultSet rs=db.sendQuery("SELECT * FROM drive_licence");
		rs.next();
		assertEquals(rs.getString("sin".toLowerCase()).trim(),"534 411 780");
		assertEquals(rs.getString("licence_no".toLowerCase()).trim(), "1");
		assertEquals(rs.getString("class".toLowerCase()).trim(),"driving");
		assertEquals(rs.getDate("issuing_date".toLowerCase()),Date.valueOf("2012-05-06"));
		assertEquals(rs.getDate("expiring_date".toLowerCase()),Date.valueOf("2015-05-06"));
	}	
	
	private void setUpTable() throws SQLException, ClassNotFoundException{
		db.sendUpdate("CREATE TABLE  people (sin CHAR(15),name VARCHAR(40),height number(5,2),weight number(5,2),eyecolor VARCHAR (10),haircolor VARCHAR(10),addr VARCHAR2(50),gender CHAR,birthday DATE,PRIMARY KEY (sin),CHECK (gender IN ('m', 'f')))");
		populatePeople();
		db.sendUpdate("CREATE TABLE drive_licence (licence_no CHAR(15),sin char(15),class VARCHAR(10),photo BLOB,issuing_date DATE, expiring_date DATE,PRIMARY KEY (licence_no),UNIQUE (sin), FOREIGN KEY (sin) REFERENCES people ON DELETE CASCADE)");
		
	}
	@Test
	public void testController() throws SQLException{
		DLController dlcon=new DLController(db);
		dlcon.setExpiryDate(dl.getExpiring_date().toString());
		dlcon.setIssueDate(dl.getIssuing_date().toString());
		dlcon.setLicenceNum(dl.getLicence_no());
		dlcon.setSin(dl.getSin());
		dlcon.setClass((dl.getDclass()));
		dlcon.setPhoto(dl.getPhotoFilename());
		dlcon.submit();
		
		ResultSet rs=db.sendQuery("SELECT * FROM drive_licence");
		rs.next();
		assertEquals(rs.getString("sin".toLowerCase()).trim(),"534 411 780");
		assertEquals(rs.getString("licence_no".toLowerCase()).trim(), "1");
		assertEquals(rs.getString("class".toLowerCase()).trim(),"driving");
		assertEquals(rs.getDate("issuing_date".toLowerCase()),Date.valueOf("2012-05-06"));
		assertEquals(rs.getDate("expiring_date".toLowerCase()),Date.valueOf("2015-05-06"));
		
		dlcon.setSin("534 411 780");
		dlcon.submit();
		
		dlcon.setSin("asdasdasdada");
		dlcon.submit();
		
		
		
		
	}
	private void populatePeople() throws SQLException {
		db.sendUpdate("INSERT INTO people(SIN,NAME,HEIGHT,WEIGHT,EYECOLOR,HAIRCOLOR,ADDR,GENDER,BIRTHDAY) VALUES ('534 411 780','James',1.84,94.6,'blue','black','3220 Victoria Park Ave, Toronto, ON M2J 3T7','m','25-AUG-85')");
	}
}
