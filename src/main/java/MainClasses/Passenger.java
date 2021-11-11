package MainClasses;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Passenger {
    private String firstname;
    private String lastname;
    private String flight;
    private String idNumber;
    private LocalDateTime dateOfAquisition;

    public Passenger(String firstname, String lastname, String flight, String idNumber, LocalDateTime dateOfAquisition) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.flight = flight;
        this.idNumber = idNumber;
        this.dateOfAquisition = dateOfAquisition;
    }

    public Passenger() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFlight() {
        return flight;
    }

    public void setFlight(String flight) {
        this.flight = flight;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public LocalDateTime getDateOfAquisition() {
        return dateOfAquisition;
    }

    public void setDateOfAquisition(LocalDateTime dateOfAquisition) {
        this.dateOfAquisition = dateOfAquisition;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateFormatted = dateOfAquisition.format(formatter);

        return "Passenger :" +
                "name='" + firstname + " " + lastname +'\'' +
                ", flight='" + flight + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", dateOfAquisition=" + dateFormatted;
    }
}
