package ui;

import p1.VRegController;

public class VehicleView implements View {
	private VRegController vcon=new VRegController();
	@Override
	public void run() {
		vcon.printSomeThing();
		//registerNewVehicle();
	}
	//vehicle( serial_no, maker, model, year, color, type_id )
	private void registerNewVehicle() {
		getSerialNumber();
		addOwners();
		addMaker();
		addModel();
		addMaker();
		addColor();
		declareTypeID();
		vcon.submit();
	}
	private void declareTypeID() {
		boolean success=false;
		while(!success){
			try {
				vcon.setVehicleType(System.console().readLine(" Enter Vehicle Type:"));
				success=true;
			} catch (NumberFormatException e) {
				success=false;
				System.out.println("Not a Number.Vehicle type should be number");
			}
		}
	}
	private void addColor() {
		vcon.setColor(System.console().readLine(" Enter Vehicle Color:"));
		System.out.println();
	}
	private void addModel() {
		vcon.setVModel(System.console().readLine(" Enter Vehicle Model:"));
		System.out.println();
	}
	private void addMaker() {
		System.console().readLine(" Enter Vehicle Maker:");
		System.out.println();
		
	}
	private void addOwners() {
		vcon.addPrimaryOwner(System.console().readLine("Enter Primary Owner's SIN:"));
		while(true){
			String response=System.console().readLine("Add  a secondary owner(y/n)?:");
			if(response.equalsIgnoreCase("n")){
				return;
			}
			else if(response.equalsIgnoreCase("y")){
				vcon.addSecondaryOwner(System.console().readLine("Secondary Owner SIN:"));
			}
			else{
				System.out.println("Invalid Input. Try again.....");
			}
		}
	}
	private void getSerialNumber() {
		vcon.addSN(System.console().readLine("Enter Vehicle S/N: "));
		System.out.println();
	}

}
