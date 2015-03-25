package p1;

import java.sql.Date;

public class People extends Model {

	private String sin;
	private String name;
	private double height;
	private double weight;
	private String eyecolor;
	private String haircolor;
	private String addr;
	private String gender;
	private Date bday;
	
	// people( sin, name, height,weight,eyecolor, haircolor,addr,gender,birthday )
	public People() {
		
	}
	public People(String sin, String name, double height, double weight, String eyecolor, String haircolor, String addr, String gender, Date bday) {
		this.sin = sin;
		this.name = name;
		this.height = height;
		this.weight = weight;
		this.eyecolor = eyecolor;
		this.haircolor = haircolor;
		this.addr = addr;
		this.gender = gender;
		this.bday = bday;
	}
	
	public String getSIN() {
		return this.sin;
	}
	public void setSIN(String sin) {
		this.sin = sin;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHeight() {
		return this.height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public double getWeight() {
		return this.weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getEyecolor() {
		return this.eyecolor;
	}
	public void setEyecolor(String eyecolor) {
		this.eyecolor = eyecolor;
	}
	public String getHaircolor() {
		return this.haircolor;
	}
	public void setHaircolor(String haircolor) {
		this.haircolor = haircolor;
	}
	public String getAddr() {
		return this.addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getGender() {
		return this.gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBday() {
		return this.bday;
	}
	public void setBday(Date bday) {
		this.bday = bday;
	}
	
	@Override
	public String generateStatement() {
		return super.generateInsert("people","sin","name","height","weight","eyecolor","haircolor","addr","gender","birthday")+" "+super.encapsulate("'"+sin+"','"+name+"',"+height+","+weight+",'"+eyecolor+"','"+haircolor+"','"+addr+"','"+gender+"',TO_DATE('"+bday+"','YYYY-MM-DD')");
	}
	
}
