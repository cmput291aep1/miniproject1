package p1;

public class Vehicle extends Model {
	private int Year;
	private String color;
	private int type_id;
	private String serial_no;
	private String maker;
	private String model;

	public Vehicle(String serial_no, String maker, String model, int Year,
			String color, int type_id) {
		this.serial_no=serial_no; 
		this.maker=maker; 
		this.model=model;
		this.Year=Year;
		this.color=color;
		this.type_id=type_id;
	}
	@Override
	public String generateStatement() {
		return super.generateInsert("vehicle","serial_no","maker","model","Year","color","type_id")+" "+super.encapsulate("'"+serial_no+"','"+maker+"','"+model+"',"+Year+",'"+color+"','"+type_id);
	}
}
