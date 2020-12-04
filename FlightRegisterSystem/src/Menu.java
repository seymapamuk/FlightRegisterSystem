import java.util.ArrayList;
import java.text.SimpleDateFormat;

import java.util.Scanner;

public class Menu {	
	public static ArrayList<Flight> savedFlights; // tum ucuslar bu listeye kaydediliyor
	public static SimpleDateFormat sdf; 
	
	private Scanner scanInput;
	private Flight newFlight;
	
	Menu(){
		savedFlights = new ArrayList<Flight>(); 
		sdf = new SimpleDateFormat("dd.MM.yyyy"); 
		start();
	}
	
	public void start() {
		scanInput = new Scanner(System.in);
		
		System.out.println("*******************");
		System.out.println("WELCOME TO THE FLIGHT REGISTER SYSTEM");
		System.out.println("(1) - TO ADD NEW FLIGHT");
		System.out.println("(2) - TO SEE EXISTING FLIGHTS");
		
		String userInput;
		
		userInput = scanInput.nextLine();
		
		if(Integer.parseInt(userInput) == 1)
			addNewFlight();
		else if(Integer.parseInt(userInput) == 2)
			showExistingFlights();
		else {
			System.out.println("PLEASE TRY AGAIN");
			start();
		}
		
		scanInput.close();
	}
	
	public void addNewFlight() {
		String userInput;
		
		System.out.println("*******************");
		System.out.println("FLIGHT TYPE: (1) - PASSENGER OR (2) - CARGO?");
		userInput = scanInput.nextLine(); 

		if(Integer.parseInt(userInput) == 1) {
			newFlight = new PassengerFlight();
			savedFlights.add(newFlight);
		}
		else if(Integer.parseInt(userInput) == 2) {
			newFlight = new CargoFlight();
			savedFlights.add(newFlight);
		}
		else {
			System.out.println("PLEASE TRY AGAIN");
			System.out.println("===================");
			addNewFlight();
		}
		
		start();
	}
	
	public void showExistingFlights() {
		int count = 1;
		
		for (Flight flight : savedFlights) {
			System.out.println("*******************");
			System.out.println("FLIGHT " + count);
			System.out.println("===================");
			flight.showFlightInformation();
			count++;
		}
		
		System.out.println("PRESS ANY BUTTON TO RETURN TO MAIN MENU");
		boolean userInput = scanInput.hasNext();
		
		if(userInput) 
			start();
	}
}

class Main {
	public static void main(String[] args) {
		Menu m = new Menu();
	}
}
