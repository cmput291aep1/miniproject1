package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC
{

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException
	{
		String m_driverName = "oracle.jdbc.driver.OracleDriver";

		Class drvClass = Class.forName(m_driverName);
		//DriverManager.registerDriver((Driver)drvClass.newInstance());

		// Establish Connection
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@gwynne.cs.ualberta.ca:1521:CRS", "edrick", "cwielkisbl7");
		System.out.println("Connection Created");

		// Creating a statement
		Statement stmt = con.createStatement();
		String query = "select name from people";
		// Result
		ResultSet rs = stmt.executeQuery(query);

		// Retrieve Data
		while(rs.next()) {
			System.out.println(rs.getString("Name"));
		}

	}

	public void connect(String m_driverName, String m_url, String username,
			String password)
	{
		try
		{
			Class drvClass = Class.forName(m_driverName);
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Establish Connection
		try
		{
			Connection con = DriverManager.getConnection(m_url, username, password);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connection Created");

	}

}
