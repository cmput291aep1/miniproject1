package p1;

import jdbc.JDBC;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import ui.PersonView;

public class DLController {
	private JDBC db;
	private DriversLicense dl;
	private People driver=new People();
	public DLController(JDBC db) {
		this.db=db;
		dl=new DriversLicense();
	}
	public void setLicenceNum(String readLine) {
		dl.setLicence_no(readLine);
		
	}
	public void setSin(String readLine) {
		dl.setSin(readLine);
		driver.setSIN(readLine);
		
		
	}
	public void setClass(String readLine) {
		dl.setDclass(readLine);
		
	}
	public void setPhoto(String readLine) {
		dl.setPhoto(readLine);
		
	}
	public void setIssueDate(String readLine) throws IllegalArgumentException {
		dl.setIssuing_date(Date.valueOf(readLine));
		
	}
	public void setExpiryDate(String readLine) throws IllegalArgumentException {
		dl.setExpiring_date(Date.valueOf(readLine));
	}
	public void submit() {
		if(personHasLicence()){
			System.out.printf("Person %s already has a licence\n",driver.getSIN());
			return;
		}
		try {
			db.sendModelinfo(dl);
		} catch (FileNotFoundException e) {
			System.out.println("Picture Not Found\n");
		} catch (SQLException e) {
			if(e.getErrorCode()==1 || e.getErrorCode()==2291){
				System.out.printf("Person %s does not exist. They  will need to be created\n",driver.getSIN());
				Resend(driver);
			}
			else{
				System.out.println(e.getMessage());
			}
		}
	}
	private boolean personHasLicence() {
		try {
			ResultSet rs=db.sendQuery("select * from drive_licence where UPPER(sin)=UPPER('"+driver.getSIN()+"')");
			boolean failed=rs.next();
			rs.close();
			return failed;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return true;
		}
	}
	private void Resend(People driver) {
		new PersonView().run(driver);
		try {
			db.sendModelinfo(driver);
			db.sendModelinfo(dl);
		} catch (FileNotFoundException e) {
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
