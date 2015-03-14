package p1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.JDBC;


public class Search
{

	private JDBC mgr;
	private String query1;
	private String query2;
	private String query3;
	private String query4;
	private String query5;
	private String[] headers;

	public Search(JDBC mgr) {
		this.mgr = mgr;
	}

	public void queryGeneralInfoByName(String searchName) throws SQLException {
		query1 = "select p.name, d.licence_no, p.addr, p.birthday, d.class, dc.description, d.expiring_date " +
				"from people p, drive_licence d, driving_condition dc, restriction r " +
				"where p.sin=d.sin and d.licence_no=r.licence_no and dc.c_id=r.r_id and p.name="+ "'" + searchName + "'";
		ResultSet rs = mgr.sendQuery(query1);
		ArrayList<String> collectedData = collectData(rs, "Name", "Licence_No", "Addr", "Birthday", "Class", "Description", "Expiring_Date");
		printResult(collectedData, 7);
		rs.close();
	}

	public void queryGeneralInfoByLicenceNo(String searchLicenceNo) throws SQLException {
		query2 = "select p.name, d.licence_no, p.addr, p.birthday, d.class, dc.description, d.expiring_date " +
				"from people p, drive_licence d, driving_condition dc, restriction r " +
				"where p.sin=d.sin and d.licence_no=r.licence_no and dc.c_id=r.r_id and d.licence_no=" + "'" + searchLicenceNo + "'";
		ResultSet rs = mgr.sendQuery(query2);
		ArrayList<String> collectedData = collectData(rs, "Name", "Licence_No", "Addr", "Birthday", "Class", "Description", "Expiring_Date");
		printResult(collectedData, 7);
		rs.close();

	}

	public void queryViolationBySIN(String searchSIN) throws SQLException {
		query3 = "select distinct ticket_no, violator_no, vehicle_id, office_no, vtype, vdate, place, descriptions " +
				"from ticket, people p " +
				"where violator_no=p.sin AND p.sin=" + "'" + searchSIN + "'";
		ResultSet rs = mgr.sendQuery(query3);
		ArrayList<String> collectedData = collectData(rs, "Ticket_No", "Violator_No", "Vehicle_ID", "Office_No", "vType", "vDate", "Place", "Descriptions");
		printResult(collectedData, 8);
		rs.close();
	}

	public void queryViolationByLicenceNo(String searchLicenceNo) throws SQLException {
		query4 = "select distinct ticket_no, violator_no, vehicle_id, office_no, vtype, vdate, place, descriptions " +
				"from ticket, people p, drive_licence d " +
				"where violator_no=p.sin AND p.sin=d.sin AND d.licence_no=" + "'" + searchLicenceNo + "'";
		ResultSet rs = mgr.sendQuery(query4);
		ArrayList<String> collectedData = collectData(rs, "Ticket_No", "Violator_No", "Vehicle_ID", "Office_No", "vType", "vDate", "Place", "Descriptions");
		printResult(collectedData, 8);
		rs.close();
	}

	public void queryVehicleHistBySerialNo(String query5) {
		// TODO Auto-generated method stub

	}

	public ArrayList<String> collectData(ResultSet rs, String... columnNames) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<String> dataArray = new ArrayList<String>();
		// Retrieve Column names
		headers = columnNames;
		// Insert header into dataArray
		for (int i = 0; i < headers.length; i++) {
			dataArray.add(headers[i]);
		}

		while(rs.next()) {
			for (int i = 0; i < headers.length; i++) {
				dataArray.add(rs.getString(headers[i]).trim());
			}
		}
//		System.out.println("DATA ARRAY SIZE = " + dataArray.size());
//		for (int i = 0; i < dataArray.size(); i++) {
//			System.out.println(dataArray.get(i));
//		}
		return dataArray;
	}	
	
	//	public void printResult(ResultSet rs, String... columnNames) throws SQLException {
	//		// TODO Auto-generated method stub
	//		String[] headers = columnNames;		
	//		// Print headers first
	//		StringBuilder sbHeader = new StringBuilder();
	//		StringBuilder sbResult = new StringBuilder();
	//		
	//		ArrayList<String> retrievedData = new ArrayList<String>();
	//		
	//		for (int i = 0; i < headers.length; i++) {
	//			sbHeader.append(headers[i] + "\t");
	//		}
	//		System.out.println(sbHeader.toString());
	//		// Print Results
	//		while(rs.next()) {
	//			for (int i = 0; i < headers.length; i++) {
	//				sbResult.append(rs.getString(headers[i]).trim() + "\t");
	//			}
	//			System.out.println(sbResult.toString());
	//			sbResult.setLength(0);
	//		}
	//		
	//		// Close ResultStatement
	//		rs.close();
	//		
	//		
	//	}

	public void printResult(ArrayList<String> dataCollected, int headerLength) throws SQLException {
		// TODO Auto-generated method stub

		// Print headers first
		StringBuilder sbData = new StringBuilder();
		// Print Results

		int columnCounter = 0;
		for (int i = 0; i < dataCollected.size(); i++) {
			sbData.append(dataCollected.get(i) + "\t");
			columnCounter++;
			if (columnCounter%headerLength == 0) {
				columnCounter = 0;
				System.out.println(sbData.toString());
				sbData.setLength(0);
			}
		}
	}

	public int getHeadersSize() {
		return headers.length;
	}

}
