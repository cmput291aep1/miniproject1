package p1;

import java.sql.Date;


public class Ticket extends Model {

	//ticket( ticket_no, violator_no,vehicle_no,office_no,vtype,vdate,place,descriptions )\
	/*
	 * ticket_no     int,
  violator_no   CHAR(15),  
  vehicle_id    CHAR(15),
  office_no     CHAR(15),
  vtype        char(10),
  vdate        date,
  place        varchar(20),
  descriptions varchar(1024),
	 */
	private int ticket_no;
	private String violator_no;
	private String vehicle_id;
	private String office_no;
	private String vtype;
	private Date vdate;
	private String place;
	private String description;
	
	public Ticket () {
		
	}
	public Ticket(int ticket_no,String violator_no,String vehicle_id,String office_no,String vtype,Date vdate,String place,String description) {
		this.ticket_no = ticket_no;
		this.violator_no = violator_no;
		this.vehicle_id = vehicle_id;
		this.office_no = office_no;
		this.vtype = vtype;
		this.vdate = vdate;
		this.place = place;
		this.description = description;
	}
	
	public int getTicket_no() {
		return this.ticket_no;
	}
	public void setTicket_no(int ticket_no) {
		this.ticket_no = ticket_no;
	}
	
	public String getViolator_no() {
		return this.violator_no;
	}
	public void setViolator_no(String violator_no) {
		this.violator_no = violator_no;
	}
	
	public String getVehicle_id() {
		return this.vehicle_id;
	}
	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	
	public String getOffice_no() {
		return this.office_no;
	}
	public void setOffice_no(String office_no) {
		this.office_no = office_no;
	}
	
	public String getVType() {
		return this.vtype;
	}
	public void setVType(String vtype) {
		this.vtype = vtype;
	}
	
	public Date getVDate() {
		return this.vdate;
	}
	public void setVDate(Date vdate) {
		this.vdate = vdate;
	}
	
	public String getPlace() {
		return this.place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String generateStatement() {
		return generateInsert("ticket","ticket_no","violator_no","vehicle_id","office_no","vtype","vdate","place","description")+encapsulate("'"+ticket_no+"','"+violator_no+"','"+vehicle_id+"','"+office_no+"','"+vtype+"','"+vdate+"','"+place+"','"+description);
	}
	
	//ticket( ticket_no, violator_no,vehicle_no,office_no,vtype,vdate,place,descriptions )\

}
