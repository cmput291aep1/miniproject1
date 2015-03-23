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
			int ticket_num;
			String violator_num;
			String vehicle_num;
			String office_num;
			String vtype;
			//Date date = new Date(Calendar.getInstance().getTimeInMillis());
			//Date date = null;
			String place;
			String desc;
			try {
				// Ticket number must be automatically generated, find number total number of tickets then add 1 to count
				ticket_num = Integer.parseInt(System.console().readLine("Enter Ticket Number: "));
				ticket.setTicket_no(ticket_num);
				System.out.println();
				violator_num = System.console().readLine("Enter Violator SIN: ");
				ticket.setViolator_no(violator_num);
				System.out.println();
				vehicle_num = System.console().readLine("Enter Vehicle Serial Number: ");
				ticket.setVehicle_id(vehicle_num);
				System.out.println();
				office_num = System.console().readLine("Enter Officer SIN: ");
				ticket.setOffice_no(office_num);
				System.out.println();
				vtype = System.console().readLine("Enter Ticket Type: ");
				ticket.setVType(vtype);
				System.out.println();
				place = System.console().readLine("Enter Place of Violation: ");
				ticket.setPlace(place);
				System.out.println();
				desc = System.console().readLine("Enter description: ");
				ticket.setDescription(desc);
				System.out.println();
				//ticket.setVDate(getDate());
				// Set the controller to send the updates
				vc = new VRecController(db, ticket);
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
