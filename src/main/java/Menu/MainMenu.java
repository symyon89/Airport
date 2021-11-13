package Menu;

import java.util.Scanner;

public class MainMenu {
    public void menu(){
        int option;
        Scanner scanner = new Scanner(System.in);
        do {
            menuOptions();
            option = scanner.nextInt();
            menuAction(option);

        }while (option != 0);

    }
    private void menuOptions(){
        System.out.println("1.Flight menu");
        System.out.println("2.Passenger menu");
        System.out.println("3.Plane menu");
        System.out.println("0.Exit");
        System.out.println("Choose an option");
    }

    private void menuAction(int index){
        switch (index) {
            case 1 -> {
                FlightMenu flightMenu = new FlightMenu();
                flightMenu.menu();
            }
            case 2 -> {
                PassengerMenu passengerMenu = new PassengerMenu();
                passengerMenu.menu();
            }
            case 3 -> {
                PlaneMenu planeMenu = new PlaneMenu();
                planeMenu.menu();
            }
            case 0 -> System.out.println("Goodbye!");
            default -> System.out.println("Invalid option!");
        }
    }
}
