package Lists;

import MainClasses.Flight;
import MainClasses.Plane;
import MainClasses.ReadFiles;
import MainClasses.TravelTime;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ListOfFlights {

    private final Map<String, Flight> flights;
    private final Map<String, Plane> planes;

    public ListOfFlights() {
        flights = ReadFiles.readFlights();
        planes = ReadFiles.readPlanes();

    }

    public void showFlights() {
        AtomicInteger index = new AtomicInteger();
        index.set(1);
        TravelTime travelHours = (dist, avg) -> (dist / avg);
        TravelTime travelminutes = (dist, avg) -> (int) (((dist / (avg * 1.0)) - ((dist / avg))) * 60);
        flights.forEach((key, flight) -> System.out.println(index.getAndIncrement() + "." + flight + "Travel time = "
                + travelHours.calculateTime(flight.getDistance(),planes.get(flight.getPlane()).getAverageSpeed()) + " hours "
                + travelminutes.calculateTime(flight.getDistance(),planes.get(flight.getPlane()).getAverageSpeed()) + " minutes"));
    }

}
