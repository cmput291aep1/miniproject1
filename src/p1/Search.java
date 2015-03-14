package p1;

import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JDBC;


public class Search
{

	private JDBC mgr;
	private String query1;
	private String query2;
	private String query3;
	private String query4;
	private String query5;
	
	public Search(JDBC mgr) {
		
		this.mgr = mgr;
	}
	
	public void queryGeneralInfoByName(String query1) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = mgr.sendQuery(query1);
	}

	public void queryGeneralInfoByLicenceNo(String query2) {
		// TODO Auto-generated method stub
		
	}

	public void queryViolationBySIN(String query3) {
		// TODO Auto-generated method stub
		
	}

	public void queryViolationByLicenceNo(String query4) {
		// TODO Auto-generated method stub
		
	}

	public void queryVehicleHistBySerialNo(String query5) {
		// TODO Auto-generated method stub
		
	}

	public void printResult(ResultSet rs, String... columnNames) throws SQLException {
		// TODO Auto-generated method stub
		String[] headers = columnNames;		
		// Print headers first
		StringBuilder sbHeader = new StringBuilder();
		for (int i = 0; i < headers.length; i++) {
			sbHeader.append(headers[i] + "\t\t\t\t");
		}
		System.out.println(sbHeader.toString());
		// Print Results
		StringBuilder sbResult = new StringBuilder();
		int j = 0;
		while(rs.next()) {
			//sbResult.append(rs.getString("Name") + "\t\t\t\t" + rs.getString("SIN") + "\t\t\t\t" + rs.getString("Addr") + "\t\t\t\t");
			System.out.println(rs.getString("Name") + "\t\t\t\t" + rs.getString("SIN") + "\t\t\t\t" + rs.getString("Addr") + "\t\t\t\t");
			//j++;
		}
		
		
	}

}
