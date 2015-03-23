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

import p1.Ticket;
import p1.Vehicle;

public class ViolationRecordTest {

	private Ticket vr;
	private JDBC db;
	private Date date;
	public ViolationRecordTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		date=Date.valueOf("2015-10-06");
		vr=new Ticket(6, "534 411 780", "12345", "630 708 949", "moving", null,"", "");
		db=new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1525:CRS", "bmaroney","mypasswordisc00l");
		setUpTable();
	}

	@After
	public void tearDown() throws SQLException{
		try {
			db.sendUpdate("DROP TABLE ticket");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			db.sendUpdate("DROP TABLE ticket_type");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		String statement=vr.generateStatement();
		System.out.println(statement);
		assertTrue(statement.equals("INSERT INTO ticket (ticket_no,violator_no,vehicle_id,office_no,vtype,vdate,place,descriptions) VALUES (6,'534 411 780','12345','630 708 949','moving',TO_DATE('2015-10-06','YYYY-MM-DD'),'','')"));
		
	}
	@Test
	public void testSend() throws SQLException, FileNotFoundException {
		db.sendModelinfo(vr);
		ResultSet rs=db.sendQuery("SELECT * FROM ticket");
		rs.next();
		assertEquals(rs.getString("violator_no").trim(),"534 411 780");
		assertEquals(rs.getInt("ticket_no"),6);
		assertEquals(rs.getString("vehicle_id").trim(),"12345");
		assertEquals(rs.getString("office_no").trim(),"630 708 949");
		assertEquals(rs.getString("vtype").trim(),"moving");
		assertEquals(rs.getDate("vdate"),date);
		
	}	
	
	private void setUpTable() throws SQLException, ClassNotFoundException{
		db.sendUpdate("CREATE TABLE vehicle_type (type_id integer,type CHAR(10),PRIMARY KEY (type_id))");
		populateVehicleType();
		db.sendUpdate("CREATE TABLE vehicle (serial_no CHAR(15),maker VARCHAR(20),model VARCHAR(20),year number(4,0),color VARCHAR(10),type_id integer,PRIMARY KEY (serial_no),FOREIGN KEY (type_id) REFERENCES vehicle_type)");
		populateVehicle();
		db.sendUpdate("CREATE TABLE  people (sin CHAR(15),name VARCHAR(40),height number(5,2),weight number(5,2),eyecolor VARCHAR (10),haircolor VARCHAR(10),addr VARCHAR2(50),gender CHAR,birthday DATE,PRIMARY KEY (sin),CHECK (gender IN ('m', 'f')))");
		populatePeople();
		db.sendUpdate("CREATE TABLE ticket_type (vtype CHAR(10),fine number(5,2),PRIMARY KEY (vtype))");
		populateTicketType();
		db.sendUpdate("CREATE TABLE ticket (ticket_no int, violator_no CHAR(15),vehicle_id CHAR(15), office_no CHAR(15),vtype char(10),vdate date,place varchar(20), descriptions varchar(1024),PRIMARY KEY (ticket_no),FOREIGN KEY (vtype) REFERENCES ticket_type,FOREIGN KEY (violator_no) REFERENCES people ON DELETE CASCADE,FOREIGN KEY (vehicle_id)  REFERENCES vehicle,FOREIGN KEY (office_no) REFERENCES people ON DELETE CASCADE)");
	}

	private void populateTicketType() throws SQLException {
		ResultSet rs=db.sendQuery("SELECT vtype,fine FROM ticket_type");
		rs.moveToInsertRow();
		rs.updateInt("fine",350);
		rs.updateString("vtype","moving");
		rs.insertRow();
	}

	private void populatePeople() throws SQLException {
		db.sendUpdate("INSERT INTO people(SIN,NAME,HEIGHT,WEIGHT,EYECOLOR,HAIRCOLOR,ADDR,GENDER,BIRTHDAY) VALUES ('534 411 780','James',1.84,94.6,'blue','black','3220 Victoria Park Ave, Toronto, ON M2J 3T7','m','25-AUG-85')");
		db.sendUpdate("INSERT INTO people(SIN,NAME,HEIGHT,WEIGHT,EYECOLOR,HAIRCOLOR,ADDR,GENDER,BIRTHDAY) VALUES ('630 708 949','Alex',1.75,105,'brown','auburn','4351 Merivale Road,Stittsville,ON K2S 1B9','f','08-MAR-85')");
	}

	private void populateVehicle() throws SQLException {
		db.sendUpdate("INSERT INTO vehicle (serial_no,maker,model,year,color,type_id) VALUES ('12345','toyota','corolla',1994,'blue',2)");
		
	}

	private void populateVehicleType() throws SQLException {
		ResultSet rs=db.sendQuery("SELECT type_id,type FROM vehicle_type");
		
		rs.moveToInsertRow();
		rs.updateInt("type_id",2);
		rs.updateString("type","Sedan");
		rs.insertRow();
		
	}

}
