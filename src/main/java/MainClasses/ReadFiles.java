package MainClasses;

import java.io.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadFiles {
    private static final String flightsTxt = "src/main/resources/flights.txt";
    private static final String passengersTxt = "src/main/resources/passengers.txt";
    private static final String planesTxt = "src/main/resources/planes.txt";
    private static final String seatsTxt = "src/main/resources/seats.txt";

    public static Map<String, Flight> readFlights() {
        Map<String, Flight> listOfFlights = new HashMap<>();
        File file = new File(flightsTxt);
        try (FileReader fileReader = new FileReader(file); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null ) {
                List<String> list = List.of(line.split(","));
                listOfFlights.put(list.get(1), new Flight(list.get(0), list.get(1), list.get(2), list.get(3), Integer.parseInt(list.get(4)),
                        LocalTime.of(Integer.parseInt(list.get(5)), Integer.parseInt(list.get(6)))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfFlights;
    }

    private void updateFlights(Map<String, Flight> flights) {
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

    public static Map<String, Passenger> readPassengers() {
        Map<String, Passenger> listOfPassengers = new HashMap<>();
        File file = new File(passengersTxt);
        try (FileReader fileReader = new FileReader(file); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null ) {
                List<String> list = List.of(line.split(","));
                listOfPassengers.put(list.get(3), new Passenger(list.get(0), list.get(1), list.get(2), list.get(3), LocalDateTime.parse(list.get(4))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfPassengers;
    }

    private void updatePassengers(Map<String, Passenger> passengers) {
        Runnable savePassengers = () -> {
            File file = new File(flightsTxt);
            try (FileWriter fileWriter = new FileWriter(file)) {
                final StringBuilder stringBuilder = new StringBuilder();
                passengers.forEach((key, passenger) -> stringBuilder
                        .append(passenger.getFirstname()).append(",")
                        .append(passenger.getLastname()).append(",")
                        .append(passenger.getFlight()).append(",")
                        .append(passenger.getIdNumber()).append(",")
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
            while ((line = bufferedReader.readLine()) != null ) {
                List<String> list = List.of(line.split(","));
                listOfPlanes.put(list.get(0), new Plane(list.get(0), Integer.parseInt(list.get(1)), Integer.parseInt(list.get(2))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfPlanes;
    }

    private void updatePlanes(Map<String, Plane> planes) {
        Runnable savePlanes = () -> {
            File file = new File(flightsTxt);
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

    //TODO de finalizat citirea fisiere , corectat si de facut metoda si pentru salvare
    //TODO de facut metoda pentru fiecare fisier in parte
    public static Map<String, Seats> readSeats() {
        Map<String, Seats> listOfSeats = new HashMap<>();
        File file = new File(seatsTxt);
        try (FileReader fileReader = new FileReader(file); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null || !line.isEmpty()) {
                List<String> list = List.of(line.split(","));
                listOfSeats.put(list.get(0), new Seats());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listOfSeats;
    }
}

