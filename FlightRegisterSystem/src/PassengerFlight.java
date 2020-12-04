public class PassengerFlight extends Flight{
	String seatSelection;
	
	int minSeatNo = 1;
	int maxSeatNo = 31;
	
	String seatChars = "ABCDEFabcdef";
	
	PassengerFlight() {
		super();
		
		setSeatSelection();
	}
	
	public boolean checkSeatSelection(String selection) {
		int seatNo = 0;
		
		try {
			seatNo = Integer.parseInt(selection.substring(0, 2));	
		} catch(NumberFormatException ex) {
			return false;
		}
		
		char seatChar = selection.charAt(2);
				
		if(seatNo >= minSeatNo && seatNo <= maxSeatNo) {
			if(seatChars.indexOf(seatChar) >= 0) {
				selection = selection.toUpperCase();
				seatSelection = selection;
				return true;
			}
		}
		return false;
	}
	
	public void setSeatSelection() {
		System.out.println("PLEASE ENTER SEAT SELECTION (ex: 12A): ");
		userInput = scanInput.nextLine();
		
		// set fonksiyonlari kullanicidan dogru input alinana kadar kendini tekrar cagiriyor
		if(!checkSeatSelection(userInput)) {
			System.out.println("PLEASE TRY AGAIN");
			System.out.println("===================");
			setSeatSelection();
		}
	}
	
	@Override
	public void showFlightInformation() {
		System.out.println("FLIGHT TYPE: PASSENGER");
		System.out.println("===================");
		super.showFlightInformation();
		System.out.println("SEAT: " + seatSelection);
		System.out.println("===================");
	}
}
