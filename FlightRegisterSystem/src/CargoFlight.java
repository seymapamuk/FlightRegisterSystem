public class CargoFlight extends Flight {
	int cargoWeight;
	
	int minCargoWeight = 100;
	int maxCargoWeight = 5000;
	
	CargoFlight() {
		super();
		
		setCargoWeight();
	}
	
	public boolean checkCargoWeight(int weight) {
		if(weight >= minCargoWeight && weight <= maxCargoWeight) {
			cargoWeight = weight;
			return true;
		}
		
		return false;
	}
	
	public void setCargoWeight() {
		System.out.println("PLEASE ENTER CARGO WEIGHT (100 kg - 5000 kg): ");
		userInput = scanInput.nextLine();
		
		// set fonksiyonlari kullanicidan dogru input alinana kadar kendini tekrar cagiriyor
		if(!checkCargoWeight(Integer.parseInt(userInput))) {
			System.out.println("PLEASE TRY AGAIN");
			System.out.println("===================");
			setCargoWeight();
		}
	}
	
	@Override
	public void showFlightInformation() {
		System.out.println("FLIGHT TYPE: CARGO");
		System.out.println("===================");
		super.showFlightInformation();
		System.out.println("CARGO WEIGHT: " + cargoWeight);
		System.out.println("===================");
	}
}
