package ui;

import java.sql.SQLException;

import jdbc.JDBC;

public class SearchView implements View {

	private boolean exit=false;
	private JDBC db;

	public SearchView(JDBC db) {
		this.db = db;
	}

	@Override
	public void run() {
		System.out.println(this.getClass().getName());
		startUI();
	}
	
	private void PerformSelection(int selection) {
		View v = null;
		switch(selection){
			case 1:
				v=new SearchGeneralInfoView(db);
				break;
			case 2:
				v=new SearchViolationRecordsView(db);
				break;
			case 3:
				v=new SearchVehicleHistoryView(db);
				break;
			case 4:
				exit=true;
				return;
			default:
				System.out.println("Invalid Selection\n");
				return;
		}
		v.run();
		
	}
	private void startUI() {
		while(shouldNotExit()){
			int selection;
			try {
				selection = Integer.parseInt(System.console().readLine("Search General Information(1)\n"
						+ "Search Violation Records(2)\nSearch Vehicle History(3)\nExit(4)\nPlease make a selection: "));
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
