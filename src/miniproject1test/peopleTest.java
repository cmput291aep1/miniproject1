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

public class peopleTest {

	private People p1;
	private JDBC db;
	private Date date;
	public peopleTest() {
		super();
	}

	@Before
	public void setUp() throws Exception {
		date=Date.valueOf("1993-10-06");
		p1=new People("1234","Bob", 6.1, 13.0, "", "", "", "",date);
		db=new JDBC("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS", "bmaroney","mypasswordisc00l");
		setUpTable();
	}

	@After
	public void tearDown() throws SQLException{
		try {
			db.sendUpdate("DROP TABLE people");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testGenerateStatement() {
		String statement=p1.generateStatement();
		System.out.println(statement);
		assertTrue(statement.equals("INSERT INTO people (sin,name,height,weight,eyecolor,haircolor,addr,gender,birthday) VALUES ('1234','Bob',6.1,13.0,'','','','',TO_DATE('1993-10-06','YYYY-MM-DD'))"));
		
	}
	@Ignore @Test
	public void testSend() throws SQLException, FileNotFoundException {
		db.sendModelinfo(p1);
		ResultSet rs=db.sendQuery("SELECT * FROM vehicle");
		rs.next();
		
		assertEquals(rs.getString(rs.findColumn("sin")).trim(),"1234");
		assertEquals(rs.getDate("birthday"), date);
	}	
	
	private void setUpTable() throws SQLException, ClassNotFoundException{
		db.sendUpdate("CREATE TABLE  people (sin CHAR(15),name VARCHAR(40),height number(5,2),weight number(5,2),eyecolor VARCHAR (10),haircolor VARCHAR(10),addr VARCHAR2(50),gender CHAR,birthday DATE,PRIMARY KEY (sin),CHECK (gender IN ('m', 'f')))");
	}
}
