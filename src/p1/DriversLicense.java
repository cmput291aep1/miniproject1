package p1;

import java.sql.Blob;
import java.sql.Date;

public class DriversLicense extends Model {
	private String licence_no;
	private String sin;
	private String dclass;
	private Blob photo;
	private  Date issuing_date;
	private  Date expiring_date;
	
	public DriversLicense(){
		
	}
	public DriversLicense(String licence_no, String sin, String dclass,
			Blob photo, Date issuing_date, Date expiring_date) {
		this.licence_no = licence_no;
		this.sin = sin;
		this.dclass = dclass;
		this.photo = photo;
		this.issuing_date = issuing_date;
		this.expiring_date = expiring_date;
	}
	
	public String getLicence_no() {
		return licence_no;
	}
	public void setLicence_no(String licence_no) {
		this.licence_no = licence_no;
	}
	public String getSin() {
		return sin;
	}
	public void setSin(String sin) {
		this.sin = sin;
	}
	public String getDclass() {
		return dclass;
	}
	public void setDclass(String dclass) {
		this.dclass = dclass;
	}
	public Blob getPhoto() {
		return photo;
	}
	public void setPhoto(Blob photo) {
		this.photo = photo;
	}
	public Date getIssuing_date() {
		return issuing_date;
	}
	public void setIssuing_date(Date issuing_date) {
		this.issuing_date = issuing_date;
	}
	public Date getExpiring_date() {
		return expiring_date;
	}
	public void setExpiring_date(Date expiring_date) {
		this.expiring_date = expiring_date;
	}
	@Override
	public String generateStatement() {
		return generateInsert("licence_no","sin","class","photo","issuing_date","expiring_date")+encapsulate("'"+licence_no+"','"+sin+"','"+dclass+"','"+photo+"','"+issuing_date+"','"+expiring_date+',');
	}

}
