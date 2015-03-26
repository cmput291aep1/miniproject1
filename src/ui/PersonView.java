package ui;

import java.util.IllegalFormatException;

import p1.People;
import p1.PersonController;

public class PersonView {

	public void run(People p) {
		//people( sin, name, height,weight,eyecolor, haircolor,addr,gender,birthday 
		PersonController perCon=new PersonController(p);
		perCon.setName(System.console().readLine("Name: "));
		setHeight(perCon);
		setWeight(perCon);
		perCon.setEyeColor(System.console().readLine("eyecolor: "));
		perCon.setHairColor(System.console().readLine("Hair Color: "));
		perCon.setAddr(System.console().readLine("Address: "));
		perCon.setGender(System.console().readLine("Gender: "));
		setBDay(perCon);
	}

	private void setBDay(PersonController perCon) {
		boolean success=false;
		while(!success){
			try {
				perCon.setBirthDay(System.console().readLine("Birthday (YYYY-MM-DD): "));
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

	private void setWeight(PersonController perCon) {
		boolean success=false;
		while(!success){
			try {
				perCon.setWeight(System.console().readLine("weight: "));
				success=true;
			} catch (NumberFormatException e) {
				success=false;
				String response=System.console().readLine("Not a Number.Make a correction(y) or abandon (n)?(y\n)");
				if(!response.equalsIgnoreCase("y")){
					success=true;
				}
			}
		}
	}

	private void setHeight(PersonController perCon) {
		boolean success=false;
		while(!success){
			try {
				perCon.setHeight(System.console().readLine("height: "));
				success=true;
			} catch (NumberFormatException e) {
				success=false;
				String response=System.console().readLine("Not a Number.Make a correction(y) or abandon (n)?(y\n)");
				if(!response.equalsIgnoreCase("y")){
					success=true;
				}
			}
		}
	}

}
