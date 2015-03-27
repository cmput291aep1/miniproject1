package ui;

import p1.DLController;
import jdbc.JDBC;
/*CREATE TABLE drive_licence (
		  licence_no      CHAR(15),
		  sin             char(15),
		  class           VARCHAR(10),
		  photo           BLOB,
		  issuing_date    DATE,
		  expiring_date   DATE,
		  PRIMARY KEY (licence_no),
		  UNIQUE (sin),
		  FOREIGN KEY (sin) REFERENCES people
		        ON DELETE CASCADE
		);*/
public class DLView implements View {
	private DLController dl;
	public DLView(JDBC db) {
		dl=new DLController(db);
	}

	@Override
	public void run() {
		dl.setLicenceNum(System.console().readLine("License Number: "));
		dl.setSin(System.console().readLine("SIN: "));
		dl.setClass(System.console().readLine("Class: "));
		queryforPhoto();
		setIssueDate();
		setExpiryDate();
		dl.submit();
	}

	private void queryforPhoto() {
		String response=System.console().readLine("Attach photo(y/n)?: ");
		if(response.equalsIgnoreCase("y")){
			dl.setPhoto(System.console().readLine("Image location: "));
		}
		else{
			return;
		}
		
	}

	private void setExpiryDate() {
		boolean success=false;
		while(!success){
			try {
				dl.setExpiryDate(System.console().readLine("Expiry Date(YYYY-MM-DD): "));
				success=true;
			} catch (IllegalArgumentException e) {
				success=false;
				String response=System.console().readLine("Invalid Date.Make a correction(y) or abandon (n)?(y\n)");
				if(!response.equalsIgnoreCase("y")){
					success=true;
				}
			}
		}
	}

	private void setIssueDate() {
		boolean success=false;
		while(!success){
			try {
				dl.setIssueDate(System.console().readLine("Issuing Date(YYYY-MM-DD): "));
				success=true;
			} catch (IllegalArgumentException e) {
				success=false;
				String response=System.console().readLine("Invalid Date.Make a correction(y) or abandon (n)?(y\n)");
				if(!response.equalsIgnoreCase("y")){
					success=true;
				}
			}
		}
	}

}
