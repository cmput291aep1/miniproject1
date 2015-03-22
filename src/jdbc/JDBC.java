package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import p1.Model;

public class JDBC
{

	//	/**
	//	 * @param args
	//	 * @throws ClassNotFoundException 
	//	 * @throws IllegalAccessException 
	//	 * @throws InstantiationException 
	//	 * @throws SQLException 
	//	 */
	//	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
	//	{
	//		String m_driverName = "oracle.jdbc.driver.OracleDriver";
	//
	//		Class drvClass = Class.forName(m_driverName);
	//		//DriverManager.registerDriver((Driver)drvClass.newInstance());
	//
	//		// Establish Connection
	//		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS", "edrick", "cwielkisbl7");
	//		System.out.println("Connection Created");
	//
	//		// Creating a statement
	//		Statement stmt = con.createStatement();
	//		String query = "select name from people";
	//		// Result
	//		ResultSet rs = stmt.executeQuery(query);
	//
	//		// Retrieve Data
	//		while(rs.next()) {
	//			System.out.println(rs.getString("Name"));
	//		}
	//
	//	}

	private String username;
	private String pw;
	private String m_driverName;
	private String m_url;
	private Connection con;
	private static JDBC mgr = null;

	// Singleton
	public static JDBC getInstance(String username, String pw) throws SQLException, ClassNotFoundException {

		if (mgr == null) {
			return new JDBC(username, pw);
		} else {
			return mgr;
		}
	}

	public JDBC(String m_driverName, String m_url, String username, String pw) throws SQLException, ClassNotFoundException {
		this.m_driverName = m_driverName;
		this.m_url = m_url;
		this.username=username;
		this.pw=pw;
		this.connect();
	}

	public JDBC(String username, String pw) throws SQLException, ClassNotFoundException {
		//this("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS",username,pw);
		this("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@localhost:1525:CRS",username,pw);
		this.connect();
	}

	public void connect() throws SQLException, ClassNotFoundException {

		Class drvClass = Class.forName(m_driverName);
		// Establish Connection
		con = DriverManager.getConnection(m_url, username, pw);
		System.out.println("Connection Created");

	}

	public ResultSet sendQuery(String query) throws SQLException {
		// Creating a statement
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		// Result
		ResultSet rs = stmt.executeQuery(query);

		// Retrieve Data
		return rs;	
	}

	public void sendUpdate(String update) throws SQLException {
		// Creating a statement
		Statement stmt = con.createStatement();
		
		// Execute update
		stmt.executeUpdate(update);
	}
	public void sendModelinfo(Model m) throws SQLException, FileNotFoundException{
		if(m.hasBlob()){
			sendBlob(m);
		}
		else{
			sendUpdate(m.generateStatement());
		}
	}
	public void sendBlob(Model m) throws FileNotFoundException, SQLException{
		 PreparedStatement stmt = con.prepareStatement(m.generateStatement());
		 stmt.clearParameters();
		 File photo = new File(m.getBlobFileName());
	     stmt.setBinaryStream(1,new FileInputStream(photo),(int)photo.length());
	     stmt.executeUpdate();
	}
	public void close() throws SQLException{
		if(con!=null){
			con.commit();
			con.close();
		}
	}

}
