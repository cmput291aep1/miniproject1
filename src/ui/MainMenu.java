package ui;

import java.sql.SQLException;

import jdbc.JDBC;

public class MainMenu {
	private JDBC db;
	public static void main(String[] args) throws SQLException {
		MainMenu dsp=new MainMenu();
		try {
			dsp.loginToDatabase();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dsp.startUI();
		dsp.finish();
		return;
	}
	private void finish() throws SQLException {
		db.close();
		
	}
	private void startUI() {
		// TODO Auto-generated method stub
		
	}
	private void loginToDatabase() throws ClassNotFoundException, SQLException {
		String username=System.console().readLine("Enter a username:");
		String pw=String.valueOf(System.console().readPassword("\nEnter your password:"));
		System.out.println();
		db=JDBC.getInstance(username, pw);
	}

}
