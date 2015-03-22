package ui;

import java.sql.SQLException;

import jdbc.JDBC;

import p1.Search;

public class SearchGeneralInfoView implements View {

	private boolean exit;
	private Search search;
	private JDBC db;

	@Override
	public void run() {
		try {
			
			//search = new Search(db);
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
				//searchByLicenceNo();
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
		search.queryGeneralInfoByLicenceNo(keyword);
	}
	private void searchByName() throws SQLException {
		String keyword;
		keyword = System.console().readLine("Enter Name: ");
		search.queryGeneralInfoByName(keyword);
	}

}
