package Lists;

import Exceptions.WrongIndexException;
import MainClasses.Passenger;
import MainClasses.ReadFiles;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;


public class ListOfPassengers {
    private Map<Integer, Passenger> passengers;
    private final Scanner scannerText = new Scanner(System.in);
    private final Scanner scannerNumber = new Scanner(System.in);


    public ListOfPassengers() {
        Runnable readPassengers = () -> passengers = ReadFiles.readPassengers();
        Thread readFilePassengers = new Thread(readPassengers);
        readFilePassengers.start();
        try {
            readFilePassengers.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void showPassengers() {
        passengers.forEach((key, passenger) -> System.out.println(passenger));
    }

    public void flightReservation() {
        Passenger passenger = new Passenger();
        addPassengerDetails(passenger);
        passengers.put(passengers.size() + 1, passenger);
        updateSeats(passenger.getFlight(), passenger.getSeats());
        ReadFiles.updatePassengers(passengers);
    }

    public void updateFlightReservation() {
        showPassengers();
        System.out.println("Enter your id reservation");
        int idReservation = scannerNumber.nextInt();
        try {
            checkIdReservatioIndex(idReservation);
        } catch (WrongIndexException e) {
            System.out.println(e.getMessage());
            return;
        }
        Passenger passenger = new Passenger();
        addPassengerDetails(passenger);
        int seats = passenger.getSeats() - passengers.get(idReservation).getSeats();
        passengers.replace(idReservation, passenger);
        updateSeats(passenger.getFlight(),seats);
        ReadFiles.updatePassengers(passengers);
    }

    public void deleteFlightReservation() {
        showPassengers();
        System.out.println("Enter id reservation to delete");
        int idReservation = scannerNumber.nextInt();
        try {
            checkIdReservatioIndex(idReservation);
        } catch (WrongIndexException e) {
            System.out.println(e.getMessage());
            return;
        }
        int seats = passengers.get(idReservation).getSeats();
        String flight = passengers.get(idReservation).getFlight();
        passengers.remove(idReservation);
        seats = seats * (-1);
        updateSeats(flight, seats);
    }

    private void updateSeats(String flight, int seats) {
        ListOfFlights flights = new ListOfFlights();
        flights.updateSeats(flight,seats);
    }

    private void checkIdReservatioIndex(int option) throws WrongIndexException {
        if (option > passengers.size() || option < 1) {
            System.out.println("Invalid plane selected");
            throw new WrongIndexException();
        }
    }

    private void addPassengerDetails(Passenger passenger) {
        ListOfFlights flights = new ListOfFlights();
        flights.showFlights();
        System.out.println("Choose flight to reserve : ");
        int option = scannerNumber.nextInt();
        String flight;
        try {
            flight = flights.returnFlightFromIndex(option);
        } catch (WrongIndexException e) {
            System.out.println(e.getMessage());
            return;
        }
        passenger.setFlight(flight);
        passenger.setIdReservation(passengers.size() + 1);
        System.out.println("Enter Firstname :");
        passenger.setFirstname(scannerText.nextLine());
        System.out.println("Enter Lastname : ");
        passenger.setLastname(scannerText.nextLine());
        System.out.println("Enter id number ");
        passenger.setIdNumber(scannerText.nextLine());
        System.out.println("Enter number of seats :");
        passenger.setSeats(scannerNumber.nextInt());
        passenger.setDateOfAquisition(LocalDateTime.now());
    }

    public void showPassengersByPlaneName() {
        ListOfFlights flights = new ListOfFlights();
        flights.showFlights();
        System.out.println("Enter flight : ");
        String flight = scannerNumber.nextLine();
        AtomicInteger index = new AtomicInteger();
        index.set(1);
        passengers.forEach((key, passenger) -> {
            if (passenger.getFlight().equalsIgnoreCase(flight)) {
                System.out.println(index.getAndIncrement() + "." + passenger);
            }
        });
        if (index.get() == 1) {
            System.out.println("No passengers found");
        }
    }
}
