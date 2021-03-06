package ui;

import java.sql.SQLException;

import jdbc.JDBC;

import p1.Search;

public class SearchGeneralInfoView implements View {

	private boolean exit;
	private Search search;
	private JDBC db;

	public SearchGeneralInfoView(JDBC db) {
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
	private void PerformSelection(int selection) throws SQLException {
		View v = null;
		switch(selection){
		case 1:
			// Search for General Info Using Licence Number
			searchByLicenceNo();
			break;
		case 2:
			// Search for General Info using NAME
			searchByName();
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
						+ "By Name(2)\nExit(3)\nPlease make a selection: "));
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
	private void searchByLicenceNo() throws SQLException {
		String keyword;
		keyword = System.console().readLine("Enter Licence Number: ");
		// If return code is true, Person does not exist
		if(search.queryGeneralInfoByLicenceNo(keyword) == true) {
			System.console().printf("Sorry, the licence number does not exist in the database. Returning...\n");
			System.out.println();
			return;
		}
		
	}
	private void searchByName() throws SQLException {
		String keyword;
		keyword = System.console().readLine("Enter Name: ");
		// If return code is true, Person does not exist
		if(search.queryGeneralInfoByName(keyword) == true) {
			System.console().printf("Sorry, the person does not exist in the database. Returning...\n");
			System.out.println();
			return;
		}
	}

}
