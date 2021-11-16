package MainClasses;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadFiles {
    private static final String flightsTxt = "src/main/resources/flights.txt";
    private static final String passengersTxt = "src/main/resources/passengers.txt";
    private static final String planesTxt = "src/main/resources/planes.txt";

    public static Map<String, Flight> readFlights() {
        Map<String, Flight> listOfFlights = new HashMap<>();

        try {
            Files.lines(Path.of(flightsTxt))
                    .map(flight -> {
                        List<String> list = List.of(flight.split(","));
                        return new Flight(list.get(0), list.get(1), list.get(2), list.get(3), Integer.parseInt(list.get(4)),Integer.parseInt(list.get(5)),
                                LocalTime.of(Integer.parseInt(list.get(6)), Integer.parseInt(list.get(7))));
                    })
                    .forEach(flight -> listOfFlights.put(flight.getFlight(),flight));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return listOfFlights;
    }

    public static void updateFlights(Map<String, Flight> flights) {
        Runnable saveFlights = () -> {
            File file = new File(flightsTxt);
            try (FileWriter fileWriter = new FileWriter(file)) {
                final StringBuilder stringBuilder = new StringBuilder();
                flights.forEach((key, flight) -> stringBuilder
                        .append(flight.getPlane()).append(",")
                        .append(flight.getFlight()).append(",")
                        .append(flight.getDepartureCity()).append(",")
                        .append(flight.getDestinationCity()).append(",")
                        .append(flight.getDistance()).append(",")
                        .append(flight.getRemainingSeats()).append(",")
                        .append(flight.getDepartureTime().getHour()).append(",")
                        .append(flight.getDepartureTime().getMinute())
                        .append("\n"));
                fileWriter.write(stringBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread saveFlightsThread = new Thread(saveFlights);
        saveFlightsThread.start();
    }

    public static Map<Integer, Passenger> readPassengers() {
        Map<Integer, Passenger> listOfPassengers = new HashMap<>();
        File file = new File(passengersTxt);
        try (FileReader fileReader = new FileReader(file); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                List<String> list = List.of(line.split(","));
                listOfPassengers.put(Integer.parseInt(list.get(0)), new Passenger(Integer.parseInt(list.get(0)), list.get(1), list.get(2), list.get(3), list.get(4),Integer.parseInt(list.get(5)), LocalDateTime.parse(list.get(6))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfPassengers;
    }

    public static void updatePassengers(Map<Integer, Passenger> passengers) {
        Runnable savePassengers = () -> {
            File file = new File(passengersTxt);
            try (FileWriter fileWriter = new FileWriter(file)) {
                final StringBuilder stringBuilder = new StringBuilder();
                passengers.forEach((key, passenger) -> stringBuilder
                        .append(passenger.getIdReservation()).append(",")
                        .append(passenger.getFirstname()).append(",")
                        .append(passenger.getLastname()).append(",")
                        .append(passenger.getFlight()).append(",")
                        .append(passenger.getIdNumber()).append(",")
                        .append(passenger.getSeats()).append(",")
                        .append(passenger.getDateOfAquisition())
                        .append("\n"));
                fileWriter.write(stringBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread savePassengersThread = new Thread(savePassengers);
        savePassengersThread.start();
    }

    public static Map<String, Plane> readPlanes() {
        Map<String, Plane> listOfPlanes = new HashMap<>();
        File file = new File(planesTxt);
        try (FileReader fileReader = new FileReader(file); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                List<String> list = List.of(line.split(","));
                listOfPlanes.put(list.get(0), new Plane(list.get(0), Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfPlanes;
    }

    public static void updatePlanes(Map<String, Plane> planes) {
        Runnable savePlanes = () -> {
            File file = new File(planesTxt);
            try (FileWriter fileWriter = new FileWriter(file)) {
                final StringBuilder stringBuilder = new StringBuilder();
                planes.forEach((key, plane) -> stringBuilder
                        .append(plane.getPlaneName()).append(",")
                        .append(plane.getAverageSpeed()).append(",")
                        .append(plane.getNumebrOfSeats())
                        .append("\n"));
                fileWriter.write(stringBuilder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread savePlanesThread = new Thread(savePlanes);
        savePlanesThread.start();
    }
}



