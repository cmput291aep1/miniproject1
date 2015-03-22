package ui;

import java.sql.SQLException;

import jdbc.JDBC;
import p1.Search;

public class SearchVehicleHistoryView implements View {

	private boolean exit;
	private Search search;
	private JDBC db;

	@Override
	public void run() {
		try {
			startUI();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	private void startUI() throws SQLException {
		while(shouldNotExit()){
			String vSerialNum;
			try {
				vSerialNum = System.console().readLine("Enter Vehicle Serial Number: ");
			} catch (NumberFormatException e) {
				System.out.println("Invalid Input!\n");
				continue;
			}
			System.out.println();
			// TODO Do something with vSerialNum
		}
	}
	
	private boolean shouldNotExit() {
		return !exit;
	}

}
