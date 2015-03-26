package ui;

import java.sql.SQLException;

import jdbc.JDBC;
import p1.Search;

public class SearchVehicleHistoryView implements View {

	private boolean exit;
	private Search search;
	private JDBC db;

	public SearchVehicleHistoryView(JDBC db) {
		this.db = db;
	}

	@Override
	public void run() {
		try {
			search = new Search(db);
			startUI();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void startUI() throws SQLException {
		while(shouldNotExit()){
			String vSerialNum;
			try {
				searchVehicleHistory();
				System.out.println();
				return;
			} catch (NumberFormatException e) {
				System.out.println("Invalid Input!\n");
				continue;
			}
		}
	}
	
	private boolean shouldNotExit() {
		return !exit;
	}
	
	private void searchVehicleHistory() throws SQLException {
		String keyword;
		keyword = System.console().readLine("Enter Serial Number: ");
		// If return code is true, Person does not exist
		if(search.queryVehicleHistBySerialNo(keyword) == true) {
			System.console().printf("Sorry, the serial number does not exist in the database. Returning...\n");
			return;
		}
	}

}
