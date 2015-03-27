package p1;

public class Vehicle extends Model {
	private int Year;
	private String color;
	private int type_id;
	private String serial_no;
	private String maker;
	private String model;

	public Vehicle(){
		
	}
	
	public Vehicle(String serial_no, String maker, String model, int Year,
			String color, int type_id) {
		this.serial_no=serial_no; 
		this.maker=maker;
		this.model=model;
		this.Year=Year;
		this.color=color;
		this.type_id=type_id;
	}
	
	public int getYear() {
		return Year;
	}
	public void setYear(int year) {
		Year = year;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getSerial_no() {
		return serial_no;
	}
	public void setSerial_no(String serial_no) {
		this.serial_no = serial_no;
	}
	public String getMaker() {
		return maker;
	}
	public void setMaker(String maker) {
		this.maker = maker;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	@Override
	public String generateStatement() {
		return super.generateInsert("vehicle","serial_no","maker","model","year","color","type_id")+" "+super.encapsulate("'"+serial_no+"','"+maker+"','"+model+"',"+Year+",'"+color+"',"+type_id);
	}
}
