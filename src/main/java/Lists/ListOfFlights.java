package Lists;

import Exceptions.WrongDateException;
import Exceptions.WrongIndexException;
import MainClasses.Flight;
import MainClasses.Plane;
import MainClasses.ReadFiles;
import MainClasses.TravelTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ListOfFlights {

    private Map<String, Flight> flights;
    private Map<String, Plane> planes;
    private final AtomicInteger index = new AtomicInteger();
    private final TravelTime travelHours = (dist, avg) -> (dist / avg);
    private final TravelTime travelminutes = (dist, avg) -> (int) (((dist / (avg * 1.0)) - ((dist / avg))) * 60);
    private List<String> flightList = new ArrayList<>();
    private final Scanner scannerText = new Scanner(System.in);
    private final Scanner scannerNumber = new Scanner(System.in);

    public ListOfFlights() {
        Runnable readFlights = () -> flights = ReadFiles.readFlights();
        Runnable readPlanes = () -> planes = ReadFiles.readPlanes();
        Thread readFileFlights = new Thread(readFlights);
        Thread readFilePlanes = new Thread(readPlanes);
        readFileFlights.start();
        readFilePlanes.start();
        try {
            readFileFlights.join();
            readFilePlanes.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void showFlights() {
        flightList = new ArrayList<>();
        index.set(1);
        flights.forEach((key, flight) -> {
            System.out.println(index.getAndIncrement() + "." + flight + " Travel time = "
                    + travelHours.calculateTime(flight.getDistance(), planes.get(flight.getPlane()).getAverageSpeed()) + " hours "
                    + travelminutes.calculateTime(flight.getDistance(), planes.get(flight.getPlane()).getAverageSpeed()) + " minutes");
            flightList.add(flight.getFlight());
        });
    }

    public void addFlight() {
        Flight flight = new Flight();
        try {
            newFlight(flight);
        } catch (WrongIndexException e) {
            System.out.println(e.getMessage());
            return;
        }
        flights.put(flight.getFlight(), flight);
        ReadFiles.updateFlights(flights);

    }

    public void updateFlight() {

        showFlights();
        System.out.println("Choose Flight to update");
        int flightIndex = scannerNumber.nextInt();
        try {
            checkFlightIndex(flightIndex);
        } catch (WrongIndexException e) {
            System.out.println(e.getMessage());
            return;
        }
        String flightCode = flightList.get(flightIndex - 1);
        int remainingSeats = flights.get(flightCode).getRemainingSeats();
        Flight flight = new Flight();
        try {
            newFlight(flight);
        } catch (WrongIndexException e) {
            System.out.println(e.getMessage());
            return;
        }
        flight.setRemainingSeats(remainingSeats);
        flights.replace(flightCode, flight);
        ReadFiles.updateFlights(flights);

    }
    public void deleteFlight(){
        showFlights();
        System.out.println("Choose Flight to delete");
        int flightIndex = scannerNumber.nextInt();
        try {
            checkFlightIndex(flightIndex);
        } catch (WrongIndexException e) {
            System.out.println(e.getMessage());
            return;
        }
        String flightCode = flightList.get(flightIndex - 1);
        flights.remove(flightCode);
        ReadFiles.updateFlights(flights);
    }

    public void showFlightsByDepartureCity(){
        System.out.println("Enter city :");
        String city = scannerText.nextLine();
        index.set(1);
        flights.forEach((key, flight) -> {
            if (city.equalsIgnoreCase(flight.getDepartureCity())) {
                System.out.println(index.getAndIncrement() + "." + flight + " Travel time = "
                        + travelHours.calculateTime(flight.getDistance(), planes.get(flight.getPlane()).getAverageSpeed()) + " hours "
                        + travelminutes.calculateTime(flight.getDistance(), planes.get(flight.getPlane()).getAverageSpeed()) + " minutes");
            }
        });
        if (index.get() == 1){
            System.out.println("No flight found for " + city);
        }
    }
    public void showFlightsByDestinationCity(){
        System.out.println("Enter city :");
        String city = scannerText.nextLine();
        index.set(1);
        flights.forEach((key, flight) -> {
            if (city.equalsIgnoreCase(flight.getDestinationCity())) {
                System.out.println(index.getAndIncrement() + "." + flight + " Travel time = "
                        + travelHours.calculateTime(flight.getDistance(), planes.get(flight.getPlane()).getAverageSpeed()) + " hours "
                        + travelminutes.calculateTime(flight.getDistance(), planes.get(flight.getPlane()).getAverageSpeed()) + " minutes");
            }
        });
        if (index.get() == 1){
            System.out.println("No flight found for " + city);
        }
    }
    public void showFlightsByDestinationAndDepartureCity(){
        System.out.println("Enter departure city :");
        String departureCity = scannerText.nextLine();
        System.out.println("Enter arrival city :");
        String arrivalCity = scannerText.nextLine();
        index.set(1);
        flights.forEach((key, flight) -> {
            if (departureCity.equalsIgnoreCase(flight.getDepartureCity()) && arrivalCity.equalsIgnoreCase(flight.getDestinationCity())) {
                System.out.println(index.getAndIncrement() + "." + flight + " Travel time = "
                        + travelHours.calculateTime(flight.getDistance(), planes.get(flight.getPlane()).getAverageSpeed()) + " hours "
                        + travelminutes.calculateTime(flight.getDistance(), planes.get(flight.getPlane()).getAverageSpeed()) + " minutes");
            }
        });
        if (index.get() == 1){
            System.out.println("No flight found for " + departureCity + " - " + arrivalCity);
        }
    }

    public String returnFlightFromIndex(int index) throws WrongIndexException{
        checkFlightIndex(index);
        return flightList.get(index - 1);
    }
    public void updateSeats(String flight, int seats){
        flights.get(flight).setRemainingSeats(flights.get(flight).getRemainingSeats() - seats);
        ReadFiles.updateFlights(flights);
    }


    private void newFlight(Flight flight) throws WrongIndexException {

        List<String> listOfPlanes = showPlanes();
        System.out.println("Choose plane for your flight :");
        int option = scannerNumber.nextInt();
        checkPlaneIndex(option);
        flight.setPlane(listOfPlanes.get(option - 1));
        System.out.println("Enter flight code : ");
        flight.setFlight(scannerText.nextLine());
        System.out.println("Enter departure city :");
        flight.setDepartureCity(scannerText.nextLine());
        System.out.println("Enter destination city :");
        flight.setDestinationCity(scannerText.nextLine());
        System.out.println("Enter distance :");
        flight.setDistance(scannerNumber.nextInt());
        flight.setRemainingSeats(planes.get(listOfPlanes.get(option - 1)).getNumebrOfSeats());
        enterDate(flight);
    }

    private void checkPlaneIndex(int option) throws WrongIndexException {
        if (option > planes.size() || option < 1) {
            System.out.println("Invalid plane selected");
            throw new WrongIndexException();
        }
    }

    private void checkFlightIndex(int option) throws WrongIndexException {
        if (option > flightList.size() || option < 1) {
            System.out.println("Invalid Flight selected");
            throw new WrongIndexException();
        }
    }

    private void enterDate(Flight flight) {
        System.out.println("Enter departure hour :");
        int hour = scannerNumber.nextInt();
        System.out.println("Enter departure minutes :");
        int minutes = scannerNumber.nextInt();
        try {
            flight.setDepartureTime(hour, minutes);
        } catch (WrongDateException e) {
            System.out.println(e.getMessage());
            enterDate(flight);
        }
    }

    private List<String> showPlanes() {
        index.set(1);
        List<String> listOfPlanes = new ArrayList<>();
        planes.forEach((key, plane) -> {
            System.out.println(index + "." + plane);
            listOfPlanes.add(key);
        });
        return listOfPlanes;
    }

}
