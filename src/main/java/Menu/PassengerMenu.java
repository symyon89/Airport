package Menu;

import Lists.ListOfPassengers;

import java.util.Scanner;

public class PassengerMenu {
    ListOfPassengers passengers = new ListOfPassengers();
    Scanner scannerNumber = new Scanner(System.in);

    private void mainMenu(){
        System.out.println("1.Show Paseengers");
        System.out.println("2.Rezerve flight");
        System.out.println("3.Update reservation");
        System.out.println("4.Delete reservation");
        System.out.println("5.Search passengers by flight name");
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
            case 1 -> passengers.showPassengers();
            case 2 -> passengers.flightReservation();
            case 3 -> passengers.updateFlightReservation();
            case 4 -> passengers.deleteFlightReservation();
            case 5 -> passengers.showPassengersByPlaneName();
            case 0 -> System.out.println("Goodbye!");
            default -> System.out.println("Invalid option");
        }
    }
}
