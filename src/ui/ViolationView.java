package ui;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

import jdbc.JDBC;
import p1.Search;

public class ViolationView implements View {

	private boolean exit;
	private Search search;
	private JDBC db;
	
	@Override
	public void run() {
		System.out.println(this.getClass().getName());
		startUI();
	}
	
	private void startUI() {
		while(shouldNotExit()){
			int ticket_num;
			String violator_num;
			String vehicle_num;
			String office_num;
			String vtype;
			Date date = new Date(Calendar.getInstance().getTimeInMillis());
			String place;
			String desc;
			try {
				ticket_num = Integer.parseInt(System.console().readLine("Enter Ticket Number: "));
				System.out.println();
				violator_num = System.console().readLine("Enter Violator SIN: ");
				System.out.println();
				vehicle_num = System.console().readLine("Enter Vehicle Serial Number: ");
				System.out.println();
				office_num = System.console().readLine("Enter Officer SIN: ");
				System.out.println();
				vtype = System.console().readLine("Enter Ticket Type: ");
				System.out.println();
				place = System.console().readLine("Enter Place of Violation: ");
				System.out.println();
				desc = System.console().readLine("Enter description: ");
				System.out.println();
				
			} catch (NumberFormatException e) {
				System.out.println("Invalid Input!\n");
				continue;
			}
			System.out.println();
		}
	}
	
	private boolean shouldNotExit() {
		return !exit;
	}

}
