package miniproject1test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JDBC;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import p1.People;
import p1.VRegController;

public class VRegTest {

	private People p1;
	private JDBC db;
	private Date date;
	private VRegController vr;
	public VRegTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		date=Date.valueOf("1993-10-06");
		p1=new People("1234","Bob", 6.1, 13.0, "", "", "", "",date);
		db=new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS", "bmaroney","mypasswordisc00l");
		vr=new VRegController(db);
		setUpTable();
	}

	@After
	public void tearDown() throws SQLException{
		db.sendUpdate("drop table owner");
		try {
			db.sendUpdate("DROP TABLE people");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			db.sendUpdate("drop table vehicle");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			db.sendUpdate("drop table vehicle_type");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testSubmit() throws SQLException, FileNotFoundException {
		vr.addSN("20189");
		vr.setVehicleType("2");
		vr.setVModel("toyota");
		vr.addPrimaryOwner("534 411 780");
		vr.setYear("1994");
		vr.submit();
		ResultSet rs=db.sendQuery("SELECT * FROM vehicle");
		rs.next();
		assertEquals(rs.getString(rs.findColumn("serial_no")).trim(),"20189");
		assertEquals(rs.getString("model").trim(), "Toyota".toLowerCase());
		assertEquals(rs.getInt("year"),1994);
		rs.close();
		
		//owner(owner_id, vehicle_id, is_primary_owner)
		ResultSet ts=db.sendQuery("SELECT * FROM owner");
		ts.next();
		assertEquals(ts.getString(ts.findColumn("vehicle_id")).trim(),"20189");
		assertEquals(ts.getString(ts.findColumn("owner_id")).trim(),"534 411 780");
	}	
	@Test
	public void testOwners(){
		vr.addSN("20189");
		vr.setVehicleType("2");
		vr.setVModel("toyota");
		vr.addPrimaryOwner("534");
		vr.setYear("1994");
		vr.submit();
	}
	private void setUpTable() throws SQLException, ClassNotFoundException{
		db.sendUpdate("CREATE TABLE  people (sin CHAR(15),name VARCHAR(40),height number(5,2),weight number(5,2),eyecolor VARCHAR (10),haircolor VARCHAR(10),addr VARCHAR2(50),gender CHAR,birthday DATE,PRIMARY KEY (sin),CHECK (gender IN ('m', 'f')))");
		populatePeople();
		db.sendUpdate("CREATE TABLE vehicle_type (type_id integer,type CHAR(10),PRIMARY KEY (type_id))");
		populateVehicleType();
		db.sendUpdate("CREATE TABLE vehicle (serial_no CHAR(15),maker VARCHAR(20),model VARCHAR(20),year number(4,0),color VARCHAR(10),type_id integer,PRIMARY KEY (serial_no),FOREIGN KEY (type_id) REFERENCES vehicle_type)");
		db.sendUpdate("CREATE TABLE owner (owner_id CHAR(15),vehicle_id CHAR(15),is_primary_owner  CHAR(1),PRIMARY KEY (owner_id, vehicle_id),FOREIGN KEY (owner_id) REFERENCES people,FOREIGN KEY (vehicle_id) REFERENCES vehicle,CHECK ( is_primary_owner IN ('y', 'n')))");
	}
	private void populateVehicleType() throws SQLException {
		ResultSet rs=db.sendQuery("SELECT type_id,type FROM vehicle_type");
		
		rs.moveToInsertRow();
		rs.updateInt("type_id",2);
		rs.updateString("type","Sedan");
		rs.insertRow();
		
	}
	private void populatePeople() throws SQLException {
		db.sendUpdate("INSERT INTO people(SIN,NAME,HEIGHT,WEIGHT,EYECOLOR,HAIRCOLOR,ADDR,GENDER,BIRTHDAY) VALUES ('534 411 780','James',1.84,94.6,'blue','black','3220 Victoria Park Ave, Toronto, ON M2J 3T7','m','25-AUG-85')");
		db.sendUpdate("INSERT INTO people(SIN,NAME,HEIGHT,WEIGHT,EYECOLOR,HAIRCOLOR,ADDR,GENDER,BIRTHDAY) VALUES ('630 708 949','Alex',1.75,105,'brown','auburn','4351 Merivale Road,Stittsville,ON K2S 1B9','f','08-MAR-85')");
	}
}
