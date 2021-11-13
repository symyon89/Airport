package MainClasses;

import Exceptions.WrongDateException;

import java.time.LocalTime;

public class Flight {
    private String flight;
    private String plane;
    private String destinationCity;
    private String departureCity;
    private int distance;
    private int remainingSeats;
    LocalTime departureTime;

    public Flight(String plane,String flight, String departureCity, String destinationCity, int distance,int remainingSeats ,LocalTime departureTime) {
        this.flight = flight;
        this.plane = plane;
        this.destinationCity = destinationCity;
        this.departureCity = departureCity;
        this.distance = distance;
        this.departureTime = departureTime;
        this.remainingSeats = remainingSeats;
    }

    public Flight() {

    }

    public int getRemainingSeats() {
        return remainingSeats;
    }

    public void setRemainingSeats(int remainingSeats) {
        this.remainingSeats = remainingSeats;
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

    public void setDepartureTime (int hour,int minutes) throws WrongDateException {
        checkHour(hour);
        checkMinutes(minutes);
        this.departureTime = LocalTime.of(hour,minutes);
    }

    private void checkHour(int hour) throws WrongDateException {
        if(hour < 0 || hour > 23){
            throw new WrongDateException();
        }
    }
    private void checkMinutes(int minutes) throws WrongDateException{
        if (minutes < 0 || minutes >59) {
            throw new WrongDateException();
        }
    }

    @Override
    public String toString() {
        return "Flight : " +
                "flight=" + flight +
                ", plane=" + plane + '\'' +
                ", departureCity=" + departureCity +
                ", destinationCity=" + destinationCity +
                ", distance=" + distance +
                ", remaining seats=" + remainingSeats +
                ", departureTime=" + departureTime;
    }
}
