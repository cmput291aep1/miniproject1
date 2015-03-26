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

	public ViolationView(JDBC db)
	{
		this.db = db;
	}

	@Override
	public void run() {
		System.out.println(this.getClass().getName());
		startUI();
		ticket = new Ticket();
	}

	private void startUI() {
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
			int setVehicleCode = 0;
			boolean vehicle_not_exist = true;
			while(vehicle_not_exist) {
				vehicle_num = System.console().readLine("Enter Vehicle Serial Number: ");
				setVehicleCode = vc.setVehicleID(vehicle_num);
				// If Vehicle Doesn't Exist
				if (setVehicleCode == 1) {
					String response=System.console().readLine("Vehicle does not exist.Try Again(y/n)?");
					if(response.equals("y")){
						continue;
					}
					else if(response.equals("n")){
						System.console().printf("Vehicle does not exist in the database, returning to Main Menu\n");
						return;
					} else{
						System.out.println("\nInvalid Response... Returning to Main Menu");
						return;
					}
				} else {
					vehicle_not_exist = false;
				}
			}
			System.out.println();

			// If Primary Owner is known, Prompt user what to do
			if (setVehicleCode != 2) {

				// If error code = true
				boolean try_again = true;
				while(try_again) {
					violator_num = System.console().readLine("Enter Violator SIN: ");
					// If Violator Number Does Not Exists, ask if user wants to try again
					if (vc.setViolatorNo(violator_num) == true) {
						String response = System.console().readLine("Violator's SIN is invalid, try again? (y/n): ");
						if (response.toLowerCase().equals("y")) {
							continue;
						} else if(response.toLowerCase().equals("n")) {
							System.console().printf("The Violator SIN you are entering is invalid or does not exist in the database, returning to the Main Menu");
							return;
						} else {
							System.out.println("\nInvalid Response... Returning to Main Menu");
							return;
						}
					} else {
						try_again = false;
					}
					System.out.println();
				}
			}

			// Check datatype of setofficeno
			// TODO check if OFFICER EXISTS
			boolean office_num_err = true;
			while(office_num_err) {
				office_num = System.console().readLine("Enter Officer SIN: ");
				if (vc.setOfficeNo(office_num) == true) {
					String response = System.console().readLine("Officer SIN too long, try again? (y/n): ");
					if (response.toLowerCase().equals("y")) {
						continue;
					} else if(response.toLowerCase().equals("n")) {
						System.console().printf("Returning to the Main Menu");
						return;
					} else {
						System.out.println("\nInvalid Response... Returning to Main Menu");
					}
				} else {
					office_num_err = false;
				}
				System.out.println();
			}

			// Check datatype of vtype
			boolean vtype_err = true;
			while(vtype_err) {
				vtype = System.console().readLine("Enter Ticket Type: ");
				if (vc.setVType(vtype) == true) {
					String response = System.console().readLine("Ticket type length too long, try again? (y/n): ");
					if (response.toLowerCase().equals("y")) {
						continue;
					} else if(response.toLowerCase().equals("n")) {
						System.console().printf("Returning to Main Menu...\n");
						vtype_err = false;
						return;
					} else {
						System.console().printf("Invalid Input, try again.\n");
					}	
				} else {
					vtype_err = false;
				}
			}
			System.out.println();
			
			// Check place datatype
			boolean place_err = true;
			while(place_err) {
				place = System.console().readLine("Enter Place of Violation: ");
				if (vc.setPlace(place) == true) {
					String response = System.console().readLine("Place length too long, try again? (y/n): ");
					if (response.toLowerCase().equals("y")) {
						continue;
					} else if(response.toLowerCase().equals("n")) {
						System.console().printf("Returning to Main Menu...\n");
						place_err = false;
						return;
					} else {
						System.console().printf("Invalid Input, try again.\n");
					}	
				} else {
					place_err = false;
				}
			}
			
			// Check description datatype
			boolean desc_err = true;
			while(desc_err) {
				desc = System.console().readLine("Enter Description: ");
				if (vc.setDesc(desc) == true) {
					String response = System.console().readLine("Description too long, try again? (y/n): ");
					if (response.toLowerCase().equals("y")) {
						continue;
					} else if(response.toLowerCase().equals("n")) {
						System.console().printf("Returning to Main Menu...\n");
						desc_err = false;
						return;
					} else {
						System.console().printf("Invalid Input, try again.\n");
					}	
				} else {
					desc_err = false;
				}
			}
			System.out.println();
			// Send the updates
			//vc.test();
			try {
				vc.sendViolationUpdate();
			} catch (SQLException e) {
				System.console().printf("Violation record failed!\nInvalid inputs were provided, please try again.\n");
			}
			System.console().printf("Violation recorded.\n");

		} catch (NumberFormatException e) {
			System.out.println("Invalid Input!\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();

	}

	private boolean shouldNotExit() {
		return !exit;
	}


}
