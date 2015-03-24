package ui;

import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import jdbc.JDBC;
import p1.Search;
import p1.Ticket;
import p1.VRecController;

public class ViolationView implements View {

	private boolean exit;
	private Search search;
	private JDBC db;
	private Ticket ticket;
	private VRecController vc;

	@Override
	public void run() {
		System.out.println(this.getClass().getName());
		startUI();
		ticket = new Ticket();
	}

	private void startUI() {
		while(shouldNotExit()){
			String violator_num;
			String vehicle_num;
			String office_num;
			String vtype;
			String place;
			String desc;
			try {
				// Set the controller to send the updates
				vc = new VRecController(db);
				// Date and TicketNumbers Generated Automatically
				// Include error checking, Violation Controller should return a boolean and the view processes the boolean and 
				// if error, inform user then go back to main menu
				vc.setTicketNo();
				vc.setDate();
				violator_num = System.console().readLine("Enter Violator SIN: ");
				
				if (vc.setViolatorNo(violator_num) == true) {
					System.console().printf("Violator SIN Invalid \n");
					violator_num = System.console().readLine("Please Enter a valid SIN: ");
				}
				
				System.out.println();
				vehicle_num = System.console().readLine("Enter Vehicle Serial Number: ");
				vc.setVehicleID(vehicle_num);
				System.out.println();
				office_num = System.console().readLine("Enter Officer SIN: ");
				vc.setOfficeNo(office_num);
				System.out.println();
				vtype = System.console().readLine("Enter Ticket Type: ");
				vc.setVType(vtype);
				System.out.println();
				place = System.console().readLine("Enter Place of Violation: ");
				vc.setPlace(place);
				System.out.println();
				desc = System.console().readLine("Enter description: ");
				vc.setDesc(desc);
				System.out.println();
				// Send the updates
				vc.sendViolationUpdate();

			} catch (NumberFormatException e) {
				System.out.println("Invalid Input!\n");
				continue;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println();
		}
	}

	private boolean shouldNotExit() {
		return !exit;
	}
	

}
