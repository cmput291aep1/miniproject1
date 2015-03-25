package ui;

import java.sql.SQLException;

import jdbc.JDBC;

public class MainMenu {
	private JDBC db;
	private boolean exit=false;
	private boolean connected=false;
	public static void main(String[] args) throws SQLException {
		MainMenu dsp=new MainMenu();
		while(!dsp.isConnected()){
			try {
				dsp.loginToDatabase();
				dsp.setConnected();
			} catch (ClassNotFoundException e) {
			} catch (SQLException e) {
				dsp.invalidateConnection();
				String response=System.console().readLine("Login Failed.Try Again(y/n)?");
				if(response.equals("y")){
					continue;
				}
				else if(response.equals("n")){
					return;
				}
				else{
					System.out.println("\nInvalid Response... Terminating");
					return;
				}
			}
		}
		dsp.startUI();
		dsp.finish();
		return;
	}
	private void invalidateConnection() {
		connected=false;
		
	}
	private void setConnected() {
		connected=true;
		
	}
	private boolean isConnected() {
		return connected;
	}
	private void finish() throws SQLException {
		db.close();
		
	}
	private void startUI() {
		while(shouldNotExit()){
			int selection;
			try {
				selection = Integer.parseInt(System.console().readLine("Main Menu\nRegister a New vehicle(1)\nRecord a"
						+ " new AutoTransaction(2)\nRegister a new Drivers License(3)\nRecord A Trafic Violation(4)\n"
						+ "Perform a Search(5)\nExit(6)\nPlease make a selection: "));
			} catch (NumberFormatException e) {
				System.out.println("Invalid Input!\n");
				continue;
			}
			System.out.println();
			PerformSelection(selection);
		}
	}
	private void PerformSelection(int selection) {
		View v = null;
		switch(selection){
			case 1:
				v=new VehicleView(db);
				break;
			case 2:
				v=new AutoTransView();
				break;
			case 3:
				v=new DLView();
				break;
			case 4:
				v=new ViolationView(db);
				break;
			case 5:
				v=new SearchView();
				break;
			case 6:
				exit=true;
				return;
			default:
				System.out.println("Invalid Selection\n");
				return;
		}
		v.run();
		
	}
	private boolean shouldNotExit() {
		return !exit;
	}
	private void loginToDatabase() throws ClassNotFoundException, SQLException {
		String username=System.console().readLine("Enter a username: ");
		String pw=String.valueOf(System.console().readPassword("\nEnter your password:"));
		System.out.println();
		db=JDBC.getInstance(username, pw);
	}

}
