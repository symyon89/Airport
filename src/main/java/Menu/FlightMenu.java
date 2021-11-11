package Menu;

import Lists.ListOfFlights;

import java.util.Scanner;

public class FlightMenu {
    ListOfFlights flights = new ListOfFlights();
    Scanner scannerNumber = new Scanner(System.in);

    private void mainMenu(){
        System.out.println("1.Show Flights");
        System.out.println("0.Exit");
        System.out.print("Choose option :");
    }
    public void menu(){
        byte menuOption;
        do {
            mainMenu();
            menuOption = scannerNumber.nextByte();
            menuAction(menuOption);
        }while (menuOption != 0);
    }

    private void menuAction(byte menuOption) {
        switch (menuOption){
            case 1 -> flights.showFlights();
            case 0 -> System.out.println();
            default -> System.out.println("Invalid option");
        }
    }
}
