package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	private static JDBC mgr = null;

	// Singleton
	public static JDBC getInstance(String username, String pw) {

		if (mgr == null) {
			return new JDBC(username, pw);
		} else {
			return mgr;
		}
	}

	public JDBC(String m_driverName, String m_url, String username, String pw) throws SQLException, ClassNotFoundException {
		this(username, pw);
		this.m_driverName = m_driverName;
		this.m_url = m_url;
		this.connect();
	}

	public JDBC(String username, String pw) {
		this.m_driverName = "oracle.jdbc.driver.OracleDriver";
		this.m_url = "jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS";
		this.username = username;
		this.pw = pw;
	}

	public void connect() throws SQLException, ClassNotFoundException {

		Class drvClass = Class.forName(m_driverName);
		// Establish Connection
		Connection con = DriverManager.getConnection(m_url, username, pw);
		System.out.println("Connection Created");

	}

}
