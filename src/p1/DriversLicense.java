package p1;

import java.sql.Blob;
import java.sql.Date;

public class DriversLicense extends Model {
	private String licence_no;
	private String sin;
	private String dclass;
	private String photoFileName;
	private  Date issuing_date;
	private  Date expiring_date;
	private boolean hasPicture=false;
	
	public DriversLicense(){
		
	}
	public DriversLicense(String licence_no, String sin, String dclass,
			String photo, Date issuing_date, Date expiring_date) {
		this.licence_no = licence_no.toLowerCase();
		this.sin = sin.toLowerCase();
		this.dclass = dclass.toLowerCase();
		this.photoFileName = photo;
		this.issuing_date = issuing_date;
		this.expiring_date = expiring_date;
		if(photo!=null){
			hasPicture=true;
		}
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
		this.dclass = dclass.toLowerCase();
	}
	@Override
	public boolean hasBlob(){
		return hasPicture;
	}
	public String getPhotoFilename() {
		return photoFileName;
	}
	@Override
	public String getBlobFileName(){
		return getPhotoFilename();
	}
	public void setPhoto(String photo) {
		this.photoFileName = photo;
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
		if(hasPicture){
			return generateInsert("drive_licence","licence_no","sin","class","photo","issuing_date","expiring_date")+" "+encapsulate("'"+licence_no+"','"+sin+"','"+dclass+"',?"+",TO_DATE('"+issuing_date+"','YYYY-MM-DD')"+",TO_DATE('"+expiring_date+"','YYYY-MM-DD')");
		}
		return generateInsert("drive_licence","licence_no","sin","class","issuing_date","expiring_date")+" "+encapsulate("'"+licence_no+"','"+sin+"','"+dclass+"','"+issuing_date+"','"+expiring_date+',');
	}

}
