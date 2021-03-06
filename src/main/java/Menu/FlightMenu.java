package Menu;

import Lists.ListOfFlights;

import java.util.Scanner;

public class FlightMenu {
    ListOfFlights flights = new ListOfFlights();
    Scanner scannerNumber = new Scanner(System.in);

    private void options(){
        System.out.println("1.Show Flights");
        System.out.println("2.Add Flight");
        System.out.println("3.Update Flight");
        System.out.println("4.Delete Flight");
        System.out.println("5.Search by departure city");
        System.out.println("6.Search by destination city");
        System.out.println("7.Search by departure and destination city");
        System.out.println("0.Exit");
        System.out.print("Choose option :");
    }
    public void menu(){
        byte selectedOption;
        do {
            options();
            selectedOption = scannerNumber.nextByte();
            action(selectedOption);
        }while (selectedOption != 0);
    }

    private void action(byte menuOption) {
        switch (menuOption){
            case 1 -> flights.showFlights();
            case 2 -> flights.addFlight();
            case 3 -> flights.updateFlight();
            case 4 -> flights.deleteFlight();
            case 5 -> flights.showFlightsByDepartureCity();
            case 6 -> flights.showFlightsByDestinationCity();
            case 7 -> flights.showFlightsByDestinationAndDepartureCity();
            case 0 -> System.out.println();
            default -> System.out.println("Invalid option");
        }
    }
}
