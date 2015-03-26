package p1;
import java.sql.Date;

public class PersonController {

	private People person;

	public PersonController(People p) {
		this.person=p;
	}

	public void setName(String readLine) {
		person.setName(readLine);
		
	}

	public void setHeight(String readLine) throws NumberFormatException{
		person.setHeight(Double.valueOf(readLine));
		
	}

	public void setWeight(String readLine) throws NumberFormatException {
		person.setWeight(Double.parseDouble(readLine));
		
	}

	public void setEyeColor(String readLine) {
		person.setEyecolor(readLine);
		
	}

	public void setHairColor(String readLine) {
		person.setHaircolor(readLine);
		
	}

	public void setAddr(String readLine) {
		person.setAddr(readLine);
		
	}

	public void setGender(String readLine) {
		person.setGender(readLine);
		
	}

	public void setBirthDay(String readLine) throws IllegalArgumentException {
		person.setBday(Date.valueOf(readLine));
	}

}
