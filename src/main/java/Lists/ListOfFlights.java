package Lists;

import MainClasses.Flight;
import MainClasses.Plane;
import MainClasses.ReadFiles;
import MainClasses.TravelTime;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ListOfFlights {

    private Map<String, Flight> flights;
    private Map<String, Plane> planes;
    TravelTime travelHours = (dist, avg) -> (dist / avg);
    TravelTime travelminutes = (dist, avg) -> (int) (((dist / (avg * 1.0)) - ((dist / avg))) * 60);

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
        AtomicInteger index = new AtomicInteger();
        index.set(1);

        flights.forEach((key, flight) -> System.out.println(index.getAndIncrement() + "." + flight + " Travel time = "
                + travelHours.calculateTime(flight.getDistance(),planes.get(flight.getPlane()).getAverageSpeed()) + " hours "
                + travelminutes.calculateTime(flight.getDistance(),planes.get(flight.getPlane()).getAverageSpeed()) + " minutes"));
    }

}
