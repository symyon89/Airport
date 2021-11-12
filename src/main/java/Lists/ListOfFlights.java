package Lists;

import Exceptions.WrongDateException;
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
                    + travelHours.calculateTime(flight.getDistance(),planes.get(flight.getPlane()).getAverageSpeed()) + " hours "
                    + travelminutes.calculateTime(flight.getDistance(),planes.get(flight.getPlane()).getAverageSpeed()) + " minutes");
            flightList.add(flight.getFlight());
        });
    }

    public void addFlight(){
        Flight flight = new Flight();
        List<String> listOfPlanes = showPlanes();
        System.out.println("Choose plane for your flight :");
        Scanner scannerNumber = new Scanner(System.in);
        Scanner scannerText = new Scanner(System.in);
        int option = scannerNumber.nextInt();
        if(option > planes.size() || option < 1){
            System.out.println("Invalid plane selected");
            return;
        }
        flight.setPlane(listOfPlanes.get(option-1));
        System.out.println("Enter flight code : ");
        flight.setFlight(scannerText.nextLine());
        System.out.println("Enter departure city :");
        flight.setDepartureCity(scannerText.nextLine());
        System.out.println("Enter destination city :");
        flight.setDestinationCity(scannerText.nextLine());
        System.out.println("Enter distance :");
        flight.setDistance(scannerNumber.nextInt());
        enterDate(flight);
        flights.put(flight.getFlight(), flight);
        ReadFiles.updateFlights(flights);

    }
    public void updateFlight(){
        Scanner scannerText = new Scanner(System.in);
        Scanner scannerNumber = new Scanner(System.in);
        showFlights();
        System.out.println("Choose Flight to update");
        int flightIndex = scannerNumber.nextInt();
        if(flightIndex > flightList.size() || flightIndex < 1){
            System.out.println("Invalid flight selected");
            return;
        }
        String flightCode = flightList.get(flightIndex - 1);
        Flight flight = new Flight();
        List<String> listOfPlanes = showPlanes();
        System.out.println("Choose plane for your flight :");
        int option = scannerNumber.nextInt();
        if(option > planes.size() || option < 1){
            System.out.println("Invalid plane selected");
            return;
        }
        flight.setPlane(listOfPlanes.get(option-1));
        System.out.println("Enter flight code : ");
        flight.setFlight(scannerText.nextLine());
        System.out.println("Enter departure city :");
        flight.setDepartureCity(scannerText.nextLine());
        System.out.println("Enter destination city :");
        flight.setDestinationCity(scannerText.nextLine());
        System.out.println("Enter distance :");
        flight.setDistance(scannerNumber.nextInt());
        enterDate(flight);
        flights.replace(flightCode, flight);
        ReadFiles.updateFlights(flights);
    }

    private void enterDate(Flight flight){
        Scanner scannerNumber = new Scanner(System.in);
        System.out.println("Enter departure hour");
        int hour = scannerNumber.nextInt();
        System.out.println("Enter departure minutes");
        int minutes = scannerNumber.nextInt();
        try {
            flight.setDepartureTime(hour,minutes);
        }catch (WrongDateException e){
            System.out.println(e.getMessage());
            enterDate(flight);
        }
    }

    private List<String>  showPlanes(){
        index.set(1);
        List<String> listOfPlanes = new ArrayList<>();
        planes.forEach((key, plane) ->{
            System.out.println(index + "." + plane);
            listOfPlanes.add(key);
        });
        return listOfPlanes;
    }

}
