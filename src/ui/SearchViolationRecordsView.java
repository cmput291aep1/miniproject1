package ui;

import java.sql.SQLException;

import jdbc.JDBC;
import p1.Search;

public class SearchViolationRecordsView implements View {

	
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
	private void PerformSelection(int selection) throws SQLException {
		View v = null;
		switch(selection){
			case 1:
				// TODO
				break;
			case 2:
				// TODO
				break;
			case 3:
				exit=true;
				return;
			default:
				System.out.println("Invalid Selection\n");
				return;
		}		
	}
	private void startUI() throws SQLException {
		while(shouldNotExit()){
			int selection;
			try {
				selection = Integer.parseInt(System.console().readLine("By Lincence Number(1)\n"
						+ "By SIN(2)\nExit(3)\nPlease make a selection: "));
			} catch (NumberFormatException e) {
				System.out.println("Invalid Input!\n");
				continue;
			}
			System.out.println();
			PerformSelection(selection);
		}
	}
	
	private boolean shouldNotExit() {
		return !exit;
	}

}
