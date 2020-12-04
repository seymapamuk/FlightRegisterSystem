import java.util.Date;
import java.text.ParseException;

import java.util.Scanner;

// havayolu ve destinasyon bilgilerini daha kolay kullanmak icin enum kullandim.
enum Airline{
	KLM("KLM Airlines"), LH("Lufthansa Airlines"), TK("Turkish Airlines");
	
	String airlineName;
	Airline(String name){
		airlineName = name;
	}
}

enum Destination{
	ISL("Istanbul"), AMS("Schiphol"), FRA("Frankfurt");
	
	String destinationName;
	Destination(String name){
		destinationName = name;
	}
}

/*
 *  Flight base class'inin altinda PassengerFlight ve CargoFlight class'lari var. Passenger ve
 * Cargo objelerinin ortak tasidigi variable'lari Flight class'i tasiyor. Kendilerine ozel
 * variable'lar kendi class'lari icinde tutuluyor.
 * */

public class Flight {
	private Date fDate;
	private Airline fAirline;
	private Destination departure;
	private Destination arrival;
	
	protected Scanner scanInput;
	protected String userInput;
	
	/* 
	 *  Flight objesi yaratildiginda kendi setup'ini baslatiyor ve gerekli parametreler kullanicidan
	 * kontrol edilerek aliniyor. her parametrenin kendi set fonksiyonu var ve onun icinde gereken
	 * sartlara uygun input girilip girilmedigi kontrol ediliyor.
	*/
	Flight(){
		scanInput = new Scanner(System.in);
		
		System.out.println("===================");
		setDate();
		System.out.println("===================");
		setAirline();
		System.out.println("===================");
		setDestination("departure");
		System.out.println("===================");
		setDestination("arrival");
		System.out.println("===================");
	}
	
	// DATE METHODS
	public boolean checkDateFormat(String value) throws ParseException {
		Date date = null;
        date = Menu.sdf.parse(value);
        
        if (!value.equals(Menu.sdf.format(date))) // girilen tarih dogru formatta mi 
        	date = null;
        	
        else { // dogru formattaysa, daha once girilen tarihlerle karsilastiriliyor
        	fDate = date;
        	
        	for (Flight flight : Menu.savedFlights) {
				if(date.equals(flight.getDate()))
					fDate = null;
			}
        }
        
        return fDate != null;
    }
	
	public void setDate() {
		System.out.println("PLEASE ENTER FLIGHT DATE IN DD.MM.YYYY FORMAT: ");
		userInput = scanInput.nextLine();
		
		// set fonksiyonlari kullanicidan dogru input alinana kadar kendini tekrar cagiriyor
		try {
			if(!checkDateFormat(userInput)) {
				System.out.println("PLEASE TRY AGAIN");
				System.out.println("===================");
				setDate();
			}
		} catch (ParseException e) {
			System.out.println("PLEASE TRY AGAIN");
			System.out.println("===================");
			setDate();
		}
	}
	
	public Date getDate() {
		return fDate;
	}
	
	// AIRLINE METHODS
	public Airline findAirline(String input) {
		if(input.length() <= 3) {
			switch(input.toUpperCase()) {
			case("KLM"): 
				return Airline.KLM;
			case("LH"): 
				return Airline.LH;
			case("TK"): 
				return Airline.TK;
			}
		}
		else {
			switch(input.toUpperCase()) {
			case("KLM AIRLINES"): 
				return Airline.KLM;
			case("LUFTHANSA AIRLINES"): 
				return Airline.LH;
			case("TURKISH AIRLINES"): 
				return Airline.TK;
			}
		}
		return null;
	}
	
	public void setAirline() {
		System.out.println("PLEASE ENTER AIRLINE NAME OR CODE: ");
		userInput = scanInput.nextLine();
		
		fAirline = findAirline(userInput);
		
		// set fonksiyonlari kullanicidan dogru input alinana kadar kendini tekrar cagiriyor
		if(fAirline == null) {
			System.out.println("PLEASE TRY AGAIN");
			System.out.println("===================");
			setAirline();
		}
	}
	
	// DESTINATION METHODS
	public Destination findDestination(String input) {
		if(input.length() <= 3) {
			switch(input.toUpperCase()) {
			case("ISL"): 
				return Destination.ISL;
			case("AMS"): 
				return Destination.AMS;
			case("FRA"): 
				return Destination.FRA;
			}
		}
		else {
			switch(input.toUpperCase()) {
			case("ISTANBUL"): 
				return Destination.ISL;
			case("SCHIPHOL"): 
				return Destination.AMS;
			case("FRANKFURT"): 
				return Destination.FRA;
			}
		}
		return null;
	}
	
	public void setDestination(String key) {
		System.out.println("PLEASE ENTER " + key.toUpperCase() + " DESTINATION NAME OR CODE: ");
		userInput = scanInput.nextLine();
		
		boolean control = true;
		
		if(key.equals("departure")) {
			departure = findDestination(userInput);
			
			if(departure == null)
				control = false;
		}
		else {
			arrival = findDestination(userInput);
			
			if(arrival == null)
				control = false;
		}
		
		// set fonksiyonlari kullanicidan dogru input alinana kadar kendini tekrar cagiriyor
		if(!control || arrival == departure) {
			System.out.println("PLEASE TRY AGAIN");
			System.out.println("===================");
			setDestination(key);
		}
	}
		
	public void showFlightInformation() {
		System.out.println("DATE: " + Menu.sdf.format(fDate));
		System.out.println("===================");
		System.out.println("AIRLINE: " + fAirline.airlineName);
		System.out.println("===================");
		System.out.println("DEPARTURE: " + departure.destinationName);
		System.out.println("===================");
		System.out.println("ARRIVAL: " + arrival.destinationName);
		System.out.println("===================");
	}

}

