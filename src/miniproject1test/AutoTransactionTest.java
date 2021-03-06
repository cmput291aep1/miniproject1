package miniproject1test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JDBC;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import p1.ATController;
import p1.AutoTransaction;
import p1.Vehicle;

public class AutoTransactionTest {
	private AutoTransaction at;
	private JDBC db;
	private Date date;
	private ATController atcon;
	public AutoTransactionTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		date=Date.valueOf("2015-10-06");
		at=new AutoTransaction(5678, "534 411 780","630 708 949","12345",date,2000);
		db=new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1525:CRS", "bmaroney","mypasswordisc00l");
		atcon=new ATController(db);
		setUpTable();
	}

	@After
	public void tearDown() throws SQLException{
		try {
			db.sendUpdate("DROP TABLE owner");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			db.sendUpdate("DROP TABLE auto_sale");
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
		String statement=at.generateStatement();
		System.out.println(statement);
		String test="INSERT INTO auto_sale ("+"TRANSACTION_ID,".toLowerCase()+"SELLER_ID,".toLowerCase()+"BUYER_ID,".toLowerCase()+"VEHICLE_ID,".toLowerCase()+"S_DATE,".toLowerCase()+"PRICE".toLowerCase()+") VALUES (5678,'534 411 780','630 708 949','12345',TO_DATE('"+date.toString()+"','YYYY-MM-DD'),2000.0)";
		System.out.println(test); 
		assertTrue(statement.equals(test));
		
	}
	@Test
	public void testSend() throws SQLException, FileNotFoundException {
		db.sendModelinfo(at);
		ResultSet rs=db.sendQuery("SELECT * FROM auto_sale");
		rs.next();
		
		assertEquals(rs.getInt(rs.findColumn("transaction_id")),5678);
		assertEquals(rs.getString("SELLER_ID".toLowerCase()).trim(),"534 411 780");
		assertEquals(rs.getString("BUYER_ID".toLowerCase()).trim(),"630 708 949");
		assertEquals(rs.getDate("S_DATE".toLowerCase()),date);
		assertEquals(rs.getInt("PRICE".toLowerCase()),2000);
		assertEquals(rs.getString("VEHICLE_ID".toLowerCase()).trim(), "12345");
	}	
	@Test
	public void testController() throws SQLException{
		atcon.setTransactionID(at.getTransaction_id().toString());
		atcon.setSellDate(at.getS_date().toString());
		atcon.setBuyerID(at.getBuyer_id());
		atcon.setSellerID(at.getSeller_id());
		atcon.setVehicleID(at.getVehicle_id());
		atcon.getPrice(new Double(at.getPrice()).toString());
		atcon.submit();
		
		ResultSet rs=db.sendQuery("SELECT * FROM auto_sale");
		if(rs.next()){
		
		assertEquals(rs.getInt(rs.findColumn("transaction_id")),5678);
		assertEquals(rs.getString("SELLER_ID".toLowerCase()).trim(),"534 411 780");
		assertEquals(rs.getString("BUYER_ID".toLowerCase()).trim(),"630 708 949");
		assertEquals(rs.getDate("S_DATE".toLowerCase()),date);
		assertEquals(rs.getInt("PRICE".toLowerCase()),2000);
		assertEquals(rs.getString("VEHICLE_ID".toLowerCase()).trim(), "12345");
		rs.close();
		
		}
		atcon.setSellerID("1234");
		
		atcon.submit();
		
		atcon.setSellerID(at.getSeller_id());
		atcon.setVehicleID("asdasdadsd");
		atcon.submit();
	}
	private void setUpTable() throws SQLException, ClassNotFoundException{
		db.sendUpdate("CREATE TABLE vehicle_type (type_id integer,type CHAR(10),PRIMARY KEY (type_id))");
		populateVehicleType();
		db.sendUpdate("CREATE TABLE vehicle (serial_no CHAR(15),maker VARCHAR(20),model VARCHAR(20),year number(4,0),color VARCHAR(10),type_id integer,PRIMARY KEY (serial_no),FOREIGN KEY (type_id) REFERENCES vehicle_type)");
		populateVehicle();
		db.sendUpdate("CREATE TABLE  people (sin CHAR(15),name VARCHAR(40),height number(5,2),weight number(5,2),eyecolor VARCHAR (10),haircolor VARCHAR(10),addr VARCHAR2(50),gender CHAR,birthday DATE,PRIMARY KEY (sin),CHECK (gender IN ('m', 'f')))");
		populatePeople();
		db.sendUpdate("CREATE TABLE auto_sale (transaction_id  int,seller_id CHAR(15),buyer_id CHAR(15),vehicle_id CHAR(15), s_date date,price numeric(9,2),PRIMARY KEY (transaction_id),FOREIGN KEY (seller_id) REFERENCES people,FOREIGN KEY (buyer_id) REFERENCES people,FOREIGN KEY (vehicle_id) REFERENCES vehicle)");
		db.sendUpdate("CREATE TABLE owner (owner_id CHAR(15),vehicle_id CHAR(15),is_primary_owner  CHAR(1),PRIMARY KEY (owner_id, vehicle_id),FOREIGN KEY (owner_id) REFERENCES people,FOREIGN KEY (vehicle_id) REFERENCES vehicle,CHECK ( is_primary_owner IN ('y', 'n')))");
		populateOwnerTable();
	}
	
	private void populateOwnerTable() throws SQLException {
		ResultSet rs = db.sendQuery("select owner_id,vehicle_id,is_primary_owner from owner");
		rs.moveToInsertRow();
		rs.updateString("owner_id","534 411 780");
		rs.updateString("vehicle_id","12345");
		rs.updateString("is_primary_owner","y");
		rs.insertRow();

	}

	private void populatePeople() throws SQLException {
		db.sendUpdate("INSERT INTO people(SIN,NAME,HEIGHT,WEIGHT,EYECOLOR,HAIRCOLOR,ADDR,GENDER,BIRTHDAY) VALUES ('534 411 780','James',1.84,94.6,'blue','black','3220 Victoria Park Ave, Toronto, ON M2J 3T7','m',TO_DATE('25-08-85','DD-MM-YYYY'))");
		db.sendUpdate("INSERT INTO people(SIN,NAME,HEIGHT,WEIGHT,EYECOLOR,HAIRCOLOR,ADDR,GENDER,BIRTHDAY) VALUES ('630 708 949','Alex',1.75,105,'brown','auburn','4351 Merivale Road,Stittsville,ON K2S 1B9','f',TO_DATE('08-03-85','DD-MM-YYYY'))");
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
