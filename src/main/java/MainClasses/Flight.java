package MainClasses;

import java.time.LocalTime;

public class Flight {
    private String flight;
    private String plane;
    private String destinationCity;
    private String departureCity;
    private int distance;
    LocalTime departureTime;

    public Flight(String plane,String flight, String destinationCity, String departureCity, int distance, LocalTime departureTime) {
        this.flight = flight;
        this.plane = plane;
        this.destinationCity = destinationCity;
        this.departureCity = departureCity;
        this.distance = distance;
        this.departureTime = departureTime;
    }

    public Flight() {

    }

    public String getFlight() {
        return flight;
    }
    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getPlane() {
        return plane;
    }

    public void setPlane(String plane) {
        this.plane = plane;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String toString() {
        return "Flight : " +
                "flight='" + flight + '\'' +
                ", plane='" + plane + '\'' +
                ", destinationCity='" + destinationCity + '\'' +
                ", departureCity='" + departureCity + '\'' +
                ", distance=" + distance +
                ", departureTime=" + departureTime;
    }
}
